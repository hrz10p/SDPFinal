package factory;

import observer.HotelRoom;

public class FamilyRoomFactory implements RoomFactory{
    @Override
    public HotelRoom createRoom() {
        return new HotelRoom("Family" , 35000);
    }
}
