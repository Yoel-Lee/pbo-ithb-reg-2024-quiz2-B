package View;

import javax.swing.*;
import Controller.LoginController;
import java.awt.*;

public class LoginView {
    public LoginView() {
        showLogin();
    }

    public void showLogin() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setLayout(null);
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = (screenSize.width - loginFrame.getWidth()) / 2;
        int y = (screenSize.height - loginFrame.getHeight()) / 2;
        loginFrame.setLocation(x, y);

        JLabel title = new JLabel("Login");
        title.setBounds(150, 20, 100, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginFrame.add(title);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 25);
        loginFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 80, 200, 25);
        loginFrame.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 25);
        loginFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 200, 25);
        loginFrame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 160, 90, 30);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Email atau Password tidak boleh kosong ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                LoginController controller = new LoginController();
                Integer userId = controller.loginUser(email, password);

                if (userId != null) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.dispose();
                    new ArtworksView(userId); 
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Email atau Password salah", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 160, 90, 30);
        loginFrame.add(backButton);

        backButton.addActionListener(e -> {
            loginFrame.dispose();
            new MainMenu(); // Kembali ke mainmenu
        });

        loginFrame.setVisible(true);
    }
}
