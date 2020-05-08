package QazaqCafe.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import QazaqCafe.classes.Admin;
import QazaqCafe.classes.Waiter;
import QazaqCafe.configs.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button canсelButton;

    @FXML
    void initialize() {

        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login or password is empty");
        });

        canсelButton.setOnAction(event -> {
            setNewWindow("../scenes/mainFX.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Admin admin = new Admin();

        admin.setLogin(loginText);
        admin.setPassword(loginPassword);
        ResultSet resultAd = dbHandler.getAdmin(admin);

        Waiter waiter = new Waiter();

        waiter.setLogin(loginText);
        waiter.setPassword(loginPassword);
        ResultSet result = dbHandler.getWaiter(waiter);

        int counterWaiter = 0;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counterWaiter++;
        }

        int counterAd = 0;
        while (true) {
            try {
                if (!resultAd.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counterAd++;
        }

        if (counterWaiter >= 1) {
            setNewWindow("../scenes/waiterAccount.fxml");
        }
        else if (counterAd >= 1) setNewWindow("../scenes/adminAccount.fxml");
        else System.out.println("error!");
    }

    public void setNewWindow(String window) {
        canсelButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
