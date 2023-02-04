package pl.cinema.cinemaapp.dao;

import pl.cinema.cinemaapp.model.CinemaHall;
import pl.cinema.cinemaapp.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa realizujaca zapytania do bazy po encje Product
 */
public class ProductDAO {

    private DbConnection dbConnection;

    public ProductDAO(){
        this.dbConnection = new DbConnection();
    }

    /**
     * Metoda pobierająca wszystkie produkty z bazy
     * @return List of CinemaHall
     */
    public List<Product> findAll(){
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from products");
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                products.add(product);
            }

            statement.close();
            connection.close();

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda dodajaca nowa sale kinowa
     * @param name
     * @param price
     */
    public void add(String name, Double price){
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (NAME, PRICE) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.executeUpdate();

            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
