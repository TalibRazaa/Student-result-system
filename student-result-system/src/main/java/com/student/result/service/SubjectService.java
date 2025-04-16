package com.student.result.service;

import com.student.result.dao.SubjectDAO;
import com.student.result.model.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    private final SubjectDAO subjectDAO;

    public SubjectService() {
        this.subjectDAO = new SubjectDAO();
    }

    public void addSubject(Subject subject) throws SQLException {
        validateSubject(subject);
        subjectDAO.addSubject(subject);
    }

    public List<Subject> getAllSubjects() throws SQLException {
        return subjectDAO.getAllSubjects();
    }

    public Subject getSubjectById(int subjectId) throws SQLException {
        return subjectDAO.getSubjectById(subjectId);
    }

    public void updateSubject(Subject subject) throws SQLException {
        validateSubject(subject);
        if (subject.getSubjectId() <= 0) {
            throw new IllegalArgumentException("Invalid subject ID");
        }
        subjectDAO.updateSubject(subject);
    }

    public void deleteSubject(int subjectId) throws SQLException {
        if (subjectId <= 0) {
            throw new IllegalArgumentException("Invalid subject ID");
        }
        subjectDAO.deleteSubject(subjectId);
    }

    private void validateSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        if (subject.getSubjectCode() == null || subject.getSubjectCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Subject code is required");
        }
        if (subject.getSubjectName() == null || subject.getSubjectName().trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name is required");
        }
        if (subject.getCredits() <= 0) {
            throw new IllegalArgumentException("Credits must be greater than 0");
        }
    }
} 