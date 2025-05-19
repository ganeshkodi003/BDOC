package com.bornfire.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.Assosiate_Profile_Entity;

import com.bornfire.entities.CandEvalFormEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter; 	
@Service
@Transactional
public class ProjectMasterServices {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectMasterServices.class);
	
	@Autowired
	DataSource srcdataSource;

	@Autowired
	Environment env;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	FileUploadServices fileUploadServices;
	

	public String getSrlNoValue() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("000");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PMSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno=""+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	
	 

	
	  
/*	public File getTdsExcel(String a, String filetype) throws FileNotFoundException, JRException, SQLException {
	    String path = env.getProperty("output.exportpath");
	    String fileName = "";
	    File outputFile = null;

	    try {
	        logger.info("Getting Output file: Third_PARTY");

	        fileName = "Appointment" + a;

	        if ("pdf".equals(filetype)) {
	            fileName = fileName + ".pdf";
	        } else {
	            fileName = fileName + ".xlsx";
	        }

	        path = path + fileName;

	        logger.info("File Name: {}", fileName);
	        logger.info("Path: {}", path);
	        System.out.println(a);

	        InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/offer.jrxml");

	        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
	        logger.info("Compiled JasperReport successfully");

	        HashMap<String, Object> map = new HashMap<>();
	        map.put("refno", a);

	        if ("pdf".equals(filetype)) {
	            logger.info("Generating PDF...");
	            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	            JasperExportManager.exportReportToPdfFile(jp, path);
	            logger.info("PDF generated successfully");
	        } else {
	            logger.info("Generating XLSX...");
	            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jp));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
	            exporter.exportReport();
	            logger.info("XLSX generated successfully");
	        }

	        outputFile = new File(path);
	    } catch (Exception e) {
	        logger.error("Error generating TDS file", e);
	        // Optionally rethrow the exception if necessary
	        // throw new YourCustomException("Error generating TDS file", e);
	    }

	    return outputFile;
	}
*/
	
	public File getTdsExcel(String a, String filetype) throws FileNotFoundException, JRException, SQLException {
		 //  String path = "C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports\\";// Modify this to the desired directory in the C drive
		// String path = env.getProperty("output.exportpath");
		//String path="D:\\";
		String path=env.getProperty("output.exportpath");

     
        String fileName = "";
        File outputFile = null;

        try {
            logger.info("Getting Output file: Third_PARTY");

            fileName = "AppointmentLetter" + a;

            if ("pdf".equals(filetype)) {
                fileName = fileName + ".pdf";
            } else {
                fileName = fileName + ".xlsx";
            }

            path = path + fileName;

            logger.info("File Name: {}", fileName);
            logger.info("Path: {}", path);
            System.out.println(a);

            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/appointback.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
            logger.info("Compiled JasperReport successfully");

            HashMap<String, Object> map = new HashMap<>();
            map.put("reference", a);

            if ("pdf".equals(filetype)) {
                logger.info("Generating PDF...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
                logger.info("PDF generated successfully");
            } else {
                logger.info("Generating XLSX...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
                logger.info("XLSX generated successfully");
            }

            outputFile = new File(path);
        } catch (Exception e) {
            logger.error("Error generating TDS file", e);
            // Optionally rethrow the exception if necessary
            // throw new YourCustomException("Error generating TDS file", e);
        }

        return outputFile;
    }
	
	
	public File getofferExcel(String a, String filetype) throws FileNotFoundException, JRException, SQLException {
     // String path ="C:\\";
	    //String path = env.getProperty("output.exportpath");

		//String path = this.env.getProperty("output.exportpath");
      
      
      // Modify this to the desired directory in the C drive
		
		 //String path = env.getProperty("output.exportpath");
		String path=env.getProperty("output.exportpath");
        String fileName = "";
        File outputFile = null;

        try {
            logger.info("Getting Output file: Third_PARTY");

            fileName = "offerletter" + a;

            if ("pdf".equals(filetype)) {
                fileName = fileName + ".pdf";
            } else {
                fileName = fileName + ".xlsx";
            }

            path = path + fileName;

            logger.info("File Name: {}", fileName);
            logger.info("Path: {}", path);
            System.out.println(a);

            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/offerr1_1.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
            logger.info("Compiled JasperReport successfully");

            HashMap<String, Object> map = new HashMap<>();
            map.put("refnno", a);

            if ("pdf".equals(filetype)) {
                logger.info("Generating PDF...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
                logger.info("PDF generated successfully");
            } else {
                logger.info("Generating XLSX...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
                logger.info("XLSX generated successfully");
            }

            outputFile = new File(path);
        } catch (Exception e) {
            logger.error("Error generating TDS file", e);
            // Optionally rethrow the exception if necessary
            // throw new YourCustomException("Error generating TDS file", e);
        }

        return outputFile;
    }

	public File getsalExcel(String a,String ctc_eff_date, String filetype) throws FileNotFoundException, JRException, SQLException {
		// String path = "C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports\\";
		// String path = env.getProperty("output.exportpath");
	//	String path="D:\\";
		String path=env.getProperty("output.exportpath");

        String fileName = "";
        File outputFile = null;

        try {
            logger.info("Getting Output file: Third_PARTY");

            fileName = "Salary Structure" + a;

            if ("pdf".equals(filetype)) {
                fileName = fileName + ".pdf";
            } else {
                fileName = fileName + ".xlsx";
            }

            path = path + fileName;

            logger.info("File Name: {}", fileName);
            logger.info("Path: {}", path);
            System.out.println(a);

            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/salaryy.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
            logger.info("Compiled JasperReport successfully");

            HashMap<String, Object> map = new HashMap<>();
            map.put("salaryparam", a);
            map.put("ctc_date", ctc_eff_date);

            if ("pdf".equals(filetype)) {
                logger.info("Generating PDF...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
                logger.info("PDF generated successfully");
            } else {
                logger.info("Generating XLSX...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
                logger.info("XLSX generated successfully");
            }

            outputFile = new File(path);
        } catch (Exception e) {
            logger.error("Error generating TDS file", e);
            // Optionally rethrow the exception if necessary
            // throw new YourCustomException("Error generating TDS file", e);
        }

        return outputFile;
    }
	
	public File getctcpdf(String emp_no,String ctc_eff_date, String filetype) throws FileNotFoundException, JRException, SQLException {
		// String path = "C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports\\";
		// String path = env.getProperty("output.exportpath");
	//	String path="D:\\";
		String path=env.getProperty("output.exportpath");

        String fileName = "";
        File outputFile = null;

        try {
            logger.info("Getting Output file: Third_PARTY");

            fileName = "Salary Revision Covering Letter " + emp_no;

            if ("pdf".equals(filetype)) {
                fileName = fileName + ".pdf";
            } else {
                fileName = fileName + ".xlsx";
            }

            path = path + fileName;

            logger.info("File Name: {}", fileName);
            logger.info("Path: {}", path);
            System.out.println(emp_no);

            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/ctc.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
            logger.info("Compiled JasperReport successfully");

            HashMap<String, Object> map = new HashMap<>();
            map.put("salaryparam", emp_no);
            map.put("ctc_date", ctc_eff_date);

            if ("pdf".equals(filetype)) {
                logger.info("Generating PDF...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
                logger.info("PDF generated successfully");
            } else {
                logger.info("Generating XLSX...");
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
                logger.info("XLSX generated successfully");
            }

            outputFile = new File(path);
        } catch (Exception e) {
            logger.error("Error generating TDS file", e);
            // Optionally rethrow the exception if necessary
            // throw new YourCustomException("Error generating TDS file", e);
        }

        return outputFile;
    }
	
	public String UploadPO(String screenId, MultipartFile file, String userid, Assosiate_Profile_Entity Assosiate_Profile_Entity)
	        throws FileNotFoundException, SQLException, IOException, NullPointerException {
	    System.out.println(screenId);
	    
	    // Create an instance of FileUploadServices
	 //   FileUploadServices fileUploadServices = new FileUploadServices();
	    
	    // Call the non-static UploadPO method on the instance
	    String msg = fileUploadServices.UploadPO(userid, file, userid, Assosiate_Profile_Entity);
	    
	    return msg;
	}

	
	
	
	/*
	 * public File getappointmentsss(String a, String fileType) throws
	 * FileNotFoundException,JRException { // Assuming "output.exportpath" is the
	 * correct property name, uncomment the following line if using Spring
	 * Environment // String path = env.getProperty("output.exportpath"); // String
	 * path = "C:\\newbhrps\\bhrps\\BHRPS_NEW\\downloadreports\\"; // String path =
	 * env.getProperty("output.exportpath"); // String path =
	 * env.getProperty("output.exportpath"); // String path="D:\\"; String
	 * path=env.getProperty("output.exportpath");
	 * 
	 * String fileName = "Appointment"; File outputFile = null;
	 * 
	 * try { // Load JasperReport files try { InputStream[] jasperFiles = {
	 * this.getClass().getResourceAsStream("/static/jasper/appointmetorder.jrxml"),
	 * //
	 * this.getClass().getResourceAsStream("/static/jasper/appointmetorder.jrxml"),
	 * };
	 * 
	 * // Compile JasperReports JasperReport[] jasperReports = new
	 * JasperReport[jasperFiles.length]; for (int i = 0; i < jasperFiles.length;
	 * i++) { jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
	 * }
	 * 
	 * // Prepare parameters HashMap<String, Object> map = new HashMap<>();
	 * map.put("refernece", a);
	 * 
	 * // Fill JasperPrint for each report JasperPrint[] jasperPrints = new
	 * JasperPrint[jasperReports.length]; for (int i = 0; i < jasperReports.length;
	 * i++) { try (Connection connection = srcdataSource.getConnection()) {
	 * jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map,
	 * connection); } }
	 * 
	 * if ("pdf".equals(fileType)) { logger.info("Generating PDFs..."); for (int i =
	 * 0; i < jasperPrints.length; i++) { fileName = "Appointment_" + i + ".pdf"; //
	 * Unique file name for each PDF String pdfPath = path + File.separator +
	 * fileName; // Construct PDF path
	 * JasperExportManager.exportReportToPdfFile(jasperPrints[i], pdfPath);
	 * logger.info("PDF generated successfully: " + pdfPath); } } else {
	 * logger.info("Generating XLSX..."); // Code for XLSX generation }
	 * 
	 * } catch (Exception e) { logger.error("Error generating files", e); }
	 * 
	 * // Check if the file exists before returning if (new File(path).exists()) {
	 * outputFile = new File(path); } } catch (Exception e) {
	 * logger.error("Error generating files", e); }
	 * 
	 * return outputFile; }
	 */
	
	
	public File gettdsexcelbatchjob(String filename, String filetype, String a) {
		String path=env.getProperty("output.exportpath");
	    String fileName = "bankFile_excelDownload" + filetype;
	    File outputFile = null;

	    try {
	       try {
	          InputStream[] jasperFiles = new InputStream[]{this.getClass().getResourceAsStream("/static/jasper/batchjob.jrxml")};
	          JasperReport[] jasperReports = new JasperReport[jasperFiles.length];

	          for(int i = 0; i < jasperFiles.length; ++i) {
	             jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
	          }

	          HashMap<String, Object> map = new HashMap();
	          map.put("a", a);
	       
	          JasperPrint[] jasperPrints = new JasperPrint[jasperReports.length];

	          for(int i = 0; i < jasperReports.length; ++i) {
	             Connection connection = this.srcdataSource.getConnection();

	             try {
	                jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map, connection);
	             } catch (Throwable var21) {
	                if (connection != null) {
	                   try {
	                      connection.close();
	                   } catch (Throwable var20) {
	                      var21.addSuppressed(var20);
	                   }
	                }

	                throw var21;
	             }

	             if (connection != null) {
	                connection.close();
	             }
	          }

	          JasperPrint combinedJasperPrint = new JasperPrint();
	          JasperPrint[] var26 = jasperPrints;
	          int var14 = jasperPrints.length;

	          for(int var15 = 0; var15 < var14; ++var15) {
	             JasperPrint jasperPrint = var26[var15];
	             List<JRPrintPage> pages = jasperPrint.getPages();
	             Iterator var18 = pages.iterator();

	             while(var18.hasNext()) {
	                JRPrintPage page = (JRPrintPage)var18.next();
	                combinedJasperPrint.addPage(page);
	             }
	          }

	          fileName = fileName + ".xlsx";
	          path = path + File.separator + fileName;
	          SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
	          reportConfig.setSheetNames(new String[]{filename});
	          JRXlsxExporter exporter = new JRXlsxExporter();
	          exporter.setExporterInput(new SimpleExporterInput(combinedJasperPrint));
	          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
	          exporter.setConfiguration(reportConfig);
	          exporter.exportReport();
	       } catch (Exception var22) {
	          logger.error("Error generating TDS file", var22);
	       }

	       if ((new File(path)).exists()) {
	          outputFile = new File(path);
	       }
	    } catch (Exception var23) {
	       logger.error("Error generating TDS file", var23);
	    }

	    return outputFile;
	}
	
	public File payslip(String a,String t, String filetype) throws FileNotFoundException, JRException, SQLException {
	     // String path ="C:\\";
		    //String path = env.getProperty("output.exportpath");

			//String path = this.env.getProperty("output.exportpath");
	      
	      
	      // Modify this to the desired directory in the C drive
			
			 //String path = env.getProperty("output.exportpath");
			String path=env.getProperty("output.exportpath");
	        String fileName = "";
	        File outputFile = null;
	        System.out.println(a+t);

	        try {
	            logger.info("Getting Output file: Third_PARTY");

	            fileName = "payslip" + a;

	            if ("pdf".equals(filetype)) {
	                fileName = fileName + ".pdf";
	            } else {
	                fileName = fileName + ".xlsx";
	            }

	            path = path + fileName;

	            logger.info("File Name: {}", fileName);
	            logger.info("Path: {}", path);
	            System.out.println(a);

	            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/demopayslip.jrxml");

	            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
	            logger.info("Compiled JasperReport successfully");

	            HashMap<String, Object> map = new HashMap<>();
	            map.put("emp", t);
	            map.put("month",a);

	            if ("pdf".equals(filetype)) {
	                logger.info("Generating PDF...");
	                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	                JasperExportManager.exportReportToPdfFile(jp, path);
	                logger.info("PDF generated successfully");
	            } else {
	                logger.info("Generating XLSX...");
	                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	                JRXlsxExporter exporter = new JRXlsxExporter();
	                exporter.setExporterInput(new SimpleExporterInput(jp));
	                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
	                exporter.exportReport();
	                logger.info("XLSX generated successfully");
	            }

	            outputFile = new File(path);
	        } catch (Exception e) {
	            logger.error("Error generating TDS file", e);
	            // Optionally rethrow the exception if necessary
	            // throw new YourCustomException("Error generating TDS file", e);
	        }

	        return outputFile;
	    }

	
	public File payslippredim(String a,String filetype) throws FileNotFoundException, JRException, SQLException {
	     // String path ="C:\\";
		    //String path = env.getProperty("output.exportpath");

			//String path = this.env.getProperty("output.exportpath");
	      
	      
	      // Modify this to the desired directory in the C drive
			
			 //String path = env.getProperty("output.exportpath");
			String path=env.getProperty("output.exportpath");
	        String fileName = "";
	        File outputFile = null;
	        System.out.println(a);

	        try {
	            logger.info("Getting Output file: Third_PARTY");

	            fileName = "payslip" + a;

	            if ("pdf".equals(filetype)) {
	                fileName = fileName + ".pdf";
	            } else {
	                fileName = fileName + ".xlsx";
	            }

	            path = path + fileName;

	            logger.info("File Name: {}", fileName);
	            logger.info("Path: {}", path);
	            System.out.println(a);

	            InputStream jasperFile = this.getClass().getResourceAsStream("/static/jasper/PERDIEM_2.jrxml");

	            JasperReport jr = JasperCompileManager.compileReport(jasperFile);
	            logger.info("Compiled JasperReport successfully");

	            HashMap<String, Object> map = new HashMap<>();
	            
	            map.put("EMPNO",a);

	            if ("pdf".equals(filetype)) {
	                logger.info("Generating PDF...");
	                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	                JasperExportManager.exportReportToPdfFile(jp, path);
	                logger.info("PDF generated successfully");
	            } else {
	                logger.info("Generating XLSX...");
	                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
	                JRXlsxExporter exporter = new JRXlsxExporter();
	                exporter.setExporterInput(new SimpleExporterInput(jp));
	                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
	                exporter.exportReport();
	                logger.info("XLSX generated successfully");
	            }

	            outputFile = new File(path);
	        } catch (Exception e) {
	            logger.error("Error generating TDS file", e);
	            // Optionally rethrow the exception if necessary
	            // throw new YourCustomException("Error generating TDS file", e);
	        }

	        return outputFile;
	    }


}