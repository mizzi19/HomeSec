package HomeSec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventsUI extends JPanel {
    private ApplicationLogic logic;

    public EventsUI(ApplicationLogic logic) {
        this.logic = logic;

        // Components for the events UI
        JButton addEventButton = new JButton("Add Event");
        JButton viewEventsButton = new JButton("View Events");

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the panel
        add(addEventButton, gbc);
        add(viewEventsButton, gbc);

        // Button action listeners
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEventDialog();
            }
        });

        viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEventsView();
            }
        });
    }

    // Method to show the add event dialog
    private void showAddEventDialog() {
        String eventName = JOptionPane.showInputDialog(this, "Enter event name:");

        if (eventName != null && !eventName.isEmpty()) {
            logic.createEvent(eventName);
            JOptionPane.showMessageDialog(this, "Event added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to show the events view
    private void showEventsView() {
        List<ApplicationLogic.Event> events = logic.getEvents();

        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No events available.", "No Events", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFrame eventsFrame = new JFrame("Events");
        eventsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> eventsListModel = new DefaultListModel<>();
        for (ApplicationLogic.Event event : events) {
            eventsListModel.addElement(event.getName());
        }

        JList<String> eventsList = new JList<>(eventsListModel);
        eventsFrame.getContentPane().add(new JScrollPane(eventsList));

        eventsFrame.pack();
        eventsFrame.setVisible(true);
    }
}
