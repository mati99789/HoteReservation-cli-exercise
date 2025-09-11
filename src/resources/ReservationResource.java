package resources;

import common.Result;
import model.FreeRoom;
import service.ReservationService;

import java.time.LocalDate;
import java.util.List;

public class ReservationResource {

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
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
            return Result.ok(reservationService.findFreeRooms(startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
