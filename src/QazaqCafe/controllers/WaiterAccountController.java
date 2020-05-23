package QazaqCafe.controllers;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import QazaqCafe.classes.Waiter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WaiterAccountController extends LoginSignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Pane Uimg;


    @FXML
    void initialize() {
        try {
            waiterData();
        } catch (SQLException | IOException | ClassNotFoundException e) {
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

    private void waiterData() throws SQLException, IOException, ClassNotFoundException {
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

        printWriter.println("waiter");

        Waiter waiter = (Waiter)objectInputStream.readObject();

        setWaiterData(waiter);
    }

    @FXML
    public void setWaiterData(Waiter waiter) throws MalformedURLException {
        waiterId.setText(String.valueOf(waiter.getId()));
        waiterName.setText(waiter.getName());
        waiterSname.setText(waiter.getSurname());
        waiterAge.setText(String.valueOf(waiter.getAge()));
        waiterLogin.setText(waiter.getLogin());
        waiterPosition.setText(waiter.getPosition());

        File input = new File("D:/IITU/JAVA/session/src/QazaqCafe/media/userimg/" + waiter.getImg());
        String localUrl = input.toURI().toURL().toString();

        Uimg.getChildren().clear();
        Image image = new Image(localUrl);
        ImageView img = new ImageView();
        img.setImage(image);
        Uimg.getChildren().add(img);
    }
}



