package HomeSec;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationLogic logic = new ApplicationLogic();

                LoginUI loginUI = new LoginUI(logic);
                MenuUI menuUI = new MenuUI(logic);
                TaskUI taskUI = new TaskUI(logic);

                JFrame frame = new JFrame("Beecham Family App");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Login", loginUI);
                tabbedPane.addTab("Menu", menuUI);
                tabbedPane.addTab("Tasks", taskUI);

                // Initially, only the Login tab is visible
                tabbedPane.setEnabledAt(1, false); // Disable Menu tab
                tabbedPane.setEnabledAt(2, false); // Disable Tasks tab

                frame.getContentPane().add(tabbedPane);
                frame.pack();

                // Set a larger size for the frame
                frame.setSize(400, 300);

                // Center the frame on the screen
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }
        });
    }
}
