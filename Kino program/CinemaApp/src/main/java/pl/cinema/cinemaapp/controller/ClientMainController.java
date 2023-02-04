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
 * Klasa realizujaca logike panelu glownego klienta
 */
public class ClientMainController implements Initializable {

    @FXML
    Button buyTicketButton, showTicket;
    @FXML
    Label loginText;

    @FXML
    public void switchToBuyTicket() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BuyTicketView.fxml"));
        Stage stage = (Stage) buyTicketButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @FXML
    public void switchToYourTicket() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/YourTicketsView.fxml"));
        Stage stage = (Stage) showTicket.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginText.setText(loginText.getText()+" "+ App.getUser().getUsername());
    }
}
