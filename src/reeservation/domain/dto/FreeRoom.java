package reeservation.domain.dto;

import room.domain.RoomType;

public record FreeRoom(String roomNumber, RoomType roomType, Double price) {}
