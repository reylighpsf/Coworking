package view;

import controller.RoomController;
import models.Admin;
import java.util.Scanner;

public class AdminMenu {

    public static void displayAdminMenu(Scanner scanner) {
        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Delete Room Booking");
            System.out.println("2. List Membership");
            System.out.println("3. Exit Admin Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                    Admin.deleteRoom(scanner); // Memanggil metode untuk menghapus pemesanan
                        break;
                    case 2:
                        Admin.displayMembers(scanner); // Memanggil metode untuk menampilkan anggota
                        break;
                    case 3:
                        System.out.println("Exiting admin menu...");
                        adminRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}