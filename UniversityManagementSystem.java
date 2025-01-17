import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UniversityManagementSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("University Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton studentButton = new JButton("Student Management");
        JButton facultyButton = new JButton("Faculty Management");
        JButton courseButton = new JButton("Course Management");
        JButton enrollmentButton = new JButton("Enrollment System");

        studentButton.addActionListener(e -> new StudentManagement());
        facultyButton.addActionListener(e -> new FacultyManagement());
        courseButton.addActionListener(e -> new CourseManagement());
        enrollmentButton.addActionListener(e -> new EnrollmentSystem());

        panel.add(studentButton);
        panel.add(facultyButton);
        panel.add(courseButton);
        panel.add(enrollmentButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
