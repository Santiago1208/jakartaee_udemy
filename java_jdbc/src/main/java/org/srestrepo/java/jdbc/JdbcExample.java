package org.srestrepo.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jakarta_course";
        String username = "SANTIAGO";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM PRODUCTS")) {
            while (result.next()) {
                System.out.print(result.getInt("id"));
                System.out.print(" | ");
                System.out.print(result.getString("name"));
                System.out.print(" | ");
                System.out.print(result.getInt("price"));
                System.out.print(" | ");
                System.out.println(result.getDate("register_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
