package decorator;

import factory.Room;

public class TransferDecorator extends BookingDecorator {
    public TransferDecorator(Room room) {
        super(room);
    }

    @Override
    public double getCost() {
        return room.getCost() + 10000; // Adding transfer cost
    }

    @Override
    public String getDescription() {
        return room.getDescription() + ", with Transfer";
    }
}
