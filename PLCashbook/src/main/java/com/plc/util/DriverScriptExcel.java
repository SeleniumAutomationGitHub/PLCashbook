package com.plc.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DriverScriptExcel {
	
	//Reading the test data from flat file (excel)
	private static FileInputStream fis;
	private static Workbook wb;
	private static Sheet sh;
	private static String[][] strArryData;
	
	//Writing the test results in flat file (excel)
	private static FileOutputStream fos;
	private static WritableWorkbook wwb;
	private static WritableSheet wsh;
	
	
	
	//Retrieving the test data from flat file (excel)
	public static String[][] getTestData(String filePath, String sheetName) throws BiffException, IOException{
		
		int rowValue = 0;
		int columnValue = 0;
		
		fis = new FileInputStream(filePath);
		wb = Workbook.getWorkbook(fis);
		sh = wb.getSheet(sheetName);
		
		rowValue= sh.getRows();
		columnValue= sh.getColumns();
		
		strArryData = new String[rowValue-1][columnValue];
		
		for(int i = 1; i <= rowValue-1; i++){
			for(int j = 0; j < columnValue; j++){
				strArryData[i-1][j]= sh.getCell(j, i).getContents();
			}
		}
		return strArryData;
	}
	
	
	//Updating the test result in flat file (excel)
	public static void setTestResult(String filePath) throws BiffException, IOException, WriteException{
		
		Object[][] objData = DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "login");
		
		fos = new FileOutputStream(filePath);
		wwb = Workbook.createWorkbook(fos);
		wsh = wwb.createSheet("Result", 1);
			
		for(int i = 0; i < objData.length; i++){
			for(int j = 0; j < objData[i].length; j++){
				Label lb = new Label(j, i+1, objData[i][j].toString());
				wsh.addCell(lb);
			}
		}
		
		Label lb1 = new Label(0,0, "Username");
		wsh.addCell(lb1);
		Label lb2 = new Label(1,0, "Password");
		wsh.addCell(lb2);
		Label lb3 = new Label(2,0, "Status");
		wsh.addCell(lb3);
		Label lb4 = new Label(2,1, "Pass");
		wsh.addCell(lb4);
		
		wwb.write();
		wwb.close();
	}	
	
	
	
	
	
	/*public static void main(String[] args) throws Exception{
		
		Object[][] org = DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "login");
		
		for(int i = 0; i< org.length; i++){
			for(int j = 0; j < org[i].length; j++){
				System.out.println("Values at: " + org[i][j]);
			}
		}
		
		new DriverScriptExcel().setTestResult(".\\src\\test\\resources\\OutputTestResult.xls");
		
	}*/

}
