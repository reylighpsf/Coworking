

package services;

import Interfaces.Payment;

public class OnlinePayment implements Payment {
    private final String paymentMethod; // Nama metode pembayaran (OVO, DANA, ShopeePay, GoPay)
    private final String customerName;
    private final String roomType;
    private final String membershipType; // Tipe keanggotaan
    private final String paymentTo; // Tujuan pembayaran (rekening atau akun virtual)
    private final String paymentCode; // Kode pembayaran unik
    private double finalAmount; // Jumlah akhir setelah diskon

    // Constructor
    public OnlinePayment(String paymentMethod, String customerName, String roomType, String membershipType) {
        this.paymentMethod = paymentMethod;
        this.customerName = customerName;
        this.roomType = roomType;
        this.membershipType = membershipType;
        this.paymentTo = generatePaymentDestination(paymentMethod);
        this.paymentCode = generatePaymentCode();
    }

    // Generate Payment Destination based on method
    private String generatePaymentDestination(String method) {
        return switch (method) {
            case "OVO" -> "OVO Account: 081234567890";
            case "DANA" -> "DANA Account: 081234567891";
            case "ShopeePay" -> "ShopeePay Account: 081234567892";
            case "GoPay" -> "GoPay Account: 081234567893";
            default -> "Unknown Payment Method";
        };
    }

    // Generate a unique payment code
    private String generatePaymentCode() {
        return "PAY" + (int) (Math.random() * 1_000_000); // Random 6-digit code
    }

    // Calculate discount for members
    private double calculateDiscount(double totalPrice) {
        if (membershipType != null && !membershipType.isEmpty()) {
            return totalPrice * 0.10; // 10% discount
        }
        return 0;
    }

    // Process the payment
    @Override
    public void processPayment(double totalPrice) {
        double discountAmount = calculateDiscount(totalPrice);
        finalAmount = totalPrice - discountAmount;

        displayPaymentDetails(totalPrice, discountAmount, finalAmount);
        System.out.println("Payment is being processed...\n");
    }

    // Display payment details in a formatted receipt
    private void displayPaymentDetails(double originalAmount, double discountAmount, double finalAmount) {
        System.out.println("======================================");
        System.out.println("           ONLINE PAYMENT DETAILS      ");
        System.out.println("======================================");
        System.out.println("  Customer Name       : " + customerName);
        System.out.println("  Room Type           : " + roomType);
        System.out.println("  Membership Type     : " + (membershipType == null || membershipType.isEmpty() ? "None" : membershipType));
        System.out.println("  Payment To          : " + paymentTo);
        System.out.println("  Payment Code        : " + paymentCode);
        System.out.printf("  Original Amount     : Rp %,d%n", (int) originalAmount);
        System.out.printf("  Discount Amount     : Rp %,d%n", (int) discountAmount);
        System.out.printf("  Payment Amount      : Rp %,d%n", (int) finalAmount);
        System.out.println("======================================");
    }

    // Return payment details as a formatted receipt
    @Override
    public String getPaymentDetails() {
        return "======================================\n" +
                "               PAYMENT RECEIPT        \n" +
                "======================================\n" +
                "Customer Name : " + customerName + "\n" +
                "Room Type     : " + roomType + "\n" +
                "Payment Method: " + paymentMethod + "\n" +
                "Payment To    : " + paymentTo + "\n" +
                "Payment Code  : " + paymentCode + "\n" +
                "Amount        : Rp " + String.format("%,.2f", finalAmount) + "\n" +
                "======================================";
    }

    // Static method to display available online payment methods
    public static void displayPaymentMethods() {
        System.out.println("Available Online Payment Methods:");
        System.out.println("1. OVO");
        System.out.println("2. DANA");
        System.out.println("3. ShopeePay");
        System.out.println("4. GoPay");
    }

    // Static method to map choice to payment method
    public static String getPaymentMethodByChoice(int choice) {
        return switch (choice) {
            case 1 -> "OVO";
            case 2 -> "DANA";
            case 3 -> "ShopeePay";
            case 4 -> "GoPay";
            default -> null;
        };
    }
}
