package View;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.DBConnection;

public class ArtworksView {

    private Integer userId;

    public ArtworksView(Integer userId) {
        this.userId = userId;
        showArtworks();
    }

    public void showArtworks() {
        JFrame frame = new JFrame("Artwork Gallery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton addArtworkButton = new JButton("Add Artwork");
        topPanel.add(addArtworkButton);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 3, 5, 5));  
        gridPanel.setPreferredSize(new Dimension(250, 150)); // 

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connect();

        if (connection != null) {
            try {
                String query = """
                    SELECT a.title, a.description, a.image_path, u.name AS artist_name 
                    FROM artworks a
                    JOIN users u ON a.user_id = u.id
                    WHERE a.user_id = ? 
                """;
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String artistName = resultSet.getString("artist_name");
                    String imagePath = resultSet.getString("image_path");

                    JPanel artworkPanel = new JPanel();
                    artworkPanel.setLayout(new BorderLayout());
                    artworkPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    artworkPanel.setPreferredSize(new Dimension(150, 2000)); 

                    
                    JLabel titleLabel = new JLabel("Title: " + title, JLabel.CENTER);
                    titleLabel.setFont(new Font("Arial", Font.PLAIN, 10)); 
                    JLabel artistLabel = new JLabel("Artist: " + artistName, JLabel.CENTER);
                    artistLabel.setFont(new Font("Arial", Font.PLAIN, 8)); 

                    // Deskripsi dengan font lebih kecil dan menyesuaikan panjang
                    JLabel descriptionLabel = new JLabel("<html><body style='width:180px'>" + description + "</body></html>", JLabel.CENTER);
                    descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 8)); 

                    
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);  
                    JLabel imageLabel = new JLabel(new ImageIcon(image));


                    artworkPanel.add(titleLabel, BorderLayout.NORTH);
                    artworkPanel.add(imageLabel, BorderLayout.CENTER);
                    artworkPanel.add(artistLabel, BorderLayout.SOUTH);
                    artworkPanel.add(descriptionLabel, BorderLayout.SOUTH);

                    gridPanel.add(artworkPanel);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error fetching artworks!", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                dbConnection.closeConnection(connection);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        addArtworkButton.addActionListener(e -> {
           
            new AddArtworksView(userId);  
        });

        frame.setVisible(true);
    }
}
