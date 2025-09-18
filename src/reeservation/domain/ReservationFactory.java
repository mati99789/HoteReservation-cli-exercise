package reeservation.domain;

import customer.domain.Customer;
import room.domain.Room;
import validation.exceptions.ValidationException;

import java.time.LocalDate;

public final class ReservationFactory {
    private ReservationFactory() {}

    public static Reservation create(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        if(customer == null) {
            throw new ValidationException("Customer is required");
        }
        if(room == null) {
            throw new ValidationException("Room is required");
        }

        ReservationDates dates = new ReservationDates(checkIn, checkOut);

        return new Reservation(customer, room, dates);

    }
}
