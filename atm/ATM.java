// The software to be designed will control a simulated
// automated teller machine (ATM) having a magnetic
// stripe reader for reading an ATM card, a customer
// console (keyboard and display) for interaction with the
// customer, a slot for depositing envelopes, a dispenser
// for cash (in multiples of $20), and a printer for printing
// customer receipts.
// The ATM will service one customer at a time. A
// customer will be required to insert an ATM card and
// enter a personal identification number (PIN) - both of
// which will be sent to the bank for validation as part of
// each transaction. The customer will then be able to
// perform one or more transactions. The card will be
// retained in the machine until the customer indicates
// that he/she desires no further transactions, at which
// point it will be returned - except as noted below.
// The ATM must be able to provide the following
// services to the customer:
// • A customer must be able to make a cash withdrawal
// from any suitable account linked to the card, in multiples
// of $20.00. Approval must be obtained from the bank
// before cash is dispensed.
// • A customer must be able to make a deposit to any
// account linked to the card, consisting of cash and/or
// checks in an envelope. The customer will enter the
// amount of the deposit into the ATM, subject to manual
// verification when the envelope is removed from the
// machine by an operator. Approval must be obtained from
// the bank before physically accepting the envelope.
// • A customer must be able to make a transfer of money
// between any two accounts linked to the card.
// • A customer must be able to make a balance inquiry of
// any account linked to the card.
// • A customer must be able to abort a transaction in
// progress by pressing the Cancel key instead of
// responding to a request from the machine.
// • The ATM will communicate each transaction to the bank
// and obtain verification that it was allowed by the bank.
// Ordinarily, a transaction will be considered complete by
// the bank once it has been approved. In the case of a
// deposit, a second message will be sent to the bank
// indicating that the customer has deposited the envelope.
// (If the customer fails to deposit the envelope within the
// timeout period, or presses cancel instead, no second
// message will be sent to the bank and the deposit will not
// be credited to the customer.)
// • If the bank determines that the customer's PIN is invalid,
// the customer will be required to re
// -enter the PIN before a
// transaction can proceed. If the customer is unable to
// successfully enter the PIN after three tries, the card will
// be permanently retained by the machine, and the
// customer will have to contact the bank to get it back.
// • If a transaction fails for any reason other than an invalid
// PIN, the ATM will display an explanation of the problem,
// and will then ask the customer whether he/she wants to
// do another transaction.
// • The ATM will provide the customer with a printed receipt
// for each successful transaction, showing the date, time,
// machine location, type of transaction, account(s),
// amount, and ending and available balance(s) of the
// affected account ("to" account for transfers).
// • The ATM will also maintain an internal log of transactions
// to facilitate resolving ambiguities arising from a hardware
// failure in the middle of a transaction. Entries will be made
// in the log when the ATM is started up and shut down, for
// each message sent to the Bank (along with the response
// back, if one is expected), for the dispensing of cash, and
// for the receiving of an envelope. Log entries may contain
// card numbers and dollar amounts, but for security will
// never contain a PIN.

package atm;

import java.util.Scanner;

public class ATM {

    private int currentCustomerID;
    private int currentAccountID;
    private int currentPIN;
    private int withdrawalAmount;
    private int depositAmount;
    private String transferAccount;
    private boolean hasEnvelope;
    private boolean transactionInProgress;
    private boolean cardRetained;
    private boolean transactionApproved;

    // Constructor
    /**
     * Creates a new instance of ATM
     */
    public ATM() {
        currentCustomerID = 0;
        currentAccountID = 0;
        currentPIN = 0;
        withdrawalAmount = 0;
        depositAmount = 0;
        transferAccount = "";
        hasEnvelope = false;
        transactionInProgress = false;
        cardRetained = false;
        transactionApproved = false;
    }


    // Method for inserting an ATM card
    /**
     * Inserts an ATM card into the ATM. If a transaction is already in progress,
     * 
     * @param customerID the customer ID on the card
     * @param pin the PIN on the card
     */
    public void insertCard(int customerID, int pin) {
        if (!transactionInProgress) {
            currentCustomerID = customerID;
            currentPIN = pin;
            transactionInProgress = true;
            System.out.println("Please select a transaction or press Cancel to exit.");
        } else {
            System.out.println("Another transaction is already in progress.");
        }
    }

    // Method for entering a PIN
    /**
     * Enters a PIN for the current transaction. If the PIN is invalid, the
     * 
     * @param pin the PIN entered by the customer
     */
    public void enterPIN(int pin) {
        if (currentPIN == pin) {
            System.out.println("PIN accepted.");
        } else {
            System.out.println("Invalid PIN. Please try again.");
            currentPIN = 0;
            transactionInProgress = false;
        }
    }

    // Method for withdrawing cash
    /**
     * Withdraws cash from the current account. If the withdrawal amount is not
     * 
     * @param amount the amount to be withdrawn
     */
    public void withdrawCash(int amount) {
        withdrawalAmount = amount;
        if (withdrawalAmount % 20 == 0) {
            // Send request to bank for approval
            transactionApproved = true; // Assume approval for simulation purposes
            if (transactionApproved) {
                // Dispense cash and print receipt
                System.out.println("Please take your cash.");
                printReceipt("Withdrawal", withdrawalAmount, currentAccountID);
            } else {
                System.out.println("Transaction declined.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Amount must be in multiples of $20.");
        }
        transactionInProgress = false;
    }

    // Method for depositing cash and checks
    /**
     * Deposits cash and checks into the current account. If the deposit is
     */
    public void deposit() {
        System.out.println("Please insert your envelope.");
        hasEnvelope = true;
        // Wait for operator to manually verify deposit
        transactionApproved = true; // Assume approval for simulation purposes
        if (transactionApproved) {
            depositAmount = 0; // Calculate deposit amount based on envelope contents
            // Send request to bank for approval
            if (transactionApproved) {
                // Credit account and print receipt
                System.out.println("Deposit accepted.");
                printReceipt("Deposit", depositAmount, currentAccountID);
            } else {
                System.out.println("Transaction declined.");
            }
        } else {
            System.out.println("Transaction declined.");
        }
        transactionInProgress = false;
    }

    // Method for transferring money between accounts
    /**
     * Transfers money from the current account to another account. If the
     * 
     * @param account the account to which money will be transferred
     * @param amount the amount to be transferred
     */
    public void transfer(String account, int amount) {
        transferAccount = account;
        // Send request to bank for approval
        transactionApproved = true; // Assume approval for simulation purposes
        if (transactionApproved) {
            // Update account balances and print receipt
            System.out.println("$" + amount + " transferred to account " + transferAccount + ".");
            printReceipt("Transfer", amount, currentAccountID, transferAccount);
        } else {
            System.out.println("Transaction declined.");
        }
        transactionInProgress = false;
    }

    // Method for checking account balance
    /**
     * Checks the balance of the current account.
     */
    public void checkBalance() {
        // Send request to bank for approval
        transactionApproved = true; // Assume approval for simulation purposes
        if (transactionApproved) {
            // Print receipt
            System.out.println("Your current balance is $" + 0 + ".");
            printReceipt("Balance", 0, currentAccountID);
        } else {
            System.out.println("Transaction declined.");
        }
        transactionInProgress = false;
    }

    // Method for printing a receipt
    /**
     * Prints a receipt for the current transaction.
     * 
     * @param transactionType the type of transaction
     * @param amount the amount of the transaction
     * @param accountID the account ID of the account involved in the transaction
     */
    public void printReceipt(String transactionType, int amount, int accountID) {
        System.out.println("Transaction type: " + transactionType);
        System.out.println("Amount: $" + amount);
        System.out.println("Account ID: " + accountID);
    }

    // Method for printing a receipt
    /**
     * Prints a receipt for the current transaction.
     * 
     * @param transactionType the type of transaction
     * @param amount the amount of the transaction
     * @param accountID the account ID of the account involved in the transaction
     * @param transferAccount the account ID of the account to which money was transferred
     */
    public void printReceipt(String transactionType, int amount, int accountID,
            String transferAccount) {
        System.out.println("Transaction type: " + transactionType);
        System.out.println("Amount: $" + amount);
        System.out.println("Account ID: " + accountID);
        System.out.println("Transfer account: " + transferAccount);
    }

    // Method for retaining an ATM card
    /**
     * Retains an ATM card after a transaction.
     */
    public void retainCard() {
        cardRetained = true;
        System.out.println("Please take your card.");
    }

    // Method for ejecting an ATM card
    /**
     * Ejects an ATM card after a transaction.
     */
    public void ejectCard() {
        if (cardRetained) {
            currentCustomerID = 0;
            currentAccountID = 0;
            currentPIN = 0;
            withdrawalAmount = 0;
            depositAmount = 0;
            transferAccount = "";
            hasEnvelope = false;
            transactionInProgress = false;
            cardRetained = false;
            transactionApproved = false;
            System.out.println("Thank you for using our ATM. Have a nice day!");
        } else {
            System.out.println("Please retain your card before ejecting.");
        }
    }

    // Method for canceling a transaction
    /**
     * Cancels a transaction in progress.
     */
    public void cancel() {
        if (transactionInProgress) {
            System.out.println("Transaction canceled.");
            transactionInProgress = false;
        } else {
            System.out.println("No transaction in progress.");
        }
    }

    // Method for exiting the ATM
    /**
     * Exits the ATM.
     */
    public void exit() {
        System.out.println("Thank you for using our ATM. Have a nice day!");
        System.exit(0);
    }

    // Main method
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int pin = 0;
        int amount = 0;
        String account = "";
        while (choice != 9) {
            System.out.println("1. Insert card");
            System.out.println("2. Enter PIN");
            System.out.println("3. Withdraw cash");
            System.out.println("4. Deposit cash or checks");
            System.out.println("5. Transfer money");
            System.out.println("6. Check balance");
            System.out.println("7. Retain card");
            System.out.println("8. Eject card");
            System.out.println("9. Cancel transaction");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    atm.insertCard(0, 0);
                    break;
                case 2:
                    System.out.print("Enter your PIN: ");
                    pin = input.nextInt();
                    atm.enterPIN(pin);
                    break;
                case 3:
                    System.out.print("Enter the amount you wish to withdraw: ");
                    amount = input.nextInt();
                    atm.withdrawCash(amount);
                    break;
                case 4:
                    atm.deposit();
                    break;
                case 5:
                    System.out.print("Enter the account number you wish to transfer to: ");
                    account = input.next();
                    System.out.print("Enter the amount you wish to transfer: ");
                    amount = input.nextInt();
                    atm.transfer(account, amount);
                    break;
                case 6:
                    atm.checkBalance();
                    break;
                case 7:
                    atm.retainCard();
                    break;
                case 8:
                    atm.ejectCard();
                    break;
                case 9:
                    atm.cancel();
                    break;
                case 10:
                    atm.exit();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        input.close();
    }
}
