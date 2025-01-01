
package controller;

import Interfaces.Payment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import models.Admin;
import models.Room;
import services.BankTransferPayment;
import services.MemberMenu;
import services.OnlinePayment;
import utilities.ClearScreen;

public class RoomController {
    private static final List<String> customerNames = new ArrayList<>(); // List untuk menyimpan nama pemesan

    // Metode untuk menampilkan semua pemesanan
    public static void displayAllBookings(Scanner scanner) {
        if (Room.bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            Room.displayAllBookings(); 
        }
        returnToMainMenu(scanner);  // Panggil metode untuk kembali ke menu utama
    }

    // Metode untuk membatalkan pemesanan
    public static void cancelBooking(Scanner scanner) {
        Room.displayCustomerNames();
        System.out.print("Enter the name of the customer to cancel the booking: ");
        String customerName = scanner.nextLine();
    
        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }
    
        boolean found = false;
        Iterator<Room> iterator = Room.bookings.iterator();
    while (iterator.hasNext()) {
            Room booking = iterator.next();
            if (booking.getCustomerName().equalsIgnoreCase(customerName)) {
                iterator.remove();
                System.out.println("Booking for " + customerName + " has been canceled.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No booking found for the name: " + customerName);
        }
        returnToMainMenu(scanner);
    }

    public static void adminMenu(Scanner scanner) {
        if (Admin.login(scanner)) {
            boolean adminRunning = true;
            while (adminRunning) {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Delete Room Booking");
                System.out.println("2. List Membership");
                System.out.println("4. Exit Admin Menu");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    switch (choice) {
                        case 1 -> Admin.deleteRoom(scanner);
                        case 2 -> Admin.displayMembers(scanner);
                        case 3 -> {
                            System.out.println("Exiting admin menu...");
                            adminRunning = false;
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
        } else {
            System.out.println("Invalid admin credentials. Access denied.");
        }
    }

    public static void handlePaymentByCustomerName(Scanner scanner) {
        System.out.println("=== Payment by Customer Name ===");
        Room.displayCustomerNames(); // Tampilkan semua nama pelanggan
        System.out.print("Enter the name of the customer for payment: ");
        String customerName = scanner.nextLine();

        // Mencari pemesanan berdasarkan nama
        Room booking = null;
        for (Room b : Room.bookings) {
            if (b.getCustomerName().equalsIgnoreCase(customerName)) {
                booking = b;
                break;
            }
        }

        if (booking != null) {
            // Jika pemesanan ditemukan, lakukan pembayaran
            handlePayment(booking.getTotalPrice(), booking, scanner);
        } else {
            System.out.println("No booking found for the name: " + customerName);
            returnToMainMenu(scanner);//Kembali ke menu utama
        }
    }
    private static void handlePayment(double totalPrice, Room booking, Scanner scanner) {
        Payment paymentMethod = null;
    
        while (paymentMethod == null) {
            System.out.println("=== Payment Options ===");
            System.out.println("1. Online Payment");
            System.out.println("2. Bank Transfer");
            System.out.print("Select payment method (1 or 2): ");
    
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
    
                switch (choice) {
                    case 1: // Online Payment
                        OnlinePayment.displayPaymentMethods();
                        System.out.print("Select an online payment method (1-4): ");
                        int methodChoice = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer
                    
                        String selectedMethod = OnlinePayment.getPaymentMethodByChoice(methodChoice);
                        if (selectedMethod != null) {
                            paymentMethod = new OnlinePayment(selectedMethod, booking.getCustomerName(), booking.getroomType(), selectedMethod);
                            paymentMethod.processPayment(totalPrice);
                        } else {
                            System.out.println("Invalid choice. Returning to payment options.");
                        }
                        break;
    
                    case 2: // Bank Transfer
                        System.out.print("Enter bank account number: ");
                        String bankAccountNumber = scanner.nextLine();
                        String membershipType = MemberMenu.selectedMembershipType; // Get membership type
                        paymentMethod = new BankTransferPayment(bankAccountNumber, booking.getroomType(), booking.getCustomerName(), membershipType);
                        paymentMethod.processPayment( totalPrice);
                        break;
    
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        System.out.println("press 'Enter' if payment is done");
        scanner.nextLine();
        ClearScreen.clearScreen();

        System.out.println(paymentMethod.getPaymentDetails());
        booking.setPaymentStatus("Paid");
        System.out.println("Payment successful! Thank you for your payment.");
        returnToMainMenu(scanner);
    }

    public static void returnToMainMenu(Scanner scanner) {
        System.out.print("\nTekan Enter untuk kembali ke menu utama...");
        scanner.nextLine(); // Tunggu input pengguna
        ClearScreen.clearScreen(); // Bersihkan layar
        // Kode untuk menampilkan menu utama bisa ditambahkan di sini
    }

    public static List<String> getCustomerNames() {
        return customerNames;
    }
}
