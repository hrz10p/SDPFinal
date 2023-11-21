package decorator;

import factory.Room;

public class ExcursionDecorator extends BookingDecorator {
    public ExcursionDecorator(Room room){
        super(room);
    }
    @Override
    public String getDescription() {
        return room.getDescription() + ", with Excursion";
    }

    @Override
    public double getCost() {
        return room.getCost() + 10000.0;
    }
}
