package Controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import Connection.DBConnection;

public class Controller {


    public static boolean checkInput(JTextField title, JTextField description, File photoFile) {
        if (title.getText().trim().isEmpty() || description.getText().trim().isEmpty() || photoFile == null ) {
            return false;
        } else {
            return true;
        }




        
    }
 public static void AddArtworksController(String title, String description, File photoFile, Integer userID) {

        String query = "INSERT INTO artworks (title, description, image_path, user_id) VALUES (?, ?, ?, ?)";

        
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connect();

        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setString(3, photoFile.getAbsolutePath());  
                statement.setInt(4, userID);  

                // Execute dan menginsert query
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Artwork added !");
                } else {
                    System.out.println("Failed to add artwork ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection(connection);  
            }
        }



    }
}
