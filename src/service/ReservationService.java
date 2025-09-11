package service;

import model.FreeRoom;
import model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    Reservation createReservation(String customerEmail, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate);
    void cancelReservation(UUID reservationId);
    List<FreeRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate);
}
