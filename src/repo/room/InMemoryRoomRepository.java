package repo.room;

import model.Room;

import java.util.*;

public class InMemoryRoomRepository implements RoomRepository {
	private final Map<String, Room> rooms = new HashMap<>();
	@Override
	public List<Room> findAll() {
		return new ArrayList<>(rooms.values());
	}

	@Override
	public Optional<Room> findByRoomNumber(String roomNumber) {
		return Optional.ofNullable(rooms.get(roomNumber));
	}

	@Override
	public Room save(Room room) {
		if(rooms.containsKey(room.getRoomNumber())) {
			throw new RuntimeException("There is already a room.");
		}
		rooms.put(room.getRoomNumber(), room);
		return room;
	}

	@Override
	public void deleteByNumber(String roomNumber) {
		rooms.remove(roomNumber);
	}
}
