package interfaces.bootstrap;

import customer.domain.CustomerRepository;
import customer.infrastructure.InMemoryCustomerRepository;
import reeservation.domain.policy.AvailabilityPolicy;
import reeservation.infrastructure.InMemoryReservationRepository;
import reeservation.domain.ReservationRepository;
import room.infrastructure.InMemoryRoomRepository;
import room.domain.RoomRepository;
import interfaces.cli.ReservationResource;
import reeservation.application.ReservationUseCase;
import reeservation.application.ReservationApplicationService;

public class AppContext {
    private final ReservationResource reservationResource;

    public AppContext() {
        // Repo
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        RoomRepository roomRepository = new InMemoryRoomRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();


        // Service
        ReservationUseCase reservationUseCase = new ReservationApplicationService(reservationRepository, customerRepository, roomRepository);

        // Resources
        this.reservationResource = new  ReservationResource(reservationUseCase);
    }

    public ReservationResource getReservationResource() {
        return reservationResource;
    }
}
