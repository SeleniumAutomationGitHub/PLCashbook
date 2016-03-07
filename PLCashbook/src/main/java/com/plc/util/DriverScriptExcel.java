package com.plc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DriverScriptExcel {
	
	private static FileInputStream fis;
	private static Workbook wb;
	private static Sheet sh;
	private static String[][] strArryData;
	
	
	
	//Retrieving the test data from external file (excel)
	public static String[][] getTestData(String filePath, String sheetName) throws BiffException, IOException{
		
		int rowValue = 0;
		int columnValue = 0;
		
		fis = new FileInputStream(filePath);
		wb = Workbook.getWorkbook(fis);
		sh = wb.getSheet(sheetName);
		
		rowValue= sh.getRows();
		//System.out.println(rowValue);
		columnValue= sh.getColumns();
		//System.out.println(columnValue);
		
		strArryData = new String[rowValue-1][columnValue];
		//System.out.println(strArryData.length);
		
		for(int i = 1; i <= rowValue-1; i++){
			for(int j = 0; j < columnValue; j++){
				
				strArryData[i-1][j]= sh.getCell(j, i).getContents();
				
			}
		}
		
		return strArryData;
		
	}
	
	
	/*public static void main(String[] args) throws Exception{
		
		Object[][] org = DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "Sheet1");
		for(int i = 0; i< org.length; i++){
			for(int j = 0; j < org[i].length; j++){
				
				System.out.println("Values at: " + org[i][j]);
			}
		}
	}*/
	
	

}
