package com.example.demo.excel;

	import java.io.IOException;
	import java.util.List;
	import javax.servlet.ServletOutputStream;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFFont;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entity.User;


	public class ExcelGeneratorUser {

	    private List < User > users;
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;

	    public ExcelGeneratorUser(List < User > users) {
	        this.users = users;
	        workbook = new XSSFWorkbook();
	    }
	    private void writeHeader() {
	        sheet = workbook.createSheet("User");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "User Name", style);
	        createCell(row, 2, "Phone No", style);
	        createCell(row, 3, "Email", style);
	        
	    }
	    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (valueOfCell instanceof Integer) {
	            cell.setCellValue((Integer) valueOfCell);
	        } else if (valueOfCell instanceof Long) {
	            cell.setCellValue((Long) valueOfCell);
	        } else if (valueOfCell instanceof String) {
	            cell.setCellValue((String) valueOfCell);
	        } else {
	            cell.setCellValue((Boolean) valueOfCell);
	        }
	        cell.setCellStyle(style);
	    }
	    private void write() {
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        for (User record: users) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell(row, columnCount++, record.getUserId(), style);
	            createCell(row, columnCount++, record.getName(), style);
	            createCell(row, columnCount++, record.getPhoneNo(), style);
	            createCell(row, columnCount++, record.getEmail(), style);
	         
	        }
	    }
	    public void generateExcelFile(HttpServletResponse response) throws IOException {
	        writeHeader();
	        write();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }
	}