package pl.cinema.cinemaapp.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.dao.CinemaHallDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa realizujaca logike dodawania sali
 */
public class AddCinemaHallController implements Initializable {

    private CinemaHallDAO cinemaHallDAO;

    public AddCinemaHallController(){
        this.cinemaHallDAO = new CinemaHallDAO();
    }

    @FXML
    TextField nameField;
    @FXML
    TextField sitesField;
    @FXML
    Button addButton;
    @FXML
    Label errorText;
    @FXML
    Button backButton;
    @FXML
    public void addHall() {

        try{
    if(!nameField.getText().isBlank() && !sitesField.getText().isBlank()){
            cinemaHallDAO.add(nameField.getText(), Integer.valueOf(sitesField.getText()));

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
        errorText.setTextAlignment(TextAlignment.CENTER);
sitesField.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
            sitesField.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
    }
}
