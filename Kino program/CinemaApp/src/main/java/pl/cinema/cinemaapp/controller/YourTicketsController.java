package pl.cinema.cinemaapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.App;
import pl.cinema.cinemaapp.dao.MovieDAO;
import pl.cinema.cinemaapp.dao.TicketDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa obslugujaca wyswietlanie filmow w widoku admina
 */
public class YourTicketsController implements Initializable {

    private TicketDAO ticketDAO;

    public YourTicketsController() {
        this.ticketDAO = new TicketDAO();
    }

    @FXML
    TableView movieTable;
@FXML
    Button backButton;


    public void backToMain() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientMainView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn movieName = new TableColumn("Nazwa filmu");
        movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        TableColumn hallColumn = new TableColumn<>("Nazwa sali");
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("cinemaHall"));
        TableColumn ticketsColumn = new TableColumn<>("Ilość biletów");
        ticketsColumn.setCellValueFactory(new PropertyValueFactory<>("ticketNumber"));
        TableColumn startTimeColumn = new TableColumn<>("Data rozpoczęcia");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        TableColumn endTimeColumn = new TableColumn<>("Data zakończenia");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        TableColumn totalSumColumn = new TableColumn<>("Wartość zamówienia");
        totalSumColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        movieTable.getColumns().addAll(movieName, hallColumn, ticketsColumn, startTimeColumn, endTimeColumn, totalSumColumn);
        movieTable.getItems().addAll(ticketDAO.findAllByUserId(App.getUser().getId()));
    }
}
