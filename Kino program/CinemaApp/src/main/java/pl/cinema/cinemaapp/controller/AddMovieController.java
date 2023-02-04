package pl.cinema.cinemaapp.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.dao.CinemaHallDAO;
import pl.cinema.cinemaapp.dao.MovieDAO;
import pl.cinema.cinemaapp.dao.ProductDAO;
import pl.cinema.cinemaapp.model.CinemaHall;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Klasa realizujaca logike dodawania produktow
 */
public class AddMovieController implements Initializable {

    private MovieDAO movieDAO;
private CinemaHallDAO cinemaHallDAO;
    public AddMovieController(){
        this.movieDAO = new MovieDAO();
        this.cinemaHallDAO = new CinemaHallDAO();
    }

    @FXML
    TextField nameField, directorField, startTimeField, endTimeField;
    @FXML
    TextField priceField;
    @FXML
    DatePicker startDateField, endDateField;
    @FXML
    Button addButton;
    @FXML
    Label errorText;
    @FXML
    Button backButton;
    @FXML
    ChoiceBox<CinemaHall> hallList;
    @FXML
    public void addHall() {

        try{
    if(!nameField.getText().isBlank() && !directorField.getText().isBlank() &&  !startTimeField.getText().isBlank() && !endTimeField.getText().isBlank() && !priceField.getText().isBlank() && Objects.nonNull(hallList.getValue())){
        LocalDate dateStart = startDateField.getValue();
        LocalTime timeStart = LocalTime.parse(startTimeField.getText());
        LocalDateTime dateTimeStart = dateStart.atTime(timeStart);

        LocalDate dateEnd = endDateField.getValue();
        LocalTime timeEnd = LocalTime.parse(endTimeField.getText());
        LocalDateTime dateTimeEnd = dateEnd.atTime(timeEnd);

        CinemaHall hall = hallList.getValue();
        movieDAO.add(nameField.getText(), directorField.getText(), hall.getName(), hall.getSites(), dateTimeStart, dateTimeEnd, Double.valueOf(priceField.getText()));

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/CinemaHallView.fxml"));
            Stage stage = (Stage) addButton.getScene().getWindow();

            stage.setScene(new Scene(root, 800, 800));
        }else{
            errorText.setText("Musisz uzupelnić wszystkie pola!");
        }
        }catch (Exception e){
            e.getStackTrace();
            errorText.setText("Wystąpił nieznany błąd!");

        }
    }

    public void backToMain() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMainView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CinemaHall> halls = cinemaHallDAO.findAll();
        hallList.getItems().addAll(halls);
        errorText.setTextAlignment(TextAlignment.CENTER);
        priceField.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
            priceField.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
    }
}
