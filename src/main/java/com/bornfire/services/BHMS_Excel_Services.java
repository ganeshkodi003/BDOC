package com.bornfire.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.BHMSInventoryProductStock;
import com.bornfire.entities.BHMSInventoryProductStockCurrent;
import com.bornfire.entities.BhmsInvCgyMasterRep;
@Service
public class BHMS_Excel_Services {

	@Autowired
	BhmsInvCgyMasterRep bhmsInvCgyMasterRep;

	public  String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "MFR", "BATCH", "EXPIRY_DATE", "HSN_CODE", "PRODUCT_NAME", "PKG", "QUANTITY",
			"MRP", "RATE", "DISCOUNT_PERCENT", "DISCOUNT_AMOUNT", "GST_PERCENT", "AMOUNT", "GSTIN",
			"PURCHASED_FROM_NAME", "PURCHASE_DATE", "CATEGORY_NAME", "SUB_UNITS", "SUB_UNITS_COST", "NO_OF_UNITS" };
	static String SHEET = "Sheet1";
	
	
	
	
	
	public  List<BHMSInventoryProductStock> excelToBhmsInvStockMasterTable(InputStream is) {
		
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<BHMSInventoryProductStock> stocks = new ArrayList<BHMSInventoryProductStock>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				BHMSInventoryProductStock stock = new BHMSInventoryProductStock();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						
						System.out.println("inside loop");
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						//stock.setID(currentCell.getStringCellValue());
						String num = bhmsInvCgyMasterRep.srlnum();
						System.out.println(num+"number3");
						stock.setID("ID"+num);
						
						
						System.out.println(currentCell.getStringCellValue() + "      ID");
						break;

					case 1:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setMFR(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "      mfr");
						break;
					case 2:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setBATCH(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "     batch");
						break;
					case 3:
						stock.setEXPIRY_DATE(currentCell.getDateCellValue());
						System.out.println(currentCell.getDateCellValue() + "     exp date");
						break;
					case 4:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setHSN_CODE(currentCell.getStringCellValue());
						
						System.out.println( currentCell.getStringCellValue() + "    hsn");
						break;
					case 5:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setPRODUCT_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "     product name");
						break;
					case 6:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setPKG(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "    package type");
						break;
					case 7:
						stock.setUNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      units");
						break;
					case 8:
						stock.setMRP((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      mrp price ");
						break;
					case 9:
						stock.setRATE((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "        rate");
						break;
					case 10:
						stock.setDISCOUNT_PERCENT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "          discount perccent");
						break;
					case 11:
						stock.setDISCOUNT_AMOUNT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "        discount amt");
						break;
					case 12:
						stock.setGST_PERCENT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "     gst percent");
						break;
					case 13:
						stock.setAMOUNT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      amount");
						break;
					case 14:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setGSTIN(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "       gstin");
						break;
					case 15:

						stock.setPURCHASED_FROM_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "        purhchased from");
						break;
					case 16:
						stock.setPURCHASE_DATE(currentCell.getDateCellValue());
						System.out.println(currentCell.getDateCellValue() + "     purchase date");
						break;
					case 17:
						stock.setCATEGORY_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "        category name");
						break;
					case 18:
						stock.setSUB_UNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      sub units");
						break;
					case 19:
						stock.setSUB_UNITS_COST((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      sub units cost");
						break;
					case 20:
						stock.setNO_OF_UNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      no of units ");
						break;
					default:
						break;
					}

					cellIdx++;
				}
				stocks.add(stock);

			}
			workbook.close();

			return stocks;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	
	
	public  List<BHMSInventoryProductStockCurrent> excelToBHMSInventoryProductStockCurrent(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<BHMSInventoryProductStock> stocks = new ArrayList<BHMSInventoryProductStock>();
			List<BHMSInventoryProductStockCurrent> currentstocks = new ArrayList<BHMSInventoryProductStockCurrent>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				BHMSInventoryProductStock stock = new BHMSInventoryProductStock();
				BHMSInventoryProductStockCurrent currentstock = new BHMSInventoryProductStockCurrent();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						//stock.setID(currentCell.getStringCellValue());s
						//currentstock.setID(currentCell.getStringCellValue());
						
						String num = bhmsInvCgyMasterRep.srlnum();
						System.out.println(num+"number3");
						currentstock.setID("ID"+num);
						
						System.out.println(currentCell.getStringCellValue() + "      id");
						break;
					
					case 1:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setMFR(currentCell.getStringCellValue());
						currentstock.setMFR(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "      mfr");
						break;
					case 2:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setBATCH(currentCell.getStringCellValue());
						currentstock.setBATCH(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "     batch");
						break;
					case 3:
						stock.setEXPIRY_DATE(currentCell.getDateCellValue());
						currentstock.setEXPIRY_DATE(currentCell.getDateCellValue());
						System.out.println(currentCell.getDateCellValue() + "     exp date");
						break;
					case 4:
						
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setHSN_CODE(currentCell.getStringCellValue());
						
						System.out.println(currentCell.getStringCellValue() + "    hsn");
						break;
					case 5:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setPRODUCT_NAME(currentCell.getStringCellValue());
						currentstock.setPRODUCT_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "     product name");
						break;
					case 6:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setPKG(currentCell.getStringCellValue());
						currentstock.setPKG(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "    package type");
						break;
					case 7:
						stock.setUNITS((Double) currentCell.getNumericCellValue());
						currentstock.setUNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      quantity");
						break;
					case 8:
						stock.setMRP((Double) currentCell.getNumericCellValue());
						currentstock.setMRP((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      mrp price ");
						break;
					case 9:
						stock.setRATE((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "        rate");
						break;
					case 10:
						stock.setDISCOUNT_PERCENT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "          discount perccent");
						break;
					case 11:
						stock.setDISCOUNT_AMOUNT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "        discount amt");
						break;
					case 12:
						stock.setGST_PERCENT((Double) currentCell.getNumericCellValue());
						currentstock.setGST_PERCENT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "     gst percent");
						break;
					case 13:
						stock.setAMOUNT((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      amount");
						break;
					case 14:
						if (currentCell.getCellType() == currentCell.CELL_TYPE_NUMERIC) {
							currentCell.setCellType(CellType.STRING);

						}
						stock.setGSTIN(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "       gstin");
						break;
					case 15:
						stock.setPURCHASED_FROM_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "        purhchased from");
						break;
					case 16:
						stock.setPURCHASE_DATE(currentCell.getDateCellValue());
						System.out.println(currentCell.getDateCellValue() + "     purchase date");
						break;
					case 17:
						stock.setCATEGORY_NAME(currentCell.getStringCellValue());
						currentstock.setCATEGORY_NAME(currentCell.getStringCellValue());
						System.out.println(currentCell.getStringCellValue() + "        category name");
						break;
					case 18:
						stock.setSUB_UNITS((Double) currentCell.getNumericCellValue());
						currentstock.setSUB_UNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      sub units");
						break;
					case 19:
						stock.setSUB_UNITS_COST((Double) currentCell.getNumericCellValue());
						currentstock.setSUB_UNITS_COST((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      sub units cost");
						break;
					case 20:
						stock.setNO_OF_UNITS((Double) currentCell.getNumericCellValue());
						currentstock.setNO_OF_UNITS((Double) currentCell.getNumericCellValue());
						System.out.println(currentCell.getNumericCellValue() + "      no of units");
						break;
					default:
						break;
					}

					cellIdx++;
				}
				
				currentstocks.add(currentstock);
				

			}
			workbook.close();

			
			return currentstocks;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
