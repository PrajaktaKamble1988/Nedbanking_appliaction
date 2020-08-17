package com.Nedbank.Utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Nedbank.TestCases.BaseClass;

public class XLUtils extends BaseClass{

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	String XLSheet_Path = System.getProperty("user.dir") + "\\NedBank_XLSheets\\NedBank_LginXL_File.xlsx";

	public static int getRow_Count(String xlFile, String xlSheet) throws Exception { // Method IS FOR Counting Total Rows
		fi = new FileInputStream(xlFile); // Read the File
		wb = new XSSFWorkbook(fi); // Open the Workbook
		ws = wb.getSheet(xlSheet); // open the Sheet
		int rowCount = ws.getLastRowNum(); // Count Total ROws(Records in sheet)
		wb.close();
		fi.close();
		return rowCount;

	}

	public static int getColumn_Count(String xlFile, String xlSheet, int noOfRows) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(noOfRows);
		int cellCount = row.getLastCellNum(); // Find out last Coumn Of that Row
		wb.close();
		fi.close();
		return cellCount;

	}

	public static String getCoumn_Data(String xlFile, String xlSheet, int noOfRows, int noOFColumn) throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(noOfRows);
		cell = row.getCell(noOFColumn);

		String Data;

		try {

			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;

		} catch (Exception e) {
			Data = "";

		}
		wb.close();
		fi.close();
		return Data;

	}

	public static void setCellData(String xlFile, String xlSheet, int noOfRows, int noOfColumn, String Data)
			throws Exception {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(noOfRows);
		cell = row.getCell(noOfColumn);
		cell.setCellValue(Data);

		fo = new FileOutputStream(xlFile); // Open XlFile
		wb.write(fo); // Write The XLFile
		wb.close();
		fi.close();
		fo.close();

	}
	
	
}
