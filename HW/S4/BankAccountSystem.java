import java.util.Random;

// BankAccount Class
class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    // 1. Default constructor → balance = 0
    public BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // 2. Constructor with name → assigns random account number
    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // 3. Constructor with name and initial balance
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = initialBalance;
    }

    // Method to generate random account number
    private int generateAccountNumber() {
        Random rand = new Random();
        return 10000 + rand.nextInt(90000); // 5-digit random number
    }

    // deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + " to " + accountHolder + "'s account.");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from " + accountHolder + "'s account.");
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // display account details
    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("------------------------");
    }
}

// Main Class
public class BankAccountSystem {
    public static void main(String[] args) {
        // Default constructor
        BankAccount acc1 = new BankAccount();

        // Constructor with name
        BankAccount acc2 = new BankAccount("Alice");

        // Constructor with name and initial balance
        BankAccount acc3 = new BankAccount("Bob", 5000.0);

        // Perform transactions
        acc1.deposit(1000);
        acc2.deposit(2000);
        acc3.withdraw(1500);

        // Display account details
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
    }
}
