package factory;

import observer.HotelRoom;

public class LuxuryRoomFactory implements RoomFactory {
    @Override
    public HotelRoom createRoom() {
        return new HotelRoom("Luxury" , 50000);
    }
}
