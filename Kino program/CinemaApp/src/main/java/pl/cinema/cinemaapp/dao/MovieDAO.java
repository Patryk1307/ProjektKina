package pl.cinema.cinemaapp.dao;

import pl.cinema.cinemaapp.model.Movie;
import pl.cinema.cinemaapp.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa realizujaca zapytania do bazy po encje Movie
 */
public class MovieDAO {

    private DbConnection dbConnection;

    public MovieDAO(){
        this.dbConnection = new DbConnection();
    }

    /**
     * Metoda pobierająca wszystkie seanse z bazy
     * @return List of CinemaHall
     */
    public List<Movie> findAll(){
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from movies");
            List<Movie> movies = new ArrayList<>();
            while (resultSet.next()){
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("ID"));
                movie.setName(resultSet.getString("NAME"));
                movie.setDirector(resultSet.getString("DIRECTOR"));
                movie.setCinemaHall(resultSet.getString("CINEMA_HALL"));
                movie.setFreeSites(resultSet.getInt("FREE_SITES"));
                movie.setStartTime(resultSet.getTimestamp("START_TIME").toLocalDateTime());
                movie.setEndTime(resultSet.getTimestamp("END_TIME").toLocalDateTime());
                movie.setTicketPrice(resultSet.getDouble("TICKET_PRICE"));
                movies.add(movie);
            }

            statement.close();
            connection.close();

            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSites(Integer id, Integer sites){
        try{
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE movies SET FREE_SITES = ? WHERE ID = ?");
            statement.setInt(1, sites);
            statement.setInt(2, id);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String name, String director, String cinemaHall, Integer freeSites, LocalDateTime startTime, LocalDateTime endTime, Double ticketPrice){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO movies (NAME, DIRECTOR, CINEMA_HALL, FREE_SITES, START_TIME, END_TIME, TICKET_PRICE) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, director);
            statement.setString(3, cinemaHall);
            statement.setInt(4, freeSites);
            statement.setTimestamp(5, Timestamp.valueOf(startTime));
            statement.setTimestamp(6, Timestamp.valueOf(endTime));
            statement.setDouble(7, ticketPrice);
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
