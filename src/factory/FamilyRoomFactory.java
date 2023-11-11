package factory;

public class FamilyRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new FamilyRoom();
    }
}
