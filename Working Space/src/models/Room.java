package models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String customerName;
    private final String bookingDuration;
    private final List<String> selectedFacilities;
    private final String roomType; 
    private final double totalPrice; 
    private String paymentStatus; 
    protected final  boolean isMember; 

    
    public static final List<Room> bookings = new ArrayList<>();

    // Konstruktor untuk membuat pesanan baru
    public Room(String customerName, String bookingDuration, List<String> selectedFacilities, String roomType, double totalPrice, boolean isMember) {
        this.customerName = customerName;
        this.bookingDuration = bookingDuration;
        this.selectedFacilities = selectedFacilities;
        this.roomType = roomType;
        this.totalPrice = totalPrice;
        this.paymentStatus = "Pending"; // Default payment status
        this.isMember = isMember; // Set membership status
    }

    // Getter untuk nama pelanggan
    public String getCustomerName() {
        return customerName;
    }

    // Getter untuk total harga
    public double getTotalPrice() {
        return totalPrice;
    }

    // Getter dan Setter untuk status pembayaran
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getroomType() {
        return roomType;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Menampilkan detail pemesanan
    private void displayBookingDetails() {
        System.out.println("Customer Name      : " + customerName);
        System.out.println("Room Type          : " + roomType);
        System.out.println("Duration           : " + bookingDuration);
        System.out.println("Selected Facilities: " + (selectedFacilities.isEmpty() ? "None" : String.join(", ", selectedFacilities)));
        System.out.println("Payment Status     : " + paymentStatus);
        System.out.println("Membership Status  : " + (isMember ? "Member" : "Non-Member")); // Display membership status
    }

    // Menampilkan daftar nama pelanggan dan jenis kamar yang dipesan
    public static void displayCustomerNames() {
        if (Room.bookings.isEmpty()) {
            System.out.println("Tidak ada pemesanan yang tersedia.");
        } else {
            System.out.println("\nDaftar Nama Pemesan dan Jenis Kamar:");
            for (Room booking : Room.bookings) {
                // Menampilkan nama pelanggan dan jenis kamar
                System.out.printf("- Nama Pemesan: %s\n", booking.getCustomerName());
                System.out.printf("  Jenis Kamar: %s\n", booking.getroomType());
                System.out.printf("  Status Pembayaran: %s\n", booking.getPaymentStatus());
                System.out.printf("  Membership Status: %s\n", booking.isMember ? "Member" : "Non-Member"); // Display membership status
            }
        }
    }

    // Menampilkan semua pesanan yang ada
    public static void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("\nAll Bookings:");
            for (int i = 0; i < bookings.size(); i++) {
                System.out.println("\nBooking " + (i + 1) + ":");
                bookings.get(i).displayBookingDetails();
            }
        }
    }
}