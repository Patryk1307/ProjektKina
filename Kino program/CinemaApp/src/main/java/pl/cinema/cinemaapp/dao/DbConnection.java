package pl.cinema.cinemaapp.dao;


import java.sql.*;

/**
 * Klasa zwracajaca polaczenie do bazy danych
 */
public class DbConnection {
    private final static String DBURL = "jdbc:mysql://localhost:3306/cinema";
    private final static String DBUSER = "root";
    private final static String DBPASS = "password123";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection(){
        try {
            Class.forName(DBDRIVER).newInstance();
            return DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
