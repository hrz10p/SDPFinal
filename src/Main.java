import adapter.KaspiPayment;
import adapter.PaymentSystem;
import booking.Booking;
import decorator.BreakfastDecorator;
import decorator.TransferDecorator;
import factory.LuxuryRoom;
import factory.LuxuryRoomFactory;
import factory.Room;
import factory.RoomFactory;
import singleton.HotelManager;
import strategy.LastMinurePricingStrategy;
import strategy.PricingStrategy;

public class Main {
    public static void main(String[] args) {
        PaymentSystem p = new KaspiPayment();
        PricingStrategy s = new LastMinurePricingStrategy();
        RoomFactory luxuryFactory=new LuxuryRoomFactory();
        Room luxury=luxuryFactory.createRoom();
        Booking b=new Booking(luxury,s,"aza",p);
        HotelManager manager = HotelManager.getInstance();
        manager.BookRoom(b);
        manager.FireAlarm();
        b.check();
        luxury = new BreakfastDecorator(luxury);
        luxury = new TransferDecorator(luxury);

        Booking n=new Booking(luxury,s,"yerla",p);
        n.check();
        System.out.println(n.getDescription());
    }
}
