package HomeSec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI extends JPanel {
    private ApplicationLogic logic;

    public MenuUI(ApplicationLogic logic) {
        this.logic = logic;

        // Components for the menu UI
        JButton tasksButton = new JButton("Tasks");
        JButton eventsButton = new JButton("Events");
        JButton exitButton = new JButton("Exit");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(150, 40);
        tasksButton.setPreferredSize(buttonSize);
        eventsButton.setPreferredSize(buttonSize);
 
        
                // Set background color of back to menu button to a red hue
        exitButton.setPreferredSize(buttonSize);
        exitButton.setBackground(new Color(255, 100, 100)); // Red hue

 

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        add(tasksButton, gbc);
        add(eventsButton, gbc);
        add(exitButton, gbc);

        // Button action listeners
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToTaskUI();
            }
        });

        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToEventsUI();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
    }

    private void navigateToTaskUI() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new TaskUI(logic));
        frame.pack();
        frame.revalidate();
    }

    private void navigateToEventsUI() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new EventsUI(logic));
        frame.pack();
        frame.revalidate();
    }

    // Method to handle exiting the application
    private void exitApplication() {
        // Save tasks and perform other cleanup
        logic.saveTasksToFile(); // Save tasks before exiting
        System.exit(0); // Close the application
    }
}
