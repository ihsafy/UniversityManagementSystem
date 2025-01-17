import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EnrollmentSystem {
    private Map<String, List<String>> enrollments = new HashMap<>();

    public EnrollmentSystem() {
        JFrame frame = new JFrame("Enrollment System");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton enrollButton = new JButton("Enroll Student");
        JButton displayButton = new JButton("Display Enrollments");

        enrollButton.addActionListener(e -> {
            String studentId = JOptionPane.showInputDialog("Enter Student ID:");
            String courseId = JOptionPane.showInputDialog("Enter Course ID:");
            enrollStudent(studentId, courseId);
        });

        displayButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Enrollments:\n");
            enrollments.forEach((student, courses) -> {
                sb.append("Student ID: ").append(student).append(" - Courses: ").append(courses).append("\n");
            });
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        panel.add(enrollButton);
        panel.add(displayButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void enrollStudent(String studentId, String courseId) {
        enrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseId);
        JOptionPane.showMessageDialog(null, "Student Enrolled Successfully!");
    }
}
