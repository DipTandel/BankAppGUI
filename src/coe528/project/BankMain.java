package coe528.project;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Dip Tandel
 */
public class BankMain extends Application {
    
    protected Stage window;
    protected Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9;
    
    /*was going to try to instantiate a generic user and then determine if its manager or customer based on login but i cant 
    instantiate an abstract class so this is what i have now, need to create generic user named john so the app can run*/
    Manager manager = new Manager();
    Customer customer = new Customer("John", "John", 100);
    
    Label balanceLabel = new Label(customer.toString()); //needed here so it can update before sceneswitch

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setResizable(false);

        //Scene 1 (Login Page)
        Text scenetitle = new Text("Welcome to BankApp");
        scenetitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password:");
        PasswordField pwBox = new PasswordField();
        Button btn = new Button("Login");
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(scenetitle, userName, userTextField, pw, pwBox, btn);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 300, 275);
        //login button Action
        btn.setOnAction(e -> {
            if (manager.login(userTextField.getText(), pwBox.getText()) == true) {
                window.setScene(scene2); //go to manager scene
                userTextField.clear(); pwBox.clear();
            }
            else if (customer.login(userTextField.getText(), pwBox.getText()) == true) {
                window.setScene(scene3); //go to customer screen
                userTextField.clear(); pwBox.clear();
            }
        });

        
        //Scene 2 (Manager Screen)
        Text managerTitle = new Text("Hello Manager");
        managerTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Button addCustomerButton = new Button("Add Customer");
        Button deleteCustomerButton = new Button("Delete Customer");
        Button logoutButton = new Button("Logout");
        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(managerTitle, addCustomerButton, deleteCustomerButton, logoutButton);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2, 300, 275);
        //logout button Action
        logoutButton.setOnAction(e -> {
            window.setScene(scene1);
            manager.logout();
        });
        //addCustomer button Action
        addCustomerButton.setOnAction(e -> {
            window.setScene(scene4);
        });
        //deleteCustomer button Action
        deleteCustomerButton.setOnAction(e -> {
            window.setScene(scene5);
        });
        
        //Scene 4 (addCustomer)
        Text addCustomerTitle = new Text("Add Customer");
        addCustomerTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label addUserName = new Label("User Name:");
        TextField addUserTextField = new TextField();
        Label addPw = new Label("Password:");
        PasswordField addPwBox = new PasswordField();
        Button addCustomerConfirmButton = new Button("Confirm");
        Button backToManagerButton = new Button("Back");
        VBox layout4 = new VBox(10);
        layout4.getChildren().addAll(addCustomerTitle, addUserName, addUserTextField, addPw, addPwBox, addCustomerConfirmButton, backToManagerButton);
        layout4.setAlignment(Pos.CENTER);
        scene4 = new Scene(layout4, 300, 275);
        //back button Action
        backToManagerButton.setOnAction(e -> window.setScene(scene2));
        //confirm button Action
        addCustomerConfirmButton.setOnAction(e -> {
            manager.addCustomer(addUserTextField.getText(), addPwBox.getText());
            window.setScene(scene2);
            addUserTextField.clear(); addPwBox.clear();
        });
        
        //Scene 5 (deleteCustomer)
        Text deleteCustomerTitle = new Text("Delete Customer");
        deleteCustomerTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label deleteUserName = new Label("User Name:");
        TextField deleteUserTextField = new TextField();
        Button deleteCustomerConfirmButton = new Button("Confirm");
        Button backToManagerButton2 = new Button("Back");
        VBox layout5 = new VBox(10);
        layout5.getChildren().addAll(deleteCustomerTitle, deleteUserName, deleteUserTextField, deleteCustomerConfirmButton, backToManagerButton2);
        layout5.setAlignment(Pos.CENTER);
        scene5 = new Scene(layout5, 300, 275);
        //back button Action
        backToManagerButton2.setOnAction(e -> window.setScene(scene2));
        //confirm button Action
        deleteCustomerConfirmButton.setOnAction(e -> {
            manager.deleteCustomer(deleteUserTextField.getText());
            window.setScene(scene2);
            deleteUserTextField.clear();
        });

        
        //Scene 3 (Customer Screen)
        Text customerTitle = new Text("Hello " + customer.getName());
        customerTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button checkBalanceButton = new Button("Check Balance");
        Button makePurchaseButton = new Button("Make Purchase");
        Button logoutButton2 = new Button("Logout");
        VBox layout3 = new VBox(10);
        layout3.getChildren().addAll(customerTitle, depositButton, withdrawButton, checkBalanceButton, makePurchaseButton, logoutButton2);
        layout3.setAlignment(Pos.CENTER);
        scene3 = new Scene(layout3, 300, 275);
        //logout button Action
        logoutButton2.setOnAction(e -> {
            window.setScene(scene1);
            customer.logout();
        });
        //deposit button Action
        depositButton.setOnAction(e -> {
            window.setScene(scene6);
        });
        withdrawButton.setOnAction(e -> {
            window.setScene(scene7);
        });
        checkBalanceButton.setOnAction(e -> {
            balanceLabel.setText(customer.toString()); //update the label before going to that scene
            window.setScene(scene8);
        });
        makePurchaseButton.setOnAction(e -> {
            window.setScene(scene9);
        });
        
        //Scene 6 (deposit)
        Text depositTitle = new Text("Deposit");
        depositTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label depositAmount = new Label("Amount:");
        TextField depositAmountField = new TextField();
        Button depositConfirmButton = new Button("Confirm");
        Button backToCustomerButton = new Button("Back");
        VBox layout6 = new VBox(10);
        layout6.getChildren().addAll(depositTitle, depositAmount, depositAmountField, depositConfirmButton, backToCustomerButton);
        layout6.setAlignment(Pos.CENTER);
        scene6 = new Scene(layout6, 300, 275);
        //back button Action
        backToCustomerButton.setOnAction(e -> window.setScene(scene3));
        //confirm button Action
        depositConfirmButton.setOnAction(e -> {
            String text = depositAmountField.getText();
            double amount; //must convert field to double
            try {
                amount = Double.parseDouble(text);
                customer.deposit(amount);
                window.setScene(scene3);
                depositAmountField.clear();
            } catch (NumberFormatException ex) {
                System.out.println("Not a number\n");
            }
        });

        //Scene 7 (withdraw)
        Text withdrawTitle = new Text("Withdraw");
        withdrawTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label withdrawAmount = new Label("Amount:");
        TextField withdrawAmountField = new TextField();
        Button withdrawConfirmButton = new Button("Confirm");
        Button backToCustomerButton2 = new Button("Back");
        VBox layout7 = new VBox(10);
        layout7.getChildren().addAll(withdrawTitle, withdrawAmount, withdrawAmountField, withdrawConfirmButton, backToCustomerButton2);
        layout7.setAlignment(Pos.CENTER);
        scene7 = new Scene(layout7, 300, 275);
        //back button Action
        backToCustomerButton2.setOnAction(e -> window.setScene(scene3));
        //confirm button Action
        withdrawConfirmButton.setOnAction(e -> {
            String text = withdrawAmountField.getText();
            double amount; //must convert field to double
            try {
                amount = Double.parseDouble(text);
                customer.withdraw(amount);
                window.setScene(scene3);
                withdrawAmountField.clear();
            } catch (NumberFormatException ex) {
                System.out.println("Not a number\n");
            }
        });
        
        //Scene 8 (checkBalance)
        Text checkBalanceTitle = new Text("Check Balance");
        checkBalanceTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        balanceLabel.setText(customer.toString()); //this label has to constantly update when seen
        Button backToCustomerButton3 = new Button("Back");
        VBox layout8 = new VBox(10);
        layout8.getChildren().addAll(checkBalanceTitle, balanceLabel, backToCustomerButton3);
        layout8.setAlignment(Pos.CENTER);
        scene8 = new Scene(layout8, 300, 275);
        //back button Action
        backToCustomerButton3.setOnAction(e -> {
            window.setScene(scene3);
            balanceLabel.setText(customer.toString()); //update the label after hitting back
        });
        
        //Scene 9 (makePurchase)
        Text makePurchaseTitle = new Text("Make Purchase");
        makePurchaseTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Label vendorNameLabel = new Label("Vendor Name:");
        TextField vendorNameField = new TextField();
        Label productNameLabel = new Label("Product Name:");
        TextField productNameField = new TextField();
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        Button confirmPurchaseButton = new Button("Confirm");
        Button backToCustomerButton4 = new Button("Back");
        VBox layout9 = new VBox(10);
        layout9.getChildren().addAll(makePurchaseTitle, vendorNameLabel, vendorNameField, productNameLabel, productNameField, priceLabel, priceField, confirmPurchaseButton, backToCustomerButton4);
        layout9.setAlignment(Pos.CENTER);
        scene9 = new Scene(layout9, 300, 275);
        //back button Action
        backToCustomerButton4.setOnAction(e -> window.setScene(scene3));
        //confirm button Action
        confirmPurchaseButton.setOnAction(e -> {
            String text = priceField.getText();
            double amount;
            try {
                if (!productNameField.getText().isEmpty() && !vendorNameField.getText().isEmpty()) {
                    amount = Double.parseDouble(text);
                    customer.onlinePurchase(productNameField.getText(), vendorNameField.getText(), amount);
                    window.setScene(scene3);
                    priceField.clear();
                    //purchase is just any item the customer makes up, did not implement a working store class
                }
                else {
                    System.out.println("Field Empty\n");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Not a number\n");
            }
        });
        
        
        //Starting Scene set
        window.setScene(scene1);
        window.setTitle("BankApp");
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}