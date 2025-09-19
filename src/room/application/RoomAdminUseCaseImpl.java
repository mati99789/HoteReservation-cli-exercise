package room.application;

import room.application.dto.RoomDto;
import room.domain.Room;
import room.domain.RoomRepository;

public class RoomAdminUseCaseImpl implements RoomAdminUseCase {
    private final RoomRepository roomRepository;

    public RoomAdminUseCaseImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDto add(AddRoomCommand cmd) {
        var parsed = cmd.parse();
        if (!parsed.isOk()) throw new IllegalArgumentException(parsed.getError());

        var p = parsed.getData();
        var room = new Room(p.roomNumber(), p.price(), p.roomType());
        var saved = roomRepository.save(room);

        return new RoomDto(saved.getRoomNumber(), saved.getRoomType(), saved.getPrice());
    }

    @Override
    public void delete(String roomNumber) {
        this.roomRepository.deleteByNumber(roomNumber);
    }
}
