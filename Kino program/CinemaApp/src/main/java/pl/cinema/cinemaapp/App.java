package pl.cinema.cinemaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.cinema.cinemaapp.model.User;

import java.io.IOException;

public class App extends Application {

    private static User user;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(App.class.getResource("/fxml/LoginView.fxml"));
        stage.setTitle("Cinema APP!");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    public static void main(String[] args) {
        launch();
    }
}