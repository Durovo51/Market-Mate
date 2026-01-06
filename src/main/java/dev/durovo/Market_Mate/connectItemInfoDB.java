package dev.durovo.Market_Mate;

import java.sql.*;

public class connectItemInfoDB {
    private Connection connection;

    public connectItemInfoDB() {
        String url = "jdbc:mysqlite:itemInfo.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to itemInfo database successfully");
        } catch (SQLException e) {
            System.out.println("Error connecting to itemInfo database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Connection closed successfully");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }


}
