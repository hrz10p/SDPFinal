package decorator;

import booking.Booking;
import observer.HotelRoom;

public class TransferDecorator extends BookingDecorator {
    public TransferDecorator(Booking booking) {
        super(booking);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 20.0; // Add transfer cost.
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Transfer";
    }
}
