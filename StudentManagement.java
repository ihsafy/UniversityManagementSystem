import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class StudentManagement {
    private static final String FILE_NAME = "students.txt";
    private Map<String, String> students = new HashMap<>();

    public StudentManagement() {
        loadFromFile();
        JFrame frame = new JFrame("Student Management");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");

        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Student ID:");
            String name = JOptionPane.showInputDialog("Enter Student Name:");
            students.put(id, name);
            saveToFile();
            JOptionPane.showMessageDialog(null, "Student Added Successfully!");
        });

        updateButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Student ID to Update:");
            if (students.containsKey(id)) {
                String name = JOptionPane.showInputDialog("Enter New Student Name:");
                students.put(id, name);
                saveToFile();
                JOptionPane.showMessageDialog(null, "Student Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Student ID Not Found!");
            }
        });

        deleteButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Student ID to Delete:");
            if (students.remove(id) != null) {
                saveToFile();
                JOptionPane.showMessageDialog(null, "Student Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Student ID Not Found!");
            }
        });

        searchButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Enter Student ID to Search:");
            if (students.containsKey(id)) {
                JOptionPane.showMessageDialog(null, "Student Found: " + students.get(id));
            } else {
                JOptionPane.showMessageDialog(null, "Student ID Not Found!");
            }
        });

        displayButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("All Enrolled Students:\n");
            students.forEach((id, name) -> sb.append("ID: ").append(id).append(", Name: ").append(name).append("\n"));
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
                    students.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : students.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
}
