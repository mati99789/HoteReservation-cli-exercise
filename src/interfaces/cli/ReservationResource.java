package interfaces.cli;

import common.Result;
import reeservation.domain.dto.FreeRoom;
import reeservation.domain.Reservation;
import reeservation.application.ReservationUseCase;
import validation.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservationResource {

    private final ReservationUseCase reservationUseCase;

    public ReservationResource(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }


    public Result<List<FreeRoom>> findFreeRooms(String checkInDate, String checkOutDate) {
        if(checkInDate == null || checkOutDate == null) {
            return Result.error("Missing dates");
        }
        LocalDate startDate;
        LocalDate endDate;

        try {
             startDate = LocalDate.parse(checkInDate);
             endDate = LocalDate.parse(checkOutDate);
        } catch (Exception e) {
            return Result.error("Use date format yyyy-MM-dd");
        }


        try {
            return Result.ok(reservationUseCase.findFreeRooms(startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    public Result<Void> cancel(String reservationID) {
        if(reservationID == null || reservationID.isBlank()) {
            return Result.error("Missing reservation ID");
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(reservationID);
        } catch(Exception e) {
            return Result.error("Invalid reservation ID");
        }
        try {
            reservationUseCase.cancelReservation(uuid);
            return Result.ok(null);
        } catch (NotFoundException e ) {
            return Result.error( e.getMessage() != null ? e.getMessage() : "Reservation ID not found");
        }
        catch(Exception e) {
            return Result.error("Unknown Error");
        }
    }

    public Result<Reservation> createReservation(String email, String roomNumber, String fromStr, String toStr) {
        if(isBlank(fromStr) || isBlank(toStr) || isBlank(roomNumber ) ||  isBlank(email)) {
            return Result.error("Invalid arguments");
        }

        try {
            var checkIn = LocalDate.parse(fromStr);
            var checkOut = LocalDate.parse(toStr);
            this.reservationUseCase.createReservation(email, roomNumber, checkIn, checkOut);
        }catch (Exception e) {
            return Result.error(e.getMessage() != null ? e.getMessage() : "Unknown Error");
        }

        return Result.ok(null);
    }

    private boolean isBlank(String s){
        if(s == null ||  s.isBlank()) {
            return true;
        }
        return false;
    }
}
