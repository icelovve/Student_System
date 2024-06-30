package StudentSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class StudentSystem {

    public static class Student implements Serializable {
        private String studentId;
        private String name;
        private int age;
        private double gpa;

        public Student() {
            studentId = "";
            name = "";
            age = 0;
            gpa = 0.0;
        }

        public Student(String studentId, String name, int age, double gpa) {
            this.studentId = studentId;
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }

        public String getDataStudent() {
            return "Student ID: " + studentId + "\nName: " + name + "\nAge: " + age + "\nGPA: " + gpa;
        }
    }

    public static class StudentManager {
        private List<Student> students;
        private static final String FILE_NAME = "students.dat";

        public StudentManager() {
            students = new ArrayList<>();
            loadStudents();
        }

        public void addStudent(String studentId, String name, int age, double gpa) {
            if (name != null && studentId != null && !studentId.trim().isEmpty() && !name.trim().isEmpty()) {
                Student newStudent = new Student(studentId, name, age, gpa);
                students.add(newStudent);
                saveStudents();
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Error: Student ID and Name cannot be null or empty.");
            }
        }

        public void removeStudent(String studentId) {
            if (studentId != null && !studentId.trim().isEmpty()) {
                Iterator<Student> iterator = students.iterator();
                boolean found = false;
                while (iterator.hasNext()) {
                    Student student = iterator.next();
                    if (student.getStudentId().equals(studentId)) {
                        iterator.remove();
                        found = true;
                        saveStudents();
                        System.out.println("Student with ID: " + studentId + " has been removed.");
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Error: Student with ID: " + studentId + " not found.");
                }
            } else {
                System.out.println("Error: Student ID cannot be null or empty.");
            }
        }

        public void findStudentById(String studentId) {
            if (studentId != null && !studentId.trim().isEmpty()) {
                for (Student student : students) {
                    if (student.getStudentId().equals(studentId)) {
                        System.out.println("\n---------- Student Found -----------");
                        System.out.println(student.getDataStudent());
                        System.out.println("-----------------------------------");
                        return;
                    }
                }
                System.out.println("Error: Student with ID: " + studentId + " not found.");
            } else {
                System.out.println("Error: Student ID cannot be null or empty.");
            }
        }

        public void displayTotalStudent() {
            if (students.isEmpty()) {
                System.out.println("\nNo student available.");
            } else {
                System.out.println("\n---------- Students -----------");
                for (Student student : students) {
                    System.out.println(student.getDataStudent());
                    System.out.println("----------------------------");
                }
            }
        }

        private void saveStudents() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                oos.writeObject(students);
            } catch (IOException e) {
                System.out.println("Error saving students: " + e.getMessage());
            }
        }

        private void loadStudents() {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                    students = (List<Student>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error loading students: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();

        int choice;
        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String newStudentId = keyboard.nextLine();
                    System.out.print("Enter Student name: ");
                    String newName = keyboard.nextLine();
                    System.out.print("Enter Student Age: ");
                    int newAge = keyboard.nextInt();
                    System.out.print("Enter Student GPA: ");
                    double newGpa = keyboard.nextDouble();
                    keyboard.nextLine();

                    studentManager.addStudent(newStudentId, newName, newAge, newGpa);
                    break;
                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    String removeId = keyboard.nextLine();
                    studentManager.removeStudent(removeId);
                    break;
                case 3:
                    System.out.print("Enter Student ID to find: ");
                    String findId = keyboard.nextLine();
                    studentManager.findStudentById(findId);
                    break;
                case 4:
                    studentManager.displayTotalStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        keyboard.close();
    }
}
