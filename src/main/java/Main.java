import adapter.*;
import booking.Booking;
import db.DatabaseManager;
import db.User;
import decorator.BookingInterface;
import decorator.BreakfastDecorator;
import decorator.ExcursionDecorator;
import decorator.TransferDecorator;
import factory.*;
import singleton.HotelManager;
import strategy.DiscountPricingStrategy;
import strategy.LastMinurePricingStrategy;
import strategy.PricingStrategy;
import strategy.StandardPricingStrategy;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        HotelManager hotelManager=HotelManager.getInstance();
        DatabaseManager db=DatabaseManager.getInstance();
        System.out.println("Welcome to our hotel");
        System.out.println("Please choose which room you want?");
        System.out.println("Select a room type:");
        System.out.println("1. Family Room");
        System.out.println("2. Luxury Room");
        System.out.println("3. Standard Room");
        Scanner scanner = new Scanner(System.in);
        int roomChoice = scanner.nextInt();
        RoomFactory roomFactory;
        if (roomChoice == 1) {
            roomFactory = new FamilyRoomFactory();
        } else if (roomChoice == 2) {
            roomFactory = new LuxuryRoomFactory();
        } else if (roomChoice == 3) {
            roomFactory = new StandardRoomFactory();
        } else {
            System.out.println("Invalid room choice. Returning to main menu.");
            return;
        }
        Room room = roomFactory.createRoom();

        System.out.println("Select some pluses to your room:");
        System.out.println("1. Breakfast");
        System.out.println("2. Excursion");
        System.out.println("3. Transfer");
        System.out.println("4.None");
        int addChoice= scanner.nextInt();
        switch (addChoice){
            case 1:
                room=new BreakfastDecorator(room);
                break;
            case 2:
                room=new ExcursionDecorator(room);
                break;
            case 3:
                room=new TransferDecorator(room);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid pricing strategy choice. Returning to main menu.");
                return;
        }
        System.out.println("Enter the number of days:");
        int numberOfDays = scanner.nextInt();

        System.out.println("Do you have an existing account?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int response =scanner.nextInt();
        if (response==1) {
            System.out.println("Enter your user name:");
            String userName = scanner.nextLine();

            User existingUser = db.getUserByName(userName);

            if (existingUser != null) {
                System.out.println("User found: " + existingUser);
            } else {
                System.out.println("User not found. Please create a new account.");
                System.out.println("Enter user name:");
                userName = scanner.nextLine();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Enter user password:");
                String password = scanner.nextLine();


                db.addUser(userName, password);
                System.out.println("User added");

                db.addBooking(room.getCost(), "Booking for " + userName);
            }
        } else if (response==2) {
            System.out.println("Enter user name:");
            String userName = scanner.nextLine();

            System.out.println("Enter user password:");
            String password = scanner.nextLine();
            scanner.nextLine(); // Consume the newline character

            db.addUser(userName, password);
            System.out.println("User added");

            db.addBooking( room.getCost(), "Booking for " + userName);
        } else {
            System.out.println("Invalid response. Please enter 'yes' or 'no'.");
        }


        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter your name:");
        String clientName = scanner.nextLine();
        PricingStrategy pricingStrategy;
        System.out.println("Pricing:");
        System.out.println("1. Standard Pricing");
        System.out.println("2. Discount Pricing");
        System.out.println("3. Last Minute Pricing");
        int strategyChoice = scanner.nextInt();
        switch (strategyChoice) {
            case 1:
                pricingStrategy = new StandardPricingStrategy();
                break;
            case 2:
                pricingStrategy = new DiscountPricingStrategy();
                break;
            case 3:
                pricingStrategy = new LastMinurePricingStrategy();
                break;
            default:
                System.out.println("Invalid pricing strategy choice. Returning to main menu.");
                return;
        }
        PaymentSystem paymentSystem;
        System.out.println("Payment type:");
        System.out.println("1. Kaspi");
        System.out.println("2. CreditCard");
        int paymentChoice=scanner.nextInt();
        switch (paymentChoice) {
            case 1:
                paymentSystem= new KaspiPayment();
                break;
            case 2:
                paymentSystem= new CreditCardPayment();
                break;
            default:
                System.out.println("Invalid pricing strategy choice. Returning to main menu.");
                return;
        }
        Booking booking=new Booking(room,pricingStrategy,clientName,paymentSystem,numberOfDays);
        hotelManager.BookRoom(booking);
        System.out.println("Booking Information:");
        System.out.println("--------------------");
        booking.update("aa");
        System.out.println();
        booking.check();
        db.showAllBookings();
        db.closeConnection();

    }
}