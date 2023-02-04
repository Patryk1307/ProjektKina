package pl.cinema.cinemaapp.model;

import java.util.Objects;

/**
 * Klasa reprezentujaca encje Sali kinowej z bazy danych
 */
public class CinemaHall {

    private int id;
    private String name;
    private int sites;

    public CinemaHall() {
    }

    public CinemaHall(int id, String name, int sites) {
        this.id = id;
        this.name = name;
        this.sites = sites;
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

    public int getSites() {
        return sites;
    }

    public void setSites(int sites) {
        this.sites = sites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return id == that.id && sites == that.sites && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sites);
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sites=" + sites +
                '}';
    }
}
