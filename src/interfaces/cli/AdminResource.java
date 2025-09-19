package interfaces.cli;

import common.Result;
import customer.application.CustomerDto;
import customer.application.CustomerQueryUseCase;
import room.application.RoomAdminUseCase;
import room.application.RoomQueryUseCase;
import room.application.dto.RoomDto;
import room.domain.Room;

import java.util.List;

public class AdminResource {
    private final RoomAdminUseCase roomAdminUseCase;
    private final RoomQueryUseCase roomQueryUseCase;
    private final CustomerQueryUseCase customerQueryUseCase;

    public AdminResource(RoomAdminUseCase roomAdminUseCase, RoomQueryUseCase roomQueryUseCase, CustomerQueryUseCase customerQueryUseCase) {
        this.roomAdminUseCase = roomAdminUseCase;
        this.roomQueryUseCase = roomQueryUseCase;
        this.customerQueryUseCase = customerQueryUseCase;
    }


    public Result<List<RoomDto>> findRooms() {
        try {
            var rooms = this.roomQueryUseCase.listAll();
            if (rooms.isEmpty()) {
                return Result.error("There are no rooms in the database");
            }
           return Result.ok(rooms);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    public Result<List<CustomerDto>> findCustomers() {
        try {
            var customers = this.customerQueryUseCase.allCustomers();
            if (customers.isEmpty()) {
                return Result.error("There are no customers in the database");
            }
            return Result.ok(customers);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
