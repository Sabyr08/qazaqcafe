package QazaqCafe.controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginSignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private Label text_error;

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
        try {
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

            printWriter.println(loginText);
            printWriter.println(loginPassword);

            String correct = bufferedReader.readLine();

            if (correct.equals("admin")) {
                printWriter.println("connected");
                setNewWindow("../scenes/adminAccount.fxml");
            }
            else if (correct.equals("waiter")) {
                printWriter.println("connected");
                setNewWindow("../scenes/waiterAccount.fxml");
            }
            else if (correct.equals("error"))
                setError("Ошибка!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setError(String error) {
        text_error.setText(error);
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
