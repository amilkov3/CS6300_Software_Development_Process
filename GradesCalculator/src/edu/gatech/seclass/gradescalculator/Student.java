package edu.gatech.seclass.gradescalculator;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by ammilkov on 7/5/15.
 */
public class Student {

    private String name;
    private String gtid;
    private int attendance;
    private String team;
    private HashMap<String,Integer> assignmentGrades;
    private HashMap<String,Integer> projectGrades;
    private HashMap<String,Integer> teamGrades;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public HashMap<String,Integer> getAssignmentGrades() {
        return assignmentGrades;
    }

    public void setAssignmentGrades(HashMap<String,Integer> assignmentGrades) {
        this.assignmentGrades = assignmentGrades;
    }

    public HashMap<String,Integer> getProjectGrades() {
        return projectGrades;
    }

    public void setProjectGrades(HashMap<String,Integer> projectGrades) {
        this.projectGrades = projectGrades;
    }

    public HashMap<String, Integer> getTeamGrades() {
        return teamGrades;
    }

    public void setTeamGrades(HashMap<String, Integer> teamGrades) {
        this.teamGrades = teamGrades;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student(){

    }

    public Student(String name, String gtid, Course course){
        boolean found = false;
        for (Student student : course.getStudents()){
            if (gtid.equals(student.getGtid()) && name.equals(student.getName())){
                found = true;
                this.name = name;
                this.gtid = gtid;
                this.team = student.getTeam();
                this.attendance = student.getAttendance();
                this.projectGrades = student.getProjectGrades();
                this.assignmentGrades = student.getAssignmentGrades();
                this.teamGrades = student.getTeamGrades();
            }
        }
        if (!found){
            HashSet<Student> students = course.getStudents();
            students.add(this);
            course.setStudents(students);
        }
    }

    public Student(String name, String gtid){
        boolean found = false;
        for (Student student : GradesCalculatorHelper.getStudentsList()){
            if (gtid.equals(student.getGtid()) && name.equals(student.getName())){
                found = true;
                this.name = name;
                this.gtid = gtid;
                this.team = student.getTeam();
                this.attendance = student.getAttendance();
                this.projectGrades = student.getProjectGrades();
                this.assignmentGrades = student.getAssignmentGrades();
                this.teamGrades = student.getTeamGrades();
            }
        }
        if (!found){
            HashSet<Student> students = GradesCalculatorHelper.getStudentsList();
            students.add(this);
            GradesCalculatorHelper.setStudentsList(students);
        }
    }
}
