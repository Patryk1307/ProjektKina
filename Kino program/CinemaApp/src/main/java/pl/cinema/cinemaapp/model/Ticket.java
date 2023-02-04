package pl.cinema.cinemaapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Klasa reprezentujaca encje Ticket z bazy danych
 */
public class Ticket {
    private int id;
    private String movieName;
    private String cinemaHall;
    private int ticketNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalPrice;

    private int userId;
    public Ticket() {
    }

    public Ticket(String movieName, String cinemaHall, int ticketNumber, LocalDateTime startTime, LocalDateTime endTime, double totalPrice, int userId) {
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
        this.ticketNumber = ticketNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && ticketNumber == ticket.ticketNumber && Double.compare(ticket.totalPrice, totalPrice) == 0 && userId == ticket.userId && Objects.equals(movieName, ticket.movieName) && Objects.equals(cinemaHall, ticket.cinemaHall) && Objects.equals(startTime, ticket.startTime) && Objects.equals(endTime, ticket.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieName, cinemaHall, ticketNumber, startTime, endTime, totalPrice, userId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", cinemaHall='" + cinemaHall + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }
}
