package decorator;

import booking.Booking;

public class BreakfastDecorator extends BookingDecorator {
    public BreakfastDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 5000.0; // Add breakfast cost.
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Breakfast";
    }
}
