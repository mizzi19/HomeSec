package HomeSec;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskUI extends JPanel {
    private ApplicationLogic logic;

    public TaskUI(ApplicationLogic logic) {
        this.logic = logic;

        // Components for the task UI
        JButton addTaskButton = new JButton("Add Task");
        JButton viewTasksButton = new JButton("View Tasks");
        JButton backToMenuButton = new JButton("Back to Menu");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(150, 40);
        addTaskButton.setPreferredSize(buttonSize);
        viewTasksButton.setPreferredSize(buttonSize);
       
        // Set background color of back to menu button to a red hue
        backToMenuButton.setPreferredSize(buttonSize);
        backToMenuButton.setBackground(new Color(255, 100, 100)); // Red hue

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        add(addTaskButton, gbc);
        add(viewTasksButton, gbc);
        add(backToMenuButton, gbc);

        // Button action listeners
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddTaskDialog();
            }
        });

        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTasksView();
            }
        });

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToMenuUI();
            }
        });
    }

    // Method to show the add task dialog
    private void showAddTaskDialog() {
        String taskName = JOptionPane.showInputDialog(this, "Enter task name:");
        String taskDescription = JOptionPane.showInputDialog(this, "Enter task description:");

        if (taskName != null && !taskName.isEmpty()) {
            logic.createTask(taskName, taskDescription);
            JOptionPane.showMessageDialog(this, "Task added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Save tasks after adding
            logic.saveTasksToFile();
        }
    }

    // Method to show the tasks view
    private void showTasksView() {
        List<ApplicationLogic.Task> tasks = logic.getTasks();

        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tasks available.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFrame tasksFrame = new JFrame("Tasks");
        tasksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> tasksListModel = new DefaultListModel<>();
        for (ApplicationLogic.Task task : tasks) {
            tasksListModel.addElement(task.getName() + " - " + task.getDescription());
        }

        JList<String> tasksList = new JList<>(tasksListModel);
        JScrollPane scrollPane = new JScrollPane(tasksList);
        tasksFrame.getContentPane().add(scrollPane);
        
        // Center the tasks window on the screen
        tasksFrame.setLocationRelativeTo(null);
        
        tasksFrame.pack();
        tasksFrame.setVisible(true);
    }

    // Method to navigate back to MenuUI
    private void navigateToMenuUI() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MenuUI(logic)); // Create a new instance of MenuUI
        frame.pack();
        frame.revalidate();
    }

    // Save tasks using serialization
    private void saveTasks() {
        TaskStorage.saveTasks(logic.getTasks(), "tasks.dat");
    }
}
