package model;


public class Room  {
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}
