package booking;

import adapter.BookingPayment;
import adapter.PaymentSystem;
import decorator.BookingInterface;
import factory.Room;
import observer.Observer;
import strategy.PricingStrategy;

public class Booking implements Observer, BookingPayment, BookingInterface {
    private static int nextId=1;
    private int id;
    private String client;
    private Room room;
    private PricingStrategy strategy;
    private PaymentSystem paymentSystem;
    private int days;
    public Booking(Room room, PricingStrategy strategy , String client , PaymentSystem paymentSystem,int days) {
        this.paymentSystem=paymentSystem;
        this.client=client;
        this.room = room;
        this.strategy = strategy;
        this.id = nextId;
        nextId++;
        this.days=days;
    }

    @Override
    public double getPrice(){
        return strategy.calculatePrice(room.getCost()*days);
    }
    @Override
    public String getDescription(){
        return room.getDescription();
    }

    @Override
    public void update(String msg) {
        System.out.println("Room type:"+this.room.getDescription());
        System.out.println("Client name:"+this.client);
    }


    @Override
    public void makePayment(double amount) {
        paymentSystem.processPayment(amount);
    }

    public void check(){
        makePayment(getPrice());
    }
}
