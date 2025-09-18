package reeservation.domain.policy;

import reeservation.domain.ReservationDates;
import reeservation.domain.ReservationRepository;
import room.domain.Room;
import validation.exceptions.RoomUnavailableException;

public class AvailabilityPolicy {
    private final ReservationRepository reservationRepository;

    public AvailabilityPolicy(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void assertAvailable(Room room, ReservationDates dates) {
        if(reservationRepository.existsOverlapping(room, dates)) {
            throw new RoomUnavailableException("Room %s is no available from %s to %s".formatted(room.getRoomNumber(), dates.getCheckIn(), dates.getCheckOut()));
        }
    }
}
