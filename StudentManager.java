import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.txt";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadStudentsFromFile();
    }

    // Load existing data from file
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = Student.fromString(line);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("No existing data found.");
        }
    }

    // Save all students back to file
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // CRUD Operations

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("✅ Student added successfully.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            System.out.printf("ID: %s | Name: %s | Age: %d | Course: %s\n",
                s.getId(), s.getName(), s.getAge(), s.getCourse());
        }
    }

    public void updateStudent(String id, String name, int age, String course) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setName(name);
                s.setAge(age);
                s.setCourse(course);
                saveStudentsToFile();
                System.out.println("✅ Student updated.");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    public void deleteStudent(String id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId().equals(id)) {
                iterator.remove();
                saveStudentsToFile();
                System.out.println("✅ Student deleted.");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }
}
