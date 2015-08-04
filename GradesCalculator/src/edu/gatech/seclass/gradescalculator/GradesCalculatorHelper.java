package edu.gatech.seclass.gradescalculator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ammilkov on 7/8/15.
 */
public class GradesCalculatorHelper {

    private static final String PROJECT_PATH = System.getProperty("user.dir") + "/";
    private static HashSet<Student> studentsList;

    public static HashSet<Student> getStudentsList() {
        return studentsList;
    }

    public static void setStudentsList(HashSet<Student> studentsList) {
        GradesCalculatorHelper.studentsList = studentsList;
    }

    public static XSSFWorkbook initWorkbook(String dbPath) throws IOException{
        FileInputStream file = new FileInputStream(new File(PROJECT_PATH + dbPath));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        file.close();
        FileOutputStream out = new FileOutputStream(new File(PROJECT_PATH + dbPath));
        workbook.write(out);
        out.close();
        return workbook;
    }

    public static HashSet<Student> buildStudents(XSSFWorkbook workbook){
        XSSFSheet sheet = workbook.getSheet("StudentsInfo");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        HashSet<Student> studentsRoster = new HashSet<Student>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Student student = new Student();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        student.setName(cell.getStringCellValue());
                        student.setTeam(getStudentTeam(workbook, student));
                        break;
                    case 1:
                        student.setGtid(String.format("%.0f", cell.getNumericCellValue()));
                        break;
                    case 2:
                        student.setEmail(cell.getStringCellValue());
                        break;
                }
                studentsRoster.add(student);
            }
        }
        return studentsRoster;
    }

    public static String getStudentTeam(XSSFWorkbook workbook, Student student){
        XSSFSheet sheet = workbook.getSheet("Teams");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getStringCellValue().equals(student.getName())){
                    return row.getCell(0).getStringCellValue();
                }
            }
        }
        return null;
    }

    public static ArrayList<String> getAssignments(XSSFWorkbook workbook){
        XSSFSheet sheet = workbook.getSheet("IndividualGrades");
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        cellIterator.next();
        ArrayList<String> assignments = new ArrayList<>();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            assignments.add(cell.getStringCellValue());
        }
        return assignments;
    }

    public static ArrayList<String> getProjects(XSSFWorkbook workbook){
        XSSFSheet sheet = workbook.getSheet("IndividualContribs");
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        cellIterator.next();
        ArrayList<String> projects = new ArrayList<>();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            projects.add(cell.getStringCellValue());
        }
        return projects;
        
    }

    public static void setStudentAttendance(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("Attendance");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.format("%.0f", row.getCell(0).getNumericCellValue());
            for (Student student : students){
                if (gtid.equals(student.getGtid())){
                    student.setAttendance((int)row.getCell(1).getNumericCellValue());
                }
            }

        }

    }

    public static void setAssignmentGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("IndividualGrades");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.format("%.0f", row.getCell(0).getNumericCellValue());
            Iterator<Cell> cellIterator = row.cellIterator();
            cellIterator.next();
            for (Student student : students){
                HashMap<String,Integer> assignmentGrades = new HashMap<>();
                if (gtid.equals(student.getGtid())){
                    ArrayList<String> assignments = course.getAssignments();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        assignmentGrades.put(assignments.get(cell.getColumnIndex() - 1),(int) cell.getNumericCellValue());
                    }
                    student.setAssignmentGrades(assignmentGrades);
                }
            }

        }
    }

    public static void setProjectGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("IndividualContribs");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.format("%.0f", row.getCell(0).getNumericCellValue());
            Iterator<Cell> cellIterator = row.cellIterator();
            cellIterator.next();
            for (Student student : students){
                HashMap<String,Integer> projectGrades = new HashMap<>();
                if (gtid.equals(student.getGtid())){
                    ArrayList<String> projects = course.getProjects();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        projectGrades.put(projects.get(cell.getColumnIndex() - 1),(int) cell.getNumericCellValue());
                    }
                    student.setProjectGrades(projectGrades);
                }
            }

        }
    }

    public static void setTeamGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("TeamGrades");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String team =  row.getCell(0).getStringCellValue();
            for (Student student : students){
                if (team.equals(student.getTeam())){
                    Iterator<Cell> cellIterator = row.cellIterator();
                    cellIterator.next();
                    HashMap<String,Integer> teamGrades = new HashMap<>();
                    ArrayList<String> projects = course.getProjects();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        teamGrades.put(projects.get(cell.getColumnIndex() - 1), (int) cell.getNumericCellValue());
                    }
                    student.setTeamGrades(teamGrades);
                }
            }

        }
    }

}
