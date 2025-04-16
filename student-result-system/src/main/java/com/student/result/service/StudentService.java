package com.student.result.service;

import com.student.result.dao.StudentDAO;
import com.student.result.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public void addStudent(Student student) throws SQLException {
        validateStudent(student);
        studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int studentId) throws SQLException {
        return studentDAO.getStudentById(studentId);
    }

    public void updateStudent(Student student) throws SQLException {
        validateStudent(student);
        if (student.getStudentId() <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) throws SQLException {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }
        studentDAO.deleteStudent(studentId);
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (student.getRollNumber() == null || student.getRollNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Roll number is required");
        }
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (student.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
    }
} 