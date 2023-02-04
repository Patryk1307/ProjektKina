package pl.cinema.cinemaapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa realizujaca logike panelu glownego admina
 */
public class AdminMainController implements Initializable {

    @FXML
    Button addMovieButton, addProductButton, showMovie, showProduct, addHallButton, showHall;
    @FXML
    Label loginText;

    @FXML
    public void switchToAddMovieView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddMovieView.fxml"));
        Stage stage = (Stage) addHallButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @FXML
    public void switchToAddProductView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddProductView.fxml"));
        Stage stage = (Stage) addProductButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @FXML
    public void switchToShowMovieView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MovieView.fxml"));
        Stage stage = (Stage) showMovie.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @FXML
    public void switchToShowProductView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductView.fxml"));
        Stage stage = (Stage) showProduct.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @FXML
    public void switchToAddHallView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddCinemaHallView.fxml"));
        Stage stage = (Stage) addHallButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    public void switchToShowHallView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CinemaHallView.fxml"));
        Stage stage = (Stage) showHall.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginText.setText(loginText.getText()+" "+ App.getUser().getUsername());
    }
}
