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
import pl.cinema.cinemaapp.dao.ProductDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa obslugujaca wyswietlanie produktow w widoku admina
 */
public class ProductViewController implements Initializable {

    private ProductDAO productDAO;

    public ProductViewController() {
        this.productDAO = new ProductDAO();
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
        TableColumn sitesColumn = new TableColumn<>("Cena");
        sitesColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        hallTable.getColumns().addAll(nameColumn, sitesColumn);
        hallTable.getItems().addAll(productDAO.findAll());
    }
}
