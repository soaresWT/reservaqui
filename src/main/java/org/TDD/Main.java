package org.TDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String JDBC_URL = "jdbc:sqlite:src/db/reserveaqui.db";

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Senha: " + resultSet.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}