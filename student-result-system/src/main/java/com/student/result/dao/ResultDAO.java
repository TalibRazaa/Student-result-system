package com.student.result.dao;

import com.student.result.model.Result;
import com.student.result.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    private static final String INSERT_RESULT = "INSERT INTO results (student_id, subject_id, marks_obtained, grade, semester, academic_year) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_RESULTS = "SELECT r.*, s.roll_number, s.first_name, s.last_name, sub.subject_code, sub.subject_name " +
            "FROM results r " +
            "JOIN students s ON r.student_id = s.student_id " +
            "JOIN subjects sub ON r.subject_id = sub.subject_id";
    private static final String SELECT_RESULT_BY_ID = "SELECT r.*, s.roll_number, s.first_name, s.last_name, sub.subject_code, sub.subject_name " +
            "FROM results r " +
            "JOIN students s ON r.student_id = s.student_id " +
            "JOIN subjects sub ON r.subject_id = sub.subject_id " +
            "WHERE r.result_id = ?";
    private static final String UPDATE_RESULT = "UPDATE results SET student_id = ?, subject_id = ?, marks_obtained = ?, grade = ?, semester = ?, academic_year = ? WHERE result_id = ?";
    private static final String DELETE_RESULT = "DELETE FROM results WHERE result_id = ?";
    private static final String SELECT_RESULTS_BY_STUDENT = "SELECT r.*, s.roll_number, s.first_name, s.last_name, sub.subject_code, sub.subject_name " +
            "FROM results r " +
            "JOIN students s ON r.student_id = s.student_id " +
            "JOIN subjects sub ON r.subject_id = sub.subject_id " +
            "WHERE r.student_id = ?";

    private final StudentDAO studentDAO;
    private final SubjectDAO subjectDAO;

    public ResultDAO() {
        this.studentDAO = new StudentDAO();
        this.subjectDAO = new SubjectDAO();
    }

    public void addResult(Result result) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RESULT, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, result.getStudent().getStudentId());
            statement.setInt(2, result.getSubject().getSubjectId());
            statement.setDouble(3, result.getMarksObtained());
            statement.setString(4, result.getGrade());
            statement.setInt(5, result.getSemester());
            statement.setString(6, result.getAcademicYear());
            
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    result.setResultId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Result> getAllResults() throws SQLException {
        List<Result> results = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_RESULTS)) {
            
            while (resultSet.next()) {
                results.add(createResultFromResultSet(resultSet));
            }
        }
        
        return results;
    }

    public Result getResultById(int resultId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_RESULT_BY_ID)) {
            
            statement.setInt(1, resultId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createResultFromResultSet(resultSet);
                }
            }
        }
        
        return null;
    }

    public List<Result> getResultsByStudent(int studentId) throws SQLException {
        List<Result> results = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_RESULTS_BY_STUDENT)) {
            
            statement.setInt(1, studentId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(createResultFromResultSet(resultSet));
                }
            }
        }
        
        return results;
    }

    public void updateResult(Result result) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RESULT)) {
            
            statement.setInt(1, result.getStudent().getStudentId());
            statement.setInt(2, result.getSubject().getSubjectId());
            statement.setDouble(3, result.getMarksObtained());
            statement.setString(4, result.getGrade());
            statement.setInt(5, result.getSemester());
            statement.setString(6, result.getAcademicYear());
            statement.setInt(7, result.getResultId());
            
            statement.executeUpdate();
        }
    }

    public void deleteResult(int resultId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RESULT)) {
            
            statement.setInt(1, resultId);
            statement.executeUpdate();
        }
    }

    private Result createResultFromResultSet(ResultSet resultSet) throws SQLException {
        Result result = new Result();
        result.setResultId(resultSet.getInt("result_id"));
        
        // Create and set student
        com.student.result.model.Student student = new com.student.result.model.Student();
        student.setStudentId(resultSet.getInt("student_id"));
        student.setRollNumber(resultSet.getString("roll_number"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        result.setStudent(student);
        
        // Create and set subject
        com.student.result.model.Subject subject = new com.student.result.model.Subject();
        subject.setSubjectId(resultSet.getInt("subject_id"));
        subject.setSubjectCode(resultSet.getString("subject_code"));
        subject.setSubjectName(resultSet.getString("subject_name"));
        result.setSubject(subject);
        
        result.setMarksObtained(resultSet.getDouble("marks_obtained"));
        result.setGrade(resultSet.getString("grade"));
        result.setSemester(resultSet.getInt("semester"));
        result.setAcademicYear(resultSet.getString("academic_year"));
        
        return result;
    }
} 