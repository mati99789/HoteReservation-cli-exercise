package reeservation.domain;

import customer.domain.Customer;
import room.domain.Room;

import java.util.UUID;

public class Reservation {
	private final UUID uuid;
    private final Customer customer;
    private final Room room;
    private final ReservationDates reservationDates;


	public Reservation(UUID uuid, Customer customer, Room room, ReservationDates dates) {
		this.uuid = uuid;
		this.customer = customer;
		this.room = room;
        this.reservationDates = dates;
	}

    public Reservation(Customer customer, Room room, ReservationDates dates) {
        this(null, customer, room, dates);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "uuid=" + uuid +
                ", customer=" + customer +
                ", room=" + room +
                ", reservationDates=" + reservationDates +
                '}';
    }

    public UUID getUuid() {
		return uuid;
	}

	public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public ReservationDates getReservationDates() {
        return reservationDates;
    }
}
