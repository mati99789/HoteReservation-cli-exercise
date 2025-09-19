package room.application;

import common.Result;
import room.domain.RoomType;

public record AddRoomCommand(String roomNumber, String type, String price) {
    public Result<Parsed> parse() {
        if(roomNumber == null || roomNumber.isBlank()) return Result.error("Room number required");
        if(type == null || type.isBlank()) return Result.error("Room type required");
        if(price == null || price.isBlank()) return Result.error("Price required");

        try {
            double p = Double.parseDouble(price);
            var t = room.domain.RoomType.valueOf(type.toUpperCase());
            return Result.ok(new Parsed(roomNumber, t, p));
        }catch (IllegalArgumentException e) {
            return Result.error("Invalid room type or price");
        }
    }

    public record Parsed(String roomNumber, RoomType roomType, double price) {}
}
