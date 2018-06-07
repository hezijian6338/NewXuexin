package com.zhbit.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POITest {
	
	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("H:\\WorkSpace\\wzj\\NewXuexin\\test\\scoreconf.xlsx");
		Workbook wb = new XSSFWorkbook(in);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		cell.setCellValue("test1");
		FileOutputStream out = new FileOutputStream("H:\\WorkSpace\\wzj\\NewXuexin\\test\\scoreconf1.xlsx");
		wb.write(out);
	}
}
