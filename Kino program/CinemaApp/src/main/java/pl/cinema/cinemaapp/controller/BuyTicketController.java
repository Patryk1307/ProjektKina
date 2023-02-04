package pl.cinema.cinemaapp.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.App;
import pl.cinema.cinemaapp.dao.MovieDAO;
import pl.cinema.cinemaapp.dao.TicketDAO;
import pl.cinema.cinemaapp.model.CinemaHall;
import pl.cinema.cinemaapp.model.Movie;
import pl.cinema.cinemaapp.service.TicketService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Klasa realizujaca logike panelu zakupu biletu
 */
public class BuyTicketController implements Initializable {

    private MovieDAO movieDAO;
    private TicketService ticketService;
    public BuyTicketController() {
        this.movieDAO = new MovieDAO();
        this.ticketService = new TicketService();
    }

    @FXML
    TableView movieTable;
@FXML
    Button backButton, buyTicketButton;
    @FXML
    TextField tickets;
    @FXML
    ComboBox<Movie> movieList;
    @FXML
    Label errorText;
    public void buy(){
        try {
            Movie movie = movieList.getValue();
            if (Objects.nonNull(movie) && !tickets.getText().isBlank()) {
                ticketService.buyTicket(movie, Integer.valueOf(tickets.getText()), movie.getTicketPrice() * Integer.valueOf(tickets.getText()), App.getUser().getId());
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/YourTicketsView.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(new Scene(root, 800, 800));


            } else {
                errorText.setText("Musisz uzupełnić wszystkie pola!");
            }
        }catch (Exception e){
            errorText.setText(e.getMessage());
        }
    }



    public void backToMain() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientMainView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setTextAlignment(TextAlignment.CENTER);

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


        tickets.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tickets.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        List<Movie> movies = movieDAO.findAll();
        movieList.getItems().addAll(movies);
}}
