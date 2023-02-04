package pl.cinema.cinemaapp.service;

import pl.cinema.cinemaapp.App;
import pl.cinema.cinemaapp.dao.MovieDAO;
import pl.cinema.cinemaapp.dao.TicketDAO;
import pl.cinema.cinemaapp.model.Movie;

import java.time.LocalDateTime;


public class TicketService {

    private TicketDAO ticketDAO;
    private MovieDAO movieDAO;
    public TicketService() {
        this.ticketDAO = new TicketDAO();
        this.movieDAO = new MovieDAO();
    }

    /**
     * Metoda realizujaca caly proces zakupu biletu od sprawdzenia czy ilosc miejsc na sali > ilosci kupowanych biletow po wywolanie metody zapisujacej w bazie i aktualizacje ilosci miejsc na sali
     * @param movie
     * @param tickets
     * @param totalPrice
     * @param userId
     */
    public void buyTicket(Movie movie, Integer tickets, Double totalPrice, Integer userId) throws Exception {
        if(movie.getFreeSites() < tickets){
            throw new Exception("Niewystarczająca liczba miejsc");
        }else{
            movieDAO.updateSites(movie.getId(), movie.getFreeSites() - tickets);
            ticketDAO.add(movie.getName(), movie.getCinemaHall(), tickets, movie.getStartTime(), movie.getEndTime(), totalPrice, userId);
        }

    }
}
