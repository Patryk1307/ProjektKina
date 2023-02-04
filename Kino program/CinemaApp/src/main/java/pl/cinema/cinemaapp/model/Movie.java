package pl.cinema.cinemaapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private int id;
    private String name;
    private String director;
    private String cinemaHall;
    private int freeSites;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;

    public Movie() {
    }

    public Movie(int id, String name, String director, String cinemaHall, int freeSites, LocalDateTime startTime, LocalDateTime endTime, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.cinemaHall = cinemaHall;
        this.freeSites = freeSites;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getFreeSites() {
        return freeSites;
    }

    public void setFreeSites(int freeSites) {
        this.freeSites = freeSites;
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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(name, movie.name) && Objects.equals(director, movie.director) && Objects.equals(cinemaHall, movie.cinemaHall) && Objects.equals(startTime, movie.startTime) && Objects.equals(endTime, movie.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, director, cinemaHall, startTime, endTime, ticketPrice);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", cinemaHall='" + cinemaHall + '\'' +
                ", start=" + startTime +
                ", end=" + endTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
