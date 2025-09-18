package reeservation.infrastructure;

import reeservation.domain.Reservation;
import reeservation.domain.ReservationDates;
import reeservation.domain.ReservationRepository;
import room.domain.Room;

import java.time.LocalDate;
import java.util.*;

public class InMemoryReservationRepository implements ReservationRepository {
	private final Map<UUID, Reservation> reservations = new HashMap<>();
	@Override
	public Reservation save(Reservation reservation) {

		if(reservation.getUuid() == null) {
			UUID newId = UUID.randomUUID();
			Reservation newReservation = new Reservation(
					newId,
					reservation.getCustomer(),
					reservation.getRoom(),
					reservation.getReservationDates()
			);
			reservations.put(newId, newReservation);
			return reservation;
		}

		reservations.put(reservation.getUuid(), reservation);
		return reservation;

	}

	@Override
	public Optional<Reservation> findById(UUID id) {
		return Optional.ofNullable(reservations.get(id));
	}

	@Override
	public List<Reservation> findAll() {
		return new ArrayList<>(reservations.values());
	}

	@Override
	public void deleteById(UUID reservationId) {
		reservations.remove(reservationId);
	}

    @Override
    public boolean existsOverlapping(Room room, ReservationDates dates) {
        return reservations.values().stream()
                .filter(r -> r.getRoom().getRoomNumber().equals(room.getRoomNumber()))
                .anyMatch(r -> new ReservationDates(r.getReservationDates().getCheckIn(),  r.getReservationDates().getCheckOut()).overlaps(dates));
    }

    @Override
    public Set<String> findBookedRoomNumbers(LocalDate checkIn, LocalDate checkOut) {
        var query = new ReservationDates(checkIn, checkOut);
        var set = new HashSet<String>();

        for(var r : reservations.values()) {
            var dates = new ReservationDates(r.getReservationDates().getCheckIn(), r.getReservationDates().getCheckOut());
            if(dates.overlaps(query)) {
                set.add(r.getRoom().getRoomNumber());
            }
        }
        return set;
    }
}
