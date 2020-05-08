package QazaqCafe.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import QazaqCafe.classes.Waiter;
import QazaqCafe.configs.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WaiterAccountController extends LoginSignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView waiterImg;

    @FXML
    private Label waiterId;

    @FXML
    private Label waiterName;

    @FXML
    private Label waiterSname;

    @FXML
    private Label waiterAge;

    @FXML
    private Label waiterLogin;

    @FXML
    private Label waiterPosition;

    @FXML
    private Button waiterTakeSeat;

    @FXML
    private Button exetButton;

    @FXML
    private Button waiterChangePassword;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Waiter waiter = new Waiter();

        ResultSet result = dbHandler.getWaiter(waiter);

        String id = "?";
        String name = "?";
        String sname = "?";
        String age = "?";
        String login = "?";
        String position = "?";
        String img = "?";

        while (true) {
            try {
                if (!result.next()) break;

                id = String.valueOf(result.getInt("id"));
                name = result.getString("name");
                sname = result.getString("sname");
                age = String.valueOf(result.getInt("age"));
                login = result.getString("login");
                position = result.getString("position");
                img = result.getString("img");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        setWaiterDate(id, name, sname, age, login, position);

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
    }

    @FXML
    public void setWaiterDate(String id, String name, String sname, String age, String login, String position) {
        waiterId.setText(id);
        waiterName.setText(name);
        waiterSname.setText(sname);
        waiterAge.setText(age);
        waiterLogin.setText(login);
        waiterPosition.setText(position);
    }
}



