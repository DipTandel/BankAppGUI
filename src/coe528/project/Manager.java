package coe528.project;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

/**
 *
 * @author Dip Tandel
 */
public class Manager extends User {
    //Constructor
    public Manager() {
        super("admin","admin","manager");
    }
    
    @Override
    protected boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Login Successful\n");
            return true;
        }
        else {
            System.out.println("Login Failed\n");
            return false;
        }
    }
    
    @Override
    protected void logout() {
        System.out.println("You have been successfully logged out\n");
    }
    
    protected void addCustomer(String username, String password) {
        File cusFile = new File(username + ".txt"); //make a new customer file
        if (!cusFile.exists()) {
            try {
                FileWriter writer = new FileWriter(cusFile);
                writer.write("Username:\n" + username + "\n");
                writer.write("Password:\n" + password + "\n");
                writer.write("$Balance:\n100.0\n");
                writer.write("Level:\nSilver\n");
                writer.flush();
                writer.close();
                System.out.println("Successfully created user: " + username + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file.\n");
            }
        }
        else {
            System.out.println("A user with this username already exists.\n");
        }
        try { 
            Scanner scanner = new Scanner(cusFile);
            String user = scanner.nextLine();
            String pass = scanner.nextLine();
            scanner.close();
            Customer cus = new Customer(user, pass, 100.0);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file.\n");
        }
    }
    
    protected void deleteCustomer(String username) {
        File file = new File(username + ".txt");
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName() + "\n");
        }
        else {
            System.out.println("Failed to delete the file.\n");
        }
    }
}
