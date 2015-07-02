package com.example.ettie.teachersroom;

/**
 * Created by Ettie on 6/28/2015.
 */
public class Teacher {
    private int id;
    private String name;
    private String email;
    private int grade;
    private String subjectType;

    public Teacher() {
    }

    public Teacher(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Teacher(String name, String email, int grade, String subjectType) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.subjectType = subjectType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String toString() {
        return "\nName: " + name +
               "\nEmail: " + email +
               "\nGrade: "  + grade +
               "\nSubject Type: " + subjectType;
    }
}

