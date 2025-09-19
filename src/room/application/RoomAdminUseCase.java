package room.application;

import room.application.dto.RoomDto;

public interface RoomAdminUseCase {
    RoomDto add(AddRoomCommand cmd);
    void delete(String roomNumber);
}
