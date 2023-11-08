package decorator;

import booking.Booking;

public abstract class BookingDecorator implements BookingInterface {
    protected Booking booking;

    public BookingDecorator(Booking booking) {
        this.booking = booking;
    }

    @Override
    public double getPrice() {
        return booking.getPrice();
    }

    @Override
    public String getDescription() {
        return booking.getDescription();
    }
}




