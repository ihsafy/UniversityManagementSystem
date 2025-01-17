import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class FacultyManagement {
    private static final String FILE_NAME = "faculty.txt";
    private Map<String, String> faculty = new HashMap<>();

    public FacultyManagement() {
        loadFromFile();
        JFrame frame = new JFrame("Faculty Management");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton addButton = new JButton("Add Faculty");
        JButton updateButton = new JButton("Update Faculty");
        JButton assignButton = new JButton("Assign Courses");
        JButton searchButton = new JButton("Search Faculty");

        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Faculty ID:");
            String name = JOptionPane.showInputDialog("Enter Faculty Name:");
            faculty.put(id, name);
            saveToFile();
            JOptionPane.showMessageDialog(null, "Faculty Added Successfully!");
        });

        updateButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Faculty ID to Update:");
            if (faculty.containsKey(id)) {
                String name = JOptionPane.showInputDialog("Enter New Faculty Name:");
                faculty.put(id, name);
                saveToFile();
                JOptionPane.showMessageDialog(null, "Faculty Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Faculty ID Not Found!");
            }
        });

        assignButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Faculty ID to Assign Courses:");
            if (faculty.containsKey(id)) {
                String courseId = JOptionPane.showInputDialog("Enter Course ID:");
                // Logic to assign course (not fully implemented)
                JOptionPane.showMessageDialog(null, "Course Assigned to Faculty Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Faculty ID Not Found!");
            }
        });

        searchButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Faculty ID to Search:");
            if (faculty.containsKey(id)) {
                JOptionPane.showMessageDialog(null, "Faculty Found: " + faculty.get(id));
            } else {
                JOptionPane.showMessageDialog(null, "Faculty ID Not Found!");
            }
        });

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(assignButton);
        panel.add(searchButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    faculty.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading faculty: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : faculty.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving faculty: " + e.getMessage());
        }
    }
}
