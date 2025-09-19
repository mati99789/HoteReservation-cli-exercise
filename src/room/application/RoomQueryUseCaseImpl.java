package room.application;

import room.application.dto.RoomDto;
import room.domain.RoomRepository;

import java.util.List;

public class RoomQueryUseCaseImpl implements RoomQueryUseCase {
    private final RoomRepository roomRepository;
    public RoomQueryUseCaseImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public List<RoomDto> listAll() {
        return this.roomRepository.findAll().stream()
                .map(r -> new RoomDto(r.getRoomNumber(), r.getRoomType(), r.getPrice()))
                .toList();
    }
}
