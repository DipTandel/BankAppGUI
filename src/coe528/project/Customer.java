package coe528.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dip Tandel
 */
public class Customer extends User {
    
// Overview: Customer is mutable (balance associated with account can change and file),
// it represents the functions a customer can perform on its account
//
// The abstraction function is:
// AF(account) = account.getBalance() & account.determineLevel()
// where account.getBalance() is the balance the customer has on their account
// and account.determineLevel() is the level the account has based on the balance (see BankAccount class) 
//
// The rep invariant is:
// Each Customer has a unique username, a password, an inital balance of 100, and have only have 1 account object
//
    
    //Customer has variable BankAccount
    protected BankAccount account;

    //Constructor
    public Customer(String username, String password, double initialBal) {
        //Requires: a unique String for username, a String for password, and a double value for initial balance (can be 0, usually 100 default)
        //Effects: creates a new Customer object
        super(username, password, "customer");
        account = new BankAccount(initialBal);
    }

    @Override
    protected boolean login(String username, String password) {
        //Requires: a String that matches the username of an existing Customer and the associated password (String)
        //Effects: Searches for a matching filename to username and returns true if a match is found
        File cusFile = new File(username + ".txt"); //open customer file
        try { 
            Scanner scanner = new Scanner(cusFile);
            scanner.nextLine();
            String user = scanner.nextLine();
            scanner.nextLine();
            String pass = scanner.nextLine();
            scanner.close();
            
            if (username.equals(user) && password.equals(pass)) {
                System.out.println("Login Successful\n");
                return true;
            }
            else {
                System.out.println("Login Failed\n");
                return false;
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: User not found\n");
            return false;
        }
    }

    @Override
    protected void logout() {
    //Effects: prints out a confirmation statement
        System.out.println("You have been successfully logged out\n");
    }

    protected void deposit(double amount) {
        //Requires: a double amount > 0 (will otherwise print an error and not run)
        //Modifies: Customer's Account's (object) balance will update, as well as the balance info on the .txt file of the customer
        //Effects: prints error if amount is invalid 
        if (amount > 0) {
            account.setBalance(amount);
            updateCustomerFile();
        }
        else {
            System.out.println("Error: Please deposit more than 0\n");
        }
    }

    protected void withdraw(double amount) {
        /*Requires: a double amount > 0 (will otherwise print an error and not run),
          amount also has to be <= the balance in the account since
          that amount will be deducted from their balance*/
        //Modifies: Customer's Account's (object) balance will update, as well as the balance info on the .txt file of the customer
        //Effects: prints error if amount is invalid 
        if (amount > 0) {
            if (account.getBalance() >= amount) {
                account.setBalance(((-1)*(amount)));
                updateCustomerFile();
            }
            else {
                System.out.println("Error: Insufficient Funds\n");
            }
        }
        else {
            System.out.println("Error: Please withdraw more than 0\n");
        }
    }
    
    //getters
    protected String getName() {
        //Effects: returns a String containing the username of the Customer
        return username;
    }
    protected double getBalance() {
        //Effects: returns a double containing the balance in Customer's Account object
        return account.getBalance();
    }

    //purchase a _ from _ for _
    protected void onlinePurchase(String product, String vendor, double amount) {
        //Requires: any 2 Strings and a double value that is >= 50 (will otherwise print an error and not run)
        //Modifies: Customer's Account's (object) balance will update, as well as the balance info on the .txt file of the customer
        //Effects: prints sucessfull purchase String or error if amount is invalid 
        if (amount < 50) {
            System.out.println("Error: Purchase must be at least $50\n");
        }
        else if (account.getBalance() >= amount + account.getCharge()) {
            withdraw(amount + account.getCharge());
            System.out.println("Purchased " + product + " from " + vendor + " for $" + (amount+account.getCharge()) + "\n");
        }
        else {
            System.out.println("Error: Insufficient Funds: Total = " + (amount+account.getCharge()) + "\n");
        }
    }
    
    //update the corresponding file (balance and level) needed for deposit() and withdraw()
    protected void updateCustomerFile() {
        /*Modifies: the username.txt file of the Customer will be overwritten with an 
          updated balance and updated level using the account object of the Customer*/
        //Effects: prints error message or success message based on if file was sucessfully modified
        File cusFile = new File(username + ".txt"); // Open customer file
        try {
            Scanner scanner = new Scanner(cusFile);
            StringBuilder fileContent = new StringBuilder(); //temp holds lines
            while (scanner.hasNextLine()) { //until reaches end of file
                String line = scanner.nextLine();
                if (line.startsWith("$Balance:")) {
                    line = "$Balance:\n" + account.getBalance();
                    scanner.nextLine(); //skip the next line (old balance)
                } 
                else if (line.startsWith("Level:")) {
                    line = "Level:\n" + account.determineLevel();
                    scanner.nextLine(); //skip the next line (old level)
                }
                fileContent.append(line).append("\n"); //keep adding to temp String
            }
            scanner.close();
            //write updated content back to the file
            FileWriter writer = new FileWriter(cusFile, false);
            writer.write(fileContent.toString());
            writer.flush();
            writer.close();
            System.out.println("File updated successfully\n");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.\n");
        }
    }
    
    public boolean repOK() {
        //Effects: returns true if rep invariant holds for this object
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty() && account != null && account.getBalance() >= 100) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        //Effects: retuns a String containing the balance and level of the account of the customer. implements the abstraction function
        return (getName() + " has a balance of $" + account.getBalance() + " and a " + account.determineLevel() + " level\n");
    }
}
