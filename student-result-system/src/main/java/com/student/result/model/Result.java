package com.student.result.model;

public class Result {
    private int resultId;
    private Student student;
    private Subject subject;
    private double marksObtained;
    private String grade;
    private int semester;
    private String academicYear;

    public Result() {}

    public Result(Student student, Subject subject, double marksObtained, 
                 String grade, int semester, String academicYear) {
        this.student = student;
        this.subject = subject;
        this.marksObtained = marksObtained;
        this.grade = grade;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    // Getters and Setters
    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", student=" + student +
                ", subject=" + subject +
                ", marksObtained=" + marksObtained +
                ", grade='" + grade + '\'' +
                ", semester=" + semester +
                ", academicYear='" + academicYear + '\'' +
                '}';
    }
} 