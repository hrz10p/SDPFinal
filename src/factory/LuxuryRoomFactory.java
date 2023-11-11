package factory;

public class LuxuryRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new LuxuryRoom();
    }
}

