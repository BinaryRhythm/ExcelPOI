package com.intyt.sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SheetExport {

	// 设置cell编码解决中文高位字节截断
	private static short XLS_ENCODING = HSSFCell.ENCODING_UTF_16;
	private String xlsFileName;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;

	public SheetExport(String fileName) {
		this.xlsFileName = fileName;
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
	}

	public void exportSheet() {
		try {
			FileOutputStream fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HSSFRow createRow(int index) {
		return sheet.createRow(index);
	}

	public void setCell(HSSFRow row, int col, String value) {
		HSSFCell cell = row.createCell(col);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	public void setCell(HSSFRow row, int col, long value) {
		HSSFCell cell = row.createCell(col);// row.HSSFCell(workbook,sheet,row,col,HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

}
