package pl.cinema.cinemaapp.controller;

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
 * Klasa realizujaca logike okna rejestracji
 */
public class RegistrationController implements Initializable{

   private UserService userService;
@FXML
private TextField login;
@FXML
private PasswordField password, rePassword;
@FXML
private Label errorText;

@FXML
private Button registrationButton, backButton;
    public RegistrationController() {
        this.userService = new UserService();
    }


    /**
     * Metoda obslugujaca logowanie uzytkownika
     */
    @FXML
    protected void registration() {
        try {
            if(!login.getText().isBlank() && !password.getText().isBlank() && !rePassword.getText().isBlank()){
                userService.registration(login.getText(), password.getText(), rePassword.getText());
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(new Scene(root, 800, 800));
            }else{
                errorText.setText("Musisz uzupelnić wszystkie pola!");
            }

} catch (Exception e) {
            errorText.setText(e.getMessage());
            e.getStackTrace();
        }

    }

    public void switchToLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();

        stage.setScene(new Scene(root, 800, 800));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setTextAlignment(TextAlignment.CENTER);
    }
}