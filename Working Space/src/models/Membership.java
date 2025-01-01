package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import services.MemberMenu;
import utilities.ClearScreen;

public class Membership { // Kelas ini bersifat publik
    // Map untuk menyimpan detail member
    private static final Map<String, MemberDetails> members = new HashMap<>();

    // Custom class untuk menyimpan detail member
    public static class MemberDetails {
        private final String phone;
        private final String password;
        private final String email;
        private final String membership;

        public MemberDetails(String phone, String password, String email, String membership) {
            this.phone = phone;
            this.password = password;
            this.email = email;
            this.membership = membership;
        }

        public String getPhone() {
            return phone;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getMembership() {
            return membership;
        }
    }

    // Method untuk mendaftar member baru
    public static void register(Scanner scanner) {
        String membership = MemberMenu.startMembershipSelection(scanner);

        System.out.println("Please complete your registration below.");

        System.out.print("Enter a username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter an email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        if (members.containsKey(newUsername)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }

        System.out.print("Enter a password: ");
        String newPassword = scanner.nextLine();

        members.put(newUsername, new MemberDetails(phone, newPassword, email, membership));

        ClearScreen.clearScreen();

        displayUserDetails(newUsername);
    }

    // Method untuk menampilkan detail pengguna
    private static void displayUserDetails(String username) {
        MemberDetails details = members.get(username);
        if (details != null) {
            System.out.println("Congratulations, you are now a member!");
            System.out.println("Username            : " + username);
            System.out.println("Email               : " + details.getEmail());
            System.out.println("Phone Number        : " + details.getPhone());
            System.out.println("Membership Type     : " + MemberMenu.getselectedMembershipType());
            System.out.println("Membership Benefits : " + MemberMenu.getbenefits());

            System.out.println("Press Enter to return to the main menu...");
            try {
                System.in.read(); // Menunggu input pengguna
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method untuk mendapatkan daftar member
    public static Map<String, MemberDetails> getMembers() {
        return members;
    }
}