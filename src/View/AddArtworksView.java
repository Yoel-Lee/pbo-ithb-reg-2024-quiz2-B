package View;

import javax.swing.*;

import Controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddArtworksView {
    File photoFile;
   
    private Integer userId; // get user id 

    public AddArtworksView(Integer userId) {
        this.userId = userId; 
        showView();
    }

    public void showView(){
        JFrame frame = new JFrame("Add Artwork");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); 

        
        JLabel titleLabel = new JLabel("Title : "); 
        titleLabel.setBounds(50, 50, 100, 50);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        formPanel.add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(150, 60, 200, 30);
        formPanel.add(titleField);


        JLabel descriptionLabel = new JLabel("Description : "); 
        descriptionLabel.setBounds(50, 100, 100, 50);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(descriptionLabel);

        JTextField descriptionField = new JTextField();
        descriptionField.setBounds(150, 110, 200, 30);
        formPanel.add(descriptionField);

        
        JLabel photoLabel = new JLabel("PHOTO");
        photoLabel.setBounds(50, 150, 100, 50);
        photoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(photoLabel);

        JButton photoButton = new JButton("Upload");
        photoButton.setBounds(150, 150, 100, 40);
        formPanel.add(photoButton);

        
        photoButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {

                    photoFile = fileChooser.getSelectedFile();

                }

            }

        });




        
        JButton addArtworkButton = new JButton("Add Artwork");
        addArtworkButton.setBounds(150, 200, 150, 40);
        formPanel.add(addArtworkButton);

        addArtworkButton.addActionListener(e -> {
            if(Controller.checkInput(titleField,descriptionField,photoFile)){
                String title= titleField.getText();
                String description= descriptionLabel.getText();
                
                Controller.AddArtworksController(title,description,photoFile, userId);

                new ArtworksView(userId);
            }
        });









        frame.add(formPanel, BorderLayout.CENTER); 
        frame.setVisible(true); 
    }
}
