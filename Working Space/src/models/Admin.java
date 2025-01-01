

package models;

import java.util.Iterator;
import java.util.Scanner;
import services.MemberMenu;
public class Admin {
    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    // Method to log in as admin
    public static boolean login(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(password);
    }

    // Method to delete a room booking
    public static void deleteRoom(Scanner scanner) {
        Room.displayCustomerNames();
        System.out.print("Enter the name of the customer whose booking you want to delete: ");
        String customerName = scanner.nextLine();

        boolean found = false;
        Iterator<Room> iterator = Room.bookings.iterator();
        while (iterator.hasNext()) {
            Room booking = iterator.next();
            if (booking.getCustomerName().equalsIgnoreCase(customerName)) {
                iterator.remove();
                System.out.println("Booking for " + customerName + " has been deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No booking found for the name: " + customerName);
        }
    }

    // Method to display registered members
    public static void displayMembers(Scanner scanner) {
        System.out.println("\n=== Registered Members ===");
        if (Membership.getMembers().isEmpty()) {
            System.out.println("No members registered.");
        } else {
            Membership.getMembers().forEach((username, details) -> {
                System.out.println("Username: " + username);
                System.out.println("Email: " + details.getEmail());
                System.out.println("Password: " + details.getPassword());
                System.out.println("Phone: " + details.getPhone());
                System.out.println("Membership Type: " + MemberMenu.getselectedMembershipType());
                System.out.println("====================================");
            });
        }
    }
}
