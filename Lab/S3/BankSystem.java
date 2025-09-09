import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;

    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
        else System.out.println("Invalid deposit amount.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) System.out.println("Invalid withdrawal amount.");
        else if (amount > balance) System.out.println("Insufficient funds.");
        else balance -= amount;
    }

    public double checkBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("-------------------");
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return String.format("ACC%03d", totalAccounts + 1);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of accounts to create: ");
        int n = sc.nextInt();
        sc.nextLine();

        BankAccount[] accounts = new BankAccount[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter account holder name: ");
            String name = sc.nextLine();
            System.out.print("Enter initial deposit: ");
            double deposit = sc.nextDouble();
            sc.nextLine();
            accounts[i] = new BankAccount(name, deposit);
        }

        System.out.println("\n--- Account Details ---");
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        System.out.print("Enter account index (0 to " + (n - 1) + ") to deposit: ");
        int idx = sc.nextInt();
        System.out.print("Enter deposit amount: ");
        double depAmt = sc.nextDouble();
        accounts[idx].deposit(depAmt);

        System.out.print("Enter account index (0 to " + (n - 1) + ") to withdraw: ");
        idx = sc.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double wAmt = sc.nextDouble();
        accounts[idx].withdraw(wAmt);

        System.out.println("\n--- Updated Account Details ---");
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
