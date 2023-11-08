package factory;

public class StandardRoomFactory implements RoomFactory {
    @Override
    public HotelRoom createRoom() {
        return new HotelRoom("Standard" , 20000);
    }
}
