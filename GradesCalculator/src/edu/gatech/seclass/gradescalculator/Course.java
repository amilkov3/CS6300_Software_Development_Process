package edu.gatech.seclass.gradescalculator;

import java.util.*;
import javax.script.*;

/**
 * Created by ammilkov on 7/5/15.
 */
public class Course {

    private HashSet<Student> students;
    private ArrayList<String> assignments;
    private ArrayList<String> projects;
    private String formula;
    private Grades grades;

    public int getNumStudents() {
        return students.size();
    }

    public int getNumAssignments() {
        return assignments.size();
    }

    public int getNumProjects(){
        return projects.size();
    }

    public HashSet<Student> getStudents() {
        return students;
    }

    public void setStudents(HashSet<Student> studentsRoster) {
        this.students = studentsRoster;
    }

    public ArrayList<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<String> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }

    public void setFormula(String formula){
        this.formula = formula;
    }

    public String getFormula(){ return formula;}

    public Course(Students students, Grades grades){
        this.students = students.getStudentsList();
        this.grades = grades;
        assignments = GradesCalculatorHelper.getAssignments(grades.getWorkbook());
        projects = GradesCalculatorHelper.getProjects(grades.getWorkbook());
        GradesCalculatorHelper.setStudentAttendance(grades.getWorkbook(),this);
        GradesCalculatorHelper.setAssignmentGrades(grades.getWorkbook(), this);
        GradesCalculatorHelper.setProjectGrades(grades.getWorkbook(), this);
        GradesCalculatorHelper.setTeamGrades(grades.getWorkbook(), this);
        GradesCalculatorHelper.setStudentsList(this.students);
        if (grades.getFormula() == null){
            formula = "AT * 0.2 + AA * 0.4 + AP * 0.4";
        } else{
            formula = grades.getFormula();
        }
    }

    public Student getStudentByName(String targetName){
        for (Student student: students){
            String name = student.getName();
            if(name.equals(targetName)){
                return student;
            }
        }
        return null;
    }

    public int getAttendance(Student student){
        return student.getAttendance();
    }

    public String getTeam(Student student){
        return student.getTeam();
    }

    public String getEmail(Student student) { return student.getEmail();}

    public Student getStudentByID(String gtid){
        for(Student student: students){
            if(student.getGtid().equals(gtid)){
                return student;
            }
        }
        return new Student();
    }

    public void addAssignment(String assignment){
        assignments.add(assignment);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void addProject(String projectName){
        projects.add(projectName);
    }

    public void updateGrades(Grades grades){

    }

    public void addGradesForAssignment(String assignmentName, HashMap<Student, Integer> grades){
        for (Map.Entry<Student,Integer> grade : grades.entrySet()){
            Student student = grade.getKey();
            HashMap<String,Integer> assignmentGrades = student.getAssignmentGrades();
            assignmentGrades.put(assignmentName, grade.getValue());
            student.setAssignmentGrades(assignmentGrades);

        }
    }

    public void addGradesForProject(String projectName, HashMap<Student, Integer> grades){
        for (Map.Entry<Student,Integer> grade : grades.entrySet()){
            Student student = grade.getKey();
            HashMap<String,Integer> projectGrades = student.getProjectGrades();
            projectGrades.put(projectName, grade.getValue());
            student.setProjectGrades(projectGrades);

        }
    }

    public int getAverageAssignmentsGrade(Student student){
        int sum = 0;
        for (Map.Entry<String,Integer> assignmentGrades : student.getAssignmentGrades().entrySet()){
            sum += assignmentGrades.getValue();
        }
        return Math.round((float)sum/(student.getAssignmentGrades().size()));
    }

    public int getAverageProjectsGrade(Student student){
        float sum = 0;
        HashMap<String,Integer> teamGrades = student.getTeamGrades();
        for (Map.Entry<String,Integer> projectGrade : student.getProjectGrades().entrySet()){
            Integer teamGrade = teamGrades.get(projectGrade.getKey());
            float individPercent = (float)projectGrade.getValue()/100.0f;
            float teamPercent = (float)teamGrade/100.0f;
            sum+= individPercent * teamPercent;
        }
        float avg = sum/(student.getProjectGrades().size());
        float finalSum = avg * 100;
        return Math.round(finalSum);
    }

    public void addIndividualContributions(String projectName, HashMap<Student, Integer> contributions){
        for (Map.Entry<Student,Integer> contribution : contributions.entrySet()){
            Student student = contribution.getKey();
            HashMap<String,Integer> projectGrades = student.getProjectGrades();
            for (Map.Entry<String,Integer> projectGrade : projectGrades.entrySet()){
                if (projectName.equals(projectGrade.getKey())){
                    projectGrade.setValue(contribution.getValue());
                }
            }
            student.setProjectGrades(projectGrades);
        }
    }
    public int getOverallGrade(Student student){
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        String formula = grades.getFormula();
        if (formula == null){
            formula = this.formula;
        }
        String insertedFormula = formula;
        if (formula.contains("AA")){
            insertedFormula = formula.replace("AA", Integer.toString(getAverageAssignmentsGrade(student)));
        }
        if(formula.contains("AP")){
            insertedFormula = insertedFormula.replace("AP", Integer.toString(getAverageProjectsGrade(student)));
        }
        if(formula.contains("AT")){
            insertedFormula = insertedFormula.replace("AT", Integer.toString(student.getAttendance()));
        }
        try {
            return (int)Math.round((double)scriptEngine.eval(insertedFormula));
        } catch (GradeFormulaException | ScriptException e) {
            throw new GradeFormulaException("Make sure your formula is valid");
        }

    }
}
