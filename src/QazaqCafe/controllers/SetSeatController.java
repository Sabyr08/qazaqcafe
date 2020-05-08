package QazaqCafe.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SetSeatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button setSeatButton;

    @FXML
    private RadioButton setSeat1RB;

    @FXML
    private ToggleGroup seatsrb;

    @FXML
    private RadioButton setSeat2RB;

    @FXML
    private RadioButton setSeat3RB;

    @FXML
    private RadioButton setSeat4RB;

    @FXML
    private RadioButton setSeat5RB;

    @FXML
    private TextArea setSeatText;

    @FXML
    private Button canсelButton;

    @FXML
    void initialize() {
        canсelButton.setOnAction(event -> {
            canсelButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../scenes/mainFX.fxml"));

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
}
