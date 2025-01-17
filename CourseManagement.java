import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class CourseManagement {
    private static final String FILE_NAME = "courses.txt";
    private Map<String, String> courses = new HashMap<>();

    public CourseManagement() {
        loadFromFile();
        JFrame frame = new JFrame("Course Management");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Course");
        JButton updateButton = new JButton("Update Course");
        JButton deleteButton = new JButton("Delete Course");
        JButton searchButton = new JButton("Search Course");
        JButton displayButton = new JButton("Display All Courses");

        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Course ID:");
            String name = JOptionPane.showInputDialog("Enter Course Name:");
            courses.put(id, name);
            saveToFile();
            JOptionPane.showMessageDialog(null, "Course Added Successfully!");
        });

        updateButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Course ID to Update:");
            if (courses.containsKey(id)) {
                String name = JOptionPane.showInputDialog("Enter New Course Name:");
                courses.put(id, name);
                saveToFile();
                JOptionPane.showMessageDialog(null, "Course Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Course ID Not Found!");
            }
        });

        deleteButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Course ID to Delete:");
            if (courses.remove(id) != null) {
                saveToFile();
                JOptionPane.showMessageDialog(null, "Course Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Course ID Not Found!");
            }
        });

        searchButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Course ID to Search:");
            if (courses.containsKey(id)) {
                JOptionPane.showMessageDialog(null, "Course Found: " + courses.get(id));
            } else {
                JOptionPane.showMessageDialog(null, "Course ID Not Found!");
            }
        });

        displayButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("All Courses:\n");
            courses.forEach((id, name) -> sb.append("ID: ").append(id).append(", Name: ").append(name).append("\n"));
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        panel.add(displayButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    courses.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : courses.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }
}
