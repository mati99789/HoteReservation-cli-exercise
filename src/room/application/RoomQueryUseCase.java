package room.application;

import room.application.dto.RoomDto;

import java.util.List;

public interface RoomQueryUseCase {
    List<RoomDto> listAll();
}
