package factory;

public class StandardRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new StandardRoom();
    }
}
