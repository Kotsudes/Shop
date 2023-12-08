package com.shop.shop.dao;

import com.shop.shop.database.DatabaseConnector;
import com.shop.shop.shopclass.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoesDAO  {
   
    public static Shoes get(int id) {
        final String query = "SELECT * FROM Shoes NATURAL JOIN product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Shoes(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem"),
                            resultSet.getInt("shoeSize")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public static ObservableList<Shoes> getAll() {
        final String query = "SELECT * FROM Shoes NATURAL JOIN product";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                ObservableList<Shoes> Shoes = FXCollections.observableArrayList();
                if (resultSet.next()) {
                    Shoes.add(new Shoes(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem"),
                            resultSet.getInt("shoeSize")
                    ));
                }
                return Shoes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public static void save(Shoes Shoes) {
        final String query = "INSERT INTO product (name, price, nbItem) VALUES (?, ?, ?)";
        final String queryShoes = "INSERT INTO Shoes (idProduct, shoeSize) VALUES (LAST_INSERT_ID(), ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statementShoes = connection.prepareStatement(queryShoes)) {
            statement.setString(1, Shoes.getName());
            statement.setDouble(2, Shoes.getPrice());
            statement.setInt(3, Shoes.getNbItems());
            statement.executeUpdate();

            statementShoes.setInt(1, Shoes.getShoeSize());
            statementShoes.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static void update(Shoes Shoes) {
        final String queryProduct = "UPDATE product SET name = ?, price = ?, nbItem = ? WHERE idProduct = ?";
        final String queryShoes = "UPDATE Shoes SET shoeSize = ? WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct);
             PreparedStatement statementShoes = connection.prepareStatement(queryShoes)) {
            statementProduct.setString(1, Shoes.getName());
            statementProduct.setDouble(2, Shoes.getPrice());
            statementProduct.setInt(3, Shoes.getNbItems());
            statementProduct.setInt(4, Shoes.getId());
            statementProduct.executeUpdate();

            statementShoes.setInt(1, Shoes.getShoeSize());
            statementShoes.setInt(2, Shoes.getId());
            statementShoes.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static void delete(Shoes Shoes) {
        final String queryProduct = "DELETE FROM product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct)) {

            statementProduct.setInt(1, Shoes.getId());
            statementProduct.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}