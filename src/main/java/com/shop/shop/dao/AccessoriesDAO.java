package com.shop.shop.dao;

import com.shop.shop.database.DatabaseConnector;
import com.shop.shop.shopclass.Accessories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessoriesDAO{


    public static Accessories get(int id) {
        final String query = "SELECT * FROM Accessories NATURAL JOIN product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Accessories(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Accessories> getAll() {
        final String query = "SELECT * FROM Accessories NATURAL JOIN product";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                ObservableList<Accessories> Accessories = FXCollections.observableArrayList();
                if (resultSet.next()) {
                    Accessories.add(new Accessories(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem")
                    ));
                }
                return Accessories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(Accessories Accessories) {
        final String query = "INSERT INTO product (name, price, nbItem) VALUES (?, ?, ?)";
        final String queryAccessories = "INSERT INTO Accessories (idProduct) VALUES (LAST_INSERT_ID())";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statementAccessories = connection.prepareStatement(queryAccessories)) {
            statement.setString(1, Accessories.getName());
            statement.setDouble(2, Accessories.getPrice());
            statement.setInt(3, Accessories.getNbItems());
            statement.executeUpdate();

            statementAccessories.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Accessories Accessories) {
        final String queryProduct = "UPDATE product SET name = ?, price = ?, nbItem = ? WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct)) {
            statementProduct.setString(1, Accessories.getName());
            statementProduct.setDouble(2, Accessories.getPrice());
            statementProduct.setInt(3, Accessories.getNbItems());
            statementProduct.setInt(4, Accessories.getId());
            statementProduct.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Accessories Accessories) {
        final String queryProduct = "DELETE FROM product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct)) {

            statementProduct.setInt(1, Accessories.getId());
            statementProduct.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
