package com.domain;

import com.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public boolean authenticate(String username, String password) {
        String query = "SELECT password FROM service WHERE username = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword); // Note: In real-world applications, use hashed passwords
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
