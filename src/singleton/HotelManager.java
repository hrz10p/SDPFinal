package singleton;

import booking.Booking;
import observer.HotelRoom;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Booking> bookings = new ArrayList<>();
    private static HotelManager instance;

    private HotelManager() {
        // Private constructor to prevent external instantiation.
    }

    public static HotelManager getInstance() {
        if (instance == null) {
            instance = new HotelManager();
        }
        return instance;
    }

    public void BookRoom(Booking booking){
        this.bookings.add(booking);
    }

    public void FireAlarm(){
        notifyRooms("Fire alarm was triggered");
    }



    private void notifyRooms(String msg){
        for (Booking b : this.bookings){
            b.update(msg);
        }
    }

    // Implement methods for managing rooms, bookings, and customer data.
}
