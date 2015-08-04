package edu.gatech.seclass.gradescalculator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Created by ammilkov on 7/5/15.
 */
public class Grades {

    private XSSFWorkbook workbook;
    private String formula;

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public Grades(String gradesDbPath){
        try {
            this.workbook = GradesCalculatorHelper.initWorkbook(gradesDbPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
