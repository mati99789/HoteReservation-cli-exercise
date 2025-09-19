package reeservation.application;

import reeservation.application.dto.FreeRoom;
import reeservation.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationUseCase {
    Reservation createReservation(String customerEmail, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate);
    void cancelReservation(UUID reservationId);
    List<FreeRoom> findFreeRooms(LocalDate checkInDate, LocalDate checkOutDate);
}
