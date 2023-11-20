package db;

public class BookingDB {
    private int id;
    private User user;
    private double price;
    private String description;

    public BookingDB(int id, User user, double price, String description){
        this.id = id;
        this.user = user;
        this.price = price;
        this.description = description;
    }
}
