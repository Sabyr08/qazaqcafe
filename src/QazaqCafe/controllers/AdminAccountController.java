package QazaqCafe.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminShowSeats;

    @FXML
    private Button exetButton;

    @FXML
    private Button adminChangePassword;

    @FXML
    private Label adminName;

    @FXML
    void initialize() {
        exetButton.setOnAction(event -> {
            exetButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../scenes/loginSignIn.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        setAdminDate("Abzal");
    }

    @FXML
    public void setAdminDate(String name) {
        adminName.setText(name);
    }
}
