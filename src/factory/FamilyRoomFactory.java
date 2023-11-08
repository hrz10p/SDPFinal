package factory;

public class FamilyRoomFactory implements RoomFactory{
    @Override
    public HotelRoom createRoom() {
        return new HotelRoom("Family" , 35000);
    }
}
