package pl.cinema.cinemaapp.dao;

import pl.cinema.cinemaapp.model.CinemaHall;
import pl.cinema.cinemaapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa realizujaca zapytania do bazy po encje Cinema_halls
 */
public class CinemaHallDAO {

    private DbConnection dbConnection;

    public CinemaHallDAO(){
        this.dbConnection = new DbConnection();
    }

    /**
     * Metoda pobierająca wszystkie sale kinowe z bazy
     * @return List of CinemaHall
     */
    public List<CinemaHall> findAll(){
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from cinema_halls");
            List<CinemaHall> cinemaHalls = new ArrayList<>();
            while (resultSet.next()){
                CinemaHall cinemaHall = new CinemaHall();
                cinemaHall.setId(resultSet.getInt("ID"));
                cinemaHall.setName(resultSet.getString("NAME"));
                cinemaHall.setSites(resultSet.getInt("SITES"));
                cinemaHalls.add(cinemaHall);
            }

            statement.close();
            connection.close();

            return cinemaHalls;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda dodajaca nowa sale kinowa
     * @param name
     * @param sites
     */
    public void add(String name, Integer sites){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cinema_halls (NAME, SITES) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setInt(2, sites);
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
