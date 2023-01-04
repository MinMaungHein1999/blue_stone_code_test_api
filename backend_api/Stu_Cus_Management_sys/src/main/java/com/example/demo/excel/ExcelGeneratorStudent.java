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
import com.example.demo.entity.Student;


	public class ExcelGeneratorStudent {

	    private List < Student > students;
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;

	    public ExcelGeneratorStudent(List < Student > students) {
	        this.students = students;
	        workbook = new XSSFWorkbook();
	    }
	    private void writeHeader() {
	        sheet = workbook.createSheet("Student");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "Student Name", style);
	        createCell(row, 2, "Email", style);
	        createCell(row, 3, "Password", style);
	        
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
	        for (Student record: students) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell(row, columnCount++, record.getId(), style);
	            createCell(row, columnCount++, record.getName(), style);
	            createCell(row, columnCount++, record.getEmail(), style);
	            createCell(row, columnCount++, record.getPassword(), style);
	         
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