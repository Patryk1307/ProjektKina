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
import pl.cinema.cinemaapp.dao.CinemaHallDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa obslugujaca wyswietlanie listy sal w widoku admina
 */
public class CinemaHallViewController implements Initializable {

    private CinemaHallDAO cinemaHallDAO;

    public CinemaHallViewController() {
        this.cinemaHallDAO = new CinemaHallDAO();
    }

    @FXML
    TableView hallTable;
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
        TableColumn sitesColumn = new TableColumn<>("Ilość miejsc");
        sitesColumn.setCellValueFactory(new PropertyValueFactory<>("sites"));

        hallTable.getColumns().addAll(nameColumn, sitesColumn);
        hallTable.getItems().addAll(cinemaHallDAO.findAll());
    }
}
