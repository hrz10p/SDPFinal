package factory;

public class HotelRoom {
    private double price;
    private String roomType;

    public HotelRoom(String roomType ,double  price) {
        this.price = price;
        this.roomType = roomType;
    }
    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return roomType;
    }
}
