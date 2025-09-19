package interfaces.bootstrap;

import customer.application.CustomerQueryUseCase;
import customer.application.CustomerQueryUseCaseImpl;
import customer.domain.CustomerRepository;
import customer.infrastructure.InMemoryCustomerRepository;
import interfaces.cli.AdminResource;
import reeservation.infrastructure.InMemoryReservationRepository;
import reeservation.domain.ReservationRepository;
import room.application.RoomAdminUseCase;
import room.application.RoomAdminUseCaseImpl;
import room.application.RoomQueryUseCase;
import room.application.RoomQueryUseCaseImpl;
import room.infrastructure.InMemoryRoomRepository;
import room.domain.RoomRepository;
import interfaces.cli.ReservationResource;
import reeservation.application.ReservationUseCase;
import reeservation.application.ReservationApplicationService;

public class AppContext {
    private final ReservationResource reservationResource;
    private final AdminResource adminResource;

    public AdminResource getAdminResource() {
        return adminResource;
    }

    public AppContext() {
        // Repo
        CustomerRepository customerRepository = new InMemoryCustomerRepository();
        RoomRepository roomRepository = new InMemoryRoomRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();


        // Use Cases
        ReservationUseCase reservationUseCase = new ReservationApplicationService(reservationRepository, customerRepository, roomRepository);
        RoomQueryUseCase roomQuery = new RoomQueryUseCaseImpl(roomRepository);
        RoomAdminUseCase roomAdminUseCase = new RoomAdminUseCaseImpl(roomRepository);
        CustomerQueryUseCase customerQuery = new CustomerQueryUseCaseImpl(customerRepository);


        // Resources
        this.reservationResource = new  ReservationResource(reservationUseCase);
        this.adminResource = new AdminResource(roomAdminUseCase, roomQuery, customerQuery);

    }

    public ReservationResource getReservationResource() {
        return reservationResource;
    }
}
