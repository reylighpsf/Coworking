package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Room;
import utilities.ClearScreen;

public class OfficeSpace extends Room {
    public OfficeSpace(String customerName, String bookingDuration, List<String> selectedFacilities, String room, double totalPrice, boolean isMember) {
        super(customerName, bookingDuration, selectedFacilities, room, totalPrice, isMember);
    }

    private enum RoomType {
        PRIVATE_OFFICE("Private Office", 500000),
        CUSTOM_OFFICE("Custom Office", 100000),
        DAY_OFFICE("Day Office", 200000),
        MEMBERSHIP_OFFICE("Membership Office", 1000000);

        private final String displayName;
        private final double price;

        RoomType(String displayName, double price) {
            this.displayName = displayName;
            this.price = price;
        }

        private double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    private enum Facility {
        LOCKER_ACCESS("Locker Access", 2500000),
        PANTRY_ACCESS("Per Week Pantry Access", 3500000),
        AUDIO_VISUAL_SET("Audio-Visual Set", 5000000),
        PRINTING_STATION("Printing Station", 3000000),
        NAPPING_AREA("Napping Area", 500000),
        HIGH_SPEED_INTERNET("High Speed Internet", 1500000);

        private final String name;
        private final double price;

        Facility(String name, double price) {
            this.name = name;
            this.price = price;
        }

        private String getName() {
            return name;
        }

        private double getPrice() {
            return price;
        }
    }

    private static void displayOfficeSpace() {
        System.out.println("\t===============================================================");
        System.out.println("\t==                         Room Options                      ==");
        System.out.println("\t===============================================================");
        System.out.println("\t==   No   |    Room Type        | Capacity  |   Time Period  ==");
        System.out.println("\t==-----------------------------------------------------------==");
        System.out.println("\t==   1   |    Private Office    |    20     | Month/Year     ==");
        System.out.println("\t==   2   |    Custom Office     |    30     | Month/Year     ==");
        System.out.println("\t==   3   |    Day Office        |    15     | Price/Hour     ==");
        System.out.println("\t==   4   |    Membership Office |    20     | By Membership   ==");
        System.out.println("\t===============================================================");
    }

    private static void displayFacility() {
        System.out.println("\t=============================================");
        System.out.println("\t==  NO |    Facility           | Price     ==");
        System.out.println("\t=============================================");
        System.out.println("\t==  1  | Locker Access         | 2,500,000 ==");
        System.out.println("\t==  2  | Per Week Pantry Access| 3,500,000 ==");
        System.out.println("\t==  3  | Audio-Visual Set      | 5,000,000 ==");
        System.out.println("\t==  4  | Printing Station      | 3,000,000 ==");
        System.out.println("\t==  5  | Napping Area          | 500,000   ==");
        System.out.println("\t==  6  | High Speed Internet   | 1,500,000 ==");
        System.out.println("\t=============================================");
    }

    // Method to handle office space order with membership type
    public static void officeSpaceOrder(Scanner scanner, String membershipType) {
        // Display room options
        displayOfficeSpace();

        // Ask user to select a room type
        System.out.print("Enter the number corresponding to the room type you want to book: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        RoomType selectedRoomType;
        switch (choice) {
            case 1 -> selectedRoomType = RoomType.PRIVATE_OFFICE;
            case 2 -> selectedRoomType = RoomType.CUSTOM_OFFICE;
            case 3 -> selectedRoomType = RoomType.DAY_OFFICE;
            case 4 -> selectedRoomType = RoomType.MEMBERSHIP_OFFICE;
            default -> {
                System.out.println("Invalid choice. Please restart the booking process.");
                return;
            }
        }

        // Ask for additional details
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the booking duration ( e.g., 1 month, 2 hours): ");
        String durationInput = scanner.nextLine();
        double duration = Double.parseDouble(durationInput.split(" ")[0]);
        String timeUnit = durationInput.split(" ")[1].toLowerCase();

        ClearScreen.clearScreen();

        // Display facilities
        displayFacility();
        System.out.print("Select facilities you want to add (comma-separated numbers, e.g., 1,2): ");
        String facilityChoices = scanner.nextLine();
        List<String> selectedFacilities = new ArrayList<>();
        double totalFacilityCost = 0;

        String[] choices = facilityChoices.split(",");
        for (String choiceStr : choices) {
            try {
                int facilityIndex = Integer.parseInt(choiceStr.trim()) - 1;
                if (facilityIndex >= 0 && facilityIndex < Facility.values().length) {
                    Facility facility = Facility.values()[facilityIndex];
                    selectedFacilities.add(facility.getName());

                    // Skip cost for free facilities based on membership
                    if (!( membershipType.equalsIgnoreCase("Bronze")&& (facility == Facility.HIGH_SPEED_INTERNET || facility == Facility.PRINTING_STATION))&&
                        !(membershipType.equalsIgnoreCase("Silver")&& (facility == Facility.HIGH_SPEED_INTERNET || facility == Facility.PRINTING_STATION)) &&
                        !(membershipType.equalsIgnoreCase("Gold") && (facility == Facility.HIGH_SPEED_INTERNET || facility == Facility.PRINTING_STATION))) {
                        totalFacilityCost += facility.getPrice();
                    }
                } else {
                    System.out.println("Invalid facility choice: " + choiceStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + choiceStr);
            }
        }

        ClearScreen.clearScreen();

        // Calculate total price
        double roomPrice = selectedRoomType.getPrice();
        if (timeUnit.contains("month")) {
            roomPrice *= duration;
        } else if (timeUnit.contains("hour")) {
            roomPrice *= duration;
        }

        double totalPrice = roomPrice + totalFacilityCost;

        // Create a new booking and add it to the list
        Room booking = new OfficeSpace(customerName, durationInput, selectedFacilities, selectedRoomType.toString(), totalPrice, true); // Assuming it's a member booking
        Room.bookings.add(booking); // Add booking to the list

        // Confirm booking
        System.out.println("\nBooking Confirmation:");
        System.out.println("-----------------------------------");
        System.out.println("Room Type          : " + selectedRoomType);
        System.out.println("Customer Name      : " + customerName);
        System.out.println("Duration           : " + durationInput);
        System.out.println("Selected Facilities: " + String.join(", ", selectedFacilities));
        System.out.printf("Total Price  : Rp %,d\n", (int) totalPrice);
        System.out.println("-----------------------------------");
        System.out.println("Thank you for your booking!");
        System.out.println("-----------------------------------");

        // Display membership benefits
        if (membershipType.equalsIgnoreCase("Bronze")) {
            System.out.println("Membership Type: Bronze");
            System.out.println("You have free access to the office space and the following facilities are included for free:");
            System.out.println("- High Speed Internet");
            System.out.println("- Printing Station");
        } else if (membershipType.equalsIgnoreCase("Silver")) {
            System.out.println("Membership Type: Silver");
            System.out.println("You have free access to the office space and the following facilities are included for free:");
            System.out.println("- High Speed Internet");
            System.out.println("- Printing Station");
            System.out.println("- Office cleaning & maintenance");
        } else if (membershipType.equalsIgnoreCase("Gold")) {
            System.out.println("Membership Type: Gold");
            System.out.println("You have free access to the office space and the following facilities are included for free:");
            System.out.println("- High Speed Internet");
            System.out.println("- Printing Station");
            System.out.println("- Office cleaning & maintenance");
        }else {
            System.out.println("Membership Type: None");
        }

        System.out.println("Thank you for your booking!");
        System.out.println("-----------------------------------");
        System.out.println("Press Enter Return To Menu");
        scanner.nextLine();
    }

    // New method to handle bookings without membership
    public static void officeSpaceOrderWithoutMembership(Scanner scanner) {
        // Display room options
        displayOfficeSpace();

        // Ask user to select a room type
        System.out.print("Enter the number corresponding to the room type you want to book: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        RoomType selectedRoomType;
        switch (choice) {
            case 1 -> selectedRoomType = RoomType.PRIVATE_OFFICE;
            case 2 -> selectedRoomType = RoomType.CUSTOM_OFFICE;
            case 3 -> selectedRoomType = RoomType.DAY_OFFICE;
            case 4 -> selectedRoomType = RoomType.MEMBERSHIP_OFFICE;
            default -> {
                System.out.println("Invalid choice. Please restart the booking process.");
                return;
            }
        }

        // Ask for additional details
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the booking duration (e.g., 1 month, 2 hours): ");
        String durationInput = scanner.nextLine();
        double duration = Double.parseDouble(durationInput.split(" ")[0]);
        String timeUnit = durationInput.split(" ")[1].toLowerCase();

        ClearScreen.clearScreen();

        // Display facilities
        displayFacility();
        System.out.print("Select facilities you want to add (comma-separated numbers, e.g., 1,2): ");
        String facilityChoices = scanner.nextLine();
        List<String> selectedFacilities = new ArrayList<>();
        double totalFacilityCost = 0;

        String[] choices = facilityChoices.split(",");
        for (String choiceStr : choices) {
            try {
                int facilityIndex = Integer.parseInt(choiceStr.trim()) - 1;
                if (facilityIndex >= 0 && facilityIndex < Facility.values().length) {
                    Facility facility = Facility.values()[facilityIndex];
                    selectedFacilities.add(facility.getName());
                    totalFacilityCost += facility.getPrice(); // All facilities are charged
                } else {
                    System.out.println("Invalid facility choice: " + choiceStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + choiceStr);
            }
        }

        ClearScreen.clearScreen();

        // Calculate total price
        double roomPrice = selectedRoomType.getPrice();
        if (timeUnit.contains("month")) {
            roomPrice *= duration;
        } else if (timeUnit.contains("hour")) {
            roomPrice *= duration;
        }

        double totalPrice = roomPrice + totalFacilityCost;

        // Create a new booking and add it to the list
        Room booking = new OfficeSpace(customerName, durationInput, selectedFacilities, selectedRoomType.toString(), totalPrice, false); // Assuming it's a non-member booking
        Room.bookings.add(booking); // Add booking to the list

       // Confirm booking
        System.out.println("\nBooking Confirmation:");
        System.out.println("-----------------------------------");
        System.out.println("Room Type          : " + selectedRoomType);
        System.out.println("Customer Name      : " + customerName);
        System.out.println("Duration           : " + durationInput);
        System.out.println("Selected Facilities: " + String.join(", ", selectedFacilities));
        System.out.printf("Total Price  : Rp %,d\n", (int) totalPrice);
        System.out.println("-----------------------------------");
        System.out.println("Thank you for your booking!");
        System.out.println("-----------------------------------");

        System.out.println("Press Enter Return To Menu");
        scanner.nextLine();
    }
    
}