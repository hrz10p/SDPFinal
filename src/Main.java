import adapter.KaspiPayment;
import adapter.PaymentSystem;
import booking.Booking;
import decorator.ExcursionDecorator;
import factory.HotelRoom;
import factory.LuxuryRoomFactory;
import factory.RoomFactory;
import strategy.LastMinurePricingStrategy;
import strategy.PricingStrategy;

public class Main {
    public static void main(String[] args) {
        PaymentSystem p = new KaspiPayment();
        PricingStrategy s = new LastMinurePricingStrategy();
        RoomFactory f = new LuxuryRoomFactory();
        HotelRoom r = f.createRoom();
        Booking b = new Booking(r , s , "yerlan" , p);


        b.check();
    }
}