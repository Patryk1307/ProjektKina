package pl.cinema.cinemaapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.App;
import pl.cinema.cinemaapp.model.User;
import pl.cinema.cinemaapp.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa realizujaca logike okna logowania
 */
public class LoginController implements Initializable {

   private UserService userService;
@FXML
private TextField login;
@FXML
private PasswordField password;
@FXML
private Label errorText;

@FXML
private Button loginButton, registerButton;
    public LoginController() {
        this.userService = new UserService();
    }


    /**
     * Metoda obslugujaca logowanie uzytkownika
     */
    @FXML
    protected void login() {
        try {
            User user = userService.login(login.getText(), password.getText());
            App.setUser(user);
            //Jezeli user to Admin wyswietlamy widok admina, jezeli user to widok usera
            if(user.getRole().equals("ADMIN")){
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMainView.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();

                stage.setScene(new Scene(root, 800, 800));
            }else{
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientMainView.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();

                stage.setScene(new Scene(root, 800, 800));
            }



        } catch (Exception e) {
            errorText.setText(e.getMessage());
            e.getStackTrace();
        }

    }

    public void switchToRegister() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegisterView.fxml"));
        Stage stage = (Stage) registerButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setTextAlignment(TextAlignment.CENTER);
    }
}