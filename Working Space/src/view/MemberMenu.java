package services;

import java.util.Scanner;


public class MemberMenu {

    public static String selectedMembershipType ; // Store the selected membership type
    private static String daysOfAccess = ""; // Store days of access
    private static int totalPrice = 0; // Store total price
    private static String benefits = ""; // Variable to store benefits

    // Method untuk menampilkan rencana keanggotaan

    private static void displayMembership() {
        System.out.println("\tChoose your membership plan");
        System.out.println("\t========================================================================================");
        System.out.println("\t|  NO  |    Membership |              Benefits            |   Price                    |");
        System.out.println("\t========================================================================================");
        System.out.println("\t|   1  | Bronze        | - Access to any RoFind lounge    | From                       |");
        System.out.println("\t|      |               | - Secure Wi-Fi, printer, scanner | IDR 750K (/month)          |");
        System.out.println("\t|      |               | - 10% discount via RoFind        |                            |");
        System.out.println("\t|--------------------------------------------------------------------------------------|");
        System.out.println("\t|   2  | Silver        | - All Lounge benefits            | From                       |");
        System.out.println("\t|      |               | - Coworking access               | - IDR 2.59M (/month)       |");
        System.out.println("\t|      |               | - Live receptionist services     |                            |");
        System.out.println("\t|      |               | - Book days via app              |                            |");
        System.out.println("\t|--------------------------------------------------------------------------------------|");
        System.out.println("\t|   3  | Gold          | - All Coworking benefits         | From                       |");
        System.out.println("\t|      |               | - Private office access          | - IDR 3.59M (5 days/month) |");
        System.out.println("\t|      |               | - Office cleaning & maintenance  |                            |");
        System.out.println("\t|      |               | - Book days via app              |                            |");
        System.out.println("\t========================================================================================");
    }

    // Method untuk memproses pilihan keanggotaan
    private static void showMembershipPlans(Scanner scanner) {
        displayMembership();
        System.out.print("\nEnter the number corresponding to the membership you want to choose (1-3): ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid input! Please enter a number between 1 and 3.");
            return;
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        switch (choice) {
            case 1 -> handleBronzeMembership();
            case 2 -> handleSilverMembership();
            case 3 -> handleGoldMembership();
            default -> System.out.println("Invalid choice! Please run the program again and choose a valid option.");
        }
    }

    // Handle Bronze membership selection
    private static void handleBronzeMembership() {
        selectedMembershipType = "Bronze";
        daysOfAccess = "1 month";
        totalPrice = 750_000;
        benefits ="- Access to any RoFind lounge\n" +
                "- Secure Wi-Fi, printer, scanner\n" +
                "- 10% discount via RoFind";
    }

    // Handle Silver membership selection
    private static void handleSilverMembership() {
        selectedMembershipType = "Silver";
        daysOfAccess = "1 month";
        totalPrice = 1_890_000;
        benefits = "- All Lounge benefits\n" +
                "- Coworking access\n" +
                "- Live receptionist services\n" +
                "- Book days via app";
    }

    // Handle Gold membership selection
    private static void handleGoldMembership() {
        selectedMembershipType = "Gold";
        daysOfAccess = "1 month";
        totalPrice = 3_490_000;
        benefits = "- All Coworking benefits\n" +
                "- Private office access\n" +
                "- Office cleaning & maintenance\n" +
                "- Book days via app";
    }

    // Method to display the chosen membership details
    public static void displayChosenMembership() {
        System.out.println("\nYou have chosen:");
        System.out.println("Membership type: " + selectedMembershipType);
        System.out.println("Days of Access : " + daysOfAccess);
        System.out.println("Total Price    : IDR " + totalPrice);
        System.out.println("Benefits :\n" + benefits);
    }

    public static String getselectedMembershipType() {
        return selectedMembershipType;
    }

    public static String getbenefits() {
        return benefits;
    }

    // Public method to start the membership selection process
    public static String startMembershipSelection(Scanner scanner) {
        showMembershipPlans(scanner);
    return benefits;

    }

}
