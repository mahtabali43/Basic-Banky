import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Bank Account
class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor to create a new BankAccount
    public BankAccount(String accountHolderName, String accountNumber, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to deposit funds into account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw funds from account
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    // Method to transfer funds to another account
    public boolean transfer(BankAccount recipient, double amount) {
        if (this.withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Successfully transferred $" + amount + " to " + recipient.getAccountHolderName());
            return true;
        } else {
            return false;
        }
    }

    // Getter for account holder's name
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

// Class to represent the Bank, which holds multiple accounts
class Bank {
    private ArrayList<BankAccount> accounts;
    private static int accountNumberCounter = 1001;

    // Constructor to initialize the bank's account list
    public Bank() {
        accounts = new ArrayList<>();
    }

    // Method to create a new account
    public void createAccount(String name, double initialDeposit) {
        String accountNumber = String.valueOf(accountNumberCounter++);
        BankAccount newAccount = new BankAccount(name, accountNumber, initialDeposit);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    // Method to find an account by account number
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    // Method to display all accounts
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (BankAccount account : accounts) {
                account.displayAccountDetails();
                System.out.println("----------------------------");
            }
        }
    }
}

// Main class for the BankY simulation
public class Banky {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    // Method to display the menu
    public static void displayMenu() {
        System.out.println("\nWelcome to BankY - Banking System");
        System.out.println("1. Create a new account");
        System.out.println("2. Deposit funds");
        System.out.println("3. Withdraw funds");
        System.out.println("4. Transfer funds");
        System.out.println("5. Display all accounts");
        System.out.println("6. Exit");
    }

    // Main method to handle user interaction
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    transferFunds();
                    break;
                case 5:
                    bank.displayAllAccounts();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting BankY. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    // Method to create a new account
    private static void createNewAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        bank.createAccount(name, initialDeposit);
    }

    // Method to deposit funds into an account
    private static void depositFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    // Method to withdraw funds from an account
    private static void withdrawFunds() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    // Method to transfer funds between accounts
    private static void transferFunds() {
        System.out.print("Enter sender's account number: ");
        String senderAccountNumber = scanner.nextLine();
        BankAccount senderAccount = bank.findAccount(senderAccountNumber);

        if (senderAccount != null) {
            System.out.print("Enter recipient's account number: ");
            String recipientAccountNumber = scanner.nextLine();
            BankAccount recipientAccount = bank.findAccount(recipientAccountNumber);

            if (recipientAccount != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                senderAccount.transfer(recipientAccount, amount);
            }
        }
    }
}
