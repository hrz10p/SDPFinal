package booking;

import adapter.BookingPayment;
import observer.HotelRoom;
import observer.Observer;
import strategy.PricingStrategy;

public class Booking implements Observer, BookingPayment {
    private static int nextId;
    private int id;
    private HotelRoom room;
    private PricingStrategy strategy;

    public Booking(HotelRoom room, PricingStrategy strategy) {
        this.room = room;
        this.strategy = strategy;
        this.id = nextId;
        nextId++;
    }

    public double getPrice(){
        return strategy.calculatePrice(room.getPrice());
    }

    public String getDescription(){
        return room.getDescription();
    }

    @Override
    public void update(String msg) {

    }

    @Override
    public void makePayment(double amount) {

    }
}
