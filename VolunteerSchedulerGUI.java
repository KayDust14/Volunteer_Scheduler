package volunteer_scheduler_project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class VolunteerSchedulerGUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private VolunteerScheduler scheduler = new VolunteerScheduler();
    private JTextArea outputArea;

    public VolunteerSchedulerGUI() {
        setTitle("Volunteer Scheduler");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Add Volunteer", createAddPanel());
        tabs.add("Schedule Overview", createSchedulePanel());

        add(tabs);
        setVisible(true);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(15);
        JTextField ageField = new JTextField(5);
        JComboBox<String> skillBox = new JComboBox<>(new String[]{"beer", "ball runner"});
        JComboBox<String> availabilityBox = new JComboBox<>(new String[]{"Morning", "Afternoon"});

        JCheckBox fridayBox = new JCheckBox("Friday");
        JCheckBox saturdayBox = new JCheckBox("Saturday");
        JCheckBox sundayBox = new JCheckBox("Sunday");

        JButton addButton = new JButton("Add Volunteer");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1; panel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Skill:"), gbc);
        gbc.gridx = 1; panel.add(skillBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Availability:"), gbc);
        gbc.gridx = 1; panel.add(availabilityBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Days Available:"), gbc);
        gbc.gridx = 1;
        JPanel daysPanel = new JPanel();
        daysPanel.add(fridayBox);
        daysPanel.add(saturdayBox);
        daysPanel.add(sundayBox);
        panel.add(daysPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String skill = (String) skillBox.getSelectedItem();
                String availability = (String) availabilityBox.getSelectedItem();

                List<String> days = new ArrayList<>();
                if (fridayBox.isSelected()) days.add("Friday");
                if (saturdayBox.isSelected()) days.add("Saturday");
                if (sundayBox.isSelected()) days.add("Sunday");

                if (name.isEmpty() || days.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields and select at least one day.");
                    return;
                }

                Volunteer v = new Volunteer(name, age, skill, availability, days);
                scheduler.addVolunteer(v);
                JOptionPane.showMessageDialog(this, "Volunteer added!");
                nameField.setText("");
                ageField.setText("");
                fridayBox.setSelected(false);
                saturdayBox.setSelected(false);
                sundayBox.setSelected(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check the form.");
            }
        });

        return panel;
    }

    private JPanel createSchedulePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        JButton generateButton = new JButton("Generate Schedule");
        JButton exportButton = new JButton("Export to File");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);

        generateButton.addActionListener(e -> {
            scheduler.assignVolunteers();
            outputArea.setText(scheduler.getFullScheduleText());
        });

        exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter out = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    out.print(outputArea.getText());
                    JOptionPane.showMessageDialog(this, "Schedule exported successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
                }
            }
        });

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VolunteerSchedulerGUI::new);
    }
}
/*
 * Sprint 1: Created the graphical user interface (GUI) and basic functionality for adding volunteers.
 * Sprint 2: Implemented volunteer scheduling functionality and refined GUI design.
 * Sprint 3: Added features for generating and exporting the volunteer schedule.
 * Sprint 4: Testing and making it look more user-friendly in output - the emojis are my favorite part.
 */
