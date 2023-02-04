package pl.cinema.cinemaapp.dao;

import pl.cinema.cinemaapp.model.User;

import java.sql.*;

/**
 * Klasa realizujaca zapytania do bazy po encje User
 */
public class UserDAO {

 private DbConnection dbConnection;

    public UserDAO() {
        this.dbConnection = new DbConnection();
    }

    /**
     * Metoda dodajaca uzytkownika do bazy
     * @param username
     * @param password
     * @param role
     */
    public void add(String username, String password, String role){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Metoda pobierająca użytkownika z bazy po username
     * @param username
     * @return User
     */
    public User findByUsername(String username){
        try {
        Connection connection = dbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where username = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        //Na bazie mamy ustawiona kolumne username na unikalna wiec mozemy skorzystac z takiego zapisu
        User user = null;
        while (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setRole(resultSet.getString("ROLE"));
        }

        statement.close();
        connection.close();
        return user;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

}
