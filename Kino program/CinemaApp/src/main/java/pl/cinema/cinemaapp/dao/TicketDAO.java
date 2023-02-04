package pl.cinema.cinemaapp.dao;

import pl.cinema.cinemaapp.model.Movie;
import pl.cinema.cinemaapp.model.Ticket;
import pl.cinema.cinemaapp.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa realizujaca zapytania do bazy po encje Ticket
 */
public class TicketDAO {

    private DbConnection dbConnection;

    public TicketDAO(){
        this.dbConnection = new DbConnection();
    }

    public List<Ticket> findAllByUserId(Integer userId){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from tickets where USER_ID = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            //Na bazie mamy ustawiona kolumne username na unikalna wiec mozemy skorzystac z takiego zapisu
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()){
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("ID"));
                ticket.setMovieName(resultSet.getString("MOVIE_NAME"));
                ticket.setCinemaHall(resultSet.getString("CINEMA_HALL"));
                ticket.setTicketNumber(resultSet.getInt("TICKET_NUMBER"));
                ticket.setStartTime(resultSet.getTimestamp("START_TIME").toLocalDateTime());
                ticket.setEndTime(resultSet.getTimestamp("END_TIME").toLocalDateTime());
                ticket.setTotalPrice(resultSet.getDouble("TOTAL_PRICE"));
                tickets.add(ticket);
            }

            statement.close();
            connection.close();
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda zapisujaca bilet do bazy
     * @param name
     * @param cinemaHall
     * @param tickets
     * @param startTime
     * @param endTime
     * @param totalPrice
     * @param userId
     */
    public void add(String name,  String cinemaHall, Integer tickets, LocalDateTime startTime, LocalDateTime endTime, Double totalPrice, Integer userId){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tickets (MOVIE_NAME, CINEMA_HALL, TICKET_NUMBER, START_TIME, END_TIME, TOTAL_PRICE, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, cinemaHall);
            statement.setInt(3, tickets);
            statement.setTimestamp(4, Timestamp.valueOf(startTime));
            statement.setTimestamp(5, Timestamp.valueOf(endTime));
            statement.setDouble(6, totalPrice);
            statement.setInt(7, userId);
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
