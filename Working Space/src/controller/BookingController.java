package controller;

import java.util.Scanner;
import services.CoworkingSpace;
import services.MeetingRoom;
import services.OfficeSpace;
import services.VirtualOffice;
import utilities.ClearScreen;

public class BookingController {

    public static void startBookingSystem(Scanner scanner) {
        while (true) {
            System.out.println("\n===============================");
            System.out.println("       Room Booking System      ");
            System.out.println("===============================");
            System.out.println("1. Office Space");
            System.out.println("2. Coworking Space");
            System.out.println("3. Virtual Offices");
            System.out.println("4. Meeting Rooms");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option (1-5): ");
    
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                ClearScreen.clearScreen();
    
                switch (choice) {
                    case 1 -> OfficeSpace.officeSpaceOrderWithoutMembership(scanner);
                    case 2 -> CoworkingSpace.coworkingOrderWithoutMembership(scanner);
                    case 3 -> VirtualOffice.virtualOfficeOrderWithoutMembership(scanner);
                    case 4 -> MeetingRoom.meetingRoomOrderWithoutMembership(scanner);
                    case 5 -> {
                        System.out.println("Returning to the main menu...");
                        return; 
                    }
                    default -> System.out.println("Invalid option. Please select a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }
}
