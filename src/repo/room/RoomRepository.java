package repo.room;

import model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    List<Room> findAll();
    Optional<Room> findByRoomNumber(String roomNumber);
    Room save(Room room);
    void delete(String roomNumber);
}
