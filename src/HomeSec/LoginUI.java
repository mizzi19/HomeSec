package HomeSec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JPanel {
    private ApplicationLogic logic;

    public LoginUI(ApplicationLogic logic) {
        this.logic = logic;

        // Components for the login UI
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Add components to the panel
        add(usernameLabel, gbc);
        add(usernameField, gbc);
        add(passwordLabel, gbc);
        add(passwordField, gbc);
        add(loginButton, gbc);

        // Button action listener to handle login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // Check if username and password are correct
                if (username.equals("John") && new String(password).equals("12345")) {
                    // If correct, navigate to the MenuUI
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LoginUI.this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MenuUI(logic));
                    frame.pack();
                    frame.revalidate();
                } else {
                    // If incorrect, show error message
                    JOptionPane.showMessageDialog(LoginUI.this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Clear password field
                passwordField.setText("");
            }
        });
    }
}
