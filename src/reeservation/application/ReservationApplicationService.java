package reeservation.application;

import customer.domain.Customer;
import reeservation.domain.ReservationDates;
import reeservation.application.dto.FreeRoom;
import reeservation.domain.Reservation;
import reeservation.domain.policy.AvailabilityPolicy;
import room.domain.Room;
import customer.domain.CustomerRepository;
import reeservation.domain.ReservationRepository;
import room.domain.RoomRepository;
import validation.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservationApplicationService implements ReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final AvailabilityPolicy availabilityPolicy;

    public ReservationApplicationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.availabilityPolicy = new AvailabilityPolicy(reservationRepository);
    }

    @Override
    public Reservation createReservation(String customerEmail, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        var dates = new ReservationDates(checkInDate, checkOutDate);


        Customer customer = this.customerRepository.findByEmail(customerEmail).orElseThrow(() -> new NotFoundException("Customer not found"));
        Room room = this.roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new NotFoundException("Room not found"));

        availabilityPolicy.assertAvailable(room, dates);

        return this.reservationRepository.save(new Reservation(customer, room, dates));
    }

    @Override
    public void cancelReservation(UUID reservationId) {
        this.reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation not found"));
        this.reservationRepository.deleteById(reservationId);

    }

    @Override
    public List<FreeRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        var dates = new ReservationDates(checkInDate, checkOutDate);

        List<Room> rooms = this.roomRepository.findAll();

        var bookedRooms = this.reservationRepository.findBookedRoomNumbers(dates.getCheckIn(), dates.getCheckOut());

        return rooms.stream().filter(room -> !bookedRooms.contains(room.getRoomNumber())).map((room -> {
            return new FreeRoom(room.getRoomNumber(), room.getRoomType(), room.getPrice());
        })).toList();
    }


}
