/**
 * 使用Apache POI和jxl库操作wps表格和微软的excel
 * POI中操作表格的是SSF组件：
 * 		HSSF基于BIFF8格式来处理WPS表格和Excel97-2003(.xsl)；
 * 		XSSF基于OOXML格式来处理WPS表格和Excel2007+(.xlsx)；
 * 
 * POI的maven依赖：
 * 		HSSF的依赖：org.apache.poi:poi
 * 		XSSF的依赖：org.apache.poi:poi-ooxml
 * jxl只能操作微软的excel，依赖：
 * 		net.sourceforge.jexcelapi:jxl
 */
package com.zhaiqianfeng.Lab.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LabApp {
	public static void main(String[] args) {
		poiXssf();
		System.out.println("\r\n");
		poiHssf();
	}

	//excle 2007+
	private static void poiXssf() {
		try {
			String fileName = "users.xlsx";
			// 写excel
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("sheet0");
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("姓名");
			row.createCell(1).setCellValue("年龄");

			row = sheet.createRow(1);
			row.createCell(0).setCellValue("James");
			row.createCell(1).setCellValue("30");

			FileOutputStream output = new FileOutputStream(fileName);
			wb.write(output);
			output.flush();

			//读excel
			File file = new File(fileName);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			int rowStart = xssfSheet.getFirstRowNum();
			int rowEnd = xssfSheet.getLastRowNum();
			for (int iloop = rowStart; iloop <= rowEnd; iloop++) {
				row = xssfSheet.getRow(iloop);
				System.out.println(row.getCell(0).getStringCellValue() + "\t" + row.getCell(1).getStringCellValue());
			}

			xssfWorkbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// excel97-2003
	private static void poiHssf() {
		try {
			String fileName = "users.xls";
			// 写excel
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("sheet0");
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("姓名");
			row.createCell(1).setCellValue("年龄");

			row = sheet.createRow(1);
			row.createCell(0).setCellValue("James");
			row.createCell(1).setCellValue("30");

			FileOutputStream output = new FileOutputStream(fileName);
			wb.write(output);
			output.flush();

			// 读取excel
			File file = new File(fileName);
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			int rowStart = hssfSheet.getFirstRowNum();
			int rowEnd = hssfSheet.getLastRowNum();
			for (int iloop = rowStart; iloop <= rowEnd; iloop++) {
				row = hssfSheet.getRow(iloop);
				System.out.println(row.getCell(0).getStringCellValue() + "\t" + row.getCell(1).getStringCellValue());
			}

			hssfWorkbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
