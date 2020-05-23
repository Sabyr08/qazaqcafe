package QazaqCafe.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ResourceBundle;

        import QazaqCafe.classes.Seat;
        import QazaqCafe.configs.DatabaseHandler;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;

public class SetSeatController {

    private ObservableList<Seat> seatsData = FXCollections.observableArrayList();

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
    private TableView<Seat> table;

    @FXML
    private TableColumn<Seat, Integer> numberOfSeats;

    @FXML
    private TableColumn<Seat, Boolean> checkOfSeats;

    @FXML
    private Button canсelButton;

    @FXML
    void initialize() {
        try {
            initData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        numberOfSeats.setCellValueFactory(new PropertyValueFactory<Seat, Integer>("id"));
        checkOfSeats.setCellValueFactory(new PropertyValueFactory<Seat, Boolean>("check"));

        table.setItems(seatsData);

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

    private void initData() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < 5; i++) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            Seat seat = new Seat();

            ResultSet resSet = dbHandler.getSeat(seat);

            int id = 0;
            int check = 0;
            boolean checkSeat = false;

            check = resSet.getInt(2);
            id = resSet.getInt(1);

            if (check == 0)
                checkSeat = false;
            else if (check == 1)
                checkSeat = true;
            else
                System.out.println("ERROR!");

            seat.setNum(id);
            seat.setCheck(checkSeat);

            seatsData.add(seat);
        }
    }
}

