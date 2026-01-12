package dev.durovo.Market_Mate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connectItemInfoDB {


    private static final String URL = "jdbc:sqlite:itemInfo.db";

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS item_info (id integer PRIMARY KEY, name text, price INTEGER);";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }


    public void addItem(Item item) {
        String sql = "INSERT INTO item_info(name, info) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getPrice());
            pstmt.executeUpdate();
            System.out.println("Item added: " + item.getName());

        } catch (SQLException e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT name, info FROM item_info";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                itemList.add(new Item(name, price));
            }

        } catch (SQLException e) {
            System.out.println("Error getting items: " + e.getMessage());
        }
        return itemList;
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM item_info WHERE id = ?";
    }
}