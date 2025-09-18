package reeservation.domain;

import validation.exceptions.ValidationException;

import java.time.LocalDate;

public class ReservationDates {
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public ReservationDates(LocalDate checkIn, LocalDate checkOut) {
        if(checkIn == null || checkOut==null) {
            throw new ValidationException("Dates cannot be null");
        }

        if(!checkOut.isAfter(checkIn)) {
            throw new ValidationException("Check-out mus be after check-in");
        }


        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public boolean overlaps(ReservationDates dates) {
        return this.checkIn.isBefore(dates.checkIn) && this.checkOut.isAfter(dates.checkIn);
    }
}
