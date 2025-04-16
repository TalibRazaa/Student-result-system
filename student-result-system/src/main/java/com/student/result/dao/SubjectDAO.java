package com.student.result.dao;

import com.student.result.model.Subject;
import com.student.result.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private static final String INSERT_SUBJECT = "INSERT INTO subjects (subject_code, subject_name, credits) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subjects";
    private static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM subjects WHERE subject_id = ?";
    private static final String UPDATE_SUBJECT = "UPDATE subjects SET subject_code = ?, subject_name = ?, credits = ? WHERE subject_id = ?";
    private static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE subject_id = ?";

    public void addSubject(Subject subject) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, subject.getSubjectCode());
            statement.setString(2, subject.getSubjectName());
            statement.setInt(3, subject.getCredits());
            
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setSubjectId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SUBJECTS)) {
            
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getInt("subject_id"));
                subject.setSubjectCode(resultSet.getString("subject_code"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                subject.setCredits(resultSet.getInt("credits"));
                
                subjects.add(subject);
            }
        }
        
        return subjects;
    }

    public Subject getSubjectById(int subjectId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECT_BY_ID)) {
            
            statement.setInt(1, subjectId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(resultSet.getInt("subject_id"));
                    subject.setSubjectCode(resultSet.getString("subject_code"));
                    subject.setSubjectName(resultSet.getString("subject_name"));
                    subject.setCredits(resultSet.getInt("credits"));
                    
                    return subject;
                }
            }
        }
        
        return null;
    }

    public void updateSubject(Subject subject) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT)) {
            
            statement.setString(1, subject.getSubjectCode());
            statement.setString(2, subject.getSubjectName());
            statement.setInt(3, subject.getCredits());
            statement.setInt(4, subject.getSubjectId());
            
            statement.executeUpdate();
        }
    }

    public void deleteSubject(int subjectId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT)) {
            
            statement.setInt(1, subjectId);
            statement.executeUpdate();
        }
    }
} 