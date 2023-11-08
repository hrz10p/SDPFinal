package decorator;

import booking.Booking;
import observer.HotelRoom;

public class BreakfastDecorator extends BookingDecorator {
    public BreakfastDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 10.0; // Add breakfast cost.
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Breakfast";
    }
}
