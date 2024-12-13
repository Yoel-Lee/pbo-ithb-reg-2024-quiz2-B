package Controller;

import Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    private DBConnection dbConnection;

    public LoginController() {
        dbConnection = new DBConnection();
    }

    public Integer loginUser(String email, String password) {
        Connection connection = dbConnection.connect();
        Integer userId = null;

        if (connection != null) {
            try {
                String query = "SELECT id FROM users WHERE email = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    userId = resultSet.getInt("id"); // to get user id dari matched pass and email column
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection(connection);
            }
        }
        return userId; 
    }
}
