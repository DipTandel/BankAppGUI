package coe528.project;

/**
 *
 * @author Dip Tandel
 */
public class BankAccount {
    //Instance Variables
    private double balance;
    private String level;

    //Constructor
    public BankAccount(double balance) {
        this.balance = balance;
        this.level = determineLevel();
    }

    //level should update based on balance
    protected String determineLevel() {
        if (balance < 10000) {
            return "Silver";
        } 
        else if (balance < 20000) {
            return "Gold";
        } 
        else {
            return "Platinum";
        }
    }
    
    //returns the charge to apply (positive number) based on level
    protected double getCharge() {
        if (determineLevel().equals("Silver")) {
            return 20.0;
        } else if (determineLevel().equals("Gold")) {
            return 10.0;
        } else {
            return 0;
        }
    }
    
    //setters and getters
    protected double getBalance() {
        return balance;
    }
    protected void setBalance(double x) {
        balance = balance + x;
    }
}
