package service;

import model.Customer;
import model.FreeRoom;
import model.Reservation;
import model.Room;
import repo.customer.CustomerRepository;
import repo.reservation.ReservationRepository;
import repo.room.RoomRepository;
import validation.exceptions.NotFoundException;
import validation.exceptions.RoomUnavailableException;
import validation.exceptions.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Reservation createReservation(String customerEmail, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        validateDates(checkInDate, checkOutDate);


        Customer customer = this.customerRepository.findByEmail(customerEmail).orElseThrow(() -> new NotFoundException("Customer not found"));
        Room room = this.roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new NotFoundException("Room not found"));


        var occupied = this.reservationRepository.findAll().stream().filter(reservation -> {
            return reservation.getRoom().getRoomNumber().equals(roomNumber);
        }).anyMatch(reservation -> {
            return overlaps(checkInDate, checkOutDate, reservation.getCheckInDate(), reservation.getCheckOutDate());
        });

        if(occupied) {
            throw new RoomUnavailableException("Room %s is not available from %s to %s".formatted(roomNumber, checkInDate, checkOutDate));
        }


        return this.reservationRepository.save(new Reservation( customer, room, checkInDate, checkOutDate));
    }

    @Override
    public void cancelReservation(UUID reservationId) {
        this.reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation not found"));
        this.reservationRepository.deleteById(reservationId);

    }

    @Override
    public List<FreeRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        validateDates(checkInDate, checkOutDate);

        List<Room> rooms = this.roomRepository.findAll();

        var bookedRooms = this.reservationRepository.findAll().stream().filter(reservation -> {
            return overlaps(checkInDate, checkOutDate, reservation.getCheckInDate(), reservation.getCheckOutDate());
        }).map(res -> res.getRoom().getRoomNumber()).toList();

        return rooms.stream().filter(room -> !bookedRooms.contains(room.getRoomNumber())).map((room -> {
            return new FreeRoom(room.getRoomNumber(), room.getRoomType(), room.getPrice());
        })).toList();
    }

    private boolean overlaps(LocalDate aStart, LocalDate aEnd,
                             LocalDate bStart, LocalDate bEnd) {
        return aStart.isBefore(bEnd) && aEnd.isAfter(bStart);
    }

    private void validateDates(LocalDate checkInDate, LocalDate checkOutDate) {
        if(!Objects.nonNull(checkInDate) || !Objects.nonNull(checkOutDate)) {
            throw new ValidationException("Missing dates");
        }

        if(!checkInDate.isBefore(checkOutDate)) {
            throw new ValidationException("Check in date must be before check out date");
        }
    }
}
