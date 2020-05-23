package QazaqCafe.controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import QazaqCafe.classes.Admin;
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
        try {
            adminData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    private void adminData() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8000);

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        )
                );

        PrintWriter printWriter = new PrintWriter(bufferedWriter, true);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        printWriter.println("admin");

        Admin admin = (Admin)objectInputStream.readObject();

        System.out.println(admin.getLogin());

        setAdminData(admin.getLogin());
    }

    @FXML
    public void setAdminData(String name) {
        adminName.setText(name);
    }
}
