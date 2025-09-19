package room.application.dto;

import room.domain.RoomType;

public record RoomDto(String roomNumber, RoomType roomType, double price) {
}
