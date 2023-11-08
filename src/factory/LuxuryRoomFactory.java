package factory;

public class LuxuryRoomFactory implements RoomFactory {
    @Override
    public HotelRoom createRoom() {
        return new HotelRoom("Luxury" , 50000);
    }
}
