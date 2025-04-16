package com.student.result.dao;

import com.student.result.model.Student;
import com.student.result.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String INSERT_STUDENT = "INSERT INTO students (roll_number, first_name, last_name, date_of_birth, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?";
    private static final String UPDATE_STUDENT = "UPDATE students SET roll_number = ?, first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone = ? WHERE student_id = ?";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE student_id = ?";

    public void addStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, student.getRollNumber());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, Date.valueOf(student.getDateOfBirth()));
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhone());
            
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setStudentId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_STUDENTS)) {
            
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setRollNumber(resultSet.getString("roll_number"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                
                students.add(student);
            }
        }
        
        return students;
    }

    public Student getStudentById(int studentId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            
            statement.setInt(1, studentId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getInt("student_id"));
                    student.setRollNumber(resultSet.getString("roll_number"));
                    student.setFirstName(resultSet.getString("first_name"));
                    student.setLastName(resultSet.getString("last_name"));
                    student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                    student.setEmail(resultSet.getString("email"));
                    student.setPhone(resultSet.getString("phone"));
                    
                    return student;
                }
            }
        }
        
        return null;
    }

    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
            
            statement.setString(1, student.getRollNumber());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, Date.valueOf(student.getDateOfBirth()));
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhone());
            statement.setInt(7, student.getStudentId());
            
            statement.executeUpdate();
        }
    }

    public void deleteStudent(int studentId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            
            statement.setInt(1, studentId);
            statement.executeUpdate();
        }
    }
} 