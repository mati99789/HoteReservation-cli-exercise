package reeservation.domain;

import room.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(UUID id);
    List<Reservation> findAll();
    void deleteById(UUID reservationId);

    boolean existsOverlapping(Room room, ReservationDates dates);
    Set<String> findBookedRoomNumbers(LocalDate checkIn, LocalDate checkOut);
}
