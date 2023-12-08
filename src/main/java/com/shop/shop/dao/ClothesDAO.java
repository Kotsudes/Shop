package com.shop.shop.dao;

import com.shop.shop.database.DatabaseConnector;
import com.shop.shop.shopclass.Clothes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothesDAO {

    
    public static Clothes get(int id) {
        final String query = "SELECT * FROM clothes NATURAL JOIN product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Clothes(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem"),
                            resultSet.getInt("size")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public static ObservableList<Clothes> getAll() {
        final String query = "SELECT * FROM clothes NATURAL JOIN product";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                ObservableList<Clothes> Clothes = FXCollections.observableArrayList();
                if (resultSet.next()) {
                    Clothes.add(new Clothes(
                            resultSet.getInt("idProduct"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("nbItem"),
                            resultSet.getInt("size")
                    ));
                }
                return Clothes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public static void save(Clothes clothes) {
        final String query = "INSERT INTO product (name, price, nbItem) VALUES (?, ?, ?)";
        final String queryClothes = "INSERT INTO clothes (idProduct, size) VALUES (LAST_INSERT_ID(), ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statementClothes = connection.prepareStatement(queryClothes)) {
            statement.setString(1, clothes.getName());
            statement.setDouble(2, clothes.getPrice());
            statement.setInt(3, clothes.getNbItems());
            statement.executeUpdate();

            statementClothes.setInt(1, clothes.getSize());
            statementClothes.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void update(Clothes clothes) {
        final String queryProduct = "UPDATE product SET name = ?, price = ?, nbItem = ? WHERE idProduct = ?";
        final String queryClothes = "UPDATE clothes SET size = ? WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct);
             PreparedStatement statementClothes = connection.prepareStatement(queryClothes)) {
            statementProduct.setString(1, clothes.getName());
            statementProduct.setDouble(2, clothes.getPrice());
            statementProduct.setInt(3, clothes.getNbItems());
            statementProduct.setInt(4, clothes.getId());
            statementProduct.executeUpdate();

            statementClothes.setInt(1, clothes.getSize());
            statementClothes.setInt(2, clothes.getId());
            statementClothes.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void delete(Clothes clothes) {
        final String queryProduct = "DELETE FROM product WHERE idProduct = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statementProduct = connection.prepareStatement(queryProduct)) {

            statementProduct.setInt(1, clothes.getId());
            statementProduct.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}