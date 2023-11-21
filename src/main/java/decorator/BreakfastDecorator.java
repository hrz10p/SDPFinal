package decorator;
import factory.Room;

public class BreakfastDecorator extends BookingDecorator{
    public BreakfastDecorator(Room room){
        super(room);
    }
    @Override
    public String getDescription() {
        return room.getDescription() + ", with Breakfast";
    }

    @Override
    public double getCost() {
        return room.getCost() + 5000.0;
    }
}