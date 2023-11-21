package decorator;
import factory.Room;

abstract class BookingDecorator implements Room {
protected Room room;
public BookingDecorator(Room room){
    this.room = room;
}
    public String getDescription() {
        return room.getDescription();
    }

    @Override
    public double getCost() {
        return room.getCost();
    }
}
