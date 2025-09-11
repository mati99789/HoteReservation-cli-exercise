package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Reservation {
	private final UUID uuid;
    private final Customer customer;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;


	public Reservation(UUID uuid, Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
		this.uuid = uuid;
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

    public Reservation(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this(null, customer, room, checkInDate, checkOutDate);
    }

	@Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
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

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}
