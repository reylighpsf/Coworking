package controller;

import java.util.Scanner;
import models.Membership;
import services.MemberMenu;
import services.CoworkingSpace;
import services.MeetingRoom;
import services.OfficeSpace;
import services.VirtualOffice;
import utilities.ClearScreen;

public class MemberController {

    // Method to display the member menu
    public static void showMemberMenu(Scanner scanner) {
        System.out.println("\n=== Member Menu ===");
        System.out.println("1. Register Membership");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice = getValidIntInput(scanner);

        ClearScreen.clearScreen();

        switch (choice) {
            case 1 -> Membership.register(scanner);
            case 2 -> System.out.println("Exiting Member Menu...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to handle login process
    public static boolean login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        ClearScreen.clearScreen();

        Membership.MemberDetails details = Membership.getMembers().get(inputUsername);

        if (details != null && details.getPassword().equals(inputPassword)) {
            System.out.println("Login successful! Welcome, " + inputUsername + "!");
            MemberMenu.displayChosenMembership();
            return bookMembership(scanner);
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    // Method for booking membership options
    public static boolean bookMembership(Scanner scanner) {
        String membershipType = MemberMenu.selectedMembershipType; // Get selected membership type

        while (true) {
            System.out.println("\n=== Booking Menu ===");
            System.out.println("1. Book Coworking Space");
            System.out.println("2. Book Meeting Room");
            System.out.println("3. Book Office Space");
            System.out.println("4. Book Virtual Office");
            System.out.println("5. Exit Booking Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput(scanner);

            switch (choice) {
                case 1 -> CoworkingSpace.coworkingOrder(scanner, membershipType);
                case 2 -> MeetingRoom.meetingRoomOrder(scanner, membershipType);
                case 3 -> OfficeSpace.officeSpaceOrder(scanner, membershipType);
                case 4 -> VirtualOffice.virtualOfficeOrder(scanner, membershipType);
                case 5 -> {
                    System.out.println("Exiting Booking Menu...");
                    return false;  // Exit booking
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Utility method to get valid integer input from user
    private static int getValidIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return choice;
    }
}
