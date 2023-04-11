package com.google.finance.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataReader {

XSSFWorkbook workbook;

FileInputStream fis;

public ArrayList<String> getData(String testCaseName, String sheetName){
    ArrayList<String> testData = new ArrayList<>();

    try{
        fis = new FileInputStream((System.getProperty("user.dir")+ "\\src\\test\\resources\\testData\\googleFinanceTestData.xlsx"));
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
    try{
        workbook = new XSSFWorkbook(fis);
    }catch (IOException e){
        e.printStackTrace();
    }

    int sheets = workbook.getNumberOfSheets();
    for(int i=0; i<sheets; i++){
        if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)){
            XSSFSheet sheet = workbook.getSheetAt(i);

            Iterator<Row> rows = sheet.iterator();
            Row firstRow = rows.next();
            Iterator<Cell> ce = firstRow.cellIterator();
            int k=0;
            int column = 0;
            while(ce.hasNext()){
                Cell value = ce.next();
                if(value.getStringCellValue().equalsIgnoreCase("TestCases")){
                    column=k;
                }

                k++;
            }

            while(rows.hasNext()){
                Row r = rows.next();
                if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){
                    Iterator<Cell> cv = r.cellIterator();
                    while (cv.hasNext()){
                        Cell c = cv.next();
                        if(c.getCellType()== CellType.STRING){
                            testData.add(c.getStringCellValue());
                        }else{
                            testData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                        }
                    }
                }
            }
        }
    }
    try{
        workbook.close();
        fis.close();
    }catch (IOException e){
        e.printStackTrace();
    }
    return testData;
}



}
