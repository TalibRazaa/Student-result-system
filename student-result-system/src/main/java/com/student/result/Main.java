package com.student.result;

import com.student.result.model.Result;
import com.student.result.model.Student;
import com.student.result.model.Subject;
import com.student.result.service.ResultService;
import com.student.result.service.StudentService;
import com.student.result.service.SubjectService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final SubjectService subjectService = new SubjectService();
    private static final ResultService resultService = new ResultService();

    public static void main(String[] args) {
        try {
            boolean running = true;
            while (running) {
                System.out.println("\nStudent Result Management System");
                System.out.println("1. Manage Students");
                System.out.println("2. Manage Subjects");
                System.out.println("3. Manage Results");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageSubjects();
                        break;
                    case 3:
                        manageResults();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Thank you for using the system!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void manageStudents() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\nStudent Management");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.println("\nAdd New Student");
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Student student = new Student(rollNumber, firstName, lastName, dateOfBirth, email, phone);
        studentService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() throws SQLException {
        List<Student> students = studentService.getAllStudents();
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("\nEnter Student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter new Roll Number: ");
        student.setRollNumber(scanner.nextLine());
        System.out.print("Enter new First Name: ");
        student.setFirstName(scanner.nextLine());
        System.out.print("Enter new Last Name: ");
        student.setLastName(scanner.nextLine());
        System.out.print("Enter new Date of Birth (YYYY-MM-DD): ");
        student.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
        System.out.print("Enter new Email: ");
        student.setEmail(scanner.nextLine());
        System.out.print("Enter new Phone: ");
        student.setPhone(scanner.nextLine());

        studentService.updateStudent(student);
        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("\nEnter Student ID to delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        studentService.deleteStudent(studentId);
        System.out.println("Student deleted successfully!");
    }

    private static void manageSubjects() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\nSubject Management");
            System.out.println("1. Add Subject");
            System.out.println("2. View All Subjects");
            System.out.println("3. Update Subject");
            System.out.println("4. Delete Subject");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSubject();
                    break;
                case 2:
                    viewAllSubjects();
                    break;
                case 3:
                    updateSubject();
                    break;
                case 4:
                    deleteSubject();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addSubject() throws SQLException {
        System.out.println("\nAdd New Subject");
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine();
        System.out.print("Enter Subject Name: ");
        String subjectName = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();

        Subject subject = new Subject(subjectCode, subjectName, credits);
        subjectService.addSubject(subject);
        System.out.println("Subject added successfully!");
    }

    private static void viewAllSubjects() throws SQLException {
        List<Subject> subjects = subjectService.getAllSubjects();
        System.out.println("\nAll Subjects:");
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }

    private static void updateSubject() throws SQLException {
        System.out.print("\nEnter Subject ID to update: ");
        int subjectId = scanner.nextInt();
        scanner.nextLine();

        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject == null) {
            System.out.println("Subject not found!");
            return;
        }

        System.out.print("Enter new Subject Code: ");
        subject.setSubjectCode(scanner.nextLine());
        System.out.print("Enter new Subject Name: ");
        subject.setSubjectName(scanner.nextLine());
        System.out.print("Enter new Credits: ");
        subject.setCredits(scanner.nextInt());
        scanner.nextLine();

        subjectService.updateSubject(subject);
        System.out.println("Subject updated successfully!");
    }

    private static void deleteSubject() throws SQLException {
        System.out.print("\nEnter Subject ID to delete: ");
        int subjectId = scanner.nextInt();
        scanner.nextLine();

        subjectService.deleteSubject(subjectId);
        System.out.println("Subject deleted successfully!");
    }

    private static void manageResults() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\nResult Management");
            System.out.println("1. Add Result");
            System.out.println("2. View All Results");
            System.out.println("3. View Results by Student");
            System.out.println("4. Update Result");
            System.out.println("5. Delete Result");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addResult();
                    break;
                case 2:
                    viewAllResults();
                    break;
                case 3:
                    viewResultsByStudent();
                    break;
                case 4:
                    updateResult();
                    break;
                case 5:
                    deleteResult();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addResult() throws SQLException {
        System.out.println("\nAdd New Result");
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Subject ID: ");
        int subjectId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Marks Obtained: ");
        double marksObtained = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();
        System.out.print("Enter Semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Academic Year: ");
        String academicYear = scanner.nextLine();

        Student student = new Student();
        student.setStudentId(studentId);
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);

        Result result = new Result(student, subject, marksObtained, grade, semester, academicYear);
        resultService.addResult(result);
        System.out.println("Result added successfully!");
    }

    private static void viewAllResults() throws SQLException {
        List<Result> results = resultService.getAllResults();
        System.out.println("\nAll Results:");
        for (Result result : results) {
            System.out.println(result);
        }
    }

    private static void viewResultsByStudent() throws SQLException {
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        List<Result> results = resultService.getResultsByStudent(studentId);
        System.out.println("\nResults for Student ID " + studentId + ":");
        for (Result result : results) {
            System.out.println(result);
        }
    }

    private static void updateResult() throws SQLException {
        System.out.print("\nEnter Result ID to update: ");
        int resultId = scanner.nextInt();
        scanner.nextLine();

        Result result = resultService.getResultById(resultId);
        if (result == null) {
            System.out.println("Result not found!");
            return;
        }

        System.out.print("Enter new Student ID: ");
        result.getStudent().setStudentId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new Subject ID: ");
        result.getSubject().setSubjectId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new Marks Obtained: ");
        result.setMarksObtained(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Enter new Grade: ");
        result.setGrade(scanner.nextLine());
        System.out.print("Enter new Semester: ");
        result.setSemester(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new Academic Year: ");
        result.setAcademicYear(scanner.nextLine());

        resultService.updateResult(result);
        System.out.println("Result updated successfully!");
    }

    private static void deleteResult() throws SQLException {
        System.out.print("\nEnter Result ID to delete: ");
        int resultId = scanner.nextInt();
        scanner.nextLine();

        resultService.deleteResult(resultId);
        System.out.println("Result deleted successfully!");
    }
} 