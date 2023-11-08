package decorator;

import booking.Booking;

public class ExcursionDecorator extends BookingDecorator {
    public ExcursionDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 30000.0; // Add breakfast cost.
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Excursion";
    }
}
