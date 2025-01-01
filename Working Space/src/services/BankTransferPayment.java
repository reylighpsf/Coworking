

package services;

import Interfaces.Payment;

public class BankTransferPayment implements Payment {
    private final String bankAccountNumber;
    private final String roomType;
    private final String customerName;
    private final String membershipType;
    private double finalAmount;

    public BankTransferPayment(String bankAccountNumber, String roomType, String customerName, String membershipType) {
        this.bankAccountNumber = bankAccountNumber;
        this.roomType = roomType;
        this.customerName = customerName;
        this.membershipType = membershipType; // Ensure this is initialized
    }

    private String generateVirtualAccountNumber() {
        String bankCode = "BANK"; // Placeholder for a real bank code
        String accountSuffix = bankAccountNumber.substring(bankAccountNumber.length() - 6); // Last 6 digits
        return bankCode + accountSuffix;
    }

    private double calculateDiscount(double totalPrice) {
        if (membershipType != null && !membershipType.isEmpty()) { // Check for null
            return totalPrice * 0.10; // 10% discount for members
        }
        return 0;
    }

    @Override
    public void processPayment(double totalPrice) {
        double discountAmount = calculateDiscount(totalPrice);
        double finalAmount = totalPrice - discountAmount;

        displayPaymentDetails(totalPrice, discountAmount, finalAmount);
        System.out.println("Payment is being processed...\n");
    }

    private void displayPaymentDetails(double originalAmount, double discountAmount, double finalAmount) {
        System.out.println("======================================");
        System.out.println("          BANK TRANSFER PAYMENT       ");
        System.out.println("======================================");
        System.out.println("  Customer Name       : " + customerName);
        System.out.println("  Room Type           : " + roomType);
        System.out.println("  Membership Type     : " + (membershipType != null && !membershipType.isEmpty() ? membershipType : "None")); // Check for null
        System.out.println("  Account Number      : " + bankAccountNumber);
        System.out.printf("  Original Amount     : Rp %,d%n", (int) originalAmount);
        System.out.printf("  Discount Amount     : Rp %,d%n", (int) discountAmount);
        System.out.printf("  Payment Amount      : Rp %,d%n", (int) finalAmount);
        System.out.println("  Virtual Account No  : " + generateVirtualAccountNumber());
        System.out.println("======================================");
    }

    @Override
    public String getPaymentDetails() {
        String virtualAccount = generateVirtualAccountNumber();
        return "======================================\n" +
                "               PAYMENT RECEIPT        \n" +
                "======================================\n" +
                "Customer Name       : " + customerName + "\n" +
                "Room Type           : " + roomType + "\n" +
                "Account Number      : " + bankAccountNumber + "\n" +
                "Virtual Account No  : " + virtualAccount + "\n" +
                "Amount              : Rp " + String.format("%,.2f", finalAmount) + "\n" +
                "======================================";
    }
}
