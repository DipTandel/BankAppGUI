package coe528.project;

/**
 *
 * @author Dip Tandel
 */
public abstract class User {
    //Instance variables
    protected String username;
    protected String password;
    protected String role;
    
    //Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    //abstract methods that must be implemented by children classes
    abstract boolean login(String username, String password);
    abstract void logout();
    
}
