package edu.gatech.seclass.gradescalculator;

import java.io.IOException;

/**
 * Created by ammilkov on 7/6/15.
 */
public class Run {

    static final String STUDENTS_DB = "DB/GradesDatabase6300-students.xlsx";
    static final String GRADES_DB = "DB/GradesDatabase6300-grades.xlsx";

    public static void main(String[] args) throws IOException{
        Students students = new Students(STUDENTS_DB);
        Grades grades = new Grades(GRADES_DB);
        Course course = new Course(students, grades);
        System.out.println(course.getNumStudents());
        System.out.println(course.getNumProjects());
        System.out.println(course.getNumAssignments());
        System.out.println(course.getStudents().size());
        Student rastus = course.getStudentByName("Rastus Kight");
        System.out.println(rastus.getName());
        System.out.println(rastus.getTeam());
        System.out.println(rastus.getGtid());
        System.out.println(rastus.getAttendance());
        System.out.println(rastus.getAssignmentGrades());
        System.out.println(course.getAverageAssignmentsGrade(rastus));
    }
}
