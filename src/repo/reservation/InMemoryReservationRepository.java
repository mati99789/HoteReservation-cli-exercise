package repo.reservation;

import model.Customer;
import model.Reservation;

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
					reservation.getCheckInDate(),
					reservation.getCheckOutDate()
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
}
