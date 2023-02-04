package pl.cinema.cinemaapp.service;

import pl.cinema.cinemaapp.dao.UserDAO;
import pl.cinema.cinemaapp.model.User;

import java.util.Objects;

/**
 * Klasa realizujaca operacje zwiazane z klasa User
 */
public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    //Metoda sprawdzajace czy user istnieje i czy podane haslo jest wlasciwie
    public User login(String username, String password) throws Exception {
        //Pobieramy uzytkownika z bazy
        User user = userDAO.findByUsername(username);
        //Sprawdzamy czy znaleziono
        if(Objects.nonNull(user)){
            //Jezeli haslo jest prawidlowe zwracamy usera
           if(user.getPassword().equals(password)){
               return user;
           }else {
               throw new Exception("Bad password");
           }
        }else {
            throw new Exception("User not found");
        }
    }

    public void registration(String username, String password, String rePassowrd) throws Exception {
        //Pobieramy uzytkownika z bazy by sprawdzic czy login jest unikalny
        User user = userDAO.findByUsername(username);
        if(Objects.nonNull(user)){
            throw new Exception("Username not unique");
        }else{
            //Sprawdzamy czy haslo i potwierdzenie hasla sa takie same
            if(password.equals(rePassowrd)){
                userDAO.add(username, password, "USER");
            }else{
                throw new Exception("Password mismatch");
            }

        }
    }
}
