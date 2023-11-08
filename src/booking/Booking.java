package booking;

import adapter.BookingPayment;
import adapter.PaymentSystem;
import decorator.BookingInterface;
import factory.HotelRoom;
import observer.Observer;
import strategy.PricingStrategy;

public class Booking implements Observer, BookingPayment, BookingInterface {
    private static int nextId=1;
    private int id;
    private String client;
    private HotelRoom room;
    private PricingStrategy strategy;
    private PaymentSystem paymentSystem;

    public Booking(HotelRoom room, PricingStrategy strategy , String client , PaymentSystem paymentSystem) {
        this.paymentSystem=paymentSystem;
        this.client=client;
        this.room = room;
        this.strategy = strategy;
        this.id = nextId;
        nextId++;
    }

    @Override
    public double getPrice(){
        return strategy.calculatePrice(room.getPrice());
    }
    @Override
    public String getDescription(){
        return room.getDescription();
    }

    @Override
    public void update(String msg) {
        System.out.printf("Booking with id %d , room type %s, client name %s gets notification: %s",this.id , this.room.getDescription(), this.client, msg);
    }

    @Override
    public void makePayment(double amount) {
        paymentSystem.processPayment(amount);
    }

    public void check(){
        makePayment(getPrice());
    }
}
