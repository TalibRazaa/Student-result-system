package com.student.result.service;

import com.student.result.dao.ResultDAO;
import com.student.result.dao.StudentDAO;
import com.student.result.dao.SubjectDAO;
import com.student.result.model.Result;
import com.student.result.model.Student;
import com.student.result.model.Subject;

import java.sql.SQLException;
import java.util.List;

public class ResultService {
    private final ResultDAO resultDAO;
    private final StudentDAO studentDAO;
    private final SubjectDAO subjectDAO;

    public ResultService() {
        this.resultDAO = new ResultDAO();
        this.studentDAO = new StudentDAO();
        this.subjectDAO = new SubjectDAO();
    }

    public void addResult(Result result) throws SQLException {
        validateResult(result);
        validateStudentAndSubject(result);
        resultDAO.addResult(result);
    }

    public List<Result> getAllResults() throws SQLException {
        return resultDAO.getAllResults();
    }

    public Result getResultById(int resultId) throws SQLException {
        return resultDAO.getResultById(resultId);
    }

    public List<Result> getResultsByStudent(int studentId) throws SQLException {
        return resultDAO.getResultsByStudent(studentId);
    }

    public void updateResult(Result result) throws SQLException {
        validateResult(result);
        validateStudentAndSubject(result);
        if (result.getResultId() <= 0) {
            throw new IllegalArgumentException("Invalid result ID");
        }
        resultDAO.updateResult(result);
    }

    public void deleteResult(int resultId) throws SQLException {
        if (resultId <= 0) {
            throw new IllegalArgumentException("Invalid result ID");
        }
        resultDAO.deleteResult(resultId);
    }

    private void validateResult(Result result) {
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null");
        }
        if (result.getStudent() == null) {
            throw new IllegalArgumentException("Student is required");
        }
        if (result.getSubject() == null) {
            throw new IllegalArgumentException("Subject is required");
        }
        if (result.getMarksObtained() < 0 || result.getMarksObtained() > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        if (result.getGrade() == null || result.getGrade().trim().isEmpty()) {
            throw new IllegalArgumentException("Grade is required");
        }
        if (result.getSemester() <= 0) {
            throw new IllegalArgumentException("Semester must be greater than 0");
        }
        if (result.getAcademicYear() == null || result.getAcademicYear().trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year is required");
        }
    }

    private void validateStudentAndSubject(Result result) throws SQLException {
        Student student = studentDAO.getStudentById(result.getStudent().getStudentId());
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        Subject subject = subjectDAO.getSubjectById(result.getSubject().getSubjectId());
        if (subject == null) {
            throw new IllegalArgumentException("Subject not found");
        }
    }
} 