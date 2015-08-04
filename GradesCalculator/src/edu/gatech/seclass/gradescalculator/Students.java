package edu.gatech.seclass.gradescalculator;

import java.io.*;
import java.util.HashSet;

/**
 * Created by ammilkov on 7/5/15.
 */
public class Students {

    private HashSet<Student> studentsList;

    public HashSet<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(HashSet<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public Students(String studentsDbPath){
        try {
            this.studentsList = GradesCalculatorHelper.buildStudents(GradesCalculatorHelper.initWorkbook(studentsDbPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
