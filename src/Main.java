import adapter.KaspiPayment;
import adapter.PaymentSystem;
import booking.Booking;
import decorator.BreakfastDecorator;
import factory.LuxuryRoom;
import factory.LuxuryRoomFactory;
import factory.Room;
import factory.RoomFactory;
import strategy.LastMinurePricingStrategy;
import strategy.PricingStrategy;

public class Main {
    public static void main(String[] args) {
        PaymentSystem p = new KaspiPayment();
        PricingStrategy s = new LastMinurePricingStrategy();
        RoomFactory luxuryFactory=new LuxuryRoomFactory();
        Room luxury=luxuryFactory.createRoom();
        Booking b=new Booking(luxury,s,"aza",p);
        b.update("aaa");
        b.check();
        Room breakfastAndLux=new BreakfastDecorator(luxury);
        Booking n=new Booking(breakfastAndLux,s,"yerla",p);
        n.check();
    }
}
