package com.bornfire.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bornfire.entities.AttendanceRegisterGet;
import com.bornfire.entities.AttendanceRegisterGetRep;
import com.bornfire.entities.PO_Return_Entity;
import com.bornfire.entities.PO_invoice_entity;
import com.bornfire.entities.PURCHASE_ORDER_ENTITY_NEW;
import com.bornfire.entities.SALES_ORDER_ENTITY_NEW;
import com.bornfire.entities.SALES_invoice_TABLE;
import com.bornfire.entities.Sales_Return;
import com.bornfire.entities.VendorCreationRep;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ReportServices {

	@Autowired
	Environment env;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	public File getFileAttendance(String emp_id, String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + emp_id + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// logger.info("Assigning Parameters for Jasper");

			map.put("emp_id", emp_id);
			map.put("cal_month", cal_month);
			map.put("cal_year", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;
				try {
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					JasperExportManager.exportReportToPdfFile(jp, path);

				} catch (JRException e) {
					e.printStackTrace();
					System.out.println("Error filling report: " + e.getMessage());
				}

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileDailyAttendance(String cal_year, String cal_month, String cal_date, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year + "_" + cal_date;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);

			map.put("CAL_DATE", cal_date);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileMonthyAttendance(String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Month_Report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Month_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileLeaveRegister(String employee_id, String year, String leave_category, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Leave_Register" + "_" + employee_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMPLOYEE_ID", employee_id);
			map.put("LEAVE_CATEGORY", leave_category);
			map.put("CAL_YEAR", year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileTimeSheet(String emp_id, String year, String month, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Time_Sheet" + "_" + emp_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			map.put("year", year);
			map.put("month", month);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getemployeeRegister(String monthend, String detials, String reportto, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "employeeRegister" + "_" + reportto + "_" + monthend;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("PDF")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/EmployeeRegisterReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/EmployeeRegisterReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("EMPLOYEE_ID", reportto);

			if (report_type.equals("PDF")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {
				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileWorkAssign(String emp_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Work_Assign" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileProfileMaster(String emp_id, String ProfileType, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Profile_Master" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMP_ID", emp_id);
			// map.put("profileType", ProfileType);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileProject(String proj_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Project_Report" + "_" + proj_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("PROJ_ID", proj_id);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayDetailsList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	// Excel For Attendance Register

	@Autowired
	AttendanceRegisterGetRep attendanceRegisterGetRep;

	public ResponseEntity<byte[]> getFileAttendanceone(String id, String cal_month, String cal_year, String report_type,
			String attendanceType) throws JRException, SQLException, IOException {

		System.out.println("report_type is: " + report_type);

		// Base query
		String hql = "FROM AttendanceRegisterGet a WHERE a.leave_flg = 'N'";

		// Dynamic conditions
		if (id != null && !id.isEmpty()) {
			hql += " AND a.id.emp_id LIKE :id"; // Use emp_id from composite key
		}
		if (cal_month != null && !cal_month.isEmpty()) {
			hql += " AND a.cal_month = :cal_month";
		}
		if (cal_year != null && !cal_year.isEmpty()) {
			hql += " AND a.cal_year = :cal_year";
		}

		// Create and execute query
		Query<AttendanceRegisterGet> query = sessionFactory.getCurrentSession().createQuery(hql,
				AttendanceRegisterGet.class);
		if (id != null && !id.isEmpty()) {
			query.setParameter("id", "%" + id + "%");
		}
		if (cal_month != null && !cal_month.isEmpty()) {
			query.setParameter("cal_month", cal_month);
		}
		if (cal_year != null && !cal_year.isEmpty()) {
			query.setParameter("cal_year", cal_year);
		}
		List<AttendanceRegisterGet> list = query.getResultList();

		// Create Excel Workbook
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Attendance Register");

			// Attendance Type Header
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Attendance Type: " + attendanceType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9)); // Merge cells for title
			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

			// Create Header Row
			Row headerRow = sheet.createRow(1);
			String[] headers = { "Srl. No", "Emp Id", "Emp Name", "Date", "Month", "Year", "Login Time", "Logout Time",
					"Category", "Remarks" };
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Set light gray
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Apply solid fill pattern
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
			}

			// Create reusable styles
			CellStyle centerStyle = workbook.createCellStyle();
			centerStyle.setAlignment(HorizontalAlignment.CENTER);
			centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			CellStyle dateStyle = workbook.createCellStyle();
			DataFormat dataFormat = workbook.createDataFormat();
			dateStyle.setDataFormat(dataFormat.getFormat("dd-MM-yyyy"));
			dateStyle.setAlignment(HorizontalAlignment.CENTER);
			dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			// Populate Data Rows
			int rowIndex = 2;
			int serialNumber = 1;
			for (AttendanceRegisterGet coresystem : list) {
				Row row = sheet.createRow(rowIndex++);

				// SRL NO
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(serialNumber++);
				cell0.setCellStyle(centerStyle);

				// EMPLOYEE ID
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(coresystem.getId().getEmp_id());
				cell1.setCellStyle(centerStyle);

				// EMPLOYEE NAME
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(coresystem.getEmp_name());
				cell2.setCellStyle(centerStyle);

				// DATE
				Cell cell3 = row.createCell(3);
				if (coresystem.getId().getLogin_time() != null) {
					cell3.setCellValue(coresystem.getId().getLogin_time());
					cell3.setCellStyle(dateStyle);
				}

				// MONTH
				Cell cell4 = row.createCell(4);
				cell4.setCellValue(coresystem.getCal_month());
				cell4.setCellStyle(centerStyle);

				// YEAR
				Cell cell5 = row.createCell(5);
				cell5.setCellValue(coresystem.getCal_year());
				cell5.setCellStyle(centerStyle);

				// LOGIN TIME
				Cell cell6 = row.createCell(6);
				cell6.setCellValue(coresystem.getLogin_time1());
				cell6.setCellStyle(centerStyle);

				// LOGOUT TIME
				Cell cell7 = row.createCell(7);
				cell7.setCellValue(coresystem.getLogout_time1());
				cell7.setCellStyle(centerStyle);

				// CATEGORY
				Cell cell8 = row.createCell(8);
				cell8.setCellValue(coresystem.getLeave_category());
				cell8.setCellStyle(centerStyle);

				// REMARKS
				Cell cell9 = row.createCell(9);
				cell9.setCellValue(coresystem.getLeave_remarks());
				cell9.setCellStyle(centerStyle);
			}

			// Auto-size all columns
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write Workbook to Output Stream
			workbook.write(outputStream);

			// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Attendance Register-" + id + ".xlsx");

			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(("Error generating report: " + e.getMessage()).getBytes());
		}
	}

	// PDF For Attendance Register

	public ResponseEntity<byte[]> getFileAttendancetwo(String id, String cal_month, String cal_year, String report_type,
			String attendanceType) throws JRException, SQLException, IOException, DocumentException {

		System.out.println("report_type is :" + report_type);

		// Base query
		String hql = "FROM AttendanceRegisterGet a WHERE a.leave_flg = 'N'";

		// Add conditions for dynamic query
		if (id != null && !id.isEmpty()) {
			hql += " AND a.id.emp_id LIKE :id";
		}
		if (cal_month != null && !cal_month.isEmpty()) {
			hql += " AND a.cal_month = :cal_month";
		}
		if (cal_year != null && !cal_year.isEmpty()) {
			hql += " AND a.cal_year = :cal_year";
		}

		// Create query and set parameters
		Query<AttendanceRegisterGet> query = sessionFactory.getCurrentSession().createQuery(hql,
				AttendanceRegisterGet.class);

		if (id != null && !id.isEmpty()) {
			query.setParameter("id", "%" + id + "%");
		}
		if (cal_month != null && !cal_month.isEmpty()) {
			query.setParameter("cal_month", cal_month);
		}
		if (cal_year != null && !cal_year.isEmpty()) {
			query.setParameter("cal_year", cal_year);
		}

		// Execute the query
		List<AttendanceRegisterGet> list = query.getResultList();

		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, outputStream);
			document.open();

			// Add Title
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			Paragraph title = new Paragraph("Attendance Register", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			// Add Attendance Type
			document.add(new Paragraph("\n"));
			Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Paragraph subtitle = new Paragraph("Attendance Type: " + attendanceType, subtitleFont);
			subtitle.setAlignment(Element.ALIGN_LEFT);
			document.add(subtitle);

			// Add Table
			PdfPTable table = new PdfPTable(10); // 10 columns
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);

			// set Column Widths
			table.setWidths(new float[] { 4f, 3.5f, 5f, 3.5f, 3f, 3f, 5.5f, 5.5f, 5f, 5f });

			// Define Headers
			String[] headers = { "Srl. No", "Emp Id", "Emp Name", "Date", "Month", "Year", "Login Time", "Logout Time",
					"Category", "Remarks" };

			// Add Header Cells
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
			for (String header : headers) {
				PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setFixedHeight(25f);
				table.addCell(headerCell);
			}
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy");

			// Populate Table Rows
			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
			int serialNumber = 1;
			for (AttendanceRegisterGet coresystem : list) {
				table.addCell(createCell(String.valueOf(serialNumber++), cellFont));
				table.addCell(createCell(coresystem.getId().getEmp_id(), cellFont));
				table.addCell(createCell(coresystem.getEmp_name(), cellFont));

				String formattedDate = coresystem.getId().getLogin_time() != null
						? dateFormatter.format(coresystem.getId().getLogin_time())
						: "";
				table.addCell(createCell(formattedDate, cellFont));
				table.addCell(createCell(coresystem.getCal_month(), cellFont));
				table.addCell(createCell(coresystem.getCal_year(), cellFont));
				table.addCell(createCell(coresystem.getLogin_time1(), cellFont));
				table.addCell(createCell(coresystem.getLogout_time1(), cellFont));
				table.addCell(createCell(coresystem.getLeave_category(), cellFont));
				table.addCell(createCell(coresystem.getLeave_remarks(), cellFont));
			}

			document.add(table);
		} finally {
			document.close();
		}

		// Create Response
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.APPLICATION_PDF);
		headersResponse.setContentDispositionFormData("attachment", "Attendance Register-" + id + ".pdf");

		return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
	}

	// Helper method to create cells
	private PdfPCell createCell(String content, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(content, font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(20f);
		cell.setNoWrap(false);
		return cell;
	}

	@Autowired
	VendorCreationRep Vendorrep;

	//////////////////////////////// Excel for Purchase Order Report
	public ResponseEntity<byte[]> getPoReportExcel(String POType, String vendorId, String po_cat, String poId,
			String orderDates, String from_dates, String to_dates) throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Build Query
		StringBuilder hql = new StringBuilder("FROM PURCHASE_ORDER_ENTITY_NEW a WHERE 1=1");

		if (poId != null && !poId.isEmpty())
			hql.append(" AND a.poId LIKE :poId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

		// Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

		// Execute Query
		Query<PURCHASE_ORDER_ENTITY_NEW> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				PURCHASE_ORDER_ENTITY_NEW.class);

		if (poId != null && !poId.isEmpty())
			query.setParameter("poId", "%" + poId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

		// Get the result list safely
		List<PURCHASE_ORDER_ENTITY_NEW> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Purchase Order");

			// Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Purchase Order Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

			// Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "PO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

			// Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			// Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			// Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
			// Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			// Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
			// Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					PURCHASE_ORDER_ENTITY_NEW record = list.get(i);

					/* ----forvendorname-- */
					String vendorname = getVendorNameById(record.getVendorId());

					String currentPoId = Optional.ofNullable(record.getPoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

					// Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);

					// Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
					// Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */
					row.createCell(4).setCellValue(vendorname);

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */
					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty());

					/* 8 */
					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());
					/* 10 */

					// row.createCell(12).setCellValue(record.getAmountPerItem());

					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());

					/*
					 * row.createCell(1).setCellValue(Optional.ofNullable(record.getOrderNo()).
					 * orElse("")); row.getCell(1).setCellStyle(centerAlignedStyle);
					 * row.createCell(2).setCellValue(currentPoId);
					 * row.createCell(3).setCellValue(Optional.ofNullable(record.getItemcode()).
					 * orElse(""));
					 * row.createCell(4).setCellValue(Optional.ofNullable(record.getHsncode()).
					 * orElse(""));
					 * row.createCell(5).setCellValue(Optional.ofNullable(record.getVendorId()).
					 * orElse("")); row.createCell(6).setCellValue( record.getOrderDate() != null ?
					 * dateFormat1.format(record.getOrderDate()) : ""); row.createCell(7)
					 * .setCellValue(record.getDueDate() != null ?
					 * dateFormat1.format(record.getDueDate()) : "");
					 * row.createCell(8).setCellValue(Optional.ofNullable(record.getItem()).orElse(
					 * "")); row.createCell(9).setCellValue(record.getQty());
					 * row.createCell(10).setCellValue(Optional.ofNullable(record.getUnit()).orElse(
					 * "")); row.createCell(11).setCellValue(record.getPriceHeader());
					 * 
					 * // Right-aligned cells for price, amount per item, advance amount, balance
					 * amount, paid amount row.createCell(12).setCellValue(String.format("%.2f",
					 * record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					 * row.getCell(12).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(13).setCellValue(record.getTaxPercentage());
					 * 
					 * row.createCell(14).setCellValue(record.getAmountPerItem() != null ?
					 * String.format("%.2f", record.getAmountPerItem()) : "");
					 * row.getCell(14).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(15).setCellValue( record.getAdvanceAmount() != null ?
					 * String.format("%.2f", record.getAdvanceAmount()) : "");
					 * row.getCell(15).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(16).setCellValue( record.getBalanceAmount() != null ?
					 * String.format("%.2f", record.getBalanceAmount()) : "");
					 * row.getCell(16).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(17).setCellValue(Optional.ofNullable(record.getPaymentType()).
					 * orElse(""));
					 * row.createCell(18).setCellValue(Optional.ofNullable(record.getReferenceNumber
					 * ()).orElse(""));
					 * 
					 * // Right-aligned paid amount
					 * row.createCell(19).setCellValue(record.getPaidAmount() != null ?
					 * String.format("%.2f", record.getPaidAmount().doubleValue()) : "");
					 * row.getCell(19).setCellStyle(rightAlignedStyle);
					 */
				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

			// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Purchase Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}

	/////////////////// PDF for Purchase order

	public ResponseEntity<byte[]> getPoReportPdf(String POType, String vendorId, String po_cat, String poId,
			String orderDates, String from_dates, String to_dates)
			throws JRException, SQLException, IOException, DocumentException, ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// ✅ Title
		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
		Paragraph title = new Paragraph("Purchase Order Report", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		document.add(new Paragraph("\n"));

		// ✅ Filters
		Font filterFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
		document.add(new Paragraph("Purchase Order Type: " + POType, filterFont));
		document.add(new Paragraph("\n"));

		// ✅ Query Execution
		String hql = "FROM PURCHASE_ORDER_ENTITY_NEW a WHERE 1=1";
		if (poId != null && !poId.isEmpty())
			hql += " AND a.poId LIKE :poId";
		if (vendorId != null && !vendorId.isEmpty())
			hql += " AND a.vendorId = :vendorId";
		if (orderDates != null && !orderDates.isEmpty())
			hql += " AND a.orderDate = :orderDate";
		if (from_dates != null && !from_dates.isEmpty() && to_dates != null && !to_dates.isEmpty()) {
			hql += " AND a.orderDate BETWEEN :from_date AND :to_date";
		}

		Query<PURCHASE_ORDER_ENTITY_NEW> query = sessionFactory.getCurrentSession().createQuery(hql,
				PURCHASE_ORDER_ENTITY_NEW.class);
		if (poId != null && !poId.isEmpty())
			query.setParameter("poId", "%" + poId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);

		// Handle orderDates parsing with null/empty check
		if (orderDates != null && !orderDates.isEmpty()) {
			try {
				query.setParameter("orderDate", dateFormat.parse(orderDates));
			} catch (ParseException e) {
				// Handle the error if the date format is incorrect, e.g., log it or send a
				// custom error message
				System.err.println("Invalid order date format: " + orderDates);
			}
		}

		// Handle from_dates and to_dates parsing with null/empty check
		if (from_dates != null && !from_dates.isEmpty() && to_dates != null && !to_dates.isEmpty()) {
			try {
				query.setParameter("from_date", dateFormat.parse(from_dates));
				query.setParameter("to_date", dateFormat.parse(to_dates));
			} catch (ParseException e) {
				// Handle error for invalid date formats
				System.err.println("Invalid date range: " + from_dates + " to " + to_dates);
			}
		}

		// Execute the query
		List<PURCHASE_ORDER_ENTITY_NEW> list = query.getResultList();

		// ✅ Create Table
		PdfPTable table = new PdfPTable(19); // Update to 20 columns based on the header size
		table.setWidthPercentage(100);
		table.setSpacingBefore(15f);
		table.setWidths(new float[] { 6f, 12f, 10f, 10f, 11.5f, 11f, 11f, 10f, 6f, 10f, 10f, 10f, 10f, 10f, 10f, 10f,
				7f, 10f, 10f }); // Set appropriate widths for each column

		// ✅ Table Headers
		String[] headers = { "SRL NO", "PO ID", "ITEM CODE", "HSN CODE", "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM",
				"QTY", "UNIT", "TAX STATUS", "PRICE", "TAX %", "AMT PER ITEM", "ADV AMT", "BAL AMT", "PAY TYPE",
				"REF NUM", "PAID AMT" };

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
		for (String header : headers) {
			PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			headerCell.setFixedHeight(25f);
			table.addCell(headerCell);
		}

		// ✅ Populate Rows
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 5);

		int serialNumber = 1;
		for (PURCHASE_ORDER_ENTITY_NEW record : list) {
			// Add cells for each record
			table.addCell(createCell(String.valueOf(serialNumber++), cellFont));
			table.addCell(createCell(Optional.ofNullable(record.getPoId()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(
					createCell(Optional.ofNullable(record.getItemcode()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(
					createCell(Optional.ofNullable(record.getHsncode()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(
					createCell(Optional.ofNullable(record.getVendorId()).orElse(""), cellFont, Element.ALIGN_LEFT));

			// Format dates for orderDate and dueDate
			String formattedOrderDate = (record.getOrderDate() != null) ? dateFormat.format(record.getOrderDate()) : "";
			String formattedDueDate = (record.getDueDate() != null) ? dateFormat.format(record.getDueDate()) : "";
			table.addCell(createCell(formattedOrderDate, cellFont, Element.ALIGN_LEFT));
			table.addCell(createCell(formattedDueDate, cellFont, Element.ALIGN_LEFT));

			// Add remaining fields
			table.addCell(createCell(Optional.ofNullable(record.getItem()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(createCell(String.valueOf(record.getQty()), cellFont, Element.ALIGN_RIGHT));
			table.addCell(createCell(Optional.ofNullable(record.getUnit()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(createCell(String.valueOf(record.getPriceHeader()), cellFont, Element.ALIGN_LEFT));
			table.addCell(
					createCell(String.format("%.2f", record.getPrice().doubleValue()), cellFont, Element.ALIGN_RIGHT));
			table.addCell(createCell(String.valueOf(record.getTaxPercentage()), cellFont, Element.ALIGN_LEFT));
			table.addCell(createCell(String.format("%.2f", record.getAmountPerItem()), cellFont, Element.ALIGN_RIGHT));
			table.addCell(createCell(String.format("%.2f", record.getAdvanceAmount()), cellFont, Element.ALIGN_RIGHT));
			table.addCell(createCell(String.format("%.2f", record.getBalanceAmount()), cellFont, Element.ALIGN_RIGHT));
			table.addCell(
					createCell(Optional.ofNullable(record.getPaymentType()).orElse(""), cellFont, Element.ALIGN_LEFT));
			table.addCell(createCell(Optional.ofNullable(record.getReferenceNumber()).orElse(""), cellFont,
					Element.ALIGN_LEFT));
			table.addCell(createCell(String.format("%.2f", record.getPaidAmount().doubleValue()), cellFont,
					Element.ALIGN_RIGHT));
		}

		document.add(table);
		document.close();

		// ✅ Response Headers
		HttpHeaders headersResponse = new HttpHeaders();
		headersResponse.setContentType(MediaType.APPLICATION_PDF);
		headersResponse.setContentDispositionFormData("attachment", "Purchase Order.pdf");

		return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
	}

	// Modify the createCell method to accept alignment
	public PdfPCell createCell(String text, Font font, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

	/*------EXCEL FOR SALE ORDER REPORT-----*/

	public ResponseEntity<byte[]> getWORKORDERReportExcel(String POType, String vendorId, String po_cat, String woId,
			String orderDates, String from_dates, String to_dates) throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Build Query
		StringBuilder hql = new StringBuilder("FROM SALES_ORDER_ENTITY_NEW a WHERE 1=1");

		if (woId != null && !woId.isEmpty())
			hql.append(" AND a.woId LIKE :woId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

		// Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

		// Execute Query
		Query<SALES_ORDER_ENTITY_NEW> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				SALES_ORDER_ENTITY_NEW.class);

		if (woId != null && !woId.isEmpty())
			query.setParameter("woId", "%" + woId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

		// Get the result list safely
		List<SALES_ORDER_ENTITY_NEW> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Sale Order");

			// Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Sale Order Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

			// Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "WO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

			/*
			 * String[] headers1 = { "SRL NO",
			 * "PO ID","ORDER DATE","VENDOR ID","VENDOR NAME", "ITEM CODE",
			 * "ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM","TOTAL VALUE",
			 * "TAX AMOUNT", "TOTAL AMOUNT"};
			 */

			// Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			// Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			// Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
			// Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			// Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
			// Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					SALES_ORDER_ENTITY_NEW record = list.get(i);

					String currentPoId = Optional.ofNullable(record.getWoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

					// Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);

					// Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
					// Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */

					row.createCell(4).setCellValue(record.getVendor_name());

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */

					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty());

					/* 8 */

					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);
					/* 10 */

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());

					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());

					/*
					 * row.createCell(1).setCellValue(Optional.ofNullable(record.getOrderNo()).
					 * orElse("")); row.getCell(1).setCellStyle(centerAlignedStyle);
					 * row.createCell(2).setCellValue(currentPoId);
					 * row.createCell(3).setCellValue(Optional.ofNullable(record.getItemcode()).
					 * orElse(""));
					 * row.createCell(4).setCellValue(Optional.ofNullable(record.getHsncode()).
					 * orElse(""));
					 * row.createCell(5).setCellValue(Optional.ofNullable(record.getVendorId()).
					 * orElse("")); row.createCell(6).setCellValue( record.getOrderDate() != null ?
					 * dateFormat1.format(record.getOrderDate()) : ""); row.createCell(7)
					 * .setCellValue(record.getDueDate() != null ?
					 * dateFormat1.format(record.getDueDate()) : "");
					 * row.createCell(8).setCellValue(Optional.ofNullable(record.getItem()).orElse(
					 * "")); row.createCell(9).setCellValue(record.getQty());
					 * row.createCell(10).setCellValue(Optional.ofNullable(record.getUnit()).orElse(
					 * "")); row.createCell(11).setCellValue(record.getPriceHeader());
					 * 
					 * // Right-aligned cells for price, amount per item, advance amount, balance
					 * amount, paid amount row.createCell(12).setCellValue(String.format("%.2f",
					 * record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					 * row.getCell(12).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(13).setCellValue(record.getTaxPercentage());
					 * 
					 * row.createCell(14).setCellValue(record.getAmountPerItem() != null ?
					 * String.format("%.2f", record.getAmountPerItem()) : "");
					 * row.getCell(14).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(15).setCellValue( record.getAdvanceAmount() != null ?
					 * String.format("%.2f", record.getAdvanceAmount()) : "");
					 * row.getCell(15).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(16).setCellValue( record.getBalanceAmount() != null ?
					 * String.format("%.2f", record.getBalanceAmount()) : "");
					 * row.getCell(16).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(17).setCellValue(Optional.ofNullable(record.getPaymentType()).
					 * orElse(""));
					 * row.createCell(18).setCellValue(Optional.ofNullable(record.getReferenceNumber
					 * ()).orElse(""));
					 * 
					 * // Right-aligned paid amount
					 * row.createCell(19).setCellValue(record.getPaidAmount() != null ?
					 * String.format("%.2f", record.getPaidAmount().doubleValue()) : "");
					 * row.getCell(19).setCellStyle(rightAlignedStyle);
					 */
				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

			// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Sale Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}

	@PersistenceContext
	private EntityManager entityManager;

	public String getVendorNameById(String vendorId) {
		String hql = "SELECT v.vendorName FROM VendorCreation v WHERE v.vendorId = :vendorId";
		try {
			return entityManager.createQuery(hql, String.class).setParameter("vendorId", vendorId).getSingleResult();
		} catch (NoResultException e) {
			return "Vendor Not Found"; // Handle if no vendor found
		}
	}

	public String getVendorNameByIdsale(String vendorId) {
		String hql = "SELECT v.vendor_name FROM SALES_ORDER_ENTITY_NEW v WHERE v.vendor_name = :vendorId";
		try {
			return entityManager.createQuery(hql, String.class).setParameter("vendorId", vendorId).getSingleResult();
		} catch (NoResultException e) {
			return "Vendor Not Found"; // Handle if no vendor found
		}
	}

////////////////////////////////Excel for Purchase invoice Report 
	public ResponseEntity<byte[]> getPoReportExcelforinvoive(String POType, String vendorId, String po_cat, String poId,
			String orderDates, String from_dates, String to_dates) throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

// Build Query
		StringBuilder hql = new StringBuilder("FROM PO_invoice_entity a WHERE 1=1");

		if (poId != null && !poId.isEmpty())
			hql.append(" AND a.poId LIKE :poId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

// Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

// Execute Query
		Query<PO_invoice_entity> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				PO_invoice_entity.class);

		if (poId != null && !poId.isEmpty())
			query.setParameter("poId", "%" + poId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

// Get the result list safely
		List<PO_invoice_entity> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Purchase Invoice");

// Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Purchase Invoice Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

// Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "PO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

// Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

// Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
// Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
// Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

// Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
// Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					PO_invoice_entity record = list.get(i);

					/* ----forvendorname-- */
					String vendorname = getVendorNameById(record.getVendorId());

					String currentPoId = Optional.ofNullable(record.getPoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

// Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);

// Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
// Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */
					row.createCell(4).setCellValue(vendorname);

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */
					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty());

					/* 8 */
					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());
					/* 10 */

//row.createCell(12).setCellValue(record.getAmountPerItem());

					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());

					/*
					 * row.createCell(1).setCellValue(Optional.ofNullable(record.getOrderNo()).
					 * orElse("")); row.getCell(1).setCellStyle(centerAlignedStyle);
					 * row.createCell(2).setCellValue(currentPoId);
					 * row.createCell(3).setCellValue(Optional.ofNullable(record.getItemcode()).
					 * orElse(""));
					 * row.createCell(4).setCellValue(Optional.ofNullable(record.getHsncode()).
					 * orElse(""));
					 * row.createCell(5).setCellValue(Optional.ofNullable(record.getVendorId()).
					 * orElse("")); row.createCell(6).setCellValue( record.getOrderDate() != null ?
					 * dateFormat1.format(record.getOrderDate()) : ""); row.createCell(7)
					 * .setCellValue(record.getDueDate() != null ?
					 * dateFormat1.format(record.getDueDate()) : "");
					 * row.createCell(8).setCellValue(Optional.ofNullable(record.getItem()).orElse(
					 * "")); row.createCell(9).setCellValue(record.getQty());
					 * row.createCell(10).setCellValue(Optional.ofNullable(record.getUnit()).orElse(
					 * "")); row.createCell(11).setCellValue(record.getPriceHeader());
					 * 
					 * // Right-aligned cells for price, amount per item, advance amount, balance
					 * amount, paid amount row.createCell(12).setCellValue(String.format("%.2f",
					 * record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					 * row.getCell(12).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(13).setCellValue(record.getTaxPercentage());
					 * 
					 * row.createCell(14).setCellValue(record.getAmountPerItem() != null ?
					 * String.format("%.2f", record.getAmountPerItem()) : "");
					 * row.getCell(14).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(15).setCellValue( record.getAdvanceAmount() != null ?
					 * String.format("%.2f", record.getAdvanceAmount()) : "");
					 * row.getCell(15).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(16).setCellValue( record.getBalanceAmount() != null ?
					 * String.format("%.2f", record.getBalanceAmount()) : "");
					 * row.getCell(16).setCellStyle(rightAlignedStyle);
					 * 
					 * row.createCell(17).setCellValue(Optional.ofNullable(record.getPaymentType()).
					 * orElse(""));
					 * row.createCell(18).setCellValue(Optional.ofNullable(record.getReferenceNumber
					 * ()).orElse(""));
					 * 
					 * // Right-aligned paid amount
					 * row.createCell(19).setCellValue(record.getPaidAmount() != null ?
					 * String.format("%.2f", record.getPaidAmount().doubleValue()) : "");
					 * row.getCell(19).setCellStyle(rightAlignedStyle);
					 */
				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Purchase Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}

	/* EXCEL FOR SALE ORDER INVOICE */

	public ResponseEntity<byte[]> getWORKORDERReportExcelforinvoice(String POType, String vendorId, String po_cat,
			String woId, String orderDates, String from_dates, String to_dates)
			throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Build Query
		StringBuilder hql = new StringBuilder("FROM SALES_invoice_TABLE a WHERE 1=1");

		if (woId != null && !woId.isEmpty())
			hql.append(" AND a.woId LIKE :woId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

		// Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

		// Execute Query
		Query<SALES_invoice_TABLE> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				SALES_invoice_TABLE.class);

		if (woId != null && !woId.isEmpty())
			query.setParameter("woId", "%" + woId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

		// Get the result list safely
		List<SALES_invoice_TABLE> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Sale Invoice");

			// Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Sale Invoice Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

			// Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "WO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

			/*
			 * String[] headers1 = { "SRL NO",
			 * "PO ID","ORDER DATE","VENDOR ID","VENDOR NAME", "ITEM CODE",
			 * "ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM","TOTAL VALUE",
			 * "TAX AMOUNT", "TOTAL AMOUNT"};
			 */

			// Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			// Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			// Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
			// Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			// Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
			// Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					SALES_invoice_TABLE record = list.get(i);

					String currentPoId = Optional.ofNullable(record.getWoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

					// Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);

					// Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
					// Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */

					row.createCell(4).setCellValue(record.getVendor_name());

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */

					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty());

					/* 8 */

					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);
					/* 10 */

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());

					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());

					
				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

			// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Sale Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}

////////////////////////////////Excel for Purchase Return Report 
	public ResponseEntity<byte[]> getPoReportExcelforpurchasereurn(String POType, String vendorId, String po_cat, String poId,
			String orderDates, String from_dates, String to_dates) throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

//Build Query
		StringBuilder hql = new StringBuilder("FROM PO_Return_Entity a WHERE 1=1");

		if (poId != null && !poId.isEmpty())
			hql.append(" AND a.poId LIKE :poId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

//Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

//Execute Query
		Query<PO_Return_Entity> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				PO_Return_Entity.class);

		if (poId != null && !poId.isEmpty())
			query.setParameter("poId", "%" + poId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

//Get the result list safely
		List<PO_Return_Entity> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Purchase Return");

//Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Purchase Return Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

//Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "PO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

//Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

//Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
//Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
//Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

//Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
//Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					PO_Return_Entity record = list.get(i);

					/* ----forvendorname-- */
					String vendorname = getVendorNameById(record.getVendorId());

					String currentPoId = Optional.ofNullable(record.getPoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

//Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);
					
					String status = record.getApprovedFlg().equals("R") ? "PURCHASE RETURN" : "";
					


					
					

//Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
//Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */
					row.createCell(4).setCellValue(vendorname);

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */
					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty());

					/* 8 */
					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());
					/* 10 */



					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());
					
					/* ---STATUS-- */
					//row.createCell(14).setCellValue(status);
					/*
					 * if (!status.isEmpty()) { row.createCell(14).setCellValue(status); }
					 */
				

				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

//Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Purchase Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}
	
	
	/* EXCEL FOR SALE RETURN */

	public ResponseEntity<byte[]> getWORKORDERReportExcelforsalereturn(String POType, String vendorId, String po_cat,
			String woId, String orderDates, String from_dates, String to_dates)
			throws JRException, SQLException, IOException {

		System.out.println("the daily date" + orderDates);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); // Ensures strict parsing

		Date orderDate = null;
		Date from_date = null;
		Date to_date = null;

		System.out.println("the daily date" + orderDates);

		try {
			if (orderDates != null && !orderDates.isEmpty()) {
				orderDate = new java.sql.Date(dateFormat.parse(orderDates).getTime());
			}
			if (from_dates != null && !from_dates.isEmpty()) {
				from_date = new java.sql.Date(dateFormat.parse(from_dates).getTime());
			}
			if (to_dates != null && !to_dates.isEmpty()) {
				to_date = new java.sql.Date(dateFormat.parse(to_dates).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Build Query
		StringBuilder hql = new StringBuilder("FROM Sales_Return a WHERE 1=1");

		if (woId != null && !woId.isEmpty())
			hql.append(" AND a.woId LIKE :woId");
		if (vendorId != null && !vendorId.isEmpty())
			hql.append(" AND a.vendorId = :vendorId");
		if (orderDate != null)
			hql.append(" AND a.orderDate = :orderDate");
		if (from_date != null && to_date != null)
			hql.append(" AND a.orderDate BETWEEN :from_date AND :to_date");

		// Debug Query Before Execution
		System.out.println("Generated HQL: " + hql.toString());
		System.out.println("From Date: " + from_date);
		System.out.println("To Date: " + to_date);

		System.out.println("tyhe vendor id" + vendorId);

		// Execute Query
		Query<Sales_Return> query = sessionFactory.getCurrentSession().createQuery(hql.toString(),
				Sales_Return.class);

		if (woId != null && !woId.isEmpty())
			query.setParameter("woId", "%" + woId + "%");
		if (vendorId != null && !vendorId.isEmpty())
			query.setParameter("vendorId", vendorId);
		if (orderDate != null)
			query.setParameter("orderDate", orderDate);
		if (from_date != null && to_date != null) {
			query.setParameter("from_date", from_date);
			query.setParameter("to_date", to_date);
		}

		// Get the result list safely
		List<Sales_Return> list = query.getResultList();

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Sheet sheet = workbook.createSheet("Sale Return");

			// Title Row
			Row titleRow = sheet.createRow(0);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Sale Return Type: " + POType);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

			CellStyle titleStyle = workbook.createCellStyle();
			org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
			titleFont.setBold(true);
			titleStyle.setFont(titleFont);
			titleCell.setCellStyle(titleStyle);

			// Create Header Row with all fields from your SELECT query
			Row headerRow = sheet.createRow(1);
			/*
			 * String[] headers = { "SRL NO", "ORDER NO", "PO ID", "ITEM CODE", "HSN CODE",
			 * "VENDOR ID", "ORDER DATE", "DUE DATE", "ITEM", "QTY", "UNIT", "TAX STATUS",
			 * "PRICE", "TAX %", "AMOUNT PER ITEM", "ADVANCE AMOUNT", "BALANCE AMOUNT",
			 * "PAYMENT TYPE", "REFERENCE NUMBER", "PAID AMOUNT" };
			 */

			String[] headers = { "SRL NO", "WO ID", "ORDER DATE", "VENDOR ID", "VENDOR NAME", "ITEM CODE",
					"ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM", "TOTAL VALUE", "TAX AMOUNT",
					"TOTAL AMOUNT" };

			/*
			 * String[] headers1 = { "SRL NO",
			 * "PO ID","ORDER DATE","VENDOR ID","VENDOR NAME", "ITEM CODE",
			 * "ITEM DESCRIPTION", "UOM", "TAX STATUS", "QTY", "PRICE/ITEM","TOTAL VALUE",
			 * "TAX AMOUNT", "TOTAL AMOUNT"};
			 */

			// Apply Header Style
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);

			// Set header alignment to center
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			// Create a CellStyle for right alignment
			CellStyle rightAlignedStyle = workbook.createCellStyle();
			rightAlignedStyle.setAlignment(HorizontalAlignment.RIGHT);
			// Create a CellStyle for center alignment
			CellStyle centerAlignedStyle = workbook.createCellStyle();
			centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
			centerAlignedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

			// Loop through headers and apply header style
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);
				sheet.setColumnWidth(i, 6000);

			}
			// Data Rows
			if (list != null && !list.isEmpty()) {

				int rowIndex1 = 2; // Start after the header row
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

				for (int i = 0; i < list.size(); i++) {
					Sales_Return record = list.get(i);

					String currentPoId = Optional.ofNullable(record.getWoId()).orElse("");

					BigDecimal taxamount = Optional.ofNullable(record.getTaxAmount()).orElse(BigDecimal.ZERO);
					BigDecimal amountPerItem = Optional.ofNullable(record.getAmountPerItem()).orElse(BigDecimal.ZERO);

					// Correct way to add BigDecimal values
					BigDecimal totalAmount = taxamount.add(amountPerItem);

					// Create new row for the current PO record
					Row row = sheet.createRow(rowIndex1++);
					// Fill row data
					/* ---0-- */
					row.createCell(0).setCellValue(rowIndex1 - 2);
					row.getCell(0).setCellStyle(centerAlignedStyle);

					/*--1--*/
					row.createCell(1).setCellValue(currentPoId);

					row.createCell(2).setCellValue(
							record.getOrderDate() != null ? dateFormat1.format(record.getOrderDate()) : "");

					/* 2 */
					row.createCell(3).setCellValue(Optional.ofNullable(record.getVendorId()).orElse(""));
					/* 3 */

					row.createCell(4).setCellValue(record.getVendor_name());

					/* 4 */
					row.createCell(5).setCellValue(Optional.ofNullable(record.getItemcode()).orElse(""));

					/* 4 */
					row.createCell(6).setCellValue(Optional.ofNullable(record.getItem()).orElse(""));

					/* 5 */

					row.createCell(7).setCellValue(record.getUnit());

					/* 6 */
					row.createCell(8).setCellValue(record.getPriceHeader());

					/* 7 */
					row.createCell(9).setCellValue(record.getQty().doubleValue());

					/* 8 */

					/* 9 */
					row.createCell(10).setCellValue(
							String.format("%.2f", record.getPrice() != null ? record.getPrice().doubleValue() : 0.0));
					row.getCell(10).setCellStyle(rightAlignedStyle);
					/* 10 */

					row.createCell(11).setCellValue(record.getAmountPerItem().doubleValue());

					row.createCell(12).setCellValue(record.getTaxAmount().doubleValue());

					/* 11 */
					row.createCell(13).setCellValue(totalAmount.doubleValue());

					
				}
			} else {
				System.out.println("List is empty or null, no data to write.");
			}

			// Set Response Headers
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headersResponse.setContentDispositionFormData("attachment", "Sale Order.xlsx");

			workbook.write(outputStream);
			return ResponseEntity.ok().headers(headersResponse).body(outputStream.toByteArray());
		}
	}
	
	

}
