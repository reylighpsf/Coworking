package app;

import controller.BookingController;
import controller.MemberController;
import controller.RoomController;
import java.util.Scanner;
import utilities.ClearScreen;
import view.AdminMenu;

public class CoworkingSpaceApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n===   RoFind App   ===");
                System.out.println("=======================");
                System.out.println("= 1. Join Membership  =");
                System.out.println("======================");
                System.out.println(" 2. Login Membership ");
                System.out.println(" 3. View Booked Rooms");
                System.out.println(" 4. Make a Booking"); 
                System.out.println(" 5. Cancel Booking"); 
                System.out.println(" 6. Payment"); 
                System.out.println(" 7. Exit"); 
                System.out.println(" 8. Admin");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    ClearScreen.clearScreen();
                    switch (choice) {
                        case 1 -> MemberController.showMemberMenu(scanner);
                        case 2 -> MemberController.login(scanner);
                        case 3 -> RoomController.displayAllBookings(scanner);
                        case 4 -> BookingController.startBookingSystem(scanner);
                        case 5 -> RoomController.cancelBooking(scanner);
                        case 6 -> RoomController.handlePaymentByCustomerName(scanner);
                        case 7 -> {
                            System.out.println("Exiting the app...");
                            running = false;
                        }
                        case 8 -> AdminMenu.displayAdminMenu(scanner);
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
        }
    }
}