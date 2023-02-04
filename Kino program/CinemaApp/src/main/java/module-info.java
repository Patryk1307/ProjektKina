module pl.cinema.cinemaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pl.cinema.cinemaapp to javafx.fxml;
    exports pl.cinema.cinemaapp;
    exports pl.cinema.cinemaapp.controller;
    exports pl.cinema.cinemaapp.dao;
    exports pl.cinema.cinemaapp.model;
    opens pl.cinema.cinemaapp.controller to javafx.fxml;

}