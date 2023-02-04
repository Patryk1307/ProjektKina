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
import pl.cinema.cinemaapp.dao.MovieDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa obslugujaca wyswietlanie filmow w widoku admina
 */
public class MovieViewController implements Initializable {

    private MovieDAO movieDAO;

    public MovieViewController() {
        this.movieDAO = new MovieDAO();
    }

    @FXML
    TableView movieTable;
@FXML
    Button backButton;


    public void backToMain() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMainView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn nameColumn = new TableColumn("Nazwa");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn directorColumn = new TableColumn<>("Reżyser");
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        TableColumn hallColumn = new TableColumn<>("Nazwa sali");
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("cinemaHall"));
        TableColumn freeSitesColumn = new TableColumn<>("Ilość wolnych miejsc");
        freeSitesColumn.setCellValueFactory(new PropertyValueFactory<>("freeSites"));
        TableColumn startTimeColumn = new TableColumn<>("Data rozpoczęcia");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        TableColumn endTimeColumn = new TableColumn<>("Data zakończenia");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        TableColumn ticketPriceColumn = new TableColumn<>("Cena biletu");
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        movieTable.getColumns().addAll(nameColumn, directorColumn, hallColumn, freeSitesColumn, startTimeColumn, endTimeColumn, ticketPriceColumn);
        movieTable.getItems().addAll(movieDAO.findAll());
    }
}
