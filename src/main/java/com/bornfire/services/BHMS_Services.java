package com.bornfire.services;

import java.io.IOException;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.ACCOUNT_LEDGER_PO_Entity;
import com.bornfire.entities.ACCOUNT_LEDGER_PO_Rep;
import com.bornfire.entities.ACCOUNT_LEDGER_SALE_Entity;
import com.bornfire.entities.ACCOUNT_LEDGER_SALE_Rep;
import com.bornfire.entities.AllowanceMaker;
import com.bornfire.entities.AllowancemakerRep;
import com.bornfire.entities.BGLSBusinessTable_Entity;
import com.bornfire.entities.BHMSInventoryProductStock;
import com.bornfire.entities.BHMSInventoryProductStockCurrent;
import com.bornfire.entities.BOM_ENTITY;
import com.bornfire.entities.BOM_REPO;
import com.bornfire.entities.BTMAdminOrganizationMaster;
import com.bornfire.entities.BTMAdminOrganizationMasterRep;
import com.bornfire.entities.BhmsInvCgyMasterRep;
import com.bornfire.entities.BhmsInvProductSaleMasterRep;
import com.bornfire.entities.BhmsInvProductSaleMasterTable;
import com.bornfire.entities.CapitalTrans;
import com.bornfire.entities.CapitalTransRep;
import com.bornfire.entities.Category;
import com.bornfire.entities.CategoryRep;
import com.bornfire.entities.Erp_ChartOfAccountsRep;
import com.bornfire.entities.Chart_Acc_Entity;
import com.bornfire.entities.Chart_Acc_Rep;
import com.bornfire.entities.CodeCreation;
import com.bornfire.entities.CodeCreationRep;
import com.bornfire.entities.ERP_EXPENSES_ENTITY;
import com.bornfire.entities.ERP_EXPENSES_REPO;
import com.bornfire.entities.EmployeeSalary;
import com.bornfire.entities.EmployeeSalaryRep;
import com.bornfire.entities.Erp_ChartOfAccounts;
import com.bornfire.entities.Erp_exp_category;
import com.bornfire.entities.Erp_exp_category_rep;
import com.bornfire.entities.Exp_item_rep;
import com.bornfire.entities.Exp_itemcreation_Entity;
import com.bornfire.entities.FINISHED_GOODS_ENTITY;
import com.bornfire.entities.FINISHED_GOODS_Rep;
import com.bornfire.entities.Follow_Up_Entity;
import com.bornfire.entities.Follow_Up_Rep;
import com.bornfire.entities.GeneralLedgerEntity;
import com.bornfire.entities.GeneralLedgerRep;
import com.bornfire.entities.General_journal_Rep;
import com.bornfire.entities.General_journal_entity;
import com.bornfire.entities.Inward;
import com.bornfire.entities.InwardRep;
import com.bornfire.entities.ItemCreation;
import com.bornfire.entities.ItemCreationRep;
import com.bornfire.entities.New_product_Rep;
import com.bornfire.entities.New_product_entity;
import com.bornfire.entities.PO_Return_Entity;
import com.bornfire.entities.PO_Return_Rep;
import com.bornfire.entities.PO_invoice_Pay_Out_Rep;
import com.bornfire.entities.PO_invoice_Pay_Out_entity;
import com.bornfire.entities.PO_invoice_entity;
import com.bornfire.entities.PO_invoice_rep;
import com.bornfire.entities.PROCESS_ENTITY;
import com.bornfire.entities.PROCESS_REP;
import com.bornfire.entities.PURCHASE_ORDER_ENTITY_NEW;
import com.bornfire.entities.PURCHASE_ORDER_ENTITY_NEW_REP;
import com.bornfire.entities.Pament_out;
import com.bornfire.entities.PaymentIn;
import com.bornfire.entities.PaymentInRep;
import com.bornfire.entities.Payment_Out_Rep;
import com.bornfire.entities.Purchase_Order_Entity;
import com.bornfire.entities.Purchase_Order_Repo;
import com.bornfire.entities.SALES_ORDER_ENTITY_NEW;
import com.bornfire.entities.SALES_ORDER_ENTITY_NEW_REP;
import com.bornfire.entities.SALES_invoice_TABLE;
import com.bornfire.entities.SALES_invoice_TABLERep;
import com.bornfire.entities.SALES_invoice_TABLE_PAY_IN;
import com.bornfire.entities.SALES_invoice_TABLE_PAY_IN_REP;
import com.bornfire.entities.SalesOut;
import com.bornfire.entities.SalesOutRep;
import com.bornfire.entities.Sales_Return;
import com.bornfire.entities.Sales_ReturnRep;
import com.bornfire.entities.TSK_PROCESS_ENTITY;
import com.bornfire.entities.TSK_PROCESS_REP;
import com.bornfire.entities.Transaction_table;
import com.bornfire.entities.Transaction_table_demo;
import com.bornfire.entities.Transaction_table_rep;
import com.bornfire.entities.TravelClaim;
import com.bornfire.entities.TravelClaimRep;
import com.bornfire.entities.VendorCreation;
import com.bornfire.entities.VendorCreationRep;
import com.bornfire.entities.WorkOrder;
import com.bornfire.entities.WorkOrderRep;
import com.bornfire.entities.category_creation;
import com.bornfire.entities.category_creationRep;
import com.bornfire.entities.Transaction_table_demo_rep;
@Transactional
@Service
public class BHMS_Services {
	
	@Autowired CodeCreationRep codecreationrep;
	
	@Autowired
	Erp_ChartOfAccountsRep Erp_ChartOfAccountsRep;

	@Autowired
	CategoryRep Categoryrep;
	
	@Autowired
	Transaction_table_demo_rep Transaction_table_demo_rep;
	
	@Autowired
	ACCOUNT_LEDGER_PO_Rep ACCOUNT_LEDGER_PO_rep;

	@Autowired
	ACCOUNT_LEDGER_SALE_Rep ACCOUNT_LEDGER_SALE_rep;
	
	@Autowired
	CapitalTransRep CapitalTransRep;
	@Autowired
	Transaction_table_rep Transaction_table_Rep;
	@Autowired
	TravelClaimRep TravelClaimRep;
	
	@Autowired
	AllowancemakerRep allowancemakerrep;
	
	@Autowired
	GeneralLedgerRep generalLedgerRep;
	@Autowired
	Sales_ReturnRep Sales_ReturnRep;
	@Autowired
	Payment_Out_Rep Payment_Out_Reps;
	
	@Autowired
	PaymentInRep PaymentInRep;
	
	@Autowired
	PURCHASE_ORDER_ENTITY_NEW_REP PURCHASE_ORDER_ENTITY_NEW_rep;
	
	@Autowired
	SALES_invoice_TABLERep SALES_invoice_TABLERep;
	@Autowired
	SALES_ORDER_ENTITY_NEW_REP SALES_ORDER_ENTITY_NEW_REP;
	
	@Autowired
	SALES_ORDER_ENTITY_NEW_REP SALES_ORDER_ENTITY_NEW_rep;
	
	@Autowired
	General_journal_Rep General_journal_rep;
	
	@Autowired
	ItemCreationRep ItemCreationRep;

	@Autowired
	BHMS_Excel_Services BHMS_Excel_services;
	
	@Autowired
	VendorCreationRep VendorCreationRep;

	@Autowired
	SessionFactory sessionFactory;
	

	@Autowired
	Chart_Acc_Rep chart_Acc_Reps;
	
	@Autowired
	WorkOrderRep WorkOrderRep;

	@Autowired
	PROCESS_REP PROCESS_rep;

	@Autowired
	BhmsInvCgyMasterRep bhmsInvCgyMasterRep;

	@Autowired
	com.bornfire.entities.BHMSInventoryProductStockRepo BHMSInventoryProductStockRepo;

	@Autowired
	com.bornfire.entities.BHMSInventoryProductStockCurrentRepo BHMSInventoryProductStockCurrentRepo;

	@Autowired
	BhmsInvProductSaleMasterRep bhmsInvProductSaleMasterRep;

	@Autowired
	SalesOutRep salesOutRep;
	public String createSaleMedicine(List<BhmsInvProductSaleMasterTable> saleMasterDataList) {

		String response;
		try {
			List<PROCESS_ENTITY> stockList = PROCESS_rep.findAlldatass(); // Fetch stock data once

			for (BhmsInvProductSaleMasterTable param : saleMasterDataList) {
				bhmsInvProductSaleMasterRep.save(param);

				for (PROCESS_ENTITY stock : stockList) {
					// Logging product and batch names
					String productName = param.getProduct_name();
					String batchName = param.getBatch();
					System.out.println("Product name: " + productName);
					System.out.println("Batch name: " + batchName);

					if (productName != null && batchName != null && productName.equals(stock.getMake_product_name())
							&& batchName.equals(stock.getRef_id())) {
						System.out.println("Updating Stock:");
						System.out.println("Product name: " + productName);
						System.out.println("Batch name: " + batchName);

						double currentUnits = Double.parseDouble(stock.getUnitinkg());
						double soldQuantity = Double.parseDouble(param.getQuantity());

						double updatedUnits = currentUnits - soldQuantity;
						stock.setUnitinkg(String.valueOf(updatedUnits));
						PROCESS_rep.save(stock);

					}
				}
			}

			response = "Record added successfully";
		} catch (Exception e) {
			response = "Something went wrong at server end";
		}

		return response;
	}

	public List<BhmsInvProductSaleMasterTable> getSaleReport(String sid) {

		List<BhmsInvProductSaleMasterTable> list = bhmsInvProductSaleMasterRep.findAllCustomId(sid);

		return list;
	}

	public String deletestockLock(String user_id, String product_name, String category_name, String qty, String batch) {

		String response = "";

		try {

			BHMSInventoryProductStockCurrent stockData = BHMSInventoryProductStockCurrentRepo.findPackdata(product_name,
					batch);

			Double numberQty = 0D;
			Double stripqty = 0D;
			if (category_name.equalsIgnoreCase("tablet") || category_name.equalsIgnoreCase("strip")) {
				numberQty = Double.valueOf(stockData.getNO_OF_UNITS()) + (Double.valueOf(qty));
				stripqty = numberQty / Double.valueOf(stockData.getSUB_UNITS());

				stockData.setUNITS(Double.valueOf(stripqty));
				stockData.setNO_OF_UNITS(Double.valueOf(numberQty));

			} else {
				numberQty = Double.valueOf(stockData.getNO_OF_UNITS()) + (Double.valueOf(qty));

				stockData.setUNITS(Double.valueOf(numberQty));
				stockData.setSUB_UNITS(Double.valueOf(numberQty));
				stockData.setNO_OF_UNITS(Double.valueOf(numberQty));

			}

			BHMSInventoryProductStockCurrentRepo.save(stockData);
			response = "success";

		} catch (Exception e) {
			System.out.println(e);
			response = "Something went wrong at server end";
		}

		return response;
	}

	public String newstockLock(String user_id, String product_name, String category_name, String qty, String batch) {

		String response = "";

		try {

			BHMSInventoryProductStockCurrent stockData = BHMSInventoryProductStockCurrentRepo.findPackdata(product_name,
					batch);

			Double numberQty = 0D;
			Double stripqty = 0D;
			if (category_name.equalsIgnoreCase("tablet") || category_name.equalsIgnoreCase("strip")) {
				numberQty = Double.valueOf(stockData.getNO_OF_UNITS()) - (Double.valueOf(qty));
				stripqty = numberQty / Double.valueOf(stockData.getSUB_UNITS());

				stockData.setUNITS(Double.valueOf(stripqty));
				stockData.setNO_OF_UNITS(Double.valueOf(numberQty));

			} else {
				numberQty = Double.valueOf(stockData.getNO_OF_UNITS()) - (Double.valueOf(qty));

				stockData.setUNITS(Double.valueOf(numberQty));
				stockData.setSUB_UNITS(Double.valueOf(numberQty));
				stockData.setNO_OF_UNITS(Double.valueOf(numberQty));

			}

			BHMSInventoryProductStockCurrentRepo.save(stockData);
			response = "success";

		} catch (Exception e) {
			response = "Something went wrong at server end";
		}

		return response;
	}

	public List<Object[]> getStocksList(String product_id) {

		List<Object[]> list = BHMSInventoryProductStockCurrentRepo.findAllCustom(product_id);
		// List<Object[]> list = PROCESS_rep.findAllCustomss(product_id);
		return list;
	}

	public List<Object[]> getStocksListss(String product_id) {

		// List<Object[]> list =
		// BHMSInventoryProductStockCurrentRepo.findAllCustom(product_id);
		List<Object[]> list = PROCESS_rep.findAllCustomss(product_id);
		return list;
	}

	public List<Object[]> getStockTypeList(String product_name, String batch) {

		List<Object[]> list = BHMSInventoryProductStockCurrentRepo.findallstocktype(product_name, batch);
		List<Object[]> list2 = BHMSInventoryProductStockCurrentRepo.findallexpirydate(product_name, batch);

		if (list.isEmpty()) {
			return list;
		} else {
			return list2;
		}

	}

	public List<Object[]> getProductStockCurrentfn(String product_name, String batch) {
		List<Object[]> stock = BHMSInventoryProductStockCurrentRepo.productStockCurrent(product_name, batch);
		return stock;
	}

	public String stockHistory(BHMSInventoryProductStock stocks, Date exppdate2, Date purrdate2, byte[] files) {
		String response = "";
		try {
			String num = bhmsInvCgyMasterRep.srlnum();
			System.out.println("Serial number: " + num);

			// Directly assign the dates without re-parsing
			stocks.setEXPIRY_DATE(exppdate2);
			stocks.setPURCHASE_DATE(purrdate2);
			stocks.setID("ID" + num);
			stocks.setStatus("pending");

			// Set the file if it exists and is not empty
			if (files != null && files.length > 0) {
				stocks.setFiledata(files);
				System.out.println("File data attached.");
			} else {
				System.out.println("No file provided or file is empty");
			}

			// Save the stock
			BHMSInventoryProductStockRepo.save(stocks);
			System.out.println("Stock history saved successfully.");
			response = "Stock History added successfully";
		} catch (Exception e) {
			e.printStackTrace(); // Print the stack trace for debugging
			response = "Error: " + e.getMessage(); // Return the error message for debugging
		}
		return response;
	}

	public ResponseEntity<String> add_Tax_param(List<PROCESS_ENTITY> PROCESS_ENTITY, String formmode,String woid) {

		ResponseEntity<String> msg = null;
		try {
			if (formmode.equals("add")) {
				Session session = sessionFactory.getCurrentSession();

				try {
					BigDecimal ref_id = (BigDecimal) session
							.createNativeQuery("SELECT NEXT VALUE FOR PROCESS_ONE AS SRL_NO").getSingleResult();
					

					for (PROCESS_ENTITY param : PROCESS_ENTITY) {
						BigDecimal id = (BigDecimal) session
								.createNativeQuery("SELECT NEXT VALUE FOR PROCESS_ID AS SRL_NO").getSingleResult();

						param.setId(String.valueOf(id));
						param.setRef_id("REF00" + ref_id);
						param.setStatus("work in progress");
						param.setWoid(woid);
						if(woid!=null) {
							WorkOrder wo =WorkOrderRep.getinquirywolist(woid);
							wo.setStatus("work in progress");
							wo.setRefid("REF00" + ref_id);
							WorkOrderRep.save(wo);
							}
						
						PROCESS_rep.save(param);

						// Retrieve current stock once and process it
						List<BHMSInventoryProductStockCurrent> up = BHMSInventoryProductStockCurrentRepo
								.productStockCurrentList1();
						for (BHMSInventoryProductStockCurrent stock : up) {
							if (stock.getPRODUCT_NAME().equals(param.getPname())
									&& stock.getBATCH().equals(param.getBatch())) {
								System.out.println("The product name is : " + param.getPname());
								System.out.println("The batch name is : " + param.getBatch());

								double updatedUnits = stock.getUNITS() - Double.valueOf(param.getQty());
								stock.setUNITS(updatedUnits);
								BHMSInventoryProductStockCurrentRepo.save(stock);
								break; // Exit loop after updating
							}
						}
					}
					msg = ResponseEntity.ok("Product Added Successfully.");
				} catch (Exception e) {
					// Log the exception and set an appropriate response
					msg = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("An error occurred: " + e.getMessage());
				}
			}
			/*
			 * else if ("edit".equalsIgnoreCase(formmode)) { for (PROCESS_ENTITY param :
			 * PROCESS_ENTITY) { if (param.getSrl_no() == null) { // Add new record if
			 * srl_no is null Session session = sessionFactory.getCurrentSession();
			 * BigDecimal srl_no = (BigDecimal)
			 * session.createNativeQuery("SELECT TAX_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
			 * .getSingleResult(); param.setSrl_no(srl_no); param.setStatus("Pending");
			 * PROCESS_rep.save(param); } else { // Update existing record if srl_no is not
			 * null Optional<PROCESS_ENTITY> existingRecordOptional =
			 * Taxation_parameter_rep.findById(param.getSrl_no());
			 * 
			 * if (existingRecordOptional.isPresent()) { PROCESS_ENTITY existingRecord =
			 * existingRecordOptional.get();
			 * existingRecord.setTax_code(param.getTax_code());
			 * existingRecord.setMin_amount(param.getMin_amount());
			 * existingRecord.setMax_amount(param.getMax_amount());
			 * existingRecord.setTax_value(param.getTax_value());
			 * existingRecord.setFjd(param.getFjd()); existingRecord.setStatus("Pending");
			 * existingRecord.setNational(param.getNational());
			 * 
			 * if (param.getNational().equals("yes")) { //
			 * existingRecord.setSpecify_country(param.getSpecify_country());
			 * existingRecord.setNational_country(""); System.out.println("insideeeee"); }if
			 * (param.getNational().equals("no") ) {
			 * existingRecord.setNational_country(param.getNational_country()); //
			 * existingRecord.setSpecify_country(""); }
			 * 
			 * PROCESS_rep.save(existingRecord); } } } msg=
			 * ResponseEntity.ok("Parameters updated successfully."); }
			 */
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Parameters update unsuccessful.");
		}

	}

	public String completion(String ref_id, String qty) {
		String msg = null;

		try {
			List<PROCESS_ENTITY> list = PROCESS_rep.findByID(ref_id);

			if (list != null && !list.isEmpty()) {
				System.out.println("The list size is " + list.size());

				for (PROCESS_ENTITY up : list) {
					up.setUnitinkg(qty);
					up.setStatus("Completed");
					PROCESS_rep.save(up);
					
					WorkOrder wo =WorkOrderRep.getinquirywolist(up.getWoid());
					wo.setStatus("Completed");
					WorkOrderRep.save(wo);
				}

				msg = "Process with ID " + ref_id + " completed successfully.";
			} else {
				msg = "No process found with ID " + ref_id + ".";
			}
		} catch (Exception e) {
			System.err.println("An error occurred during the completion process: " + e.getMessage());
			msg = "An error occurred while completing the process. Please try again later.";
		}

		return msg;
	}

	public String rejection(String ref_id) {
		String msg = null;

		try {
			List<PROCESS_ENTITY> list = PROCESS_rep.findByID(ref_id);

			if (list != null && !list.isEmpty()) {
				System.out.println("The list size is " + list.size());

				for (PROCESS_ENTITY up : list) {
					up.setStatus("Rejected");
					PROCESS_rep.save(up);
					WorkOrder wo =WorkOrderRep.getinquirywolist(up.getWoid());
					wo.setStatus("Rejected");
					WorkOrderRep.save(wo);

				}

				msg = "Process with ID " + ref_id + " Rejected successfully.";
			} else {
				msg = "No process found with ID " + ref_id + ".";
			}
		} catch (Exception e) {
			System.err.println("An error occurred during the Rejection process: " + e.getMessage());
			msg = "An error occurred while completing the process. Please try again later.";
		}

		return msg;
	}

	public List<BHMSInventoryProductStockCurrent> getStockUnitsLists(String product_name) {

		String cn1 = "PRODUCT_NAME='" + product_name + "'";

		System.out.println(cn1);
		@SuppressWarnings("unchecked")
		List<BHMSInventoryProductStockCurrent> list = (List<BHMSInventoryProductStockCurrent>) sessionFactory
				.getCurrentSession().createQuery(" from BHMSInventoryProductStockCurrent where " + cn1).getResultList();
		return list;
	}

	public BHMSInventoryProductStockCurrent getstockeditt(String id) {
		BHMSInventoryProductStockCurrent data = (BHMSInventoryProductStockCurrent) sessionFactory.getCurrentSession()
				.createQuery(" from BHMSInventoryProductStockCurrent where ID='" + id + "'").getSingleResult();
		return data;
	}

	public String updateStockCurrent(BHMSInventoryProductStockCurrent meMaster, String id) {
		System.out.println("The id is " + id);
		String response = "";
		String idd = meMaster.getID();
		System.out.println("The id is " + idd);
		Optional<BHMSInventoryProductStockCurrent> isData = BHMSInventoryProductStockCurrentRepo.findById(id);
		if (isData.isPresent()) {
			BHMSInventoryProductStockCurrent cgyData = isData.get();
			String mfr = cgyData.getMFR();
			String batch = cgyData.getBATCH();
			String cat_name = cgyData.getCATEGORY_NAME();
			String pro_name = cgyData.getPRODUCT_NAME();
			String pkg = cgyData.getPKG();
			Double units = cgyData.getUNITS();
			Double mrp = cgyData.getMRP();
			Double sub_units = cgyData.getSUB_UNITS();
			Double sub_units_cost = cgyData.getSUB_UNITS_COST();
			Double no_units = cgyData.getNO_OF_UNITS();
			Double gst_percentage = cgyData.getGST_PERCENT();
			if (meMaster.getMFR().equals(mfr) && meMaster.getBATCH().equals(batch)
					&& meMaster.getCATEGORY_NAME().equals(cat_name) && meMaster.getPRODUCT_NAME().equals(pro_name)
					&& meMaster.getPKG().equals(pkg) && meMaster.getUNITS().equals(units)
					&& meMaster.getMRP().equals(mrp) && meMaster.getSUB_UNITS().equals(sub_units)
					&& meMaster.getSUB_UNITS_COST().equals(sub_units_cost) && meMaster.getNO_OF_UNITS().equals(no_units)
					&& meMaster.getGST_PERCENT().equals(gst_percentage)) {
				response = "No Modification Done";
			} else {
				BHMSInventoryProductStockCurrentRepo.save(meMaster);

				response = "Medical Expense updated successfully";
			}
		}
		return response;
	}

	public String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "ID", "MFR", "BATCH", "EXPIRY_DATE", "HSN_CODE", "PRODUCT_NAME", "PKG", "QUANTITY",
			"MRP", "RATE", "DISCOUNT_PERCENT", "DISCOUNT_AMOUNT", "GST_PERCENT", "AMOUNT", "GSTIN",
			"PURCHASED_FROM_NAME", "PURCHASE_DATE", "CATEGORY_NAME", "SUB_UNITS", "SUB_UNITS_COST", "NO_OF_UNITS" };
	static String SHEET = "Sheet1";

	public boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public void save(MultipartFile file) {
		try {
			List<BHMSInventoryProductStock> stocks = BHMS_Excel_services
					.excelToBhmsInvStockMasterTable(file.getInputStream());

			List<BHMSInventoryProductStockCurrent> currentstocks = BHMS_Excel_services
					.excelToBHMSInventoryProductStockCurrent(file.getInputStream());

			BHMSInventoryProductStockRepo.saveAll(stocks);

			for (BHMSInventoryProductStockCurrent st : currentstocks) {
				String bb = st.getBATCH();
				BHMSInventoryProductStockCurrent stoc = BHMSInventoryProductStockCurrentRepo.findcurrentstockdata(bb);
				if (stoc == null) {

					BHMSInventoryProductStockCurrentRepo.save(st);
				} else {

					Double newUnit1 = Double.valueOf(stoc.getUNITS());
					Double newUnit2 = Double.valueOf(st.getUNITS());
					Double newUnits = newUnit1 + newUnit2;

					Double subnewUnit1 = Double.valueOf(stoc.getSUB_UNITS());
					Double subnewUnit2 = Double.valueOf(st.getSUB_UNITS());
					Double subnewUnits = subnewUnit1 + subnewUnit2;

					Double newnoUnits1 = Double.valueOf(stoc.getNO_OF_UNITS());
					Double newnoUnits2 = Double.valueOf(st.getNO_OF_UNITS());
					Double newnoUnits = newnoUnits1 + newnoUnits2;

					stoc.setUNITS(newUnits);
					stoc.setSUB_UNITS(subnewUnits);
					stoc.setNO_OF_UNITS(newnoUnits);
					BHMSInventoryProductStockCurrentRepo.save(stoc);

				}

			}

			// repository.saveAll(currentstocks);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public String stockHistory(BHMSInventoryProductStock stocks, String expdate, String purdate) {
		String response = "";
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date exppdate = dateformat.parse(String.valueOf(expdate));
			String num = bhmsInvCgyMasterRep.srlnum();
			System.out.println(num + "srl123");

			Date purrdate = dateformat.parse(String.valueOf(purdate));
			System.out.println("hiiiiiiiiiiiiiiiiii" + exppdate.getClass().getName());
			stocks.setEXPIRY_DATE(exppdate);
			stocks.setPURCHASE_DATE(purrdate);
			stocks.setID("ID" + num);

			BHMSInventoryProductStockRepo.save(stocks);
			response = "Stock History added successfully";
		} catch (Exception e) {
			// response = "Stock history has not added";
		}
		return response;
	}

	public String currentStock(BHMSInventoryProductStockCurrent currentstocks, String expdate) {
		String response = "";
		try {

			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date expDate = dateformat.parse(String.valueOf(expdate));

			String num = bhmsInvCgyMasterRep.CURR_STOCK_IDval();
			String bb = currentstocks.getBATCH();
			System.out.println("111111111111" + bb);
			BHMSInventoryProductStockCurrent stoc = BHMSInventoryProductStockCurrentRepo.findcurrentstockdata(bb);

			if (stoc == null) {
				currentstocks.setID("ID" + num);
				BHMSInventoryProductStockCurrentRepo.save(currentstocks);
			} else {

				Double newUnit1 = Double.valueOf(stoc.getUNITS());
				Double newUnit2 = Double.valueOf(currentstocks.getUNITS());
				Double newUnits = newUnit1 + newUnit2;

				Double subnewUnit1 = Double.valueOf(stoc.getSUB_UNITS());
				Double subnewUnit2 = Double.valueOf(currentstocks.getSUB_UNITS());
				Double subnewUnits = subnewUnit1 + subnewUnit2;

				Double newnoUnits1 = Double.valueOf(stoc.getNO_OF_UNITS());
				Double newnoUnits2 = Double.valueOf(currentstocks.getNO_OF_UNITS());
				Double newnoUnits = newnoUnits1 + newnoUnits2;

				// stoc.setID("ID" + num);

				currentstocks.setUNITS(newUnits);
				currentstocks.setSUB_UNITS(subnewUnits);
				currentstocks.setNO_OF_UNITS(newnoUnits);
				// currentstocks.setEXPIRY_DATE(expDate);
				BHMSInventoryProductStockCurrentRepo.save(currentstocks);

			}
			response = "Stock added successfully";
		} catch (Exception e) {
			System.out.println("hellllllo" + e);
			response = "Something went wrong at server end";
		}
		return response;
	}

	public String addgj(General_journal_entity generalJournalEntity, String loginuserid) {
		// Create a new instance and copy the data
		General_journal_entity param = generalJournalEntity;
		Session session = sessionFactory.getCurrentSession();

		try {
			// Get the next value from the sequence
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR GJ_ID AS id")
					.getSingleResult();
			System.out.println("Generated ID is: " + id.intValue());

			// Set the ID in the entity
			param.setGjId(id.intValue());
			param.setCreateUser(loginuserid);
			Date date = new Date();
			param.setCreateTime(date);
			param.setVerifyFlg("N");
			param.setDelFlg("N");
			param.setOrder_type("PURCHASEORDER");
			String po_id =param.getProductName();
			Purchase_Order_Entity up=po_repo.getinquiry(po_id);
			up.setJournal_flg("Y");
			po_repo.save(up);
			// Ensure that required fields are not null
			if (param.getProductName() == null || param.getCategoryName() == null) {
				return "Product Name or Category Name cannot be null";
			}

			// Save the journal entry
			General_journal_rep.save(param);

			return "Product Added Successfully!";
		} catch (SQLGrammarException e) {
			// Handle SQL-specific errors
			System.err.println("SQLGrammarException: " + e.getMessage());
			e.printStackTrace(); // For debugging
			return "Product Added Unsuccessful: " + e.getMessage();
		} catch (Exception e) {
			// Catch-all for other exceptions
			e.printStackTrace();
			return "Product Added Unsuccessful: " + e.getMessage();
		}
	}
	
	@Transactional
	public BigInteger getAccNo() {
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        Integer id = (Integer) session.createNativeQuery("SELECT NEXT VALUE FOR GET_ACC_NO AS id")
	                .getSingleResult();
	        return BigInteger.valueOf(id.longValue());
	    } catch (Exception e) {
	       // logger.error("Error while fetching account number", e);
	        throw new RuntimeException("Unable to generate account number: " + e.getMessage(), e);
	    }
	}


	public String addvandor(VendorCreation vendor, String loginUserId) {
	    String msg = null;
	    Session session = sessionFactory.getCurrentSession();

	    // Generate the next vendor ID
	    BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR vendors_id AS id")
	            .getSingleResult();
	    String vendorIdPrefix = vendor.getVendorType().equals("PurchaseVendor") ? "TSKPV00" : "TSKSV00";
	    vendor.setVendorId(vendorIdPrefix + id);
	    vendor.setEntryUser(loginUserId);
	    vendor.setDelflg("N");

	    if (vendor.getVendorType().equals("PurchaseVendor")) {
	              
	        VendorCreationRep.save(vendor);
	        BigInteger generatedId = (BigInteger) session
		            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
		            .getSingleResult();
		  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
		  
		  list.setAccountName(vendor.getVendorName());
		  list.setAccountType("Liability");
		  System.out.println("group account="+vendor.getGroup_account());
		  if(vendor.getGroup_account()!="") {
			  list.setParentaccount(vendor.getGroup_account());
		 
		  }else {
			  list.setParentaccount("ACC00039");
		  }
		  list.setOwnership("Vendor");
		  list.setOwnershipid(vendor.getVendorId());
		  Date curentdate= new Date();
		  list.setEntryDate(curentdate);
		  list.setAccountNumber("ACC000"+generatedId.toString());
		  list.setAccountBalance(BigDecimal.ZERO);
		  list.setTotalCreditBalance(BigDecimal.ZERO);
		  list.setTotalDebitBalance(BigDecimal.ZERO);
		  
		  Erp_ChartOfAccountsRep.save(list);
	        msg = "Purchase Vendor " + vendor.getVendorId() + " Added Successfully!";
		}
	    else {
	        	        
            VendorCreationRep.save(vendor);
            BigInteger generatedId = (BigInteger) session
		            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
		            .getSingleResult();
		  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
		  
		  list.setAccountName(vendor.getVendorName());
		  list.setAccountType("Asset");
		  if(vendor.getGroup_account()!="") {
			  list.setParentaccount(vendor.getGroup_account());
		 
		  }else {
			  list.setParentaccount("ACC00042");
		  }
		  
		  list.setOwnership("Vendor");
		  list.setOwnershipid(vendor.getVendorId());
		  Date curentdate= new Date();
		  list.setEntryDate(curentdate);
		  list.setAccountNumber("ACC000"+generatedId.toString());
		  list.setAccountBalance(BigDecimal.ZERO);
		  list.setTotalCreditBalance(BigDecimal.ZERO);
		  list.setTotalDebitBalance(BigDecimal.ZERO);
		  
		  Erp_ChartOfAccountsRep.save(list);

            msg = "Sale Vendor " + vendor.getVendorId() + " Added Successfully!";        
    }

	    return msg;
	}

	@Autowired CategoryRep CategoryRep;
	public String addCategory(Category Categoryadd) {
		String msg="";
		Optional<Category> optionalEntity = CategoryRep.findById(Categoryadd.getAssetcode());
		if(optionalEntity.isPresent()) {
			msg="Category code already exist";
		}
		else {
			Categoryadd.setDel_flg("N");
			CategoryRep.save(Categoryadd);
			msg="Category Added sucessfully";
		}
		return msg;
		
	}
	@Autowired category_creationRep category_creationRep;
	public String adderpCategory(category_creation Categoryadd) {
		String msg="";
		Session session = sessionFactory.getCurrentSession();
		Optional<category_creation> optionalEntity = category_creationRep.findById(Categoryadd.getCategory_id());
		if(optionalEntity.isPresent()) {
			msg="Category "+" "+Categoryadd.getCategory_name()+" " +"already exist";
		}
		else {
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR CategoryNo AS id")
					.getSingleResult();
			Categoryadd.setDel_flg("N");
			category_creationRep.save(Categoryadd);
			msg="Category"+" "+Categoryadd.getCategory_name()+" "+"Added sucessfully";
		}
		return msg;
		
	}
	
	
	
	
	
	public String additem(ItemCreation ItemCreationadd,String loginuserid ) {
		Session session = sessionFactory.getCurrentSession();
		BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR ITEMCODE AS id")
				.getSingleResult();
		ItemCreationadd.setEntryUser(loginuserid);
		ItemCreationadd.setDeleteFlag("N");
		Category allcategory=CategoryRep.getCategorybycode(ItemCreationadd.getCategory());
		System.out.println("allcategory"+ItemCreationadd.getCategory());
		String subcategory=allcategory.getSubcategorycode();
		String headcode = allcategory.getHeadcode();
		String categorycode= allcategory.getCategorycode();
		CodeCreation getingparentaccount=codecreationrep.getheadcodebyhcs(headcode,categorycode,subcategory);
		System.out.println("headcode"+headcode);
		System.out.println("categorycode"+categorycode);
		System.out.println("subcategory"+subcategory);
		
		
		
		BigInteger generatedId = (BigInteger) session
	            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
	            .getSingleResult();
	  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
	  list.setParentaccount(getingparentaccount.getAccountnumber());
	  list.setAccountName(ItemCreationadd.getItemName());
	  list.setAccountType("Asset");
	  list.setOwnership("");
	  Date curentdate= new Date();
	  list.setEntryDate(curentdate);
	  list.setAccountNumber("ACC000"+generatedId.toString());
	  list.setOwnership("ITEM");
	  list.setOwnershipid(ItemCreationadd.getItemCode());
	  list.setAccountBalance(BigDecimal.ZERO);
	  list.setTotalCreditBalance(BigDecimal.ZERO);
	  list.setTotalDebitBalance(BigDecimal.ZERO);
	  Erp_ChartOfAccountsRep.save(list);
		
		
		
		
		
		
		if(ItemCreationadd.getOpeningQuantity()!=null) {
			BigDecimal bigDecimalValue2 = new BigDecimal(ItemCreationadd.getOpeningQuantity());
			ItemCreationadd.setQuantity(bigDecimalValue2);
		}
		else {
			ItemCreationadd.setQuantity(BigDecimal.ZERO);
		}
		ItemCreationRep.save(ItemCreationadd);
		return ItemCreationadd.getItemCode() +" Created Successfully!!";
		
	}
	
	/*-----WORKOREDR JOURNAL ADD----*/
	
	public String Addworkoredrtojurnal(General_journal_entity generalJournalEntity, String loginuserid) {
		System.out.println("the enter the service for journal");
	
		General_journal_entity param = generalJournalEntity;
		Session session = sessionFactory.getCurrentSession();

		try {
			
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR GJ_ID AS id")
					.getSingleResult();
			System.out.println("Generated ID is: " + id.intValue());

			// Set the ID in the entity
			param.setGjId(id.intValue());
			param.setCreateUser(loginuserid);
			Date date = new Date();
			param.setCreateTime(date);
			param.setVerifyFlg("N");
			param.setDelFlg("N");
			param.setOrder_type("WORKORDER");
			WorkOrder up=WorkOrderRep.getinquirywolist(param.getProductName());
			up.setJournal_flg("Y");
			WorkOrderRep.save(up);

			if (param.getProductName() == null || param.getCategoryName() == null) {
				return "Product Name or Category Name cannot be null";
			}
             General_journal_rep.save(param);
             return "Product Added Successfully!";
		} catch (SQLGrammarException e) {
		
			System.err.println("SQLGrammarException: " + e.getMessage());
			e.printStackTrace();
			return "Product Added Unsuccessful: " + e.getMessage();
		} catch (Exception e) {
		       e.printStackTrace();
			return "Product Added Unsuccessful: " + e.getMessage();
		}
	}
	
	
	
	
	
	
	

	public String editgj(General_journal_entity generalJournalEntity, String loginuserid) {
		try {
			// Fetch the existing entity by ID
			General_journal_entity param = General_journal_rep.findbyid(generalJournalEntity.getGjId());

			if (param == null) {
				return "Journal Entry not found!";
			}

			// Copy data from generalJournalEntity to param without overwriting the ID
			param.setFromAcc(generalJournalEntity.getFromAcc());
			param.setToAcc(generalJournalEntity.getToAcc());
			param.setFromAccType(generalJournalEntity.getFromAccType());
			param.setToAccType(generalJournalEntity.getToAccType());
			param.setFromAccNo(generalJournalEntity.getFromAccNo());
			param.setToAccNo(generalJournalEntity.getToAccNo());
			param.setBatch(generalJournalEntity.getBatch());
			param.setDesc(generalJournalEntity.getDesc());
			param.setExpiryDate(generalJournalEntity.getExpiryDate());
			param.setEntryDate(generalJournalEntity.getEntryDate());
			param.setDesc(generalJournalEntity.getDesc());

			param.setProductName(generalJournalEntity.getProductName());
			param.setCategoryName(generalJournalEntity.getCategoryName());
			param.setDebit(generalJournalEntity.getDebit());
			param.setCredit(generalJournalEntity.getCredit());
			// Copy other fields as necessary

			// Set audit fields
			Date date = new Date();
			param.setModifyTime(date);
			param.setVerifyFlg("N");
			param.setModifyUser(loginuserid);
			param.setModFlg("Y");
			param.setDelFlg("N");

			// Ensure that required fields are not null
			if (param.getProductName() == null || param.getCategoryName() == null) {
				return "Product Name or Category Name cannot be null";
			}

			// Save the updated journal entry
			General_journal_rep.save(param);

			return "Product Modified Successfully!";
		} catch (SQLGrammarException e) {
			// Handle SQL-specific errors
			System.err.println("SQLGrammarException: " + e.getMessage());
			e.printStackTrace();
			return "Product Modified Unsuccessful: " + e.getMessage();
		} catch (Exception e) {
			// Catch-all for other exceptions
			e.printStackTrace();
			return "Product Modified Unsuccessful: " + e.getMessage();
		}
	}

	public String updatefunctions(String formmode, String id, String loginuserid) {

		String msg = "";
		try {
			if (formmode == null || id == null) {
				return "Error: formmode or id cannot be null.";
			}

			// Convert id to integer
			Integer gjId = Integer.valueOf(id);

			// Find the entity by id
			General_journal_entity list = General_journal_rep.findbyid(gjId);

			if (list == null) {
				return "Error: Record not found with ID " + id;
			}

			if (formmode.equals("verify")) {
				// Set the verify flag
				list.setVerifyFlg("Y");
				Date date = new Date();
				list.setVerifyTime(date);
				list.setVerifyUser(loginuserid);
				General_journal_rep.save(list);
				msg = "Verified successfully!";

			} else if (formmode.equals("delete")) {
				// Perform delete action (optional: check if a real delete is intended)
				General_journal_rep.delete(list);
				msg = "Deleted successfully!";

			} else {
				msg = "Error: Unknown formmode " + formmode;
			}

		} catch (NumberFormatException e) {
			msg = "Error: Invalid ID format. Please ensure the ID is a valid number.";
		} catch (Exception e) {
			msg = "Error: An unexpected error occurred - " + e.getMessage();
		}

		return msg;
	}

	@Autowired
	Purchase_Order_Repo po_repo;

	/*----TSK PURCHASE ORDER ADD--*/
	public String addpo(Purchase_Order_Entity po_entity, String loginuserid) {
		String msg = "";
		Session session = sessionFactory.getCurrentSession();

		try {

			String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

			// Check the date field
			System.out.println("entrydate: " + formattedDate);
			System.out.println("the entry user" + loginuserid);

			// Generate new PO ID from sequence
			BigInteger PO_id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR Purchase_order AS id")
					.getSingleResult();
			System.out.println("Generated Id: PO000" + PO_id);

			po_entity.setPoId("PO000" + PO_id);
			po_entity.setEntryuser(loginuserid);
			po_entity.setEntrytime(formattedDate);
			po_entity.setEntityFlg('Y');
			po_entity.setVerifyFlg('N');
			po_entity.setModifyFlg('N');
			po_entity.setDelFlg('N');
			po_entity.setGateEntryFlg('N');
			po_entity.setQcFlg('N');
			po_entity.setApprovedFlg('N');
			po_entity.setGrn_flg("N");

			// Save the entity to the database
			po_repo.save(po_entity);

			// Return success message
			msg = "PO successfully added...!!";

		} catch (Exception e) {

			msg = "could not add po";
		}

		return msg;
	}

	public String updatepo(Purchase_Order_Entity Purchase_Order_Entity) {

		System.out.println("enter the service");
		String pid = Purchase_Order_Entity.getPoId();
		System.out.println("the purchase id" + pid);

		String msg = "";
		if (pid.isEmpty()) {
			msg = "PO id not found";
		} else {
			try {
				Optional<Purchase_Order_Entity> isdata = po_repo.findById(pid);

				Purchase_Order_Entity Eentity = isdata.get();

				if (isdata.isPresent()) {
					Purchase_Order_Entity.setEntryuser(Eentity.getEntryuser());
					Purchase_Order_Entity.setEntrytime(Eentity.getEntrytime());
					Purchase_Order_Entity.setModifyFlg('Y');
					Purchase_Order_Entity.setEntityFlg('Y');
					Purchase_Order_Entity.setVerifyFlg('N');

					Purchase_Order_Entity.setDelFlg('N');
					Purchase_Order_Entity.setGateEntryFlg('N');
					Purchase_Order_Entity.setQcFlg('N');
					Purchase_Order_Entity.setApprovedFlg('N');
					po_repo.save(Purchase_Order_Entity);
				} else {
					msg = "data not found";
				}
				msg = "modified successfully...!!!";
			} catch (Exception e) {
				msg = "modified went wrong";
			}
		}
		return msg;
	}

	@Autowired
	New_product_Rep New_product_rep;

	public ResponseEntity<String> addNewProduct(List<New_product_entity> newProductEntities, String formMode) {
		
		

		ResponseEntity<String> msg;
		try {
			if ("add".equals(formMode)) {
				Session session = sessionFactory.getCurrentSession();

				try {
					// Fetch all products from the repository to check for duplicates
					List<New_product_entity> existingProducts = New_product_rep.getall();

					for (New_product_entity newProduct : newProductEntities) {
						// Check if the product already exists by comparing the MakeProductName
						boolean productExists = existingProducts.stream().anyMatch(product -> product
								.getMakeProductName().equalsIgnoreCase(newProduct.getMakeProductName()));

						if (productExists) {
							return ResponseEntity.status(HttpStatus.CONFLICT)
									.body("Product already exists: " + newProduct.getMakeProductName());
						}

						// Generate new ID using sequence
						Number id = (Number) session.createNativeQuery("SELECT NEXT VALUE FOR NP_ID AS SRL_NO")
								.getSingleResult();

						// Set the generated ID for the new product, converting id to String
						newProduct.setNpId(String.valueOf(id.intValue())); // Assuming the ID is an Integer

						New_product_rep.save(newProduct);
					}

					msg = ResponseEntity.ok("Product added successfully.");
				} catch (Exception e) {
					// Log the exception and return an internal server error
					msg = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("An error occurred: " + e.getMessage());
				}
			} else {
				msg = ResponseEntity.badRequest().body("Invalid form mode.");
			}

			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
		}
	}

	/*---verify---*/

	public String verifypo(Purchase_Order_Entity Purchase_Order_Entity, String loginuserid) {
		System.out.println("the enter service for verify");
		String msg = "";
		String poId = Purchase_Order_Entity.getPoId();
		System.out.println("service id" + poId);

		// String POID=Purchase_Order_Entity.getPoId();
		if (poId.isEmpty()) {
			msg = "Id id not found";
		} else {

			Optional<Purchase_Order_Entity> existingdata = po_repo.findById(poId);
			if (existingdata.isPresent()) {
				System.out.println("inside save method");
				Purchase_Order_Entity forverify = existingdata.get();
				String verifyname = forverify.getVerifyuser();

				if (verifyname == null || !loginuserid.equals(verifyname)) {

					String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					Purchase_Order_Entity.setEntryuser(forverify.getEntryuser());
					Purchase_Order_Entity.setEntrytime(forverify.getEntrytime());
					Purchase_Order_Entity.setVerifyFlg('Y');
					Purchase_Order_Entity.setVerifyuser(loginuserid);
					Purchase_Order_Entity.setVerifytime(formattedDate);
					Purchase_Order_Entity.setModifyFlg('N');
					Purchase_Order_Entity.setEntityFlg('Y');
					Purchase_Order_Entity.setDelFlg('N');
					Purchase_Order_Entity.setGateEntryFlg('N');
					Purchase_Order_Entity.setQcFlg('N');
					Purchase_Order_Entity.setApprovedFlg('N');

					po_repo.save(Purchase_Order_Entity);

					msg = "Record verified By user-" + loginuserid;
				} else {
					msg = "Same user cannot verify" + loginuserid;
				}

			} else {
				msg = "PO Id Is not found!!!";
			}
		}
		return msg;

	}
	/*---deleterecord--*/

	public String DeletePOrecord(Purchase_Order_Entity Purchase_Order_Entity, String poId) {
		System.out.println("the enter the service for delete");
		String msg = "";

		// String delid=Purchase_Order_Entity.getPoId();
		System.out.println("the delete id" + poId);
		if (poId.isEmpty()) {
			msg = "The Id Is not Found";
		} else {
			Optional<Purchase_Order_Entity> entity = po_repo.findById(poId);
			if (entity.isPresent()) {
				po_repo.delete(Purchase_Order_Entity);

				msg = poId + "Record Deleted";

			} else {
				msg = "The could not Delete!!!";
			}
		}

		return msg;
	}

	@Autowired
	InwardRep InwardRep;

	/*----QualityCheck--*/
	public String CheckQuality(Purchase_Order_Entity purchaseOrderEntity, Inward inward, String loginUserId) {
		System.out.println("Entering service for Quality Check");

		String poId = purchaseOrderEntity.getPoId();
		if (poId == null || poId.isEmpty()) {
			return "ID is not found"; // Early return for missing ID
		}

		Optional<Purchase_Order_Entity> existingData = po_repo.findById(poId);
		if (existingData.isPresent()) {
			System.out.println("Inside save method");
			Purchase_Order_Entity existingPurchaseOrder = existingData.get();

			// Update the purchase order entity fields
			purchaseOrderEntity.setPoId(existingPurchaseOrder.getPoId());
			purchaseOrderEntity.setVendorName(existingPurchaseOrder.getVendorName());
			purchaseOrderEntity.setDate(existingPurchaseOrder.getDate());
			purchaseOrderEntity.setItem(existingPurchaseOrder.getItem());
			purchaseOrderEntity.setQty(existingPurchaseOrder.getQty());
			purchaseOrderEntity.setHsnSacCode(existingPurchaseOrder.getHsnSacCode());
			purchaseOrderEntity.setRate(existingPurchaseOrder.getRate());
			purchaseOrderEntity.setTax(existingPurchaseOrder.getTax());
			purchaseOrderEntity.setFreight(existingPurchaseOrder.getFreight());
			purchaseOrderEntity.setFreight_terms(existingPurchaseOrder.getFreight_terms());
			purchaseOrderEntity.setAddress(existingPurchaseOrder.getAddress());
			purchaseOrderEntity.setPayment_terms(existingPurchaseOrder.getPayment_terms());
			purchaseOrderEntity.setDelivery_terms(existingPurchaseOrder.getDelivery_terms());
			purchaseOrderEntity.setVendortype(existingPurchaseOrder.getVendortype());
			purchaseOrderEntity.setAssetcategory(existingPurchaseOrder.getAssetcategory());
			purchaseOrderEntity.setAssetsubcategory(existingPurchaseOrder.getAssetsubcategory());
			purchaseOrderEntity.setAssettype(existingPurchaseOrder.getAssettype());
			String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			purchaseOrderEntity.setEntryuser(existingPurchaseOrder.getEntryuser());
			purchaseOrderEntity.setEntrytime(existingPurchaseOrder.getEntrytime());
			purchaseOrderEntity.setVerifyFlg(existingPurchaseOrder.getVerifyFlg());
			purchaseOrderEntity.setVerifyuser(loginUserId);
			purchaseOrderEntity.setVerifytime(formattedDate);
			purchaseOrderEntity.setModifyFlg(existingPurchaseOrder.getModifyFlg());
			purchaseOrderEntity.setEntityFlg(existingPurchaseOrder.getEntityFlg());
			purchaseOrderEntity.setDelFlg(existingPurchaseOrder.getDelFlg());
			purchaseOrderEntity.setGateEntryFlg(existingPurchaseOrder.getGateEntryFlg());
			purchaseOrderEntity.setVendorId(existingPurchaseOrder.getVendorId());
			purchaseOrderEntity.setPaymenttype(existingPurchaseOrder.getPaymenttype());
			purchaseOrderEntity.setQcFlg('Y');
			purchaseOrderEntity.setApprovedFlg('N');

			po_repo.save(purchaseOrderEntity);

			// Check and update the Inward entity
			Optional<Inward> inwardEntity = InwardRep.findByPoNumber(poId);
			if (inwardEntity.isPresent()) {
				Inward inwardToUpdate = inwardEntity.get();
				System.out.println("Inward PO number: " + inwardToUpdate.getPoNumber());

				inwardToUpdate.setQC_FLG("Y");
				InwardRep.save(inwardToUpdate);
			} else {
				return "Inward entity not found for PO number: " + poId;
			}

			return "Quality Checked By user: " + loginUserId;

		} else {
			return "PO ID is not found!!!";
		}
	}
	
	
	public ResponseEntity<String> add_po(List<PURCHASE_ORDER_ENTITY_NEW> list, String formmode) {

	    ResponseEntity<String> msg = null;
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if (formmode.equals("add")) {
	        	
	            // Get the PO ID as a BigInteger and convert to BigDecimal
	            BigInteger PO_idBigInt = (BigInteger) session
	                .createNativeQuery("SELECT NEXT VALUE FOR NEW_PO_ID AS PO_id")
	                .getSingleResult();
	            BigDecimal PO_id = new BigDecimal(PO_idBigInt); // Convert BigInteger to BigDecimal
	            System.out.println("Generated PO ID: " + PO_id); // Log PO ID for debugging

	            ACCOUNT_LEDGER_PO_Entity ledger=new ACCOUNT_LEDGER_PO_Entity();
	            for (PURCHASE_ORDER_ENTITY_NEW param : list) {
	                // Generate a new unique ID for the purchase order
	                BigInteger idBigInt = (BigInteger) session
	                    .createNativeQuery("SELECT NEXT VALUE FOR NEW_ID AS id")
	                    .getSingleResult();
	                	BigDecimal id = new BigDecimal(idBigInt); // Convert BigInteger to BigDecimal

	                param.setId(id.toString()); // Set the generated ID as String
	                param.setPoId("PO0000" + PO_id.toString()); // Ensure proper concatenation of PO ID
	                param.setDelFlg('N');
	                System.out.println("HSNCODE--"+param.getHsncode());

	                // Save each purchase order entity
	                PURCHASE_ORDER_ENTITY_NEW_rep.save(param);
	                System.out.println("Saved PO entity: " + param); // Log entity data for debugging
	                
	                
	                
	                
	                
	                // For Ledger
	                
	                
	                ItemCreation up1= ItemCreationRep.getitemlistbyid(param.getItemcode());
                    String aset_code=up1.getCategory();
	                    param.setAssetCategory(aset_code);

		                Category sub_cat= Categoryrep.getCategorybycode(aset_code);
		                ledger.setId(id.toString());
		                ledger.setPoId("PO0000" + PO_id.toString());
		                ledger.setVendorId(param.getVendorId());
		                ledger.setCatCode(aset_code);
		                ledger.setItem(param.getItem());
		                ledger.setItem_code(param.getItemcode());
		                ledger.setHeadDescription(sub_cat.getHeaddescription());
		                ledger.setMainDescription(sub_cat.getCategorydescription());
		                ledger.setDescription(sub_cat.getSubcategorydescription());
		                ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));
		                ledger.setTotalAmount(param.getAdvanceAmount() != null ? param.getAdvanceAmount() : BigDecimal.ZERO);

	                
	                ACCOUNT_LEDGER_PO_rep.save(ledger);
	                
	            }

	            
	            VendorCreation ch=  VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            
               // Calculation For Vendor
	            
	            //credit Amount for vendor
	            
		         if (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
		        	 
	            BigDecimal b1= ch.getCrAmt();
		        	 
	            BigDecimal b2= list.get(0).getAdvanceAmount();
	            BigDecimal bal=b1.add(b2);
	            ch.setCrAmt(bal);
		         }
	            
	           
	            
	            
	            //TSK(pending Amount) -> vendor
		         /*if (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
	            BigDecimal c1= ch.getAdvanceamount();
	            BigDecimal c2= list.get(0).getBalanceAmount();
	            BigDecimal vbal=c1.add(c2);
	            ch.setAdvanceamount(vbal);
		         }*/
	            VendorCreationRep.save(ch);
	            
	            
	            //demo transation
	            if    (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
	                
	            	Transaction_table_demo ua = new Transaction_table_demo();

		         // Generate a new unique ID for the Transaction
		         BigInteger id = (BigInteger) session
		             .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
		             .getSingleResult();

		         if (id == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
	             String sid=String.valueOf(id);

		         // Set the generated ID
		         ua.setTran_id("TRAN000" + sid);
		         System.out.println("Generated Transaction ID: " + ua.getTran_id()); // Debugging
		         // Set other fields
		         ua.setPo_id(list.get(0).getPoId());
		         ua.setVendor_id(list.get(0).getVendorId());
		         ua.setClassification(ch.getClassify());
		         ua.setVendor_name(ch.getVendorName());
		         ua.setAcct_crncy(ch.getCrncy());
		         ua.setAcct_num(ch.getAcctNum());
	             Date currentdate = new Date();
	             ua.setAcct_cls_date(currentdate);
	             ua.setTran_type(list.get(0).getPaymentType());
	             ua.setType("Payment");

		         // Set advance amount in Debit  For TSK
		             ua.setDr_amt(list.get(0).getAdvanceAmount());
		         
		         
		         BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
		         

		         // Set balance amount in COA if valid
		         if (ch.getBalanceamount() != null && ch.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
		             ua.setAcct_bal(ch.getBalanceamount());
		         }

		         // Save the entity
		         Transaction_table_demo_rep.save(ua);
		         

		}
	            
	            
	            //final chart of Accounts
	            if    (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
	                
		     
		            CapitalTrans trantab= new CapitalTrans();

		         // Generate a new unique ID for the Transaction
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         Date currentdate = new Date();
		         CapitalTrans trantab2= new CapitalTrans();
	             //patran
		         String sid=String.valueOf(journalid);
	             BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             trantab2.setId(id2.toString());
	             trantab2.setJournalTranId("TRAN000"+sid);
	             if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
	             trantab2.setDescription(list.get(0).getVendorId());
	             trantab2.setCredits(list.get(0).getAdvanceAmount());
	             trantab2.setDebits(BigDecimal.ZERO);
	             trantab2.setPartTranId(1);
	             trantab2.setJournalDate(currentdate);
	             trantab2.setTran_particulars("Vendor Advance Account");
	             //total credit total debit
	             trantab2.setTotalCredits(list.get(0).getAdvanceAmount());
	             trantab2.setTotalDebits(list.get(0).getAdvanceAmount());
	             
	             CapitalTransRep.save(trantab2);
		         
		         BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             
	             trantab.setId(id.toString());
	             trantab.setJournalTranId("TRAN000"+sid);
	             trantab.setAccountNumber("ACC00037");
	             trantab.setFullAccount("ACC00037-Vendor Advance Account");
	             trantab.setAccountName("Vendor Advance Account");
	             trantab.setDescription(list.get(0).getVendorId());
	             trantab.setDebits(list.get(0).getAdvanceAmount());
	             trantab.setCredits(BigDecimal.ZERO);
	             trantab.setPartTranId(2);
	             trantab.setJournalDate(currentdate);
	             trantab.setTotalCredits(list.get(0).getAdvanceAmount());
	             trantab.setTotalDebits(list.get(0).getAdvanceAmount());
	             trantab.setVendor_id(ch.getVendorId());
	             trantab.setVendor_name(ch.getVendorName());
	             if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab.setTran_particulars("Cash on Hand");
	             }
	             else {
	            	 trantab.setTran_particulars("IDBI Bank");
	            	 
	             }
	             CapitalTransRep.save(trantab);
	             
	             
	             // for first account
	             Erp_ChartOfAccounts chartaccount=  Erp_ChartOfAccountsRep.findsccount("ACC00037");
	             chartaccount.setTotalDebitBalance(chartaccount.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()) );
	             chartaccount.setAccountBalance(chartaccount.getAccountBalance().add(list.get(0).getAdvanceAmount()));
	             Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(chartaccount.getParentaccount());
	             parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()));
	             parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(list.get(0).getAdvanceAmount()));
	             Erp_ChartOfAccountsRep.save(chartaccount);
	             Erp_ChartOfAccountsRep.save(parentchartaccount);
	             
	             if(list.get(0).getPaymentType().equals("cash")) {
	            	 Erp_ChartOfAccounts chartaccount2=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
	            	 chartaccount2.setTotalCreditBalance(chartaccount2.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()));
	            	 chartaccount2.setAccountBalance(chartaccount2.getAccountBalance().subtract(list.get(0).getAdvanceAmount()));
	            	 Erp_ChartOfAccounts parentchartaccount2=  Erp_ChartOfAccountsRep.findsccount(chartaccount2.getParentaccount());
		             parentchartaccount2.setTotalCreditBalance(parentchartaccount2.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()));
		             parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().subtract(list.get(0).getAdvanceAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount2);
		             Erp_ChartOfAccountsRep.save(parentchartaccount2);
	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount2=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
	            	 chartaccount2.setTotalCreditBalance(chartaccount2.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()));
	            	 chartaccount2.setAccountBalance(chartaccount2.getAccountBalance().subtract(list.get(0).getAdvanceAmount()));
	            	 Erp_ChartOfAccounts parentchartaccount2=  Erp_ChartOfAccountsRep.findsccount(chartaccount2.getParentaccount());
		             parentchartaccount2.setTotalCreditBalance(parentchartaccount2.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()));
		             parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().subtract(list.get(0).getAdvanceAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount2);
		             Erp_ChartOfAccountsRep.save(parentchartaccount2);
	             }
	             
	             

		         
		}
	            
	            
	            
	            
	            
	            

	            //chart of Accounts
	   
	            
	if    (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
     
	            Transaction_table ua = new Transaction_table();

	         // Generate a new unique ID for the Transaction
	         BigInteger id = (BigInteger) session
	             .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	             .getSingleResult();

	         if (id == null) {
	             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	         }
             String sid=String.valueOf(id);

	         // Set the generated ID
	         ua.setTran_id("TRAN000" + sid);
	         System.out.println("Generated Transaction ID: " + ua.getTran_id()); // Debugging
	         // Set other fields
	         ua.setPo_id(list.get(0).getPoId());
	         ua.setVendor_id(list.get(0).getVendorId());
	         ua.setClassification(ch.getClassify());
	         ua.setVendor_name(ch.getVendorName());
	         ua.setAcct_crncy(ch.getCrncy());
	         ua.setAcct_num(ch.getAcctNum());
             Date currentdate = new Date();
             ua.setAcct_cls_date(currentdate);
             ua.setTran_type(list.get(0).getPaymentType());
             ua.setType("Purchase Order");

	         // Set advance amount in Debit  For TSK
	             ua.setDr_amt(list.get(0).getAdvanceAmount());
	         
	         
	         BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
	         if(list.get(0).getPaymentType().equals("cash")) {
	        	 up.setCurrent_cash_balance(up.getCurrent_cash_balance().subtract(list.get(0).getAdvanceAmount()));
	         }
	         else {
	        	up.setCurrent_account_balance(up.getCurrent_account_balance().subtract(list.get(0).getAdvanceAmount()));
	         }

	         // Set balance amount in COA if valid
	         if (ch.getBalanceamount() != null && ch.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	             ua.setAcct_bal(ch.getBalanceamount());
	         }

	         // Save the entity
	         Transaction_table_Rep.save(ua);

	}
	            
	            
	            msg = ResponseEntity.ok("PO0000" + PO_id.toString()+"-Added Successfully.");
	        }else if(formmode.equals("edit")) {
	        	
	        	
	        	
	        /*    //Calculation
	        	
	            // Fetch the vendor details using the vendor ID from the first element in the list
	            VendorCreation up = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            List<PURCHASE_ORDER_ENTITY_NEW> oldpo= PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(list.get(0).getPoId());

	            
	            BigDecimal old_Advance=oldpo.get(0).getAdvanceAmount();
	            BigDecimal new_poadvance=list.get(0).getAdvanceAmount();
	            BigDecimal vendor_cr= up.getCrAmt();
	            BigDecimal vendor_ad_amt= up.getAdvanceamount();
	            
	            BigDecimal old_balance=oldpo.get(0).getBalanceAmount();
	            BigDecimal new_balance=list.get(0).getBalanceAmount();

	            //Check if the new purchase order advance is greater than the old advance
	            if (old_Advance.compareTo(new_poadvance) < 0) {
	            	System.out.println("inside one");
	        	   BigDecimal newvalue= new_poadvance.subtract(old_Advance);
	        	   BigDecimal new_cr=  vendor_cr.add(newvalue);
	        	   up.setCrAmt(new_cr);
	        	   BigDecimal newa_ad=  up.getAdvanceamount().subtract(newvalue);
	        	   up.setAdvanceamount(newa_ad);
	        	   VendorCreationRep.save(up);
	           } else if (old_Advance.compareTo(new_poadvance) > 0) {
	            	System.out.println("inside two");
	        	   BigDecimal bal_ad=  old_Advance.subtract(new_poadvance);
	        	   BigDecimal new_cr= vendor_cr.subtract(bal_ad);
	        	   BigDecimal new_ad= vendor_ad_amt.add(bal_ad);
	        	   
	        	   up.setCrAmt(new_cr);
	        	   up.setAdvanceamount(new_ad);
	        	   VendorCreationRep.save(up);
	           }else if (old_balance.compareTo(new_balance) != 0) {
	            	System.out.println("inside three");
	            	
	            	if(old_balance.compareTo(new_balance) < 0) {
	            		 BigDecimal newvalue= new_balance.subtract(old_balance);
	  	        	   BigDecimal newa_ad=  up.getAdvanceamount().add(newvalue);
	  	        	   up.setAdvanceamount(newa_ad);
	            	}else {
	            		 BigDecimal newvalue= old_balance.subtract(new_balance);
		  	        	   BigDecimal newa_ad=  up.getAdvanceamount().subtract(newvalue);
		  	        	   up.setAdvanceamount(newa_ad);
	            	}
	        	   
	        	   VendorCreationRep.save(up);
	           }
	            */
	            // PO Update
	            
	        	for (PURCHASE_ORDER_ENTITY_NEW param : list) {
	        			System.out.println("poid--"+ param.getId());
	        		//	List<PURCHASE_ORDER_ENTITY_NEW> existingData =PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(param.getPoId());
	        			
	        			
	        			if (param.getId()==null) {
	        				
	        				BigInteger idBigInt = (BigInteger) session
	        	                    .createNativeQuery("SELECT NEXT VALUE FOR NEW_ID AS id")
	        	                    .getSingleResult();
	        	                	BigDecimal id = new BigDecimal(idBigInt);
	        				 // Convert BigInteger to BigDecimal
	        	            param.setId(id.toString());    	
	        				param.setPoId(param.getPoId());
	        				param.setDelFlg('N');
	        				PURCHASE_ORDER_ENTITY_NEW_rep.save(param);
	        				msg=ResponseEntity.ok(param.getPoId()+"-Product modify Successfully.");
	        			}
	        			else {
	        				param.setDelFlg('N');
	        				PURCHASE_ORDER_ENTITY_NEW_rep.save(param);	  
	        				msg=ResponseEntity.ok(param.getPoId() +"-Product modify Successfully.");
	        				
	        			}
	        				}
	        	
	        	
	        	
	        	
	        	// Chart of Accounts
	        	
	        /*	List<Transaction_table> as = Transaction_table_Rep.getbyId(list.get(0).getPoId());

	        	BigDecimal advanceAmount = list.get(0).getAdvanceAmount();

	        	// Check if advanceAmount is null or zero
	        	if (advanceAmount == null || advanceAmount.compareTo(BigDecimal.ZERO) == 0) {
	        	    if (as != null) {
	        	        as.get(0).setDr_amt(BigDecimal.valueOf(0)); // Set Dr_amt to 0
	        	        Transaction_table_Rep.saveAll(as);     // Save the updated object
	        	    }
	        	} else {
	        	    if (as != null) {
	        	        // Check if Dr_amt differs from advanceAmount
	        	        if (as.get(0).getDr_amt() == null || as.get(0).getDr_amt().compareTo(advanceAmount) != 0) {
	        	            as.get(0).setDr_amt(advanceAmount);    // Update Dr_amt with advanceAmount
	        	            Transaction_table_Rep.saveAll(as); // Save the updated object
	        	        }
	        	    }
	        	}

	        	*/
	        	
	        	
	        
	        }

	        return msg;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.ok(" Product update unsuccessful.");
	    }
	}

	
	
	/*------addwonew--*/
	
	
	public ResponseEntity<String> add_wonew(List<SALES_ORDER_ENTITY_NEW> list, String formmode) {
		
		
		System.out.println("enter the service for workoredr");

	    ResponseEntity<String> msg = null;
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        String woid="";	        
	        if (formmode.equals("add")) {
	            // Get the PO ID as a BigInteger and convert to BigDecimal
	            BigInteger PO_idBigInt = (BigInteger) session
	                .createNativeQuery("SELECT NEXT VALUE FOR wo_idnew AS WO_id")
	                .getSingleResult();
	            BigDecimal PO_id = new BigDecimal(PO_idBigInt); // Convert BigInteger to BigDecimal
	            System.out.println("Generated PO ID: " + PO_id); // Log PO ID for debugging

	            ACCOUNT_LEDGER_SALE_Entity ledger=new ACCOUNT_LEDGER_SALE_Entity();
	            for (SALES_ORDER_ENTITY_NEW param : list) {
	                // Generate a new unique ID for the purchase order
	                BigInteger idBigInt = (BigInteger) session
	                    .createNativeQuery("SELECT NEXT VALUE FOR new_id_wo AS id")
	                    .getSingleResult();
	                BigDecimal id = new BigDecimal(idBigInt); // Convert BigInteger to BigDecimal

	                param.setId(id.toString()); // Set the generated ID as String
	                woid= "WO0000" + PO_id.toString();
	                param.setWoId("WO0000" + PO_id.toString()); 
	                param.setDelFlg('N');
	                
	                // Save each purchase order entity
	                SALES_ORDER_ENTITY_NEW_rep.save(param);
	                System.out.println("Saved PO entity: " + param); // Log entity data for debugging
	                
	                
                    // For Ledger
	                
	                ItemCreation up1= ItemCreationRep.getitemlistbyid(param.getItemcode());
                    String aset_code=up1.getCategory();
	                    param.setAssetCategory(aset_code);

		                Category sub_cat= Categoryrep.getCategorybycode(aset_code);
		                ledger.setId(id.toString());
		                ledger.setWoId("WO0000" + PO_id.toString());
		                ledger.setVendorId(param.getVendorId());
		                ledger.setCatCode(aset_code);
		                ledger.setItem(param.getItem());
		                ledger.setItem_code(param.getItemcode());

		                ledger.setHeadDescription(sub_cat.getHeaddescription());
		                ledger.setMainDescription(sub_cat.getCategorydescription());
		                ledger.setDescription(sub_cat.getSubcategorydescription());
		                ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));
		                ledger.setTotalAmount(param.getAdvanceAmount() != null ? param.getAdvanceAmount() : BigDecimal.ZERO);

	                
		                ACCOUNT_LEDGER_SALE_rep.save(ledger);
					
	            }
	            
	            VendorCreation ch=  VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            
	               // Calculation For Vendor
		            
		            //credit Amount for vendor
			         if (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
		            BigDecimal b1= ch.getDrAmt();
		            BigDecimal b2= list.get(0).getAdvanceAmount();
		            BigDecimal bal=b1.add(b2);
		            ch.setDrAmt(bal);
			         }
		            
		            //TSK(pending Amount) -> vendor
			        /* if (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
		            BigDecimal c1= ch.getBalanceamount();
		            BigDecimal c2= list.get(0).getBalanceAmount();
		            BigDecimal vbal=c1.add(c2);
		            ch.setBalanceamount(vbal);
			         }*/
		            VendorCreationRep.save(ch);
		            
		            //chart of Accounts
		     	   
		            
		        	if    (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
		             
		        	            Transaction_table ua = new Transaction_table();

		        	         // Generate a new unique ID for the Transaction
		        	         BigInteger id = (BigInteger) session
		        	             .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
		        	             .getSingleResult();

		        	         if (id == null) {
		        	             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		        	         }
		                     String sid=String.valueOf(id);

		        	         // Set the generated ID
		        	         ua.setTran_id("TRAN000" + sid);
		        	         System.out.println("Generated Transaction ID: " + ua.getTran_id()); // Debugging
		        	         // Set other fields
		        	         ua.setWo_id(list.get(0).getWoId());
		        	         ua.setVendor_id(list.get(0).getVendorId());
		        	         ua.setClassification(ch.getClassify());
		        	         ua.setVendor_name(ch.getVendorName());
		        	         ua.setAcct_crncy(ch.getCrncy());
		        	         ua.setAcct_num(ch.getAcctNum());
		                     Date currentdate = new Date();
		                     ua.setAcct_cls_date(currentdate);

		        	         // Set advance amount in Debit  For TSK
		        	             ua.setCr_amt(list.get(0).getAdvanceAmount());
		        	         

		        	         // Set balance amount in COA if valid
		        	         if (ch.getBalanceamount() != null && ch.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
		        	             ua.setAcct_bal(ch.getBalanceamount());
		        	         }

		        	         // Save the entity
		        	         Transaction_table_Rep.save(ua);

		        	}
		        	
		        	//final chart of Accounts
		            if    (list.get(0).getAdvanceAmount() != null && list.get(0).getAdvanceAmount().compareTo(BigDecimal.ZERO) != 0) {
		                
			     
			            CapitalTrans trantab= new CapitalTrans();

			         // Generate a new unique ID for the Transaction
			            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
				 				.getSingleResult();

			         if (journalid == null) {
			             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
			         }
			         Date currentdate = new Date();
			         CapitalTrans trantab2= new CapitalTrans();
		             //patran
			         String sid=String.valueOf(journalid);
		             BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
		             trantab2.setId(id2.toString());
		             trantab2.setJournalTranId("TRAN000"+sid);
		             if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab2.setAccountNumber("ACC00048");
			             trantab2.setFullAccount("ACC00048-Cash on Hand");
			             trantab2.setAccountName("Cash on Hand");
		             }
		             else {
		            	 trantab2.setAccountNumber("ACC00045");
			             trantab2.setFullAccount("ACC00045-IDBI Bank");
			             trantab2.setAccountName("IDBI Bank");
		             }
		             
		             trantab2.setDescription(list.get(0).getVendorId());
		             trantab2.setDebits(list.get(0).getAdvanceAmount());
		             trantab2.setCredits(BigDecimal.ZERO);
		             trantab2.setPartTranId(1);
		             trantab2.setJournalDate(currentdate);
		             trantab2.setTran_particulars("Sale Advance Account");
		             //total credit total debit
		             trantab2.setTotalCredits(list.get(0).getAdvanceAmount());
		             trantab2.setTotalDebits(list.get(0).getAdvanceAmount());
		             
		             CapitalTransRep.save(trantab2);
			         
			         BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
		             
		             trantab.setId(id.toString());
		             trantab.setJournalTranId("TRAN000"+sid);
		             trantab.setAccountNumber("ACC00064");
		             trantab.setFullAccount("ACC00064-Sale Advance Account");
		             trantab.setAccountName("Sale Advance Account");
		             trantab.setDescription(list.get(0).getVendorId());
		             trantab.setCredits(list.get(0).getAdvanceAmount());
		             trantab.setDebits(BigDecimal.ZERO);
		             trantab.setPartTranId(2);
		             trantab.setJournalDate(currentdate);
		             trantab.setTotalCredits(list.get(0).getAdvanceAmount());
		             trantab.setTotalDebits(list.get(0).getAdvanceAmount());
		             trantab.setVendor_id(ch.getVendorId());
		             trantab.setVendor_name(ch.getVendorName());
		             if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab.setTran_particulars("Cash on Hand");
		             }
		             else {
		            	 trantab.setTran_particulars("IDBI Bank");
		            	 
		             }
		             CapitalTransRep.save(trantab);
		             
		             
		             // for first account
		             Erp_ChartOfAccounts chartaccount=  Erp_ChartOfAccountsRep.findsccount("ACC00064");
		             chartaccount.setTotalCreditBalance(chartaccount.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()) );
		             chartaccount.setAccountBalance(chartaccount.getAccountBalance().add(list.get(0).getAdvanceAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount);
		             
		             String parentaccount = chartaccount.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(list.get(0).getAdvanceAmount()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getAdvanceAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

		             if(list.get(0).getPaymentType().equals("cash")) {
		            	 Erp_ChartOfAccounts chartaccount2=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
 		            	 chartaccount2.setTotalDebitBalance(chartaccount2.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()));
		            	 chartaccount2.setAccountBalance(chartaccount2.getAccountBalance().add(list.get(0).getAdvanceAmount()));
		            	 
		            	 String parentaccount1 = chartaccount2.getParentaccount();

		            	 while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount2 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		            	     
		            	     if (parentchartaccount2 != null) {
		            	         
		            	    	 parentchartaccount2.setTotalDebitBalance(parentchartaccount2.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()));
		            	    	 parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().add(list.get(0).getAdvanceAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount2);
		            	         parentaccount1 = parentchartaccount2.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }	            	 
		            	 Erp_ChartOfAccountsRep.save(chartaccount2);
			             
		             }
		             else {
		            	 Erp_ChartOfAccounts chartaccount2=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
		            	 chartaccount2.setTotalDebitBalance(chartaccount2.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()));
		            	 chartaccount2.setAccountBalance(chartaccount2.getAccountBalance().add(list.get(0).getAdvanceAmount()));
		            	 Erp_ChartOfAccountsRep.save(chartaccount2);
		            	 String parentaccount1 = chartaccount2.getParentaccount();

		            	 while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount2 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		            	     
		            	     if (parentchartaccount2 != null) {
		            	         
		            	    	 parentchartaccount2.setTotalDebitBalance(parentchartaccount2.getTotalDebitBalance().add(list.get(0).getAdvanceAmount()));
		            	    	 parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().add(list.get(0).getAdvanceAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount2);
		            	         parentaccount1 = parentchartaccount2.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }	            	 

		             }
		             
		             

			         
			}
		        	            

	            
	            msg = ResponseEntity.ok("salesorder Added Successfully-"+woid);
	        }else if(formmode.equals("edit")) {
	        	
	        	for(SALES_ORDER_ENTITY_NEW param : list) {
	        		if(param.getId()==null) {
	        			BigInteger idBigInt = (BigInteger) session
	    	                    .createNativeQuery("SELECT NEXT VALUE FOR new_id_wo AS id")
	    	                    .getSingleResult();
	        			 BigDecimal id = new BigDecimal(idBigInt);
	        			 woid=param.getWoId();
	        			param.setId(id.toString()); // Set the generated ID as String
	 	                param.setWoId(param.getWoId()); 
	 	                param.setDelFlg('N');
	 	                // Save each purchase order entity
	 	                SALES_ORDER_ENTITY_NEW_rep.save(param);
	        		}
	        		else {
	        			woid=param.getWoId();
	        			param.setDelFlg('N');
	        			SALES_ORDER_ENTITY_NEW_rep.save(param);
	        			
	        		}
	        		
	        		
	        	}
	        	msg=ResponseEntity.ok("Saleorder modify Successfully-"+woid);
	        }

	        return msg;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.ok("salesorder update unsuccessful.");
	    }
	}

	

	public void updatePO( String Opid) {
		
	              System.out.println(" PO ID is : " + Opid); // Log PO ID for debugging
	              List<PURCHASE_ORDER_ENTITY_NEW> list=   PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(Opid);
	            for (PURCHASE_ORDER_ENTITY_NEW param : list) {
	            	param.setApprovedFlg('Y');
	                // Save each purchase order entity
	                PURCHASE_ORDER_ENTITY_NEW_rep.save(param);
	                System.out.println("Saved PO entity: " + param); // Log entity data for debugging
	            }
	           
		
	}
	
	
	public void updateWO( String OWid) {
		
        System.out.println(" PO ID is : " + OWid); // Log PO ID for debugging
        
        List<SALES_ORDER_ENTITY_NEW> list=   SALES_ORDER_ENTITY_NEW_REP.getparticular(OWid);
       
      for (SALES_ORDER_ENTITY_NEW param : list) {
    	  
    	  
      	param.setApprovedFlg('Y');
      	
          // Save each purchase order entit
      	SALES_ORDER_ENTITY_NEW_REP.save(param);
          System.out.println("Saved PO entity: " + param); // Log entity data for debugging
      }
     

}

	@Autowired
	PO_invoice_rep PO_invoice_reps;
	/*----TSK PURCHASE ORDER ADD--*/
	public ResponseEntity<String> invoice_edit(List<PO_invoice_entity> list, String formmode, String Opid) {
	    ResponseEntity<String> msg = null;

		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	//Calculation
	        	
	            // Fetch the vendor details using the vendor ID from the first element in the list
	            VendorCreation up1 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            List<PURCHASE_ORDER_ENTITY_NEW> oldpo= PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(list.get(0).getPoId());
	            
	            BigDecimal vendor_cr= up1.getCrAmt();
	            BigDecimal vendor_adnce= up1.getAdvanceamount();
	            

	            BigDecimal bal_total=oldpo.get(0).getBalanceAmount();
	            
	           
	            if(list.get(0).getBalanceAmount() !=  null || list.get(0).getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
	            	
	            	BigDecimal new_ad=vendor_adnce.add(list.get(0).getBalanceAmount());
	            	up1.setAdvanceamount(new_ad);
	            	BigDecimal new_cr= vendor_cr.add(list.get(0).getPaidAmount());
	            	up1.setCrAmt(new_cr);
		        	   VendorCreationRep.save(up1);	   
	            }
	            
	           
	            
	            
	            /*else if (list.get(0).getBalanceAmount().compareTo(bal_total) != 0) {
	            	 BigDecimal bal_ad=  bal_total.subtract(list.get(0).getTotalAmount());
		        	   BigDecimal new_ad= vendor_adnce.subtract(bal_ad);
		        	   up1.setAdvanceamount(new_ad);
		        	   VendorCreationRep.save(up1);
	            }else if (list.get(0).getTotalAmount().compareTo(old_total) > 0) {
           	 BigDecimal bal_ad=  list.get(0).getTotalAmount().subtract(old_total);
	        	   BigDecimal new_cr= vendor_cr.add(bal_ad);
	        	   BigDecimal new_ad= vendor_adnce.subtract(bal_ad);
	        	   up1.setCrAmt(new_cr);
	        	   up1.setAdvanceamount(new_ad);
	        	   VendorCreationRep.save(up1);

           }*/

	            //PO -> Invoice 
	            
	        	
	            for (PO_invoice_entity param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR INVOICE_ID AS id")
	            		    .getSingleResult()).longValue();
	            	
	            	Long idstockbatchno = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR stockbatchno AS id")
	            		    .getSingleResult()).longValue();
	            	 Date currentdate = new Date();

	            	 	param.setInvoice_conform_date(currentdate);
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setPoId(Opid);
	                    param.setExtra_amount(param.getBalanceAmount());
	                    param.setLast_extra_amount(param.getPaidAmount());
	                    param.setApprovedFlg('S');
	                    ItemCreation up= ItemCreationRep.getitemlistbyid(param.getItemcode());
	                    String catg=up.getCategory();
	                    	param.setBatch("BATCH00"+idstockbatchno.toString());
		                    param.setUseingqty(param.getQty());
		                    param.setAssetCategory(catg);
	                PO_invoice_reps.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	                
	                
	                
	             // Fetch ledger entries based on Opid (assuming multiple records exist)
	                List<ACCOUNT_LEDGER_PO_Entity> ledgerList = ACCOUNT_LEDGER_PO_rep.findByPoId(Opid); 

	                for (ACCOUNT_LEDGER_PO_Entity ledger : ledgerList) {
	                    // Check if the item matches
	                    if (ledger.getItem().equals(param.getItem())) {
	                        ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));

	                        // Handle null case for TotalAmount
	                        if (ledger.getTotalAmount() == null) {
	                            ledger.setTotalAmount(param.getPaidAmount());
	                        } else {
	                            ledger.setTotalAmount(ledger.getTotalAmount().add(param.getPaidAmount())); // Assign result back
	                        }

	                        // Save updated ledger entity
	                        ACCOUNT_LEDGER_PO_rep.save(ledger);
	                    }
	                }


	              
	                
	            }
	            

	            //PO update
	            
	            ACCOUNT_LEDGER_PO_Entity ledger=new ACCOUNT_LEDGER_PO_Entity();
	            
	    		List<PURCHASE_ORDER_ENTITY_NEW> existingData =PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(Opid);

	            for (PURCHASE_ORDER_ENTITY_NEW up : existingData) {
	            	up.setApprovedFlg('S');
	            		            	
	            	
	            	PURCHASE_ORDER_ENTITY_NEW_rep.save(up);
	            	
	            }
                
               
                
	            
	            
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getPaidAmount();
	            
	         // Check if paidAmount is not null and greater than zero
	            if (list.get(0).getTotalAmount().compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	            	Transaction_table_demo newTransaction = new Transaction_table_demo();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(list.get(0).getTotalAmount());
	                    //newTransaction.setDr_amt(paidAmount);
	                    //newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase");
	       	         
	       	         

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_demo_rep.save(newTransaction);

	                
	            }
	            
	            
	          
	           
	                
		     
		           
	            //adding entry stock to chart of account
		         // Generate a new unique ID for the Transaction
	           
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         
	             String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();

	             for (PO_invoice_entity param :list ) {
	            	 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemcode());
	            	 CapitalTrans trantab= new CapitalTrans();
	            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 trantab.setId(id.toString());
	            	 trantab.setJournalDate(currentdate);
	            	 
	            	 trantab.setJournalTranId("TRAN000"+sid);
	            	 trantab.setPartTranId(count);
	            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
	            	 trantab.setAccountNumber(account.getAccountNumber());
	            	 trantab.setAccountName(account.getAccountName());
	            	 trantab.setDescription(param.getPoId());
	            	 trantab.setDebits(param.getAmountPerItem());
	            	 trantab.setCredits(BigDecimal.ZERO);
	            	 trantab.setTran_particulars(vendor.getVendorName());
	            	 trantab.setVendor_id(vendor.getVendorId());
	            	 trantab.setVendor_name(vendor.getVendorName());
	            	 count++;
	            	 CapitalTransRep.save(trantab);
	            	 //updateing amount
	            	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(param.getAmountPerItem()));
	            	 account.setAccountBalance(account.getAccountBalance().add(param.getAmountPerItem()));
	            	 //Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(account.getParentaccount());
		             //parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(param.getAmountPerItem()));
		             //parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(param.getAmountPerItem()));
		             Erp_ChartOfAccountsRep.save(account);
		             //Erp_ChartOfAccountsRep.save(parentchartaccount);
		             
		             String parentaccount = account.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getAmountPerItem()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getAmountPerItem()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

	            	 
	            	 if(param.getPriceHeader().equals("withtax")) {
	            		 if(param.getTaxPercentage().startsWith("I")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	 	 							.getSingleResult();
	            			 Erp_ChartOfAccounts igstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00023");
	            			 CapitalTrans trantab1= new CapitalTrans();
	            			 trantab1.setId(id1.toString());
	    	            	 trantab1.setJournalDate(currentdate);
	            			 trantab1.setJournalTranId("TRAN000"+sid);
	            			 trantab1.setPartTranId(count);
	            			 trantab1.setFullAccount(igstaccount.getAccountNumber()+"-"+igstaccount.getAccountName());
	            			 trantab1.setAccountNumber(igstaccount.getAccountNumber());
	            			 trantab1.setAccountName(igstaccount.getAccountName());
	            			 trantab1.setDescription(param.getPoId());
	            			 trantab1.setDebits(param.getTaxAmount());
	            			 trantab1.setCredits(BigDecimal.ZERO);
	            			 trantab1.setTran_particulars(vendor.getVendorName());
	            			 trantab1.setVendor_id(vendor.getVendorId());
	            			 trantab1.setVendor_name(vendor.getVendorName());
	            			 CapitalTransRep.save(trantab1);
	            			 
	            			 
	            			 igstaccount.setTotalDebitBalance(igstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
	            			 igstaccount.setAccountBalance(igstaccount.getAccountBalance().add(param.getTaxAmount()));
	    	            	 
	            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(igstaccount.getParentaccount());
	    	            	 
	    		             Erp_ChartOfAccountsRep.save(igstaccount);
	    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    		             
	    		             String parentaccount1 = igstaccount.getParentaccount();
	    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	    	            	     
	    	            	     if (parentchartaccount1 != null) {
	    	            	    	 
	    	            	    	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
	    	    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
	    	            	        
	    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
	    	            	     } else {
	    	            	         break; 
	    	            	     }
	    	            	 }

	            			 count++;
	            			 	            			 	            			 
	            		 }else if(param.getTaxPercentage().startsWith("C")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Cgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00047");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Cgstaccount.getAccountNumber()+"-"+Cgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Cgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Cgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getPoId());
		            			 trantab1.setDebits(param.getTaxAmount());
		            			 trantab1.setCredits(BigDecimal.ZERO);
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Cgstaccount.setTotalDebitBalance(Cgstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
		            			 Cgstaccount.setAccountBalance(Cgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			
		    		             Erp_ChartOfAccountsRep.save(Cgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             
		    		             String parentaccount1 = Cgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		    		             
		            			 count++;
	            		 }else if(param.getTaxPercentage().startsWith("S")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Sgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00050");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Sgstaccount.getAccountNumber()+"-"+Sgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Sgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Sgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getPoId());
		            			 trantab1.setDebits(param.getTaxAmount());
		            			 trantab1.setCredits(BigDecimal.ZERO);
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Sgstaccount.setTotalDebitBalance(Sgstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
		            			 Sgstaccount.setAccountBalance(Sgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Sgstaccount.getParentaccount());
		    	            	 //parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
		    		             //parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    		             Erp_ChartOfAccountsRep.save(Sgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             String parentaccount1 = Sgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		            			 count++;
	            		 }
	            		 
	            		 
	            		 
	            	 }
	             }
	             BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
	             CapitalTrans trantab= new CapitalTrans();
	             
            	 trantab.setId(id.toString());
            	 trantab.setJournalDate(currentdate);
            	 
            	 trantab.setJournalTranId("TRAN000"+sid);
            	 trantab.setPartTranId(count);
            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
            	 trantab.setAccountNumber(account.getAccountNumber());
            	 trantab.setAccountName(account.getAccountName());
            	 trantab.setDescription(list.get(0).getPoId());
            	 trantab.setCredits(list.get(0).getTotalAmount());
            	 trantab.setDebits(BigDecimal.ZERO);
            	 trantab.setTran_particulars("Purchase");
            	 trantab.setVendor_id(vendor.getVendorId());
            	 trantab.setVendor_name(vendor.getVendorName());
            	 count++;
            	 CapitalTransRep.save(trantab);
            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(list.get(0).getTotalAmount()));
            	 account.setAccountBalance(account.getAccountBalance().add(list.get(0).getTotalAmount()));
            	 Erp_ChartOfAccountsRep.save(account);
            	 String parentaccount = account.getParentaccount();

            	 while (parentaccount != null && !parentaccount.isEmpty()) {
            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
            	     
            	     if (parentchartaccount1 != null) {
            	         
            	         parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(list.get(0).getTotalAmount()));
            	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getTotalAmount()));
            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
            	         parentaccount = parentchartaccount1.getParentaccount();
            	     } else {
            	         break; 
            	     }
            	 }

            	 
            	 
            	 //balancing the advance amount 
            	 if(existingData.get(0).getPaidAmount()>0) {
            		 //getaccount
            		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
            		 //tranid
            		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
 			 				.getSingleResult();
            		 
	            	 CapitalTrans trantab1= new CapitalTrans();
	            	 //journalid
	            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab1.setId(id1.toString());
	            	 trantab1.setJournalDate(currentdate);
	            	 
	            	 trantab1.setJournalTranId("TRAN000"+journalid1);
	            	 trantab1.setPartTranId(1);
		             trantab1.setAccountNumber("ACC00037");
		             trantab1.setFullAccount("ACC00037-Vendor Advance Account");
		             trantab1.setAccountName("Vendor Advance Account");
	            	 trantab1.setDescription(list.get(0).getPoId());
	            	 trantab1.setCredits(BigDecimal.valueOf(existingData.get(0).getPaidAmount()));
	            	 trantab1.setDebits(BigDecimal.ZERO);
	            	 trantab1.setTran_particulars(account1.getAccountName());
	            	 trantab1.setVendor_id(vendor.getVendorId());
	            	 trantab1.setVendor_name(vendor.getVendorName());
	            	 trantab1.setTotalCredits(BigDecimal.valueOf(existingData.get(0).getPaidAmount()));
	            	 trantab1.setTotalDebits(BigDecimal.ZERO);
	            	 //updating all balance child account balance 
	            	 Erp_ChartOfAccounts chartaccount=  Erp_ChartOfAccountsRep.findsccount("ACC00037");
	            	 
	            	 chartaccount.setTotalCreditBalance(chartaccount.getTotalCreditBalance().add(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
		             chartaccount.setAccountBalance(chartaccount.getAccountBalance().subtract(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
		             Erp_ChartOfAccountsRep.save(chartaccount);
		             
		             //updating parent account balance 
		             String parentaccount1 = chartaccount.getParentaccount();

	            	 while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount2 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	            	     
	            	     if (parentchartaccount2 != null) {
	            	         
	            	         parentchartaccount2.setTotalCreditBalance(parentchartaccount2.getTotalCreditBalance().add(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
	            	         parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().subtract(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount2);
	            	         parentaccount1 = parentchartaccount2.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	 
	            	 CapitalTransRep.save(trantab1);
	            	 
	            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 CapitalTrans trantab2= new CapitalTrans();
	            	 trantab2.setId(id2.toString());
	            	 trantab2.setJournalDate(currentdate);
	            	 
	            	 trantab2.setJournalTranId("TRAN000"+journalid1);
	            	 trantab2.setPartTranId(2);
		             trantab2.setAccountNumber(account1.getAccountNumber());
		             trantab2.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
		             trantab2.setAccountName(account1.getAccountName());
	            	 trantab2.setDescription(list.get(0).getPoId());
	            	 trantab2.setDebits(BigDecimal.valueOf(existingData.get(0).getPaidAmount()));
	            	 trantab2.setCredits(BigDecimal.ZERO);
	            	 trantab2.setTran_particulars("Vendor Advance Account-Adjusted");
	            	 trantab2.setVendor_id(vendor.getVendorId());
	            	 trantab2.setVendor_name(vendor.getVendorName());
	            	 trantab2.setTotalDebits(BigDecimal.valueOf(existingData.get(0).getPaidAmount()));
	            	 trantab2.setTotalCredits(BigDecimal.ZERO);
	            	 CapitalTransRep.save(trantab2);	        
	            	 
	            	 
	            	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
	            	 account1.setAccountBalance(account1.getAccountBalance().subtract(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
		             Erp_ChartOfAccountsRep.save(account1);
		             
		             
		            
	            	 String parentaccount3 = account1.getParentaccount();
	            	 
	            	 System.out.println("parentaccount="+parentaccount3);

	            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	            	     
	            	     if (parentchartaccount4 != null) {
	            	         
	            	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
	            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(BigDecimal.valueOf(existingData.get(0).getPaidAmount())));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	            	         parentaccount3 = parentchartaccount4.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

            		 
            	 }
            	 
            	 
            	 
            	 //paid amount 
            	 if(list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO)>0) {
            		 
            		//getaccount
            		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
            		 //tranid
            		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
 			 				.getSingleResult();
            		 
	            	 CapitalTrans trantab1= new CapitalTrans();
	            	 //journalid
	            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab1.setId(id1.toString());
	            	 trantab1.setJournalDate(currentdate);
	            	 trantab1.setJournalTranId("TRAN000"+journalid1);
	            	 trantab1.setPartTranId(1);
	            	 trantab1.setAccountNumber(account1.getAccountNumber());
	            	 trantab1.setAccountName(account1.getAccountName());
	            	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	            	 trantab1.setDescription(list.get(0).getPoId());
	            	 trantab1.setDebits(list.get(0).getPaidAmount());
	            	 trantab1.setCredits(BigDecimal.ZERO);
	            	 trantab1.setTotalDebits(list.get(0).getPaidAmount());
	            	 trantab1.setTotalCredits(BigDecimal.ZERO);
	            	 trantab1.setVendor_id(vendor.getVendorId());
	            	 trantab1.setVendor_name(vendor.getVendorName());
	            	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab1.setTran_particulars("Cash on Hand");
		             }
		             else {
		            	 trantab1.setTran_particulars("IDBI Bank");
		             }
	            	 
	            	 CapitalTransRep.save(trantab1);
	            	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	 account1.setAccountBalance(account1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(account1);
		             
	            	 String parentaccount3 = account1.getParentaccount();
	            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	            	     
	            	     if (parentchartaccount4 != null) {
	            	         
	            	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	            	         parentaccount3 = parentchartaccount4.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	 //part_tran=2
	            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 CapitalTrans trantab2= new CapitalTrans();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab2.setId(id2.toString());
	            	 trantab2.setJournalDate(currentdate);
	            	 trantab2.setJournalTranId("TRAN000"+journalid1);
	            	 trantab2.setPartTranId(2);
	            	 
	            	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab2.setAccountNumber("ACC00048");
			             trantab2.setFullAccount("ACC00048-Cash on Hand");
			             trantab2.setAccountName("Cash on Hand");
		             }
		             else {
		            	 trantab2.setAccountNumber("ACC00045");
			             trantab2.setFullAccount("ACC00045-IDBI Bank");
			             trantab2.setAccountName("IDBI Bank");
		             }
		             
	            	 trantab2.setDescription(list.get(0).getPoId());
	            	 trantab2.setCredits(list.get(0).getPaidAmount());
	            	 trantab2.setDebits(BigDecimal.ZERO);
	            	 trantab2.setTotalDebits(BigDecimal.ZERO);
	            	 trantab2.setTotalCredits(list.get(0).getPaidAmount());
	            	 trantab2.setVendor_id(vendor.getVendorId());
	            	 trantab2.setVendor_name(vendor.getVendorName());
	            	 trantab2.setTran_particulars(account1.getAccountName());
	            	 CapitalTransRep.save(trantab2);
	            	 
	            	 if(list.get(0).getPaymentType().equals("cash")) {
	            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
	            		 
	            		 chartaccount1.setTotalCreditBalance(chartaccount1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
	            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
			             Erp_ChartOfAccountsRep.save(chartaccount1);
			             
			             String parentaccount4 = chartaccount1.getParentaccount();
		            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
		            	     
		            	     if (parentchartaccount5 != null) {
		            	         
		            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
		            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
		            	         parentaccount4 = parentchartaccount5.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }
		            	

		             }
		             else {
		            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
	            		 
	            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
			             Erp_ChartOfAccountsRep.save(chartaccount1);
			             
			             String parentaccount4 = chartaccount1.getParentaccount();
		            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
		            	     
		            	     if (parentchartaccount5 != null) {
		            	         
		            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
		            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
		            	         parentaccount4 = parentchartaccount5.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }
		             }
		            
	            	 

	            	
	            	 
            		 
            		 
            		 
            		 
            		 
            		 
            		 
            		 
            	 }
	            
	             
	             
	             
	             
	             
	             
	       

		         
		
	            

	            
	         // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	            	Transaction_table_demo newTransaction = new Transaction_table_demo();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setDr_amt(paidAmount);
	                    newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Payment");
	       	         
	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_demo_rep.save(newTransaction);

	                
	            }
	            
	            //
	            
	            
	            
	            
	            
	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setDr_amt(paidAmount);
	                    newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase Invoice");
	       	         BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
	       	         if(list.get(0).getPaymentType().equals("cash")) {
	       	        	 up.setCurrent_cash_balance(up.getCurrent_cash_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         else {
	       	        	up.setCurrent_account_balance(up.getCurrent_account_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         
	       	      btmAdminOrganizationMasterRep.save(up);


	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }


	    
	            msg = ResponseEntity.ok("Purchase Details Updated Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}

	//purchase direct invoice
	
	public ResponseEntity<String> directinvoice_edit(List<PO_invoice_entity> list, String formmode) {
	    ResponseEntity<String> msg = null;

		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	//Calculation
	        	
	            // Fetch the vendor details using the vendor ID from the first element in the list
	            VendorCreation up1 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            
	            BigDecimal vendor_cr= up1.getCrAmt();
	            BigDecimal vendor_adnce= up1.getAdvanceamount();
	            

	            
	           
	            if(list.get(0).getBalanceAmount() !=  null || list.get(0).getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
	            	
	            	BigDecimal new_ad=vendor_adnce.add(list.get(0).getBalanceAmount());
	            	up1.setAdvanceamount(new_ad);
	            	BigDecimal new_cr= vendor_cr.add(list.get(0).getPaidAmount());
	            	up1.setCrAmt(new_cr);
		        	   VendorCreationRep.save(up1);	   
	            }
	            
	           
	            
	            //PO -> Invoice 
	            BigInteger PO_idBigInt = (BigInteger) session
		                .createNativeQuery("SELECT NEXT VALUE FOR NEW_PO_ID AS PO_id")
		                .getSingleResult();
		            BigDecimal PO_id = new BigDecimal(PO_idBigInt); 
		            String poid="PO0000" + PO_id.toString();

	            
	        	
	            for (PO_invoice_entity param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR INVOICE_ID AS id")
	            		    .getSingleResult()).longValue();
	            	
	            	Long idstockbatchno = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR stockbatchno AS id")
	            		    .getSingleResult()).longValue();
	            	 Date currentdate = new Date();

	            	 	param.setInvoice_conform_date(currentdate);
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	            		param.setPoId("PO0000" + PO_id.toString());
	                    param.setExtra_amount(param.getBalanceAmount());
	                    param.setLast_extra_amount(param.getPaidAmount());
	                    param.setApprovedFlg('S');
	                    ItemCreation up= ItemCreationRep.getitemlistbyid(param.getItemcode());
	                    String catg=up.getCategory();
	                    	param.setBatch("BATCH00"+idstockbatchno.toString());
		                    param.setUseingqty(param.getQty());
		                    param.setAssetCategory(catg);
	                PO_invoice_reps.save(param);
	                System.out.println("Saved PO entity: " + param);
	            }
	            list=PO_invoice_reps.getbyid(poid);

	            //PO update
	            
	            ACCOUNT_LEDGER_PO_Entity ledger=new ACCOUNT_LEDGER_PO_Entity();
	                            
               
                
	            
	            
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getPaidAmount();
	            
	         // Check if paidAmount is not null and greater than zero
	            if (list.get(0).getTotalAmount().compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	            	Transaction_table_demo newTransaction = new Transaction_table_demo();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(list.get(0).getTotalAmount());
	                    //newTransaction.setDr_amt(paidAmount);
	                    //newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase");
	       	         
	       	         

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_demo_rep.save(newTransaction);

	                
	            }
	            
	            
	          
	           
	                
		     
		           
	            //adding entry stock to chart of account
		         // Generate a new unique ID for the Transaction
	           
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         
	             String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();

	             for (PO_invoice_entity param :list ) {
	            	 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemcode());
	            	 CapitalTrans trantab= new CapitalTrans();
	            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 trantab.setId(id.toString());
	            	 trantab.setJournalDate(currentdate);
	            	 
	            	 trantab.setJournalTranId("TRAN000"+sid);
	            	 trantab.setPartTranId(count);
	            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
	            	 trantab.setAccountNumber(account.getAccountNumber());
	            	 trantab.setAccountName(account.getAccountName());
	            	 trantab.setDescription(param.getPoId());
	            	 trantab.setDebits(param.getAmountPerItem());
	            	 trantab.setCredits(BigDecimal.ZERO);
	            	 trantab.setTran_particulars(vendor.getVendorName());
	            	 trantab.setVendor_id(vendor.getVendorId());
	            	 trantab.setVendor_name(vendor.getVendorName());
	            	 count++;
	            	 CapitalTransRep.save(trantab);
	            	 //updateing amount
	            	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(param.getAmountPerItem()));
	            	 account.setAccountBalance(account.getAccountBalance().add(param.getAmountPerItem()));
	            	 //Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(account.getParentaccount());
		             //parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(param.getAmountPerItem()));
		             //parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(param.getAmountPerItem()));
		             Erp_ChartOfAccountsRep.save(account);
		             //Erp_ChartOfAccountsRep.save(parentchartaccount);
		             
		             String parentaccount = account.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getAmountPerItem()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getAmountPerItem()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

	            	 
	            	 if(param.getPriceHeader().equals("withtax")) {
	            		 if(param.getTaxPercentage().startsWith("I")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	 	 							.getSingleResult();
	            			 Erp_ChartOfAccounts igstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00023");
	            			 CapitalTrans trantab1= new CapitalTrans();
	            			 trantab1.setId(id1.toString());
	    	            	 trantab1.setJournalDate(currentdate);
	            			 trantab1.setJournalTranId("TRAN000"+sid);
	            			 trantab1.setPartTranId(count);
	            			 trantab1.setFullAccount(igstaccount.getAccountNumber()+"-"+igstaccount.getAccountName());
	            			 trantab1.setAccountNumber(igstaccount.getAccountNumber());
	            			 trantab1.setAccountName(igstaccount.getAccountName());
	            			 trantab1.setDescription(param.getPoId());
	            			 trantab1.setDebits(param.getTaxAmount());
	            			 trantab1.setCredits(BigDecimal.ZERO);
	            			 trantab1.setTran_particulars(vendor.getVendorName());
	            			 trantab1.setVendor_id(vendor.getVendorId());
	            			 trantab1.setVendor_name(vendor.getVendorName());
	            			 CapitalTransRep.save(trantab1);
	            			 
	            			 
	            			 igstaccount.setTotalDebitBalance(igstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
	            			 igstaccount.setAccountBalance(igstaccount.getAccountBalance().add(param.getTaxAmount()));
	    	            	 
	            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(igstaccount.getParentaccount());
	    	            	 
	    		             Erp_ChartOfAccountsRep.save(igstaccount);
	    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    		             
	    		             String parentaccount1 = igstaccount.getParentaccount();
	    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	    	            	     
	    	            	     if (parentchartaccount1 != null) {
	    	            	    	 
	    	            	    	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
	    	    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
	    	            	        
	    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
	    	            	     } else {
	    	            	         break; 
	    	            	     }
	    	            	 }

	            			 count++;
	            			 	            			 	            			 
	            		 }else if(param.getTaxPercentage().startsWith("C")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Cgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00047");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Cgstaccount.getAccountNumber()+"-"+Cgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Cgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Cgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getPoId());
		            			 trantab1.setDebits(param.getTaxAmount());
		            			 trantab1.setCredits(BigDecimal.ZERO);
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Cgstaccount.setTotalDebitBalance(Cgstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
		            			 Cgstaccount.setAccountBalance(Cgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			
		    		             Erp_ChartOfAccountsRep.save(Cgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             
		    		             String parentaccount1 = Cgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		    		             
		            			 count++;
	            		 }else if(param.getTaxPercentage().startsWith("S")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Sgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00050");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Sgstaccount.getAccountNumber()+"-"+Sgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Sgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Sgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getPoId());
		            			 trantab1.setDebits(param.getTaxAmount());
		            			 trantab1.setCredits(BigDecimal.ZERO);
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Sgstaccount.setTotalDebitBalance(Sgstaccount.getTotalDebitBalance().add(param.getTaxAmount()));
		            			 Sgstaccount.setAccountBalance(Sgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Sgstaccount.getParentaccount());
		    	            	 //parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
		    		             //parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    		             Erp_ChartOfAccountsRep.save(Sgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             String parentaccount1 = Sgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		            			 count++;
	            		 }
	            		 
	            		 
	            		 
	            	 }
	             }
	             BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
	             CapitalTrans trantab= new CapitalTrans();
	             
            	 trantab.setId(id.toString());
            	 trantab.setJournalDate(currentdate);
            	 
            	 trantab.setJournalTranId("TRAN000"+sid);
            	 trantab.setPartTranId(count);
            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
            	 trantab.setAccountNumber(account.getAccountNumber());
            	 trantab.setAccountName(account.getAccountName());
            	 trantab.setDescription(list.get(0).getPoId());
            	 trantab.setCredits(list.get(0).getTotalAmount());
            	 trantab.setDebits(BigDecimal.ZERO);
            	 trantab.setTran_particulars("Purchase");
            	 trantab.setVendor_id(vendor.getVendorId());
            	 trantab.setVendor_name(vendor.getVendorName());
            	 count++;
            	 CapitalTransRep.save(trantab);
            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(list.get(0).getTotalAmount()));
            	 account.setAccountBalance(account.getAccountBalance().add(list.get(0).getTotalAmount()));
            	 Erp_ChartOfAccountsRep.save(account);
            	 String parentaccount = account.getParentaccount();

            	 while (parentaccount != null && !parentaccount.isEmpty()) {
            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
            	     
            	     if (parentchartaccount1 != null) {
            	         
            	         parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(list.get(0).getTotalAmount()));
            	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getTotalAmount()));
            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
            	         parentaccount = parentchartaccount1.getParentaccount();
            	     } else {
            	         break; 
            	     }
            	 }

            	 
            	 
            	             	 
            	 
            	 
            	 //paid amount 
            	 if(list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO)>0) {
            		 
            		//getaccount
            		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
            		 //tranid
            		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
 			 				.getSingleResult();
            		 
	            	 CapitalTrans trantab1= new CapitalTrans();
	            	 //journalid
	            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab1.setId(id1.toString());
	            	 trantab1.setJournalDate(currentdate);
	            	 trantab1.setJournalTranId("TRAN000"+journalid1);
	            	 trantab1.setPartTranId(1);
	            	 trantab1.setAccountNumber(account1.getAccountNumber());
	            	 trantab1.setAccountName(account1.getAccountName());
	            	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	            	 trantab1.setDescription(list.get(0).getPoId());
	            	 trantab1.setDebits(list.get(0).getPaidAmount());
	            	 trantab1.setCredits(BigDecimal.ZERO);
	            	 trantab1.setTotalDebits(list.get(0).getPaidAmount());
	            	 trantab1.setTotalCredits(list.get(0).getPaidAmount());
	            	 trantab1.setVendor_id(vendor.getVendorId());
	            	 trantab1.setVendor_name(vendor.getVendorName());
	            	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab1.setTran_particulars("Cash on Hand");
		             }
		             else {
		            	 trantab1.setTran_particulars("IDBI Bank");
		             }
	            	 
	            	 CapitalTransRep.save(trantab1);
	            	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	 account1.setAccountBalance(account1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(account1);
		             
	            	 String parentaccount3 = account1.getParentaccount();
	            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	            	     
	            	     if (parentchartaccount4 != null) {
	            	         
	            	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	            	         parentaccount3 = parentchartaccount4.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	 //part_tran=2
	            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 CapitalTrans trantab2= new CapitalTrans();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab2.setId(id2.toString());
	            	 trantab2.setJournalDate(currentdate);
	            	 trantab2.setJournalTranId("TRAN000"+journalid1);
	            	 trantab2.setPartTranId(2);
	            	 
	            	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab2.setAccountNumber("ACC00048");
			             trantab2.setFullAccount("ACC00048-Cash on Hand");
			             trantab2.setAccountName("Cash on Hand");
		             }
		             else {
		            	 trantab2.setAccountNumber("ACC00045");
			             trantab2.setFullAccount("ACC00045-IDBI Bank");
			             trantab2.setAccountName("IDBI Bank");
		             }
		             
	            	 trantab2.setDescription(list.get(0).getPoId());
	            	 trantab2.setCredits(list.get(0).getPaidAmount());
	            	 trantab2.setDebits(BigDecimal.ZERO);
	            	 trantab2.setTotalDebits(list.get(0).getPaidAmount());
	            	 trantab2.setTotalCredits(list.get(0).getPaidAmount());
	            	 trantab2.setVendor_id(vendor.getVendorId());
	            	 trantab2.setVendor_name(vendor.getVendorName());
	            	 trantab2.setTran_particulars(account1.getAccountName());
	            	 CapitalTransRep.save(trantab2);
	            	 
	            	 if(list.get(0).getPaymentType().equals("cash")) {
	            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
	            		 
	            		 chartaccount1.setTotalCreditBalance(chartaccount1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
	            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
			             Erp_ChartOfAccountsRep.save(chartaccount1);
			             
			             String parentaccount4 = chartaccount1.getParentaccount();
		            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
		            	     
		            	     if (parentchartaccount5 != null) {
		            	         
		            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
		            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
		            	         parentaccount4 = parentchartaccount5.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }
		            	

		             }
		             else {
		            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
	            		 
	            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
			             Erp_ChartOfAccountsRep.save(chartaccount1);
			             
			             String parentaccount4 = chartaccount1.getParentaccount();
		            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
		            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
		            	     
		            	     if (parentchartaccount5 != null) {
		            	         
		            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
		            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(list.get(0).getPaidAmount()));
		            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
		            	         parentaccount4 = parentchartaccount5.getParentaccount();
		            	     } else {
		            	         break; 
		            	     }
		            	 }
		             }
		            
	            	 

	            	
	            	 
            		 
            		 
            		 
            		 
            		 
            		 
            		 
            		 
            	 }
	            
	             
	             
	             
	             
	             
	             
	       

		         
		
	            

	            
	         // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	            	Transaction_table_demo newTransaction = new Transaction_table_demo();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setDr_amt(paidAmount);
	                    newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Payment");
	       	         
	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_demo_rep.save(newTransaction);

	                
	            }
	            
	            //
	            
	            
	            
	            
	            
	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setDr_amt(paidAmount);
	                    newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase Invoice");
	       	         BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
	       	         if(list.get(0).getPaymentType().equals("cash")) {
	       	        	 up.setCurrent_cash_balance(up.getCurrent_cash_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         else {
	       	        	up.setCurrent_account_balance(up.getCurrent_account_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         
	       	      btmAdminOrganizationMasterRep.save(up);


	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }


	            msg = ResponseEntity.ok("PO0000" + PO_id.toString()+"-Added Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*----TSK PURCHASE RETURN--*/
	@Autowired
	PO_Return_Rep PO_Return_Reps;
	
	public ResponseEntity<String> po_return_submit(List<PO_Return_Entity> list, String formmode, String Opid) {
	    ResponseEntity<String> msg = null;
	    String OWid="";
	    String vendorid="";
        BigDecimal vendorbalance=BigDecimal.ZERO;
        BigDecimal Balance=BigDecimal.ZERO;
        BigDecimal total=BigDecimal.ZERO;
		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	//Calculation
	        	
	            // Fetch the vendor details using the vendor ID from the first element in the list
	            VendorCreation up1 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	           
	            BigDecimal vendor_cr= up1.getCrAmt();
	            BigDecimal old_adv= up1.getAdvanceamount();
            	System.out.println("The total amount is : "+list.get(0).getTotalAmounts());
            	BigDecimal PaidAmount = new BigDecimal(list.get(0).getPaidAmount());
            	
            	if (PaidAmount != null && PaidAmount.compareTo(BigDecimal.ZERO) > 0) {
            		BigDecimal new_cr=up1.getDrAmt().add(PaidAmount);
            		up1.setDrAmt(new_cr);
            	}
            	/*if (list.get(0).getTotalAmounts() != null && list.get(0).getTotalAmounts().compareTo(BigDecimal.ZERO) > 0) {
	            	System.out.println("inside-1");
	            	BigDecimal new_cr=vendor_cr.subtract(list.get(0).getTotalAmounts());
	            	up1.setCrAmt(new_cr);
	            	
	            	if (old_adv != null && old_adv.compareTo(BigDecimal.ZERO) != 0) {
	            		
	            	    BigDecimal newdr = list.get(0).getTotalAmounts().subtract(old_adv);
	            	    if(newdr.compareTo(BigDecimal.ZERO) <= 0) {
	            	    	BigDecimal d=up1.getDrAmt();
	            	        BigDecimal positiveValue = newdr.abs(); 
		            		up1.setDrAmt(d.add(positiveValue));
		            		up1.setAdvanceamount(BigDecimal.valueOf(0.00));
	            	    	
	            	    }else {
	            	    up1.setDrAmt(newdr);
	            	    }
	            	}else {
	            		BigDecimal d=up1.getDrAmt();
	            		up1.setDrAmt(d.add(list.get(0).getTotalAmounts()));
	            	}

		        	   VendorCreationRep.save(up1);
	            }*/
            	
            	List<PO_invoice_entity> poinvoice=PO_invoice_reps.getbyid(list.get(0).getPoId());
            	BigInteger pr_id = (BigInteger) session
       	             .createNativeQuery("SELECT NEXT VALUE FOR po_return AS id")
       	             .getSingleResult();
            	String pid=String.valueOf(pr_id);
            	

	           //PO Return

	        
	            for (PO_Return_Entity param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR PO_RETURN_ID AS id")
	            		    .getSingleResult()).longValue();
	            	 Date currentdate = new Date();
	            	 param.setReturn_date(currentdate);
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setPoId(Opid);
	                    param.setPo_return_id("PR000"+pid);
	                    param.setApprovedFlg('R');
	                    PO_Return_Reps.save(param);
	                    String itemcode=param.getItemcode();
	                    for(PO_invoice_entity poitem:poinvoice) {
	                    	if(poitem.getItemcode().equals(itemcode)) {
	                    		Integer result =Math.abs(poitem.getUseingqty()-param.getQty());
	                    		poitem.setUseingqty(result);
	                    		PO_invoice_reps.save(poitem);
	                    		
	                    	}
	                    	
	                    	
	                    }
	                System.out.println("Saved PO entity: " + param);
	                vendorid=param.getVendorId();
	                vendorbalance=param.getVendorbalance();
	                Balance=param.getBalanceAmount();
	                total=param.getTotalAmount();
	                
		             // Fetch ledger entries based on Opid (assuming multiple records exist)
	                
	              /*  List<ACCOUNT_LEDGER_PO_Entity> ledgerList = ACCOUNT_LEDGER_PO_rep.findByPoId(Opid); 

	                 for (ACCOUNT_LEDGER_PO_Entity ledger : ledgerList) {
	                    // Check if the item matches
	                        // Handle null case for TotalAmount
	                        if (ledger.getTotalAmount() != null) {
	                           
	                            ledger.setTotalAmount(ledger.getTotalAmount().subtract(param.getTotalAmounts())); // Assign result back
	                        }
	                        // Save updated ledger entity
	                        ACCOUNT_LEDGER_PO_rep.save(ledger);
	                    
	                }*/
	            }
	            
	            
	            
	            VendorCreation up=VendorCreationRep.getvendorlistid(vendorid);
	            if (vendorbalance != null && Balance!=null && vendorbalance.compareTo(total)>=0) {
	                // If advance is not null, add up.getAdvanceamount() to it
	            	//Balance = Balance.add(up.getAdvanceamount() != null ? up.getAdvanceamount() : BigDecimal.ZERO);
	            	up.setAdvanceamount(Balance);
	                VendorCreationRep.save(up);
	            } else if (Balance != null && Balance!=null && vendorbalance.compareTo(total)<=0) {
	                // If Balance is not null, add up.getBalanceamount() to it
	                up.setBalanceamount(Balance);
	                up.setAdvanceamount(BigDecimal.ZERO);
	                VendorCreationRep.save(up);
	            }
	           
	            

	            
	            // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal total_amt = new BigDecimal(list.get(0).getPaidAmount());

	            //BigDecimal total_amt = list.get(0).getPaidAmount();

	            // Check if paidAmount is not null and greater than zero
	            if (total_amt != null && total_amt.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    

	                    // Set cr amount in TSK credit
	                    newTransaction.setCr_amt(total_amt);
	                    
	                    /*newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase Return");
	       	         BTMAdminOrganizationMaster org = btmAdminOrganizationMasterRep.getOrganization();
	       	         if(list.get(0).getPaymentType().equals("cash")) {
	       	        	org.setCurrent_cash_balance(org.getCurrent_cash_balance().add(list.get(0).getAdvanceAmount()));
	       	         }
	       	         else {
	       	        	org.setCurrent_account_balance(org.getCurrent_account_balance().add(list.get(0).getAdvanceAmount()));
	       	         }
	       	         
	       	      	btmAdminOrganizationMasterRep.save();*/


	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(Balance);
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }


	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            msg = ResponseEntity.ok("Purchase Returned Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}

	
	
	/*----TSK sale invoice ADD--*/
	public ResponseEntity<String> saleinvoice_edit(List<SALES_invoice_TABLE> list, String formmode ) {
	    ResponseEntity<String> msg = null;
	    String OWid="";
	    String vendorid="";
        BigDecimal advance=BigDecimal.ZERO;
        BigDecimal Balance=BigDecimal.ZERO;

	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	            for (SALES_invoice_TABLE param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR sale_invoice AS id")
	            		    .getSingleResult()).longValue();
	            	Date currentdate = new Date();
	            	param.setInvoice_conform_date(currentdate);
	            	
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setApprovedFlg('S');
	                    
	                    if(param.equals("Yes")) {
	                    	param.setDeliverystatus("P");
	                    	param.setDeliverycharge(BigDecimal.ZERO);
	                    }
	                    if (param.getBalanceAmount() != null && !param.getBalanceAmount().toString().isEmpty()) {
	                        param.setExtra_amount(param.getBalanceAmount());
	                    } else {
	                        System.out.println("Balance Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setExtra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    if (param.getPaidAmount() != null && !param.getPaidAmount().toString().isEmpty()) {
	                        param.setLast_extra_amount(param.getPaidAmount());
	                    } else {
	                        System.out.println("Paid Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setLast_extra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    
	                    vendorid=param.getVendorId();
	                    String batch=param.getBatch();
	                    
	                    
	                    FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(batch);
	                    BigDecimal qty = new BigDecimal(param.getQty());
	                    BigDecimal fgqty=fg.getUsingQuantity().subtract(qty);
	                    fg.setUsingQuantity(fgqty);
	                    FINISHED_GOODS_Rep.save(fg);
	                    param.setUsingqty(qty);
	                    
		                /*if(param.getBalanceAmount().compareTo(BigDecimal.ZERO) < 0) {
		                	System.out.println(vendorid);
		                	System.out.println("advance="+ param.getBalanceAmount().negate());
		                	advance=param.getBalanceAmount().negate();
		                	
		                }else if(param.getBalanceAmount().compareTo(BigDecimal.ZERO) > 0) {
		                	Balance=param.getBalanceAmount();
		                }*/

	                    SALES_invoice_TABLERep.save(param);
	                System.out.println("Saved PO entity: " + param);
	                OWid=param.getWoId();
	                
	                
	                
	                
					  // Fetch ledger entries based on Woid (assuming multiple records exist)
	                List<ACCOUNT_LEDGER_SALE_Entity> ledgerList = ACCOUNT_LEDGER_SALE_rep.findByPoId(param.getWoId()); 

	                for (ACCOUNT_LEDGER_SALE_Entity ledger : ledgerList) {
	                    // Check if the item matches
	                    if (ledger.getItem().equals(param.getItem())) {
	                        ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));

	                        // Handle null case for TotalAmount
	                        if (ledger.getTotalAmount() == null) {
	                            ledger.setTotalAmount(param.getPaidAmount());
	                        } else {
	                            ledger.setTotalAmount(ledger.getTotalAmount().add(param.getPaidAmount())); // Assign result back
	                        }

	                        // Save updated ledger entity
	                        ACCOUNT_LEDGER_SALE_rep.save(ledger);
	                    }
	                }
	                
	                
	                
	                
	                
	            }
	            
	            VendorCreation up2 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            //List<PURCHASE_ORDER_ENTITY_NEW> oldpo= PURCHASE_ORDER_ENTITY_NEW_rep.getbyid(list.get(0).getPoId());
	            
	            BigDecimal vendor_dr= up2.getDrAmt();
	            BigDecimal vendor_bal= up2.getBalanceamount();
	            

	            if(list.get(0).getPaidAmount() !=  null || list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO) == 0) {
	            	BigDecimal new_dr=vendor_dr.add(list.get(0).getPaidAmount());
	            	up2.setDrAmt(new_dr);
	            	//BigDecimal new_ad=vendor_bal.subtract(list.get(0).getPaidAmount());
	            	BigDecimal new_ad=vendor_bal.add(list.get(0).getBalanceAmount());
	            	up2.setBalanceamount(new_ad);
	            	
		        	   VendorCreationRep.save(up2);
	            }
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getPaidAmount();

	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setWo_id(list.get(0).getWoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(paidAmount);

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }

	          //adding entry stock to chart of account
		         // Generate a new unique ID for the Transaction
	           
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         
	             String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();

	             for (SALES_invoice_TABLE param :list ) {
	            	 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemcode());
	            	 CapitalTrans trantab= new CapitalTrans();
	            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 trantab.setId(id.toString());
	            	 trantab.setJournalDate(currentdate);
	            	 
	            	 trantab.setJournalTranId("TRAN000"+sid);
	            	 trantab.setPartTranId(count);
	            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
	            	 trantab.setAccountNumber(account.getAccountNumber());
	            	 trantab.setAccountName(account.getAccountName());
	            	 trantab.setDescription(param.getWoId());
	            	 trantab.setDebits(BigDecimal.ZERO);
	            	 trantab.setCredits(param.getAmountPerItem());
	            	 trantab.setTran_particulars(vendor.getVendorName());
	            	 trantab.setVendor_id(vendor.getVendorId());
	            	 trantab.setVendor_name(vendor.getVendorName());
	            	 count++;
	            	 CapitalTransRep.save(trantab);
	            	 //updateing amount
	            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	 account.setAccountBalance(account.getAccountBalance().subtract(param.getAmountPerItem()));
	            	 //Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(account.getParentaccount());
		             //parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(param.getAmountPerItem()));
		             //parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(param.getAmountPerItem()));
		             Erp_ChartOfAccountsRep.save(account);
		             //Erp_ChartOfAccountsRep.save(parentchartaccount);
		             
		             String parentaccount = account.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(param.getAmountPerItem()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

	            	 
	            	 if(param.getPriceHeader().equals("withtax")) {
	            		 if(param.getTaxPercentage().startsWith("I")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	 	 							.getSingleResult();
	            			 Erp_ChartOfAccounts igstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00069");
	            			 CapitalTrans trantab1= new CapitalTrans();
	            			 trantab1.setId(id1.toString());
	    	            	 trantab1.setJournalDate(currentdate);
	            			 trantab1.setJournalTranId("TRAN000"+sid);
	            			 trantab1.setPartTranId(count);
	            			 trantab1.setFullAccount(igstaccount.getAccountNumber()+"-"+igstaccount.getAccountName());
	            			 trantab1.setAccountNumber(igstaccount.getAccountNumber());
	            			 trantab1.setAccountName(igstaccount.getAccountName());
	            			 trantab1.setDescription(param.getWoId());
	            			 trantab1.setCredits(param.getTaxAmount());
	            			 trantab1.setDebits(BigDecimal.ZERO);
	            			 trantab1.setTran_particulars(vendor.getVendorName());
	            			 trantab1.setVendor_id(vendor.getVendorId());
	            			 trantab1.setVendor_name(vendor.getVendorName());
	            			 CapitalTransRep.save(trantab1);
	            			 
	            			 
	            			 igstaccount.setTotalCreditBalance(igstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
	            			 igstaccount.setAccountBalance(igstaccount.getAccountBalance().add(param.getTaxAmount()));
	    	            	 
	            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(igstaccount.getParentaccount());
	    	            	 
	    		             Erp_ChartOfAccountsRep.save(igstaccount);
	    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    		             
	    		             String parentaccount1 = igstaccount.getParentaccount();
	    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	    	            	     
	    	            	     if (parentchartaccount1 != null) {
	    	            	    	 
	    	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
	    	    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
	    	            	        
	    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
	    	            	     } else {
	    	            	         break; 
	    	            	     }
	    	            	 }

	            			 count++;
	            			 	            			 	            			 
	            		 }else if(param.getTaxPercentage().startsWith("C")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Cgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00067");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Cgstaccount.getAccountNumber()+"-"+Cgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Cgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Cgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Cgstaccount.setTotalCreditBalance(Cgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Cgstaccount.setAccountBalance(Cgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			
		    		             Erp_ChartOfAccountsRep.save(Cgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             
		    		             String parentaccount1 = Cgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		    		             
		            			 count++;
	            		 }else if(param.getTaxPercentage().startsWith("S")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Sgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00068");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Sgstaccount.getAccountNumber()+"-"+Sgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Sgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Sgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Sgstaccount.setTotalCreditBalance(Sgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Sgstaccount.setAccountBalance(Sgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Sgstaccount.getParentaccount());
		    	            	 //parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
		    		             //parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    		             Erp_ChartOfAccountsRep.save(Sgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             String parentaccount1 = Sgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		            			 count++;
	            		 }
	            		 
	            		 
	            		 
	            	 }
	             }
	             BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
	             CapitalTrans trantab= new CapitalTrans();
	             
           	 trantab.setId(id.toString());
           	 trantab.setJournalDate(currentdate);
           	 
           	 trantab.setJournalTranId("TRAN000"+sid);
           	 trantab.setPartTranId(count);
           	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
           	 trantab.setAccountNumber(account.getAccountNumber());
           	 trantab.setAccountName(account.getAccountName());
           	 trantab.setDescription(list.get(0).getWoId());
           	 trantab.setCredits(BigDecimal.ZERO);
           	 trantab.setDebits(list.get(0).getTotalAmount());
           	 trantab.setTran_particulars("Sale");
           	 trantab.setVendor_id(vendor.getVendorId());
           	 trantab.setVendor_name(vendor.getVendorName());
           	 count++;
           	 CapitalTransRep.save(trantab);
           	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	 account.setAccountBalance(account.getAccountBalance().add(list.get(0).getTotalAmount()));
           	 Erp_ChartOfAccountsRep.save(account);
           	 String parentaccount = account.getParentaccount();

           	 while (parentaccount != null && !parentaccount.isEmpty()) {
           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
           	     
           	     if (parentchartaccount1 != null) {
           	         
           	         parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getTotalAmount()));
           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
           	         parentaccount = parentchartaccount1.getParentaccount();
           	     } else {
           	         break; 
           	     }
           	 }

	            
	            
	            
	            /*VendorCreation up=VendorCreationRep.getvendorlistid(vendorid);
	            if (advance != null && advance.compareTo(BigDecimal.ZERO) != 0) {
	                // If advance is not null, add up.getAdvanceamount() to it
	                advance = advance.add(up.getAdvanceamount() != null ? up.getAdvanceamount() : BigDecimal.ZERO);
	                up.setAdvanceamount(advance);
	                VendorCreationRep.save(up);
	            } else if (Balance != null && Balance.compareTo(BigDecimal.ZERO) != 0) {
	                // If Balance is not null, add up.getBalanceamount() to it
	                Balance = Balance.add(up.getBalanceamount() != null ? up.getBalanceamount() : BigDecimal.ZERO);
	                up.setBalanceamount(Balance);
	                VendorCreationRep.save(up);
	            }*/

	            

	            List<SALES_ORDER_ENTITY_NEW> lists=   SALES_ORDER_ENTITY_NEW_REP.getparticular(OWid);
	            

	            
	          //balancing the advance amount 
           	 if(lists.get(0).getPaidAmount()>0) {
           		 //getaccount
           		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
           		 //tranid
           		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();
           		 
	            	 CapitalTrans trantab1= new CapitalTrans();
	            	 //journalid
	            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 //first tran to getting back the advance to vendor account  
	            	 trantab1.setId(id1.toString());
	            	 trantab1.setJournalDate(currentdate);
	            	 
	            	 trantab1.setJournalTranId("TRAN000"+journalid1);
	            	 trantab1.setPartTranId(1);
		             trantab1.setAccountNumber("ACC00064");
		             trantab1.setFullAccount("ACC00064-Sale Advance Account");
		             trantab1.setAccountName("Sale Advance Account");
	            	 trantab1.setDescription(list.get(0).getWoId());
	            	 trantab1.setCredits(BigDecimal.ZERO);
	            	 trantab1.setDebits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 trantab1.setTran_particulars(account1.getAccountName());
	            	 trantab1.setVendor_id(vendor.getVendorId());
	            	 trantab1.setVendor_name(vendor.getVendorName());
	            	 trantab1.setTotalCredits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 trantab1.setTotalDebits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 //updating all balance child account balance 
	            	 Erp_ChartOfAccounts chartaccount=  Erp_ChartOfAccountsRep.findsccount("ACC00064");
	            	 
	            	 chartaccount.setTotalDebitBalance(chartaccount.getTotalDebitBalance().add(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
		             chartaccount.setAccountBalance(chartaccount.getAccountBalance().subtract(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
		             Erp_ChartOfAccountsRep.save(chartaccount);
		             
		             //updating parent account balance 
		             String parentaccount1 = chartaccount.getParentaccount();

	            	 while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount2 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	            	     
	            	     if (parentchartaccount2 != null) {
	            	         
	            	         parentchartaccount2.setTotalDebitBalance(parentchartaccount2.getTotalDebitBalance().add(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
	            	         parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().subtract(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount2);
	            	         parentaccount1 = parentchartaccount2.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	 
	            	 CapitalTransRep.save(trantab1);
	            	 
	            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 CapitalTrans trantab2= new CapitalTrans();
	            	 trantab2.setId(id2.toString());
	            	 trantab2.setJournalDate(currentdate);
	            	 
	            	 trantab2.setJournalTranId("TRAN000"+journalid1);
	            	 trantab2.setPartTranId(2);
		             trantab2.setAccountNumber(account1.getAccountNumber());
		             trantab2.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
		             trantab2.setAccountName(account1.getAccountName());
	            	 trantab2.setDescription(list.get(0).getWoId());
	            	 trantab2.setDebits(BigDecimal.ZERO);
	            	 trantab2.setCredits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 trantab2.setTran_particulars("Vendor Advance Account-Adjusted");
	            	 trantab2.setVendor_id(vendor.getVendorId());
	            	 trantab2.setVendor_name(vendor.getVendorName());
	            	 trantab2.setTotalDebits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 trantab2.setTotalCredits(BigDecimal.valueOf(lists.get(0).getPaidAmount()));
	            	 CapitalTransRep.save(trantab2);	        
	            	 
	            	 
	            	 account1.setTotalCreditBalance(account1.getTotalCreditBalance().add(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
	            	 account1.setAccountBalance(account1.getAccountBalance().subtract(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
		             Erp_ChartOfAccountsRep.save(account1);
		             
		             
		            
	            	 String parentaccount3 = account1.getParentaccount();
	            	 
	            	 System.out.println("parentaccount="+parentaccount3);

	            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	            	     
	            	     if (parentchartaccount4 != null) {
	            	         
	            	         parentchartaccount4.setTotalCreditBalance(parentchartaccount4.getTotalCreditBalance().add(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
	            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(BigDecimal.valueOf(lists.get(0).getPaidAmount())));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	            	         parentaccount3 = parentchartaccount4.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

           		 
           	 }

           	 //paid amount 
        	 if(list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO)>0) {
        		 
        		//getaccount
        		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
        		 //tranid
        		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();
        		 
            	 CapitalTrans trantab1= new CapitalTrans();
            	 //journalid
            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 //first tran to getting back the advance to vendor account  
            	 trantab1.setId(id1.toString());
            	 trantab1.setJournalDate(currentdate);
            	 trantab1.setJournalTranId("TRAN000"+journalid1);
            	 trantab1.setPartTranId(1);
            	 trantab1.setAccountNumber(account1.getAccountNumber());
            	 trantab1.setAccountName(account1.getAccountName());
            	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
            	 trantab1.setDescription(list.get(0).getWoId());
            	 trantab1.setDebits(BigDecimal.ZERO);
            	 trantab1.setCredits(list.get(0).getPaidAmount());
            	 trantab1.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab1.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab1.setVendor_id(vendor.getVendorId());
            	 trantab1.setVendor_name(vendor.getVendorName());
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab1.setTran_particulars("Cash on Hand");
	             }
	             else {
	            	 trantab1.setTran_particulars("IDBI Bank");
	             }
            	 
            	 CapitalTransRep.save(trantab1);
            	 account1.setTotalCreditBalance(account1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	 account1.setAccountBalance(account1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
	             Erp_ChartOfAccountsRep.save(account1);
	             
            	 String parentaccount3 = account1.getParentaccount();
            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
            	     
            	     if (parentchartaccount4 != null) {
            	         
            	         parentchartaccount4.setTotalCreditBalance(parentchartaccount4.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(list.get(0).getPaidAmount()));
            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
            	         parentaccount3 = parentchartaccount4.getParentaccount();
            	     } else {
            	         break; 
            	     }
            	 }
            	 //part_tran=2
            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 CapitalTrans trantab2= new CapitalTrans();
            	 //first tran to getting back the advance to vendor account  
            	 trantab2.setId(id2.toString());
            	 trantab2.setJournalDate(currentdate);
            	 trantab2.setJournalTranId("TRAN000"+journalid1);
            	 trantab2.setPartTranId(2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
            	 trantab2.setDescription(list.get(0).getWoId());
            	 trantab2.setCredits(BigDecimal.ZERO);
            	 trantab2.setDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab2.setVendor_id(vendor.getVendorId());
            	 trantab2.setVendor_name(vendor.getVendorName());
            	 trantab2.setTran_particulars(account1.getAccountName());
            	 CapitalTransRep.save(trantab2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	

	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	             }
	            
            	 

            	
            	 
        		 
        		 
        		 
        		 
        		 
        		 
        		 
        		 
        	 }
            
             
           
	            
	            
	            for ( SALES_ORDER_ENTITY_NEW up1 : lists) {
	            	up1.setApprovedFlg('S');
	            	SALES_ORDER_ENTITY_NEW_REP.save(up1);
	            	
	            }
	            
	            
	            msg = ResponseEntity.ok("Sale invoice Updated Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	//direct sale in
	public ResponseEntity<String> directsaleinvoice_edit1(List<SALES_invoice_TABLE> list, String formmode ) {
	    ResponseEntity<String> msg = null;
	    String OWid="";
	    String vendorid="";
        BigDecimal advance=BigDecimal.ZERO;
        BigDecimal Balance=BigDecimal.ZERO;

	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	BigInteger PO_idBigInt = (BigInteger) session
		                .createNativeQuery("SELECT NEXT VALUE FOR wo_idnew AS WO_id")
		                .getSingleResult();
		            BigDecimal PO_id = new BigDecimal(PO_idBigInt); // Convert BigInteger to BigDecimal

		            OWid= "WO0000" + PO_id.toString();
		               
	        	
	            for (SALES_invoice_TABLE param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR sale_invoice AS id")
	            		    .getSingleResult()).longValue();
	            	Date currentdate = new Date();
	            	param.setInvoice_conform_date(currentdate);
	            	
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	            		 param.setWoId(OWid); 
	                    param.setApprovedFlg('S');
	                    
	                    if(param.equals("Yes")) {
	                    	param.setDeliverystatus("P");
	                    	param.setDeliverycharge(BigDecimal.ZERO);
	                    }
	                    if (param.getBalanceAmount() != null && !param.getBalanceAmount().toString().isEmpty()) {
	                        param.setExtra_amount(param.getBalanceAmount());
	                    } else {
	                        System.out.println("Balance Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setExtra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    if (param.getPaidAmount() != null && !param.getPaidAmount().toString().isEmpty()) {
	                        param.setLast_extra_amount(param.getPaidAmount());
	                    } else {
	                        System.out.println("Paid Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setLast_extra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    
	                    vendorid=param.getVendorId();
	                    String batch=param.getBatch();
	                    
	                    
	                    FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(batch);
	                    BigDecimal qty = new BigDecimal(param.getQty());
	                    BigDecimal fgqty=fg.getUsingQuantity().subtract(qty);
	                    fg.setUsingQuantity(fgqty);
	                    FINISHED_GOODS_Rep.save(fg);
	                    param.setUsingqty(qty);
	                    
	                    SALES_invoice_TABLERep.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	                
	      
	                
	                
	                
	            }
	            list=SALES_invoice_TABLERep.getparticular(OWid);
	            
	            VendorCreation up2 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	           
	            
	            BigDecimal vendor_dr= up2.getDrAmt();
	            BigDecimal vendor_bal= up2.getBalanceamount();
	            

	            if(list.get(0).getPaidAmount() !=  null || list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO) == 0) {
	            	BigDecimal new_dr=vendor_dr.add(list.get(0).getPaidAmount());
	            	up2.setDrAmt(new_dr);
	            	//BigDecimal new_ad=vendor_bal.subtract(list.get(0).getPaidAmount());
	            	BigDecimal new_ad=vendor_bal.add(list.get(0).getBalanceAmount());
	            	up2.setBalanceamount(new_ad);
	            	
		        	   VendorCreationRep.save(up2);
	            }
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getPaidAmount();

	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setWo_id(list.get(0).getWoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(paidAmount);

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }

	          //adding entry stock to chart of account
		         // Generate a new unique ID for the Transaction
	           
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         
	             String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();

	             for (SALES_invoice_TABLE param :list ) {
	            	 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemcode());
	            	 CapitalTrans trantab= new CapitalTrans();
	            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 trantab.setId(id.toString());
	            	 trantab.setJournalDate(currentdate);
	            	 
	            	 trantab.setJournalTranId("TRAN000"+sid);
	            	 trantab.setPartTranId(count);
	            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
	            	 trantab.setAccountNumber(account.getAccountNumber());
	            	 trantab.setAccountName(account.getAccountName());
	            	 trantab.setDescription(param.getWoId());
	            	 trantab.setDebits(BigDecimal.ZERO);
	            	 trantab.setCredits(param.getAmountPerItem());
	            	 trantab.setTran_particulars(vendor.getVendorName());
	            	 trantab.setVendor_id(vendor.getVendorId());
	            	 trantab.setVendor_name(vendor.getVendorName());
	            	 count++;
	            	 CapitalTransRep.save(trantab);
	            	 //updateing amount
	            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	 account.setAccountBalance(account.getAccountBalance().subtract(param.getAmountPerItem()));
	            	 //Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(account.getParentaccount());
		             //parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(param.getAmountPerItem()));
		             //parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(param.getAmountPerItem()));
		             Erp_ChartOfAccountsRep.save(account);
		             //Erp_ChartOfAccountsRep.save(parentchartaccount);
		             
		             String parentaccount = account.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(param.getAmountPerItem()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

	            	 
	            	 if(param.getPriceHeader().equals("withtax")) {
	            		 if(param.getTaxPercentage().startsWith("I")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	 	 							.getSingleResult();
	            			 Erp_ChartOfAccounts igstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00069");
	            			 CapitalTrans trantab1= new CapitalTrans();
	            			 trantab1.setId(id1.toString());
	    	            	 trantab1.setJournalDate(currentdate);
	            			 trantab1.setJournalTranId("TRAN000"+sid);
	            			 trantab1.setPartTranId(count);
	            			 trantab1.setFullAccount(igstaccount.getAccountNumber()+"-"+igstaccount.getAccountName());
	            			 trantab1.setAccountNumber(igstaccount.getAccountNumber());
	            			 trantab1.setAccountName(igstaccount.getAccountName());
	            			 trantab1.setDescription(param.getWoId());
	            			 trantab1.setCredits(param.getTaxAmount());
	            			 trantab1.setDebits(BigDecimal.ZERO);
	            			 trantab1.setTran_particulars(vendor.getVendorName());
	            			 trantab1.setVendor_id(vendor.getVendorId());
	            			 trantab1.setVendor_name(vendor.getVendorName());
	            			 CapitalTransRep.save(trantab1);
	            			 
	            			 
	            			 igstaccount.setTotalCreditBalance(igstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
	            			 igstaccount.setAccountBalance(igstaccount.getAccountBalance().add(param.getTaxAmount()));
	    	            	 
	            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(igstaccount.getParentaccount());
	    	            	 
	    		             Erp_ChartOfAccountsRep.save(igstaccount);
	    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    		             
	    		             String parentaccount1 = igstaccount.getParentaccount();
	    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	    	            	     
	    	            	     if (parentchartaccount1 != null) {
	    	            	    	 
	    	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
	    	    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
	    	            	        
	    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
	    	            	     } else {
	    	            	         break; 
	    	            	     }
	    	            	 }

	            			 count++;
	            			 	            			 	            			 
	            		 }else if(param.getTaxPercentage().startsWith("C")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Cgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00067");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Cgstaccount.getAccountNumber()+"-"+Cgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Cgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Cgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Cgstaccount.setTotalCreditBalance(Cgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Cgstaccount.setAccountBalance(Cgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			
		    		             Erp_ChartOfAccountsRep.save(Cgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             
		    		             String parentaccount1 = Cgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		    		             
		            			 count++;
	            		 }else if(param.getTaxPercentage().startsWith("S")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Sgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00068");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Sgstaccount.getAccountNumber()+"-"+Sgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Sgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Sgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Sgstaccount.setTotalCreditBalance(Sgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Sgstaccount.setAccountBalance(Sgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Sgstaccount.getParentaccount());
		    	            	 //parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
		    		             //parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    		             Erp_ChartOfAccountsRep.save(Sgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             String parentaccount1 = Sgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		            			 count++;
	            		 }
	            		 
	            		 
	            		 
	            	 }
	             }
	             BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
	             CapitalTrans trantab= new CapitalTrans();
	             
           	 trantab.setId(id.toString());
           	 trantab.setJournalDate(currentdate);
           	 
           	 trantab.setJournalTranId("TRAN000"+sid);
           	 trantab.setPartTranId(count);
           	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
           	 trantab.setAccountNumber(account.getAccountNumber());
           	 trantab.setAccountName(account.getAccountName());
           	 trantab.setDescription(list.get(0).getWoId());
           	 trantab.setCredits(BigDecimal.ZERO);
           	 trantab.setDebits(list.get(0).getTotalAmount());
           	 trantab.setTran_particulars("Sale");
           	 trantab.setVendor_id(vendor.getVendorId());
           	 trantab.setVendor_name(vendor.getVendorName());
           	 count++;
           	 CapitalTransRep.save(trantab);
           	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	 account.setAccountBalance(account.getAccountBalance().add(list.get(0).getTotalAmount()));
           	 Erp_ChartOfAccountsRep.save(account);
           	 String parentaccount = account.getParentaccount();

           	 while (parentaccount != null && !parentaccount.isEmpty()) {
           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
           	     
           	     if (parentchartaccount1 != null) {
           	         
           	         parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getTotalAmount()));
           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
           	         parentaccount = parentchartaccount1.getParentaccount();
           	     } else {
           	         break; 
           	     }
           	 }

	            
	            
	            
	           

           	 //paid amount 
        	 if(list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO)>0) {
        		 
        		//getaccount
        		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
        		 //tranid
        		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();
        		 
            	 CapitalTrans trantab1= new CapitalTrans();
            	 //journalid
            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 //first tran to getting back the advance to vendor account  
            	 trantab1.setId(id1.toString());
            	 trantab1.setJournalDate(currentdate);
            	 trantab1.setJournalTranId("TRAN000"+journalid1);
            	 trantab1.setPartTranId(1);
            	 trantab1.setAccountNumber(account1.getAccountNumber());
            	 trantab1.setAccountName(account1.getAccountName());
            	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
            	 trantab1.setDescription(list.get(0).getWoId());
            	 trantab1.setDebits(BigDecimal.ZERO);
            	 trantab1.setCredits(list.get(0).getPaidAmount());
            	 trantab1.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab1.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab1.setVendor_id(vendor.getVendorId());
            	 trantab1.setVendor_name(vendor.getVendorName());
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab1.setTran_particulars("Cash on Hand");
	             }
	             else {
	            	 trantab1.setTran_particulars("IDBI Bank");
	             }
            	 
            	 CapitalTransRep.save(trantab1);
            	 account1.setTotalCreditBalance(account1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	 account1.setAccountBalance(account1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
	             Erp_ChartOfAccountsRep.save(account1);
	             
            	 String parentaccount3 = account1.getParentaccount();
            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
            	     
            	     if (parentchartaccount4 != null) {
            	         
            	         parentchartaccount4.setTotalCreditBalance(parentchartaccount4.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(list.get(0).getPaidAmount()));
            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
            	         parentaccount3 = parentchartaccount4.getParentaccount();
            	     } else {
            	         break; 
            	     }
            	 }
            	 //part_tran=2
            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 CapitalTrans trantab2= new CapitalTrans();
            	 //first tran to getting back the advance to vendor account  
            	 trantab2.setId(id2.toString());
            	 trantab2.setJournalDate(currentdate);
            	 trantab2.setJournalTranId("TRAN000"+journalid1);
            	 trantab2.setPartTranId(2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
            	 trantab2.setDescription(list.get(0).getWoId());
            	 trantab2.setCredits(BigDecimal.ZERO);
            	 trantab2.setDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab2.setVendor_id(vendor.getVendorId());
            	 trantab2.setVendor_name(vendor.getVendorName());
            	 trantab2.setTran_particulars(account1.getAccountName());
            	 CapitalTransRep.save(trantab2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	

	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	             }
	            
        		 
        	 }
            
             
	            
	            
	            msg = ResponseEntity.ok("Sale invoice Updated Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	//Sale out
		public ResponseEntity<String> saleOut(List<SalesOut> list, String formmode ) {
			 ResponseEntity<String> msg = null;
			    String OWid="";
			    String vendorid="";
		        BigDecimal advance=BigDecimal.ZERO;
		        BigDecimal Balance=BigDecimal.ZERO;

		    try {
		        Session session = sessionFactory.getCurrentSession();
		        if ("add".equals(formmode)) {
		        	BigInteger sale_idBigInt = (BigInteger) session
			                .createNativeQuery("SELECT NEXT VALUE FOR SaleOutId AS WO_id")
			                .getSingleResult();
			            BigDecimal sale_id = new BigDecimal(sale_idBigInt); // Convert BigInteger to BigDecimal

			            OWid= "WO0000" + sale_id.toString();
			               
		        	
		            for (SalesOut param : list) {
		            	Long idLong = ((Number) session
		            		    .createNativeQuery("SELECT NEXT VALUE FOR SaleOutId2 AS id")
		            		    .getSingleResult()).longValue();
		            	Date currentdate = new Date();
		            	param.setInvoice_conform_date(currentdate);
		            	
		            		BigDecimal id = BigDecimal.valueOf(idLong);
		            		param.setId(id.toString());
		            		 param.setWoId(OWid); 
		                    
		                                      
		                    vendorid=param.getVendorId();
		                    String batch=param.getBatch();
		                    
		                    
		                    FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(batch);
		                    BigDecimal qty = new BigDecimal(param.getQty());
		                    BigDecimal fgqty=fg.getUsingQuantity().subtract(qty);
		                    fg.setUsingQuantity(fgqty);
		                    FINISHED_GOODS_Rep.save(fg);
		                    param.setUsingqty(qty);
		                    param.setReturning_flg("N");
		                    salesOutRep.save(param);
		                System.out.println("Saved PO entity: " + param);

			            msg = ResponseEntity.ok("Sale out Updated Successfully.");
		            }
		            }
		        }            
		                
		                   
		                
		           catch (Exception e) {
		    	        e.printStackTrace();
		    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		    	    }
			
		        
			return msg;
			
		}
	
	
	//direct sale invoice
	
	public ResponseEntity<String> directsaleinvoice_edit(List<SALES_invoice_TABLE> list, String formmode ) {
	    ResponseEntity<String> msg = null;
	    String OWid="";
	    String vendorid="";
        BigDecimal advance=BigDecimal.ZERO;
        BigDecimal Balance=BigDecimal.ZERO;

	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	BigInteger PO_idBigInt = (BigInteger) session
		                .createNativeQuery("SELECT NEXT VALUE FOR wo_idnew AS WO_id")
		                .getSingleResult();
		            BigDecimal PO_id = new BigDecimal(PO_idBigInt); // Convert BigInteger to BigDecimal

		            OWid= "WO0000" + PO_id.toString();
		               
	        	
	            for (SALES_invoice_TABLE param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR sale_invoice AS id")
	            		    .getSingleResult()).longValue();
	            	Date currentdate = new Date();
	            	param.setInvoice_conform_date(currentdate);
	            	
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	            		 param.setWoId(OWid); 
	                    param.setApprovedFlg('S');
	                    
	                    if(param.equals("Yes")) {
	                    	param.setDeliverystatus("P");
	                    	param.setDeliverycharge(BigDecimal.ZERO);
	                    }
	                    if (param.getBalanceAmount() != null && !param.getBalanceAmount().toString().isEmpty()) {
	                        param.setExtra_amount(param.getBalanceAmount());
	                    } else {
	                        System.out.println("Balance Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setExtra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    if (param.getPaidAmount() != null && !param.getPaidAmount().toString().isEmpty()) {
	                        param.setLast_extra_amount(param.getPaidAmount());
	                    } else {
	                        System.out.println("Paid Amount is null or empty");
	                        // Optionally, set a default value or handle the case
	                        param.setLast_extra_amount(BigDecimal.valueOf(0)); // Example: Setting a default value of 0
	                    }

	                    
	                    vendorid=param.getVendorId();
	                    String batch=param.getBatch();
	                    
	                    
	                    FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(batch);
	                    BigDecimal qty = new BigDecimal(param.getQty());
	                    BigDecimal fgqty=fg.getUsingQuantity().subtract(qty);
	                    fg.setUsingQuantity(fgqty);
	                    FINISHED_GOODS_Rep.save(fg);
	                    param.setUsingqty(qty);
	                    
	                    SALES_invoice_TABLERep.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	                
	      
	                
	                
	                
	            }
	            list=SALES_invoice_TABLERep.getparticular(OWid);
	            
	            VendorCreation up2 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	           
	            
	            BigDecimal vendor_dr= up2.getDrAmt();
	            BigDecimal vendor_bal= up2.getBalanceamount();
	            

	            if(list.get(0).getPaidAmount() !=  null || list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO) == 0) {
	            	BigDecimal new_dr=vendor_dr.add(list.get(0).getPaidAmount());
	            	up2.setDrAmt(new_dr);
	            	//BigDecimal new_ad=vendor_bal.subtract(list.get(0).getPaidAmount());
	            	BigDecimal new_ad=vendor_bal.add(list.get(0).getBalanceAmount());
	            	up2.setBalanceamount(new_ad);
	            	
		        	   VendorCreationRep.save(up2);
	            }
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getPaidAmount();

	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setWo_id(list.get(0).getWoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(paidAmount);

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }

	          //adding entry stock to chart of account
		         // Generate a new unique ID for the Transaction
	           
		            BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();

		         if (journalid == null) {
		             throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		         }
		         
	             String sid=String.valueOf(journalid);
	             int count=1;
	             Date currentdate = new Date();

	             for (SALES_invoice_TABLE param :list ) {
	            	 Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(param.getItemcode());
	            	 CapitalTrans trantab= new CapitalTrans();
	            	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
								.getSingleResult();
	            	 trantab.setId(id.toString());
	            	 trantab.setJournalDate(currentdate);
	            	 
	            	 trantab.setJournalTranId("TRAN000"+sid);
	            	 trantab.setPartTranId(count);
	            	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
	            	 trantab.setAccountNumber(account.getAccountNumber());
	            	 trantab.setAccountName(account.getAccountName());
	            	 trantab.setDescription(param.getWoId());
	            	 trantab.setDebits(BigDecimal.ZERO);
	            	 trantab.setCredits(param.getAmountPerItem());
	            	 trantab.setTran_particulars(vendor.getVendorName());
	            	 trantab.setVendor_id(vendor.getVendorId());
	            	 trantab.setVendor_name(vendor.getVendorName());
	            	 count++;
	            	 CapitalTransRep.save(trantab);
	            	 //updateing amount
	            	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	 account.setAccountBalance(account.getAccountBalance().subtract(param.getAmountPerItem()));
	            	 //Erp_ChartOfAccounts parentchartaccount=  Erp_ChartOfAccountsRep.findsccount(account.getParentaccount());
		             //parentchartaccount.setTotalDebitBalance(parentchartaccount.getTotalDebitBalance().add(param.getAmountPerItem()));
		             //parentchartaccount.setAccountBalance(parentchartaccount.getAccountBalance().add(param.getAmountPerItem()));
		             Erp_ChartOfAccountsRep.save(account);
		             //Erp_ChartOfAccountsRep.save(parentchartaccount);
		             
		             String parentaccount = account.getParentaccount();

	            	 while (parentaccount != null && !parentaccount.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
	            	     
	            	     if (parentchartaccount1 != null) {
	            	         
	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getAmountPerItem()));
	            	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(param.getAmountPerItem()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	            	         parentaccount = parentchartaccount1.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }

	            	 
	            	 if(param.getPriceHeader().equals("withtax")) {
	            		 if(param.getTaxPercentage().startsWith("I")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	 	 							.getSingleResult();
	            			 Erp_ChartOfAccounts igstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00069");
	            			 CapitalTrans trantab1= new CapitalTrans();
	            			 trantab1.setId(id1.toString());
	    	            	 trantab1.setJournalDate(currentdate);
	            			 trantab1.setJournalTranId("TRAN000"+sid);
	            			 trantab1.setPartTranId(count);
	            			 trantab1.setFullAccount(igstaccount.getAccountNumber()+"-"+igstaccount.getAccountName());
	            			 trantab1.setAccountNumber(igstaccount.getAccountNumber());
	            			 trantab1.setAccountName(igstaccount.getAccountName());
	            			 trantab1.setDescription(param.getWoId());
	            			 trantab1.setCredits(param.getTaxAmount());
	            			 trantab1.setDebits(BigDecimal.ZERO);
	            			 trantab1.setTran_particulars(vendor.getVendorName());
	            			 trantab1.setVendor_id(vendor.getVendorId());
	            			 trantab1.setVendor_name(vendor.getVendorName());
	            			 CapitalTransRep.save(trantab1);
	            			 
	            			 
	            			 igstaccount.setTotalCreditBalance(igstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
	            			 igstaccount.setAccountBalance(igstaccount.getAccountBalance().add(param.getTaxAmount()));
	    	            	 
	            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(igstaccount.getParentaccount());
	    	            	 
	    		             Erp_ChartOfAccountsRep.save(igstaccount);
	    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    		             
	    		             String parentaccount1 = igstaccount.getParentaccount();
	    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
	    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
	    	            	     
	    	            	     if (parentchartaccount1 != null) {
	    	            	    	 
	    	            	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
	    	    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
	    	            	        
	    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
	    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
	    	            	     } else {
	    	            	         break; 
	    	            	     }
	    	            	 }

	            			 count++;
	            			 	            			 	            			 
	            		 }else if(param.getTaxPercentage().startsWith("C")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Cgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00067");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Cgstaccount.getAccountNumber()+"-"+Cgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Cgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Cgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Cgstaccount.setTotalCreditBalance(Cgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Cgstaccount.setAccountBalance(Cgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			
		    		             Erp_ChartOfAccountsRep.save(Cgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             
		    		             String parentaccount1 = Cgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		    		             
		            			 count++;
	            		 }else if(param.getTaxPercentage().startsWith("S")) {
	            			 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
		 	 							.getSingleResult();
		            			 Erp_ChartOfAccounts Sgstaccount= Erp_ChartOfAccountsRep.findsccount("ACC00068");
		            			 CapitalTrans trantab1= new CapitalTrans();
		            			 trantab1.setId(id1.toString());
		    	            	 trantab1.setJournalDate(currentdate);
		            			 trantab1.setJournalTranId("TRAN000"+sid);
		            			 trantab1.setPartTranId(count);
		            			 trantab1.setFullAccount(Sgstaccount.getAccountNumber()+"-"+Sgstaccount.getAccountName());
		            			 trantab1.setAccountNumber(Sgstaccount.getAccountNumber());
		            			 trantab1.setAccountName(Sgstaccount.getAccountName());
		            			 trantab1.setDescription(param.getWoId());
		            			 trantab1.setDebits(BigDecimal.ZERO);
		            			 trantab1.setCredits(param.getTaxAmount());
		            			 trantab1.setTran_particulars(vendor.getVendorName());
		            			 trantab1.setVendor_id(vendor.getVendorId());
		            			 trantab1.setVendor_name(vendor.getVendorName());
		            			 CapitalTransRep.save(trantab1);
		            			 
		            			 Sgstaccount.setTotalCreditBalance(Sgstaccount.getTotalCreditBalance().add(param.getTaxAmount()));
		            			 Sgstaccount.setAccountBalance(Sgstaccount.getAccountBalance().add(param.getTaxAmount()));
		            			 //Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Sgstaccount.getParentaccount());
		    	            	 //parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(param.getTaxAmount()));
		    		             //parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    		             Erp_ChartOfAccountsRep.save(Sgstaccount);
		    		             //Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    		             String parentaccount1 = Sgstaccount.getParentaccount();
		    		             while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		    	            	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		    	            	     
		    	            	     if (parentchartaccount1 != null) {
		    	            	    	 
		    	            	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
				    	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(param.getTaxAmount()));
				    		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(param.getTaxAmount()));
		    	            	        
		    	            	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		    	            	         parentaccount1 = parentchartaccount1.getParentaccount();
		    	            	     } else {
		    	            	         break; 
		    	            	     }
		    	            	 }
		    		             
		            			 count++;
	            		 }
	            		 
	            		 
	            		 
	            	 }
	             }
	             BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
	             Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
	             CapitalTrans trantab= new CapitalTrans();
	             
           	 trantab.setId(id.toString());
           	 trantab.setJournalDate(currentdate);
           	 
           	 trantab.setJournalTranId("TRAN000"+sid);
           	 trantab.setPartTranId(count);
           	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
           	 trantab.setAccountNumber(account.getAccountNumber());
           	 trantab.setAccountName(account.getAccountName());
           	 trantab.setDescription(list.get(0).getWoId());
           	 trantab.setCredits(BigDecimal.ZERO);
           	 trantab.setDebits(list.get(0).getTotalAmount());
           	 trantab.setTran_particulars("Sale");
           	 trantab.setVendor_id(vendor.getVendorId());
           	 trantab.setVendor_name(vendor.getVendorName());
           	 count++;
           	 CapitalTransRep.save(trantab);
           	 account.setTotalDebitBalance(account.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	 account.setAccountBalance(account.getAccountBalance().add(list.get(0).getTotalAmount()));
           	 Erp_ChartOfAccountsRep.save(account);
           	 String parentaccount = account.getParentaccount();

           	 while (parentaccount != null && !parentaccount.isEmpty()) {
           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
           	     
           	     if (parentchartaccount1 != null) {
           	         
           	         parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(list.get(0).getTotalAmount()));
           	         parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(list.get(0).getTotalAmount()));
           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
           	         parentaccount = parentchartaccount1.getParentaccount();
           	     } else {
           	         break; 
           	     }
           	 }

	            
	            
	            
	           

           	 //paid amount 
        	 if(list.get(0).getPaidAmount().compareTo(BigDecimal.ZERO)>0) {
        		 
        		//getaccount
        		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(vendor.getVendorId());
        		 //tranid
        		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
			 				.getSingleResult();
        		 
            	 CapitalTrans trantab1= new CapitalTrans();
            	 //journalid
            	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 //first tran to getting back the advance to vendor account  
            	 trantab1.setId(id1.toString());
            	 trantab1.setJournalDate(currentdate);
            	 trantab1.setJournalTranId("TRAN000"+journalid1);
            	 trantab1.setPartTranId(1);
            	 trantab1.setAccountNumber(account1.getAccountNumber());
            	 trantab1.setAccountName(account1.getAccountName());
            	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
            	 trantab1.setDescription(list.get(0).getWoId());
            	 trantab1.setDebits(BigDecimal.ZERO);
            	 trantab1.setCredits(list.get(0).getPaidAmount());
            	 trantab1.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab1.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab1.setVendor_id(vendor.getVendorId());
            	 trantab1.setVendor_name(vendor.getVendorName());
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab1.setTran_particulars("Cash on Hand");
	             }
	             else {
	            	 trantab1.setTran_particulars("IDBI Bank");
	             }
            	 
            	 CapitalTransRep.save(trantab1);
            	 account1.setTotalCreditBalance(account1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	 account1.setAccountBalance(account1.getAccountBalance().subtract(list.get(0).getPaidAmount()));
	             Erp_ChartOfAccountsRep.save(account1);
	             
            	 String parentaccount3 = account1.getParentaccount();
            	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
            	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
            	     
            	     if (parentchartaccount4 != null) {
            	         
            	         parentchartaccount4.setTotalCreditBalance(parentchartaccount4.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(list.get(0).getPaidAmount()));
            	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
            	         parentaccount3 = parentchartaccount4.getParentaccount();
            	     } else {
            	         break; 
            	     }
            	 }
            	 //part_tran=2
            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 CapitalTrans trantab2= new CapitalTrans();
            	 //first tran to getting back the advance to vendor account  
            	 trantab2.setId(id2.toString());
            	 trantab2.setJournalDate(currentdate);
            	 trantab2.setJournalTranId("TRAN000"+journalid1);
            	 trantab2.setPartTranId(2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
            	 trantab2.setDescription(list.get(0).getWoId());
            	 trantab2.setCredits(BigDecimal.ZERO);
            	 trantab2.setDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalDebits(list.get(0).getPaidAmount());
            	 trantab2.setTotalCredits(list.get(0).getPaidAmount());
            	 trantab2.setVendor_id(vendor.getVendorId());
            	 trantab2.setVendor_name(vendor.getVendorName());
            	 trantab2.setTran_particulars(account1.getAccountName());
            	 CapitalTransRep.save(trantab2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	

	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(list.get(0).getPaidAmount()));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(list.get(0).getPaidAmount()));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	             }
	            
        		 
        	 }
            
             
	            
	            
	            msg = ResponseEntity.ok("Sale invoice Updated Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	
	
	
	
	


	public String Add_payment_out(String vandor_id,String vendorname,Pament_out Pament_out) {
	    String msg = null;
	    Session session = sessionFactory.getCurrentSession();
		
	    try {
			Pament_out.setVendorId(vandor_id);
			Pament_out.setVendorName(vendorname);
			Payment_Out_Reps.save(Pament_out);
			
			VendorCreation up=VendorCreationRep.getvendorlistid(vandor_id);
			BigDecimal np= new BigDecimal(Pament_out.getAdvance());
			up.setAdvanceamount(np);
			VendorCreationRep.save(up);
			
			 // Check if paidAmount is not null and greater than zero
	        if (Pament_out.getReceivedamount() != null && Pament_out.getReceivedamount().compareTo(BigDecimal.ZERO) != 0) {
	            
	                // Create a new Transaction_table object
	                Transaction_table newTransaction = new Transaction_table();

	                // Generate a new unique ID for the Transaction
	                BigInteger generatedId = (BigInteger) session
	                    .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                    .getSingleResult();

	                if (generatedId == null) {
	                    throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                }

	                String transactionId = "TRAN000" + generatedId;
	                newTransaction.setTran_id(transactionId);
	                System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                // Set other fields
	                //newTransaction.setPo_id(list.get(0).getPoId());
	                newTransaction.setVendor_id(up.getVendorId());
	                newTransaction.setClassification(up.getClassify());
	                newTransaction.setVendor_name(up.getVendorName());
	                newTransaction.setAcct_crncy(up.getCrncy());
	                newTransaction.setAcct_num(up.getAcctNum());
	                Date currentdate = new Date();
	                newTransaction.setAcct_cls_date(currentdate);
	                // Set advance amount in Debit
	                newTransaction.setDr_amt(Pament_out.getReceivedamount());
	                newTransaction.setTran_type(Pament_out.getPaymentType());
                    newTransaction.setType("Payment Out");
       	         	BTMAdminOrganizationMaster up1 = btmAdminOrganizationMasterRep.getOrganization();
	       	         if(Pament_out.getPaymentType().equals("cash")) {
	       	        	 up1.setCurrent_cash_balance(up1.getCurrent_cash_balance().subtract(Pament_out.getReceivedamount()));
	       	         }
	       	         else {
	       	        	up1.setCurrent_account_balance(up1.getCurrent_account_balance().subtract(Pament_out.getReceivedamount()));
	       	         }
       	         
	       	         btmAdminOrganizationMasterRep.save(up1);


	                
	                // Set balance amount in COA if valid
	                if (np!= null && np.compareTo(BigDecimal.ZERO) != 0) {
	                    newTransaction.setAcct_bal(np);
	                }

	                // Save the new transaction
	                Transaction_table_Rep.save(newTransaction);

	            
	        }
	            msg = "Paid Successfully.";
	            
	            
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return  msg = "Error: " + e.getMessage();
	    }
	    return msg;
	}

	public ResponseEntity<String> sale_return_submit(List<Sales_Return> list, String formmode) {
	    ResponseEntity<String> msg = null;
	    String OWid="";
	    String vendorid="";
        BigDecimal vendorbalance=BigDecimal.ZERO;
        BigDecimal Balance=BigDecimal.ZERO;
        List<SALES_invoice_TABLE> sale = SALES_invoice_TABLERep.getparticular(list.get(0).getWoId());
		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	BigInteger sr_id = (BigInteger) session
	       	             .createNativeQuery("SELECT NEXT VALUE FOR sale_return_id AS id")
	       	             .getSingleResult();
	            	String sid=String.valueOf(sr_id);
	            	
	        	
	            for (Sales_Return param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR sales_return_no AS id")
	            		    .getSingleResult()).longValue();
	            		Date currentdate = new Date();
	            		param.setReturn_date(currentdate);
	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setApprovedFlg('R');
	                    param.setWo_Return_id("SR000"+sid);
	                    
	                    for(SALES_invoice_TABLE sal:sale) {
	                    	if(sal.getItemcode().equals(param.getItemcode())) {
	                    		//Integer result =poitem.getUseingqty()-param.getQty();
	                    		BigDecimal result=sal.getUsingqty().subtract(param.getQty());
	                    		sal.setUsingqty(result);;
	                    		SALES_invoice_TABLERep.save(sal);
	                    		
	                    	}
	                    	
	                    	
	                    }
	                    Sales_ReturnRep.save(param);
	                System.out.println("Saved Sale entity: " + param);
	                vendorid=param.getVendorId();
	                vendorbalance=param.getVendorbalance();
	                Balance=param.getBalanceAmount();
	                
	            }
	            VendorCreation up=VendorCreationRep.getvendorlistid(vendorid);
	            BigDecimal total_amt = new BigDecimal(list.get(0).getPaidAmount());
	            if (vendorbalance != null && Balance!=null && vendorbalance.compareTo(Balance)<0) {
	                // If advance is not null, add up.getAdvanceamount() to it
	            	Balance = Balance.add(up.getAdvanceamount() != null ? up.getAdvanceamount() : BigDecimal.ZERO);
	                up.setAdvanceamount(Balance);
	                up.setBalanceamount(BigDecimal.ZERO);
	                VendorCreationRep.save(up);
	            } else if (Balance != null && Balance!=null && vendorbalance.compareTo(Balance)>0) {
	                // If Balance is not null, add up.getBalanceamount() to it
	                up.setBalanceamount(Balance);
	                VendorCreationRep.save(up);
	            }
	            
	            BigDecimal PaidAmount = new BigDecimal(list.get(0).getPaidAmount());
	            if (PaidAmount != null && PaidAmount.compareTo(BigDecimal.ZERO) > 0) {
            		BigDecimal new_cr=up.getDrAmt().add(PaidAmount);
            		up.setDrAmt(new_cr);
            	}
	            
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            //BigDecimal total_amt = list.get(0).getTotalAmount();
	            
	            // Check if paidAmount is not null and greater than zero
	            if (total_amt != null && total_amt.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setWo_id(list.get(0).getWoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    Date currentdate = new Date();
	                    newTransaction.setAcct_cls_date(currentdate);
	                    

	                    // Set cr amount in TSK credit
	                    newTransaction.setDr_amt(total_amt);

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);

	                
	            }


	            

	    		
	            
	            msg = ResponseEntity.ok("Sale Returned Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}


	public ResponseEntity<String> ledgedit(Chart_Acc_Entity ua, String acct, String userId) {
	    try {
	        // Retrieve the existing entity from the database
	        Chart_Acc_Entity up = chart_Acc_Reps.getaedit(acct);

	        if (up == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found for the given account.");
	        }

	        // Update relevant fields from `ua` to `up`
	        up.setClassification(ua.getClassification());
	        up.setAcct_type(ua.getAcct_type());
	        up.setGl_code(ua.getGl_code());
	        up.setGl_desc(ua.getGl_desc());
	        up.setGlsh_code(ua.getGlsh_code());
	        up.setGlsh_desc(ua.getGlsh_desc());
	        up.setSchm_type(ua.getSchm_type());
	        up.setSchm_code(ua.getSchm_code());
	        up.setAcct_name(ua.getAcct_name());
	        up.setAdd_det_flg(ua.getAdd_det_flg());
	        up.setAcct_partition(ua.getAcct_partition());
	        up.setAcct_crncy(ua.getAcct_crncy());
	        up.setRef_crncy(ua.getRef_crncy());
	        up.setRef_code(ua.getRef_code());
	        up.setRef_desc(ua.getRef_desc());
	        up.setRpt_code(ua.getRpt_code());
	        up.setAcct_status(ua.getAcct_status());
	        up.setCr_amt(ua.getCr_amt());
	        up.setDr_amt(ua.getDr_amt());
	        up.setAcct_bal(ua.getAcct_bal());
	        up.setRef_crncy_bal(ua.getRef_crncy_bal());
	        up.setOwn_type(ua.getOwn_type());
	        up.setOwn_remarks(ua.getOwn_remarks());
	        up.setVendor_type(ua.getVendor_type());
	        up.setVendor_name(ua.getVendor_name());
	        up.setPan_no(ua.getPan_no());
	        up.setGstin(ua.getGstin());
	        up.setPassport(ua.getPassport());
	        up.setBank_acc_name(ua.getBank_acc_name());
	        up.setBank_remarks(ua.getBank_remarks());
	        up.setCrncy_code(ua.getCrncy_code());
	        up.setBranch_id(ua.getBranch_id());
	        up.setAcct_open_date(ua.getAcct_open_date());
	        up.setNational_id(ua.getNational_id());
	        up.setMobile_no(ua.getMobile_no());
	        up.setVendor_id(ua.getVendor_id());

	        // Set modification details
	        up.setModify_user(userId);
	        up.setModify_time(new Date());

	        // Save the updated entity
	        chart_Acc_Reps.save(up);

	        return ResponseEntity.ok("Modified Successfully");

	    } catch (Exception e) {
	        // Log the exception for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An error occurred while saving: " + e.getMessage());
	    }
	}

	public String addGeneralLedger(GeneralLedgerEntity getGeneralLedger, String formmode , String GLSH_CODE  ,String userid ) {

		String msg = "";
		 BGLSBusinessTable_Entity audit = new BGLSBusinessTable_Entity();
		if (formmode.equals("add")) {

			GeneralLedgerEntity up = getGeneralLedger;

			up.setDelFlg("N");

			up.setModifyFlg("N");

			generalLedgerRep.save(up);

			msg = "Added Successfully";
			
			 //FOR AUIDT
	        /*Long auditID = BGLSBusinessTable_Rep.getAuditRefUUID();
	        Optional<UserProfile> up1 = userProfileRep.findById(userid);
			UserProfile user = up1.get();
	       
	     
			LocalDateTime currentDateTime = LocalDateTime.now();
			Date dateValue = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			audit.setAudit_date(new Date());
			audit.setEntry_time(dateValue);
			audit.setEntry_user(user.getUserid());
			 audit.setFunc_code("Branch Id");
			audit.setRemarks("Added Successfully");
			audit.setAudit_table("BGLS_GENERAL_LED");
			audit.setAudit_screen("GENERAL LEDGER - ADD");
			audit.setEvent_id(user.getUserid());
			audit.setEvent_name(user.getUsername());
			//audit.setModi_details("Login Successfully");
			UserProfile auth_user = userProfileRep.getRole(user.getUserid());
			String auth_user_val = auth_user.getAuth_user();
			Date auth_user_date = auth_user.getAuth_time();
			audit.setAuth_user(auth_user_val);
			audit.setAuth_time(auth_user_date);
			audit.setAudit_ref_no(auditID.toString());
			audit.setField_name("-");
			
			bGLSBusinessTable_Rep.save(audit)*/;

		}
		else if (formmode.equals("edit")) {
			
		
			GeneralLedgerEntity up = generalLedgerRep.getsinglevalue(GLSH_CODE);
			getGeneralLedger.setModifyFlg("Y");
			getGeneralLedger.setDelFlg("N");
			
				generalLedgerRep.save(getGeneralLedger);

			msg = "Edited Successfully";	
		}
		
		else if (formmode.equals("delete")) {

			GeneralLedgerEntity up =generalLedgerRep.getsinglevalue(GLSH_CODE);
			up.setDelFlg("Y");
			generalLedgerRep.save(up);
			msg = "Deleted Successfully";
		}
		
		return msg;
	}

	/*------bom add*/
	
	@Autowired 
	BOM_REPO pOM_REPO;
	
	public ResponseEntity<String> addNewbom(List<BOM_ENTITY> BOM_ENTITY, String formMode) {
		
		System.out.println("entry service for bom");

		ResponseEntity<String> msg;
		try {
			if ("add".equals(formMode)) {
				Session session = sessionFactory.getCurrentSession();

				try {
					
					 BigInteger BOMID = (BigInteger) session
				                .createNativeQuery("SELECT NEXT VALUE FOR Bom_id AS Bom_id")
				                .getSingleResult();
				            BigDecimal Bom_id = new BigDecimal(BOMID);
				            LocalDate currentDate = LocalDate.now();

				            // Define the desired formatter
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

				            // Format the date and parse it back to LocalDate
				            String formattedDateStr = currentDate.format(formatter);
				            LocalDate formattedDate = LocalDate.parse(formattedDateStr, formatter);

			                
			                System.out.println("the current date" +formattedDate);
			                for (BOM_ENTITY newProduct : BOM_ENTITY) {
						
						Long idLong = ((BigInteger) session
		            		    .createNativeQuery("SELECT NEXT VALUE FOR bom_newid AS id")
		            		    .getSingleResult()).longValue();
						BigDecimal idforunique = new BigDecimal(idLong);
						

						        newProduct.setCreateDate(formattedDate);
						        newProduct.setId(idLong);
						        newProduct.setBomId("BOM000"+Bom_id.toString());
						        newProduct.setDeleteFlag("N");
					            pOM_REPO.save(newProduct);
					}

					msg = ResponseEntity.ok(BOM_ENTITY.get(0).getBomId()+"-Product added successfully.");
				} catch (Exception e) {
					// Log the exception and return an internal server error
					msg = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("An error occurred: " + e.getMessage());
				}
			}
			
			else if("edit".equals(formMode)) {
				
				System.out.println("edit");
				
			     LocalDate currentDate = LocalDate.now();

		            // Define the desired formatter
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		            // Format the date and parse it back to LocalDate
		            String formattedDateStr = currentDate.format(formatter);
		            LocalDate formattedDate = LocalDate.parse(formattedDateStr, formatter);
				
				Session session = sessionFactory.getCurrentSession();
				
				for (BOM_ENTITY newProduct : BOM_ENTITY) {
				
					  if(newProduct.getId() == null) {
						 Long idLong = ((BigInteger) session
			            		    .createNativeQuery("SELECT NEXT VALUE FOR bom_newid AS id")
			            		    .getSingleResult()).longValue();
							BigDecimal idforunique = new BigDecimal(idLong);
							
							        newProduct.setCreateDate(formattedDate);
							        newProduct.setId(idLong);
							        
							        pOM_REPO.save(newProduct);
                                 }
					 
					 else {
						 
							newProduct.setCreateDate(formattedDate);
							 pOM_REPO.save(newProduct);
					 }
					}
				 msg = ResponseEntity.ok(BOM_ENTITY.get(0).getBomId()+"-Product Modified successfully.");
			}
			else {
				msg = ResponseEntity.badRequest().body("Invalid form mode.");
			}

			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
		}
	}
	@Autowired FINISHED_GOODS_Rep FINISHED_GOODS_Rep;
	public ResponseEntity<String> add_ConvertToFG(List<FINISHED_GOODS_ENTITY> list, String formmode,String process_id) {

	    ResponseEntity<String> msg = null;
	    String val="";
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if (formmode.equals("add")) {
	        	 // Generate a new unique ID for the purchase order
                /*BigInteger idBigInt = (BigInteger) session
                    .createNativeQuery("SELECT NEXT VALUE FOR HRMS.fg_batch AS id")
                    .getSingleResult();
                	BigDecimal id = new BigDecimal(idBigInt); // Convert BigInteger to BigDecimal*/
	        	
	        	
	            for (FINISHED_GOODS_ENTITY param : list) {
	            	
	            	List<TSK_PROCESS_ENTITY> up=TSK_PROCESS_REP.getprocessbyid(process_id);
		            for (TSK_PROCESS_ENTITY up1:up) {
		           
		            		up1.setUseingquantity(param.getRemainingQuantity());
		            		System.out.println("q"+param.getRemainingQuantity());
		            		TSK_PROCESS_REP.save(up1);
		            }
		            
		            PO_invoice_entity pm = PO_invoice_reps.get_twos(param.getBatch());
		            
		            BigDecimal usingQty = BigDecimal.valueOf(pm.getUseingqty());
		            System.out.println("useingqnt"+usingQty);
                    BigDecimal reqQty =  param.getReqQty();
                    System.out.println("reqqnt"+reqQty);
                    BigDecimal newvalue = usingQty.subtract(reqQty);
                    System.out.println("subvalue"+newvalue);
                    pm.setUseingqty(newvalue.intValueExact()); 
                    PO_invoice_reps.save(pm);
                    
	               	                		
	                	 BigInteger FG_idBigInt = (BigInteger) session
	         	                .createNativeQuery("SELECT NEXT VALUE FOR FG_Id AS FG_Id")
	         	                .getSingleResult();
	         	            BigDecimal FG_Id= new BigDecimal(FG_idBigInt);
	         	            param.setFgId("FG00"+FG_Id);
	         	           param.setUsingQuantity(param.getReqQty());
	         	          param.setDelFlg("N");
	         	         Date currentdate = new Date();
	         	        param.setCreateDate(currentdate);
	         	           FINISHED_GOODS_Rep.save(param);
	         	           val=val+","+param.getFgId();
	                
	            }
	            msg = ResponseEntity.ok("Product "+val+" added successfully.");
	        }
	    }catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
		}
	    return msg;
	}
	
	@Autowired TSK_PROCESS_REP TSK_PROCESS_REP;
	public String validation(String process_id,String units, BigDecimal returnquantity) {
		List<TSK_PROCESS_ENTITY> list=TSK_PROCESS_REP.getprocessbyid(process_id);
		for(TSK_PROCESS_ENTITY up:list ) {
			up.setReturnquantity(returnquantity);
			up.setReturnquantityunit(units);
			up.setUseingquantity(returnquantity);
			 
			 up.setProcessEndDate(new Date());
			 up.setStatus("Completed");
		}
		return process_id+"-Validation Completed";
	}
	
	
	public String tskvalidation(String process_id,String units, BigDecimal returnquantity
			,BigDecimal actualkasadu,BigDecimal differghee,BigDecimal differkasadu ) {
		Session session = sessionFactory.getCurrentSession();
		List<TSK_PROCESS_ENTITY> list=TSK_PROCESS_REP.getprocessbyid(process_id);
		for(TSK_PROCESS_ENTITY up:list ) {
			up.setReturnquantity(returnquantity);
			up.setReturnquantityunit(units);
			up.setActualkasadu(actualkasadu);
			up.setDifferghee(differghee);
			up.setDifferkasadu(differkasadu);
			up.setUseingquantity(returnquantity);
			 
			 up.setProcessEndDate(new Date());
			 up.setStatus("Completed");
		}
			
		
			BigInteger journalid =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
		 				.getSingleResult();
		
		     if (journalid == null) {
		         throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
		     }
		     String sid=String.valueOf(journalid);
		     Date currentdate = new Date();
		     
		     //parttran 1
				Erp_ChartOfAccounts account =Erp_ChartOfAccountsRep.findsccount("ACC000101");
		   	 CapitalTrans trantab= new CapitalTrans();
		   	 BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
		   	 trantab.setId(id.toString());
		   	 trantab.setJournalDate(currentdate);
		   	 trantab.setJournalTranId("TRAN000"+sid);
		   	 trantab.setPartTranId(1);
		   	 trantab.setFullAccount(account.getAccountNumber()+"-"+account.getAccountName());
		   	 trantab.setAccountNumber(account.getAccountNumber());
		   	 trantab.setAccountName(account.getAccountName());
		   	 trantab.setDescription(list.get(0).getProcessId());
		   	 trantab.setDebits(BigDecimal.ZERO);
		   	 trantab.setCredits(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal()));
		   	 trantab.setTran_particulars("Semi Finished Goods");
		   	 CapitalTransRep.save(trantab);
		   	 //updateing amount
		   	 account.setTotalCreditBalance(account.getTotalCreditBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	 account.setAccountBalance(account.getAccountBalance().subtract(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		        Erp_ChartOfAccountsRep.save(account);
		        
		        String parentaccount = account.getParentaccount();
		
		   	 while (parentaccount != null && !parentaccount.isEmpty()) {
		   	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount);
		   	     
		   	     if (parentchartaccount1 != null) {
		   	         
		   	    	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	    	 parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		   	         parentaccount = parentchartaccount1.getParentaccount();
		   	     } else {
		   	         break; 
		   	     }
			}
		   	 
		   	//parttran 2
		   	 
		   	 
		   	Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsccount("ACC00020");
		   	 CapitalTrans trantab1= new CapitalTrans();
		   	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
		   	 trantab1.setId(id1.toString());
		   	 trantab1.setJournalDate(currentdate);
		   	 trantab1.setJournalTranId("TRAN000"+sid);
		   	 trantab1.setPartTranId(1);
		   	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
		   	 trantab1.setAccountNumber(account1.getAccountNumber());
		   	 trantab1.setAccountName(account1.getAccountName());
		   	 trantab1.setDescription(list.get(0).getProcessId());
		   	 trantab1.setDebits(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal()));
		   	 trantab1.setCredits(BigDecimal.ZERO);
		   	 trantab1.setTran_particulars("Work In Process");
		   	 CapitalTransRep.save(trantab1);
		   	 //updateing amount
		   	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	 account1.setAccountBalance(account1.getAccountBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		        Erp_ChartOfAccountsRep.save(account1);
		        
		        String parentaccount1 = account1.getParentaccount();
		
		   	 while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		   	     Erp_ChartOfAccounts parentchartaccount2 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		   	     
		   	     if (parentchartaccount2 != null) {
		   	         
		   	    	 parentchartaccount2.setTotalCreditBalance(parentchartaccount2.getTotalCreditBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	    	 parentchartaccount2.setAccountBalance(parentchartaccount2.getAccountBalance().add(list.get(0).getMaterialtotalvalue().add(list.get(0).getExpensesgrandtotal())));
		   	         Erp_ChartOfAccountsRep.save(parentchartaccount2);
		   	         parentaccount1 = parentchartaccount2.getParentaccount();
		   	     } else {
		   	         break; 
		   	     }
			}
			         
    
		
		
		return process_id+"-Validation Completed";
	}

	
	@Autowired Erp_exp_category_rep erp_exp_category_rep;
	public String adderpexpCategory(Erp_exp_category erp_exp_category) {
		String msg="";
		Session session = sessionFactory.getCurrentSession();
		Optional<Erp_exp_category> optionalEntity = erp_exp_category_rep.findById(erp_exp_category.getCatId());
		if(optionalEntity.isPresent()) {
			msg="Category "+" "+erp_exp_category.getCatName()+" " +"already exist";
		}
		else {
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR ERP_EXP_CAT_SEQ AS id")
					.getSingleResult();
			erp_exp_category.setDelFlg("N");
			erp_exp_category_rep.save(erp_exp_category);
			
			msg="Expenses"+" "+erp_exp_category.getCatName()+" "+"Added sucessfully";
		}
		return msg;
		
	}
	
	
	
	@Autowired Exp_item_rep exp_item_rep;
	public String addexpitem(Exp_itemcreation_Entity exp_itemcreation_Entity) {
		String msg="";
		Session session = sessionFactory.getCurrentSession();
		Optional<Exp_itemcreation_Entity> optionalEntity = exp_item_rep.findById(exp_itemcreation_Entity.getExpItemId());
		if(optionalEntity.isPresent()) {
			msg="Item "+" "+exp_itemcreation_Entity.getExpItemName()+" " +"already exist";
		}
		else {
			
			exp_itemcreation_Entity.setDelFlg("N");
			exp_item_rep.save(exp_itemcreation_Entity);
			
			msg="Expenses"+" "+exp_itemcreation_Entity.getExpItemName()+" "+"Added sucessfully";
		}
		return msg;
		
	}
	
	
	@Autowired
	ERP_EXPENSES_REPO eRP_EXPENSES_REPO;

	public String addexpenses(List<ERP_EXPENSES_ENTITY> eRP_EXPENSES_ENTITY) {
	    String msg = "";

	    // Assuming ExpId is a String, convert it to Long
	    String idString = eRP_EXPENSES_ENTITY.get(0).getExpId();
	  

	    Session session = sessionFactory.getCurrentSession();
	    
		/*
		 * Optional<ERP_EXPENSES_ENTITY> optionalEntity =
		 * eRP_EXPENSES_REPO.findById(idString); if (optionalEntity.isPresent()) { msg =
		 * "Category " + idString + " already exists"; } else {
		 * 
		 */
	    	int count=1;
	    	String accountname="";
	    	Date currentdate =new Date();
	    	//tranid
      		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
		 				.getSingleResult();
      		 
	        for (ERP_EXPENSES_ENTITY entity : eRP_EXPENSES_ENTITY) {
	            BigInteger generatedId = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR EXP_SEQ AS id")
	                    .getSingleResult();

	            entity.setId(generatedId.longValue());
	            entity.setDelFlg("N");
	            entity.setEntityFlg("Y");
	            entity.setVerifyFlg("N");
	            entity.setModFlg("N");

	            eRP_EXPENSES_REPO.save(entity);
	            msg = "Expenses " + entity.getExpId() + " added successfully";
	            
	            if(entity.getExpenses_type().equals("DIRECT")) {
	            	//getaccount
	          		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsccount("ACC00086");
	          		accountname=account1.getAccountName();
	           		 CapitalTrans trantab1= new CapitalTrans();	           		
	           		 //journalid
	               	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	    							.getSingleResult();
	               	 
	               	trantab1.setId(id1.toString());
	                trantab1.setJournalDate(currentdate);
	                trantab1.setJournalTranId("TRAN000"+journalid1);
	                trantab1.setPartTranId(count);
	              	 trantab1.setAccountNumber(account1.getAccountNumber());
	              	 trantab1.setAccountName(account1.getAccountName());
	              	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	              	 trantab1.setDescription(eRP_EXPENSES_ENTITY.get(0).getExpId());
	              	 trantab1.setDebits(entity.getAmount());
	              	 trantab1.setCredits(BigDecimal.ZERO);
	              	 trantab1.setTotalDebits(entity.getAmount());
	              	 trantab1.setTotalCredits(BigDecimal.ZERO);
	              	 if(eRP_EXPENSES_ENTITY.get(0).getPaymentType().equals("cash")) {
	   	            	 trantab1.setTran_particulars("Cash on Hand");
	   	             }
	   	             else {
	   	            	 trantab1.setTran_particulars("IDBI Bank");
	   	             }
	              	 
	              	 CapitalTransRep.save(trantab1);
	              	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(entity.getAmount()));
	              	 account1.setAccountBalance(account1.getAccountBalance().add(entity.getAmount()));
	   	             Erp_ChartOfAccountsRep.save(account1);
	   	             
	              	 String parentaccount3 = account1.getParentaccount();
	              	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	              	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	              	     
	              	     if (parentchartaccount4 != null) {
	              	         
	              	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(entity.getAmount()));
	              	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().add(entity.getAmount()));
	              	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	              	         parentaccount3 = parentchartaccount4.getParentaccount();
	              	     } else {
	              	         break; 
	              	     }
	              	 }

	            }
	            else {
	            	
	            	//getaccount
	          		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsccount("ACC00087");
	          		 
	          		accountname=account1.getAccountName();
	           		 CapitalTrans trantab1= new CapitalTrans();	           		
	           		 //journalid
	               	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	    							.getSingleResult();
	               	 
	               	trantab1.setId(id1.toString());
	                trantab1.setJournalDate(currentdate);
	                trantab1.setJournalTranId("TRAN000"+journalid1);
	                trantab1.setPartTranId(count);
	              	 trantab1.setAccountNumber(account1.getAccountNumber());
	              	 trantab1.setAccountName(account1.getAccountName());
	              	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	              	 trantab1.setDescription(eRP_EXPENSES_ENTITY.get(0).getExpId());
	              	 trantab1.setDebits(entity.getAmount());
	              	 trantab1.setCredits(BigDecimal.ZERO);
	              	 trantab1.setTotalDebits(entity.getAmount());
	              	 trantab1.setTotalCredits(BigDecimal.ZERO);
	              	 if(eRP_EXPENSES_ENTITY.get(0).getPaymentType().equals("cash")) {
	   	            	 trantab1.setTran_particulars("Cash on Hand");
	   	             }
	   	             else {
	   	            	 trantab1.setTran_particulars("IDBI Bank");
	   	             }
	              	 
	              	 CapitalTransRep.save(trantab1);
	              	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(entity.getAmount()));
	              	 account1.setAccountBalance(account1.getAccountBalance().add(entity.getAmount()));
	   	             Erp_ChartOfAccountsRep.save(account1);
	   	             
	              	 String parentaccount3 = account1.getParentaccount();
	              	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	              	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	              	     
	              	     if (parentchartaccount4 != null) {
	              	         
	              	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(entity.getAmount()));
	              	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().add(entity.getAmount()));
	              	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	              	         parentaccount3 = parentchartaccount4.getParentaccount();
	              	     } else {
	              	         break; 
	              	     }
	              	 }
	            	
	            }
	            
	            count++;
	        }
	        
	      //part_tran=2
       	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
						.getSingleResult();
       	 CapitalTrans trantab2= new CapitalTrans();
       	 //first tran to getting back the advance to vendor account  
       	 trantab2.setId(id2.toString());
       	 trantab2.setJournalDate(currentdate);
       	 trantab2.setJournalTranId("TRAN000"+journalid1);
       	 trantab2.setPartTranId(count);
       	 
       	 if(eRP_EXPENSES_ENTITY.get(0).getPaymentType().equals("cash")) {
           	 trantab2.setAccountNumber("ACC00048");
	             trantab2.setFullAccount("ACC00048-Cash on Hand");
	             trantab2.setAccountName("Cash on Hand");
            }
            else {
           	 trantab2.setAccountNumber("ACC00045");
	             trantab2.setFullAccount("ACC00045-IDBI Bank");
	             trantab2.setAccountName("IDBI Bank");
            }
            
       	 trantab2.setDescription(eRP_EXPENSES_ENTITY.get(0).getExpId());
       	 trantab2.setCredits(eRP_EXPENSES_ENTITY.get(0).getTotalAmount());
       	 trantab2.setDebits(BigDecimal.ZERO);
       	 trantab2.setTotalDebits(BigDecimal.ZERO );
       	 trantab2.setTotalCredits(eRP_EXPENSES_ENTITY.get(0).getTotalAmount());
       	 trantab2.setTran_particulars(accountname);
       	 CapitalTransRep.save(trantab2);
       	 
       	 if(eRP_EXPENSES_ENTITY.get(0).getPaymentType().equals("cash")) {
       		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
       		 
       		 chartaccount1.setTotalCreditBalance(chartaccount1.getTotalCreditBalance().add(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
       		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
	             Erp_ChartOfAccountsRep.save(chartaccount1);
	             
	             String parentaccount4 = chartaccount1.getParentaccount();
           	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
           	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
           	     
           	     if (parentchartaccount5 != null) {
           	         
           	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
           	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
           	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
           	         parentaccount4 = parentchartaccount5.getParentaccount();
           	     } else {
           	         break; 
           	     }
           	 }
           	

            }
            else {
           	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
       		 
       		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
       		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
	             Erp_ChartOfAccountsRep.save(chartaccount1);
	             
	             String parentaccount4 = chartaccount1.getParentaccount();
           	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
           	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
           	     
           	     if (parentchartaccount5 != null) {
           	         
           	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
           	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(eRP_EXPENSES_ENTITY.get(0).getTotalAmount()));
           	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
           	         parentaccount4 = parentchartaccount5.getParentaccount();
           	     } else {
           	         break; 
           	     }
           	 }
            }

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	    
	    return msg;
	}
	
	
	
	/*----editexp--*/
	public String editexpenses(List<ERP_EXPENSES_ENTITY> eRP_EXPENSES_ENTITY) {
	    System.out.println("Inside editexpenses method");
	    String msg = "";
	    Session session = sessionFactory.getCurrentSession();

	    for (ERP_EXPENSES_ENTITY entity : eRP_EXPENSES_ENTITY) {
	    	System.out.println("inside not null");
	        if (entity.getId() != null) {
	            // Modify existing row if unique ID exists
	            entity.setDelFlg("N");
	            entity.setEntityFlg("Y");
	            entity.setVerifyFlg("N");
	            entity.setModFlg("Y");

	            eRP_EXPENSES_REPO.save(entity);
	            msg = "Expenses " + entity.getExpId() + " modified successfully.";
	        } else {
	        	
	        	System.out.println("inside  null");
	            // Add new row if unique ID is null
	            BigInteger generatedId = (BigInteger) session
	                    .createNativeQuery("SELECT NEXT VALUE FOR EXP_SEQ AS id")
	                    .getSingleResult();

	            entity.setId(generatedId.longValue());
	            entity.setDelFlg("N");
	            entity.setEntityFlg("Y");
	            entity.setVerifyFlg("N");
	            entity.setModFlg("Y");

	            eRP_EXPENSES_REPO.save(entity);
	            msg = "Expenses " + entity.getExpId() + " modified successfully.";
	        }
	    }
	    return msg;
	}

	public String paymentin(PaymentIn Paymentinaddd,String vandor_id, String vendorname) {
		String msg = "";
		Session session = sessionFactory.getCurrentSession();
		Paymentinaddd.setVendorId(vandor_id);
		Paymentinaddd.setVendorName(vendorname);
		PaymentInRep.save(Paymentinaddd);
		VendorCreation up = VendorCreationRep.getvendorlistid(vandor_id);
		up.setBalanceamount(Paymentinaddd.getBalance());
		VendorCreationRep.save(up);
		

       

        // Check if paidAmount is not null and greater than zero
        if (Paymentinaddd.getReceivedamount() != null && Paymentinaddd.getReceivedamount().compareTo(BigDecimal.ZERO) != 0) {
            
                // Create a new Transaction_table object
                Transaction_table newTransaction = new Transaction_table();

                // Generate a new unique ID for the Transaction
                BigInteger generatedId = (BigInteger) session
                    .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
                    .getSingleResult();

                if (generatedId == null) {
                    throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
                }

                String transactionId = "TRAN000" + generatedId;
                newTransaction.setTran_id(transactionId);
                System.out.println("Generated Transaction ID: " + transactionId); // Debugging

                // Set other fields
                //newTransaction.setPo_id(list.get(0).getPoId());
                newTransaction.setVendor_id(up.getVendorId());
                newTransaction.setClassification(up.getClassify());
                newTransaction.setVendor_name(up.getVendorName());
                newTransaction.setAcct_crncy(up.getCrncy());
                newTransaction.setAcct_num(up.getAcctNum());
                Date currentdate = new Date();
                newTransaction.setAcct_cls_date(currentdate);
                // Set advance amount in Debit
                newTransaction.setCr_amt(Paymentinaddd.getReceivedamount());

                // Set balance amount in COA if valid
                if (Paymentinaddd.getBalance()!= null) {
                    newTransaction.setAcct_bal(Paymentinaddd.getBalance());
                }

                // Save the new transaction
                Transaction_table_Rep.save(newTransaction);

            
        }
		msg ="paymentIn Add successfully-" + Paymentinaddd.getReceiptNo();
	    
	    return msg;
	}


	

	@Autowired
	PO_invoice_Pay_Out_Rep PO_invoice_Pay_Out_rep;
	/*----PO Invoice Pay Out--*/
	public ResponseEntity<String> invoice_edit_Pay_Out(List<PO_invoice_Pay_Out_entity> list, String formmode, String Opid) {
	    ResponseEntity<String> msg = null;

		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	//Calculation
	        	
	        	VendorCreation up1 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal vendor_cr= up1.getCrAmt();

	            if(list.get(0).getTotal_vendor_balance() !=  null || list.get(0).getTotal_vendor_balance().compareTo(BigDecimal.ZERO) == 0) {
	            	
	            	up1.setAdvanceamount(list.get(0).getTotal_vendor_balance());
	            	BigDecimal new_cr= vendor_cr.add(list.get(0).getBuy_Amount());
	            	up1.setCrAmt(new_cr);
		        	   VendorCreationRep.save(up1);
		        	   
	            }
	            
	            
	            BigDecimal buy_now = list.get(0).getBuy_Amount();
	            Date currentdate= new Date();
	            //accounts
	            if(buy_now.compareTo(BigDecimal.ZERO)>0) {
	            	
	            	 //getaccount
	          		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(up1.getVendorId());
	          		 
	          		 //tranid
	           		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
	    		 				.getSingleResult();
	           		 
	           		CapitalTrans trantab1= new CapitalTrans();
	           		
	           	//journalid
	              	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	   							.getSingleResult();
	              	 
	              	//first tran to getting back the advance to vendor account  
	               	 trantab1.setId(id1.toString());
	               	 trantab1.setJournalDate(currentdate);
	               	 trantab1.setJournalTranId("TRAN000"+journalid1);
	               	 trantab1.setPartTranId(1);
	               	 trantab1.setAccountNumber(account1.getAccountNumber());
	               	 trantab1.setAccountName(account1.getAccountName());
	               	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	               	 trantab1.setDescription(list.get(0).getPoId());
	               	 trantab1.setDebits(buy_now);
	               	 trantab1.setCredits(BigDecimal.ZERO);
	               	 trantab1.setTotalDebits(buy_now);
	               	 trantab1.setTotalCredits(BigDecimal.ZERO);
	               	 trantab1.setVendor_id(up1.getVendorId());
	               	 trantab1.setVendor_name(up1.getVendorName());
	               
	               	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab1.setTran_particulars("Cash on Hand");
		             }
		             else {
		            	 trantab1.setTran_particulars("IDBI Bank");
		             }
	           	 
	           	 CapitalTransRep.save(trantab1);
	           	 account1.setTotalDebitBalance(account1.getTotalDebitBalance().add(buy_now));
	           	 account1.setAccountBalance(account1.getAccountBalance().subtract(buy_now));
		             Erp_ChartOfAccountsRep.save(account1);
		             
	           	 String parentaccount3 = account1.getParentaccount();
	           	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	           	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	           	     
	           	     if (parentchartaccount4 != null) {
	           	         
	           	         parentchartaccount4.setTotalDebitBalance(parentchartaccount4.getTotalDebitBalance().add(buy_now));
	           	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(buy_now));
	           	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	           	         parentaccount3 = parentchartaccount4.getParentaccount();
	           	     } else {
	           	         break; 
	           	     }
	           	 }
	            
	            
	           //part_tran=2
            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 CapitalTrans trantab2= new CapitalTrans();
            	 //first tran to getting back the advance to vendor account  
            	 trantab2.setId(id2.toString());
            	 trantab2.setJournalDate(currentdate);
            	 trantab2.setJournalTranId("TRAN000"+journalid1);
            	 trantab2.setPartTranId(2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
            	 trantab2.setDescription(list.get(0).getPoId());
            	 trantab2.setCredits(buy_now);
            	 trantab2.setDebits(BigDecimal.ZERO);
            	 trantab2.setTotalDebits(BigDecimal.ZERO);
            	 trantab2.setTotalCredits(buy_now);
            	 trantab2.setVendor_id(up1.getVendorId());
            	 trantab2.setVendor_name(up1.getVendorName());
            	 trantab2.setTran_particulars(account1.getAccountName());
            	 CapitalTransRep.save(trantab2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
            		 
            		 chartaccount1.setTotalCreditBalance(chartaccount1.getTotalCreditBalance().add(buy_now));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(buy_now));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(buy_now));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(buy_now));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	

	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
            		 
            		 chartaccount1.setTotalCreditBalance(chartaccount1.getTotalCreditBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(buy_now));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalCreditBalance(parentchartaccount5.getTotalCreditBalance().add(buy_now));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().subtract(buy_now));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            
	            }
	            }
	            
	            
	            
	         	            
	            
	            
	            
	            
	            
	         // PO -> Invoice
	            List<PO_invoice_entity> al = PO_invoice_reps.getbyid(Opid);
	            for (PO_invoice_entity param : al) {
	                // Check if buy_Amount or extra_amount is null or invalid
	                 buy_now = list.get(0).getBuy_Amount();
	                BigDecimal totalbalance = param.getExtra_amount();
	                BigDecimal last_paid_Amount = param.getLast_extra_amount();
	                
	                BigDecimal total_paid_amt=last_paid_Amount.add(buy_now);
	              
	                
	                if (buy_now == null || totalbalance == null || buy_now.compareTo(BigDecimal.ZERO) <= 0 || totalbalance.compareTo(BigDecimal.ZERO) <= 0) {
	                    System.out.println("Skipping update as values are null or invalid. buy_now: " + buy_now + ", totalbalance: " + totalbalance);
	                    continue; // Skip this iteration and move to the next one
	                }

	                // Perform the calculation
	                BigDecimal new_amt = totalbalance.subtract(buy_now);
	                
	                // Set the new amount and save
	                param.setExtra_amount(new_amt);
	                param.setLast_extra_amount(total_paid_amt);
	                PO_invoice_reps.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	                // Fetch ledger entries based on Opid (assuming multiple records exist)
	                List<ACCOUNT_LEDGER_PO_Entity> ledgerList = ACCOUNT_LEDGER_PO_rep.findByPoId(Opid); 

	                for (ACCOUNT_LEDGER_PO_Entity ledger : ledgerList) {
	                    // Check if the item matches
	                    if (ledger.getItem().equals(param.getItem())) {
	                        ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));

	                        // Handle null case for TotalAmount
	                        if (ledger.getTotalAmount() == null) {
	                            ledger.setTotalAmount(buy_now);
	                        } else {
	                            ledger.setTotalAmount(ledger.getTotalAmount().add(buy_now)); // Assign result back
	                        }

	                        // Save updated ledger entity
	                        ACCOUNT_LEDGER_PO_rep.save(ledger);
	                    }
	                }

	            }

	            //PO -> Invoice Pay-Out
	        	
	            for (PO_invoice_Pay_Out_entity param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR INVOICE_ID_PAY_OUT AS id")
	            		    .getSingleResult()).longValue();
	            	
	            	Long idstockbatchno = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR stockbatchno_pay_out AS id")
	            		    .getSingleResult()).longValue();
	            	

	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setPoId(Opid);
	                    param.setPaidAmount(param.getPaidAmount().add(param.getBuy_Amount()));
	                    param.setApprovedFlg('S');
	                    param.setTotal_vendor_balance(param.getTotal_vendor_balance());
	          
	                    
	                    ItemCreation up= ItemCreationRep.getitemlistbyid(param.getItemcode());
	                    String catg=up.getCategory();
	                    	param.setBatch("BATCH00"+idstockbatchno.toString());
		                    param.setUseingqty(param.getQty());
		                    param.setAssetCategory(catg);
		                    PO_invoice_Pay_Out_rep.save(param);
	                System.out.println("Saved PO entity: " + param);
	            }
	            

	            
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getBuy_Amount();
	            
	            
	            //For demotran
	            
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
                    // Create a new Transaction_table object
            	Transaction_table_demo newTransaction = new Transaction_table_demo();

                    // Generate a new unique ID for the Transaction
                    BigInteger generatedId = (BigInteger) session
                        .createNativeQuery("SELECT NEXT VALUE FOR demo_tran_id AS id")
                        .getSingleResult();

                    if (generatedId == null) {
                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
                    }

                    String transactionId = "TRAN000" + generatedId;
                    newTransaction.setTran_id(transactionId);
                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

                    // Set other fields
                    newTransaction.setPo_id(list.get(0).getPoId());
                    newTransaction.setVendor_id(list.get(0).getVendorId());
                    newTransaction.setClassification(vendor.getClassify());
                    newTransaction.setVendor_name(vendor.getVendorName());
                    newTransaction.setAcct_crncy(vendor.getCrncy());
                    newTransaction.setAcct_num(vendor.getAcctNum());
                    currentdate = new Date();
                    newTransaction.setAcct_cls_date(currentdate);
                    // Set advance amount in Debit
                    newTransaction.setDr_amt(paidAmount);
                    newTransaction.setTran_type(list.get(0).getPaymentType());
                    newTransaction.setType("Payment");
       	         
                    // Set balance amount in COA if valid
                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
                        newTransaction.setAcct_bal(vendor.getBalanceamount());
                    }

                    // Save the new transaction
                    Transaction_table_demo_rep.save(newTransaction);

                
            }

	            
	            
	            
	            

	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getPoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    currentdate = new Date(); 
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setDr_amt(paidAmount);
	                    
	                    

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }
	                    
	                    newTransaction.setTran_type(list.get(0).getPaymentType());
	                    newTransaction.setType("Purchase Invoice");
	       	         BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();
	       	         if(list.get(0).getPaymentType().equals("cash")) {
	       	        	 up.setCurrent_cash_balance(up.getCurrent_cash_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         else {
	       	        	up.setCurrent_account_balance(up.getCurrent_account_balance().subtract(list.get(0).getAdvanceAmount()));
	       	         }
	       	         
	       	      btmAdminOrganizationMasterRep.save(up);



	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);
	            }


	    
	            msg = ResponseEntity.ok("Amount Paid Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	
	
	
	@Autowired Follow_Up_Rep followup_repo;
	@Autowired
	SALES_invoice_TABLE_PAY_IN_REP SALES_invoice_TABLE_PAY_IN_rep;
	/*----SO Invoice Pay In--*/
	public ResponseEntity<String> invoice_edit_Pay_in(List<SALES_invoice_TABLE_PAY_IN> list, String formmode, String Opid) {
	    ResponseEntity<String> msg = null;

		
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        if ("add".equals(formmode)) {
	        	
	        	//Calculation
	        	
	        	VendorCreation up1 = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal vendor_cr= up1.getDrAmt();

	            if(list.get(0).getTotal_vendor_balance() !=  null || list.get(0).getTotal_vendor_balance().compareTo(BigDecimal.ZERO) == 0) {
	            	
	            	up1.setBalanceamount(list.get(0).getTotal_vendor_balance());
	            	BigDecimal new_dr= vendor_cr.add(list.get(0).getBuy_Amount());
	            	up1.setDrAmt(new_dr);
		        	   VendorCreationRep.save(up1);
	            }
	            
				/* ----SAVE FOLLOWUP--- */
	            
	            System.out.println("the checking Followup id" +list.get(0).getWoId());
	            
	            Optional<Follow_Up_Entity> followup = followup_repo.findById(list.get(0).getWoId());
	            if (followup.isPresent()) {
	                Follow_Up_Entity follow = followup.get();
	                follow.setBalanceAmount(list.get(0).getBalanceAmount());
	                followup_repo.save(follow);  // Save inside the if block
	            }
	            
	            
	            
	          //accounts
	            BigDecimal buy_now = list.get(0).getBuy_Amount();
	            Date currentdate= new Date();
	            
	            if(buy_now.compareTo(BigDecimal.ZERO)>0) {
	            	
	            	 //getaccount
	          		 Erp_ChartOfAccounts account1 =Erp_ChartOfAccountsRep.findsbyowner(up1.getVendorId());
	          		 
	          		 //tranid
	           		 BigInteger journalid1 =  (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
	    		 				.getSingleResult();
	           		 
	           		CapitalTrans trantab1= new CapitalTrans();
	           		
	           	//journalid
	              	 BigInteger id1 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
	   							.getSingleResult();
	              	 
	              	//first tran to getting back the advance to vendor account  
	               	 trantab1.setId(id1.toString());
	               	 trantab1.setJournalDate(currentdate);
	               	 trantab1.setJournalTranId("TRAN000"+journalid1);
	               	 trantab1.setPartTranId(1);
	               	 trantab1.setAccountNumber(account1.getAccountNumber());
	               	 trantab1.setAccountName(account1.getAccountName());
	               	 trantab1.setFullAccount(account1.getAccountNumber()+"-"+account1.getAccountName());
	               	 trantab1.setDescription(list.get(0).getWoId());
	               	 trantab1.setDebits(BigDecimal.ZERO);
	               	 trantab1.setCredits(buy_now);
	               	 trantab1.setTotalDebits(BigDecimal.ZERO);
	               	 trantab1.setTotalCredits(buy_now);
	               	 trantab1.setVendor_id(up1.getVendorId());
	               	 trantab1.setVendor_name(up1.getVendorName());
	               
	               	 if(list.get(0).getPaymentType().equals("cash")) {
		            	 trantab1.setTran_particulars("Cash on Hand");
		             }
		             else {
		            	 trantab1.setTran_particulars("IDBI Bank");
		             }
	           	 
	           	 CapitalTransRep.save(trantab1);
	           	 account1.setTotalCreditBalance(account1.getTotalCreditBalance().add(buy_now));
	           	 account1.setAccountBalance(account1.getAccountBalance().subtract(buy_now));
		             Erp_ChartOfAccountsRep.save(account1);
		             
	           	 String parentaccount3 = account1.getParentaccount();
	           	 while (parentaccount3 != null && !parentaccount3.isEmpty()) {
	           	     Erp_ChartOfAccounts parentchartaccount4 = Erp_ChartOfAccountsRep.findsccount(parentaccount3);
	           	     
	           	     if (parentchartaccount4 != null) {
	           	         
	           	         parentchartaccount4.setTotalCreditBalance(parentchartaccount4.getTotalCreditBalance().add(buy_now));
	           	         parentchartaccount4.setAccountBalance(parentchartaccount4.getAccountBalance().subtract(buy_now));
	           	         Erp_ChartOfAccountsRep.save(parentchartaccount4);
	           	         parentaccount3 = parentchartaccount4.getParentaccount();
	           	     } else {
	           	         break; 
	           	     }
	           	 }
	            
	            
	           //part_tran=2
            	 BigInteger id2 = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
							.getSingleResult();
            	 CapitalTrans trantab2= new CapitalTrans();
            	 //first tran to getting back the advance to vendor account  
            	 trantab2.setId(id2.toString());
            	 trantab2.setJournalDate(currentdate);
            	 trantab2.setJournalTranId("TRAN000"+journalid1);
            	 trantab2.setPartTranId(2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
	            	 trantab2.setAccountNumber("ACC00048");
		             trantab2.setFullAccount("ACC00048-Cash on Hand");
		             trantab2.setAccountName("Cash on Hand");
	             }
	             else {
	            	 trantab2.setAccountNumber("ACC00045");
		             trantab2.setFullAccount("ACC00045-IDBI Bank");
		             trantab2.setAccountName("IDBI Bank");
	             }
	             
            	 trantab2.setDescription(list.get(0).getWoId());
            	 trantab2.setCredits(BigDecimal.ZERO);
            	 trantab2.setDebits(buy_now);
            	 trantab2.setTotalDebits(buy_now);
            	 trantab2.setTotalCredits(BigDecimal.ZERO);
            	 trantab2.setVendor_id(up1.getVendorId());
            	 trantab2.setVendor_name(up1.getVendorName());
            	 trantab2.setTran_particulars(account1.getAccountName());
            	 CapitalTransRep.save(trantab2);
            	 
            	 if(list.get(0).getPaymentType().equals("cash")) {
            		 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00048");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(buy_now));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().add(buy_now));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(buy_now));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(buy_now));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            	

	             }
	             else {
	            	 Erp_ChartOfAccounts chartaccount1=  Erp_ChartOfAccountsRep.findsccount("ACC00045");
            		 
            		 chartaccount1.setTotalDebitBalance(chartaccount1.getTotalDebitBalance().add(list.get(0).getPaidAmount()));
            		 chartaccount1.setAccountBalance(chartaccount1.getAccountBalance().subtract(buy_now));
		             Erp_ChartOfAccountsRep.save(chartaccount1);
		             
		             String parentaccount4 = chartaccount1.getParentaccount();
	            	 while (parentaccount4 != null && !parentaccount4.isEmpty()) {
	            	     Erp_ChartOfAccounts parentchartaccount5 = Erp_ChartOfAccountsRep.findsccount(parentaccount4);
	            	     
	            	     if (parentchartaccount5 != null) {
	            	         
	            	         parentchartaccount5.setTotalDebitBalance(parentchartaccount5.getTotalDebitBalance().add(buy_now));
	            	         parentchartaccount5.setAccountBalance(parentchartaccount5.getAccountBalance().add(buy_now));
	            	         Erp_ChartOfAccountsRep.save(parentchartaccount5);
	            	         parentaccount4 = parentchartaccount5.getParentaccount();
	            	     } else {
	            	         break; 
	            	     }
	            	 }
	            
	            }
	            }
	            
	            
	            
	            
	            
	            
	            


	            
	            
	            
	         // SO -> Invoice
	            List<SALES_invoice_TABLE> al = SALES_invoice_TABLERep.getparticular(Opid);
	            for (SALES_invoice_TABLE param : al) {
	                // Check if buy_Amount or extra_amount is null or invalid
	                 buy_now = list.get(0).getBuy_Amount();
	                BigDecimal totalbalance = param.getExtra_amount();
	                BigDecimal last_paid_Amount = param.getLast_extra_amount();
	                
	                BigDecimal total_paid_amt=last_paid_Amount.add(buy_now);
	              
	                
	                if (buy_now == null || totalbalance == null || buy_now.compareTo(BigDecimal.ZERO) <= 0 || totalbalance.compareTo(BigDecimal.ZERO) <= 0) {
	                    System.out.println("Skipping update as values are null or invalid. buy_now: " + buy_now + ", totalbalance: " + totalbalance);
	                    continue; // Skip this iteration and move to the next one
	                }

	                // Perform the calculation
	                BigDecimal new_amt = totalbalance.subtract(buy_now);
	                
	                // Set the new amount and save
	                param.setExtra_amount(new_amt);
	                param.setLast_extra_amount(total_paid_amt);
	                SALES_invoice_TABLERep.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	                // Fetch ledger entries based on Opid (assuming multiple records exist)
	                List<ACCOUNT_LEDGER_SALE_Entity> ledgerList = ACCOUNT_LEDGER_SALE_rep.findByPoId(Opid); 

	                for (ACCOUNT_LEDGER_SALE_Entity ledger : ledgerList) {
	                    // Check if the item matches
	                    if (ledger.getItem().equals(param.getItem())) {
	                        ledger.setAmountPerItem(String.valueOf(param.getAmountPerItem()));

	                        // Handle null case for TotalAmount
	                        if (ledger.getTotalAmount() == null) {
	                            ledger.setTotalAmount(buy_now);
	                        } else {
	                            ledger.setTotalAmount(ledger.getTotalAmount().add(buy_now)); // Assign result back
	                        }

	                        // Save updated ledger entity
	                        ACCOUNT_LEDGER_SALE_rep.save(ledger);
	                    }
	                }
	                
	            }

	            //SO -> Invoice Pay-In
	        	
	            for (SALES_invoice_TABLE_PAY_IN param : list) {
	            	Long idLong = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR PAYMENT_IN_ID AS id")
	            		    .getSingleResult()).longValue();
	            	
	            	Long idstockbatchno = ((Number) session
	            		    .createNativeQuery("SELECT NEXT VALUE FOR stockbatchno_pay_in AS id")
	            		    .getSingleResult()).longValue();
	            	

	            		BigDecimal id = BigDecimal.valueOf(idLong);
	            		param.setId(id.toString());
	                    param.setWoId(Opid);
	                    param.setPaidAmount(param.getPaidAmount().add(param.getBuy_Amount()));
	                    param.setApprovedFlg('S');
	                    param.setTotal_vendor_balance(param.getTotal_vendor_balance());
	          
	                    
	                    ItemCreation up= ItemCreationRep.getitemlistbyid(param.getItemcode());
	                    String catg=up.getCategory();
	                    	param.setBatch("BATCH00"+idstockbatchno.toString());
		                    param.setQty(param.getQty());
		                    param.setAssetCategory(catg);
		                    SALES_invoice_TABLE_PAY_IN_rep.save(param);
	                System.out.println("Saved PO entity: " + param);
	                
	                
	             
	            }
	            

	            
	         // For Chart of Accounts
	            VendorCreation vendor = VendorCreationRep.getvendorlistid(list.get(0).getVendorId());
	            BigDecimal paidAmount = list.get(0).getBuy_Amount();

	            // Check if paidAmount is not null and greater than zero
	            if (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) != 0) {
	                
	                    // Create a new Transaction_table object
	                    Transaction_table newTransaction = new Transaction_table();

	                    // Generate a new unique ID for the Transaction
	                    BigInteger generatedId = (BigInteger) session
	                        .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	                        .getSingleResult();

	                    if (generatedId == null) {
	                        throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	                    }

	                    String transactionId = "TRAN000" + generatedId;
	                    newTransaction.setTran_id(transactionId);
	                    System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	                    // Set other fields
	                    newTransaction.setPo_id(list.get(0).getWoId());
	                    newTransaction.setVendor_id(list.get(0).getVendorId());
	                    newTransaction.setClassification(vendor.getClassify());
	                    newTransaction.setVendor_name(vendor.getVendorName());
	                    newTransaction.setAcct_crncy(vendor.getCrncy());
	                    newTransaction.setAcct_num(vendor.getAcctNum());
	                    currentdate = new Date(); 
	                    newTransaction.setAcct_cls_date(currentdate);
	                    // Set advance amount in Debit
	                    newTransaction.setCr_amt(paidAmount);

	                    // Set balance amount in COA if valid
	                    if (vendor.getBalanceamount() != null && vendor.getBalanceamount().compareTo(BigDecimal.ZERO) != 0) {
	                        newTransaction.setAcct_bal(vendor.getBalanceamount());
	                    }

	                    // Save the new transaction
	                    Transaction_table_Rep.save(newTransaction);
	            }


	    
	            msg = ResponseEntity.ok("Amount Paid Successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	    return msg;
	}
	
	@Autowired AllowancemakerRep Allowancemakerrep;
	public String addallowancemaker(AllowanceMaker AllowanceMakeradd) {
		String msg="";
		Session session = sessionFactory.getCurrentSession();
		//Optional<AllowanceMaker> optionalEntity = Allowancemakerrep.findById(AllowanceMakeradd.getDesignation());
		AllowanceMaker up1=Allowancemakerrep.getbyallid(AllowanceMakeradd.getDesignation());
		if(up1!=null) {
			msg="Designation "+" "+AllowanceMakeradd.getDesignation()+" " +"already exist";
		}
		else {
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR Allowanceseq AS id")
					.getSingleResult();
			AllowanceMakeradd.setAllowancemakerId("All000"+id);
			AllowanceMakeradd.setDelFlg("N");
			Allowancemakerrep.save(AllowanceMakeradd);
			msg="Allowance for"+" "+AllowanceMakeradd.getDesignation()+" "+"Added sucessfully";
		}
		return msg;
		
	}
	
	public String Addtraveltclaims(TravelClaim TravelClaimadd,String loginuserid,String formmode) {
		Session session = sessionFactory.getCurrentSession();
		String msg="";
		if(formmode.equals("add")) {
		System.out.println("value"+TravelClaimadd.getRole()+TravelClaimadd.getMarketType());
		BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR TravelClaimSeq AS id").getSingleResult();
		System.out.println("id="+id);
		TravelClaimadd.setId("Travel00"+id);
		TravelClaimadd.setEntry_user(loginuserid);
		TravelClaimadd.setDel_flg("N");
		TravelClaimadd.setModify_flg("N");
		TravelClaimadd.setVerify_flg("N");
		TravelClaimRep.save(TravelClaimadd);
		msg="Travel Claim Added Successfully!!";
		}
		else if(formmode.equals("edit")) {
			TravelClaim up=TravelClaimRep.climelistbyid(TravelClaimadd.getId());
			
			TravelClaimadd.setDel_flg(up.getDel_flg());
			TravelClaimadd.setModify_flg("Y");
			TravelClaimadd.setVerify_flg(up.getVerify_flg());
			TravelClaimadd.setEntry_user(up.getEntry_user());
			TravelClaimadd.setVerify_flg("N");
			
			TravelClaimRep.save(TravelClaimadd);
			msg="Travel Claim Modify Successfully!!";
			
		}
		return msg;
		
	}
	
	
	

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;
	public ResponseEntity<String> add_Tran(Transaction_table list) {
	    ResponseEntity<String> msg = null;
	    Session session = sessionFactory.getCurrentSession();

	    try {
	        // Create a new Transaction_table object
	        Transaction_table newTransaction = new Transaction_table();

	        // Generate a new unique ID for the Transaction
	        BigInteger generatedId = (BigInteger) session
	            .createNativeQuery("SELECT NEXT VALUE FOR TRAN_ID AS id")
	            .getSingleResult();

	        if (generatedId == null) {
	            throw new IllegalStateException("Failed to generate a transaction ID. Ensure the sequence exists and is configured correctly.");
	        }

	        String transactionId = "TRAN000" + generatedId;
	        newTransaction.setTran_id(transactionId);
	        System.out.println("Generated Transaction ID: " + transactionId); // Debugging

	        // Set other fields
	        newTransaction.setClassification("Asset");
	        newTransaction.setVendor_name(list.getAcct_name());
	        newTransaction.setAcct_crncy(list.getAcct_crncy());
	        newTransaction.setAcct_num(list.getAcct_num());
	        newTransaction.setCr_amt(list.getCr_amt());
	        Date currentdate = new Date();
	        newTransaction.setAcct_cls_date(currentdate);
	        newTransaction.setCapital_amt(list.getCr_amt());
	        
	        newTransaction.setAcct_bal(list.getAcct_bal());

	        // Save the new transaction
	        Transaction_table_Rep.save(newTransaction);

	        // Update in Organization Table
	        BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.getOrganization();//("U72900TN2017PTC115892").orElse(null); // Assuming ID=1, adjust as needed
	        if (up != null) {
	            if (up.getCapitalamount() == null) {
	                up.setCapitalamount(list.getCr_amt());
	            } else {
	                BigDecimal newValue = up.getCapitalamount().add(list.getCr_amt());
	                up.setCapitalamount(newValue);
	            }
	            btmAdminOrganizationMasterRep.save(up);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Organization data not found.");
	        }

	        // Success response
	        return ResponseEntity.ok("Fund Added Successfully.");
	    } catch (Exception e) {
	        // Handle any exception
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
	    }
	}

	
	public String bigIntegerToAlphabet(BigInteger bigNumber) {
	    StringBuilder result = new StringBuilder();

	    // Adjust for the 1-based indexing, where 1 maps to 'A'
	    while (bigNumber.compareTo(BigInteger.ZERO) > 0) {
	        // Subtract 1 before calculating the remainder to handle 'A' for 1
	        bigNumber = bigNumber.subtract(BigInteger.ONE);
	        
	        // Get remainder when divided by 26
	        BigInteger remainder = bigNumber.mod(BigInteger.valueOf(26));

	        // Convert remainder to corresponding alphabet letter
	        result.insert(0, (char) ('A' + remainder.intValue()));

	        // Divide the number by 26 to move to the next "digit"
	        bigNumber = bigNumber.divide(BigInteger.valueOf(26));
	    }

	    return result.toString();
	}

	public String addcodecreation(CodeCreation CodeCreationadd, String addtype) {
		String msg="";
		Session session = sessionFactory.getCurrentSession();
			BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR codeid AS id")
					.getSingleResult();
			CodeCreationadd.setId(id.toString());
			if(addtype.equals("HeadCode")) {
			BigInteger headcodeid = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR Head_code AS id")
					.getSingleResult();
			
			msg="HeadCode Added sucessfully";
			
			
			  BigInteger generatedId = (BigInteger) session
			            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
			            .getSingleResult();
			  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
			  
			  list.setAccountName(CodeCreationadd.getHeadDescription());
			  list.setAccountType("Asset");
			  list.setOwnership("");
			  Date curentdate= new Date();
			  list.setEntryDate(curentdate);
			  list.setAccountNumber("ACC000"+generatedId.toString());
			  CodeCreationadd.setAccountnumber("ACC000"+generatedId.toString());
			  list.setAccountBalance(BigDecimal.ZERO);
			  list.setTotalCreditBalance(BigDecimal.ZERO);
			  list.setTotalDebitBalance(BigDecimal.ZERO);
			  list.setParentaccount("");
			  Erp_ChartOfAccountsRep.save(list);
				        

			
			}
			else if(addtype.equals("Categorycode")) {
				BigInteger Categorycodeid = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR CATEGORYCODESEQ AS id")
						.getSingleResult();
				 /*BigInteger generatedId = (BigInteger) session
				            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
				            .getSingleResult();*/
				  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
				  
				  System.out.println("headcode"+CodeCreationadd.getHeadCode());
				  CodeCreation accounrcr=codecreationrep.getheadcodebyaccount(CodeCreationadd.getHeadCode());
				  
				  BigInteger accountcount=Erp_ChartOfAccountsRep.parentcount(accounrcr.getAccountnumber());
				  System.out.println("accountcount"+accountcount);
				  String accountalphabet=bigIntegerToAlphabet(accountcount.add(BigInteger.ONE));
				  System.out.println("accountalphabet"+accountalphabet);
				  list.setParentaccount(accounrcr.getAccountnumber());
				  list.setAccountName(CodeCreationadd.getCategoryDescription());
				  list.setAccountType("Asset");
				  list.setOwnership("");
				  Date curentdate= new Date();
				  list.setEntryDate(curentdate);
				  list.setAccountNumber(accounrcr.getAccountnumber()+accountalphabet);
				  CodeCreationadd.setAccountnumber(accounrcr.getAccountnumber()+accountalphabet);
				  list.setAccountBalance(BigDecimal.ZERO);
				  list.setTotalCreditBalance(BigDecimal.ZERO);
				  list.setTotalDebitBalance(BigDecimal.ZERO);
				  Erp_ChartOfAccountsRep.save(list);
				
				msg="Categorycode Added sucessfully";
			}
			
			else if(addtype.equals("subcategorycode")) {
				 
				  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
				  CodeCreation accounrcr=codecreationrep.getheadcodebysubacccount(CodeCreationadd.getHeadCode(),CodeCreationadd.getCategoryCode());
				  
				  String accountcount=Erp_ChartOfAccountsRep.parentcountnumber(accounrcr.getAccountnumber());
				  System.out.println("accountcount"+accountcount);
	
				  list.setParentaccount(accounrcr.getAccountnumber());
				  list.setAccountName(CodeCreationadd.getSubCategoryDescription());
				  list.setAccountType("Asset");
				  list.setOwnership("");
				  Date curentdate= new Date();
				  list.setEntryDate(curentdate);
				  list.setAccountNumber(accounrcr.getAccountnumber()+accountcount);
				  CodeCreationadd.setAccountnumber(accounrcr.getAccountnumber()+accountcount);
				  list.setAccountBalance(BigDecimal.ZERO);
				  list.setTotalCreditBalance(BigDecimal.ZERO);
				  list.setTotalDebitBalance(BigDecimal.ZERO);
				  Erp_ChartOfAccountsRep.save(list);
				msg="subcategorycode Added sucessfully";
			}
			codecreationrep.save(CodeCreationadd);
		return msg;
		
	}

	
	
	public ResponseEntity<String> addcaps(List<CapitalTrans> CapitalTransadd,String loginuserid) {
		ResponseEntity<String> msg = null;
		Session session = sessionFactory.getCurrentSession();
		BigInteger id = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalentrieid AS id")
				.getSingleResult();
		
		int count=CapitalTransadd.size()-1;
		System.out.println("count"+CapitalTransadd.size());
		
		for(CapitalTrans data:CapitalTransadd) {
			BigInteger journalid = (BigInteger) session.createNativeQuery("SELECT NEXT VALUE FOR journalid AS id")
					.getSingleResult();
			data.setId(journalid.toString());
			data.setTran_particulars(CapitalTransadd.get(count).getAccountName());
			count--;
			Erp_ChartOfAccounts list=Erp_ChartOfAccountsRep.findsccount(data.getAccountNumber());
			
				
			
			if(list.getAccountType().equals("Asset")||list.getAccountType().equals("Expense")){
				
				if(data.getCredits()!=null && data.getCredits().compareTo(BigDecimal.ZERO)>0) {
					list.setTotalCreditBalance(list.getTotalCreditBalance().add(data.getCredits()));					
					list.setAccountBalance(list.getAccountBalance().subtract(data.getCredits()));	
					Erp_ChartOfAccountsRep.save(list);
					String parentaccount1 = list.getParentaccount();
					
					while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		           	     
		           	     if (parentchartaccount1 != null) {		           	    	 
		           	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
		   	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(data.getCredits()));
		   		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(data.getCredits()));		           	        
		           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		           	         parentaccount1 = parentchartaccount1.getParentaccount();
		           	     } else {
		           	         break; 
		           	     }
		           	 }
				}
				else if(data.getDebits()!=null && data.getDebits().compareTo(BigDecimal.ZERO)>0) {
					list.setTotalDebitBalance(list.getTotalDebitBalance().add(data.getDebits()));					
					list.setAccountBalance(list.getAccountBalance().add(data.getDebits()));	
					Erp_ChartOfAccountsRep.save(list);
					String parentaccount1 = list.getParentaccount();
					
					while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		           	     
		           	     if (parentchartaccount1 != null) {		           	    	 
		           	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
		   	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(data.getDebits()));
		   		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(data.getDebits()));		           	        
		           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		           	         parentaccount1 = parentchartaccount1.getParentaccount();
		           	     } else {
		           	         break; 
		           	     }
		           	 }
				}
				   
				
			}else if(list.getAccountType().equals("Liability")||list.getAccountType().equals("Income")||list.getAccountType().equals("Equity")) {
				if(data.getCredits()!=null && data.getCredits().compareTo(BigDecimal.ZERO)>0) {
					list.setTotalCreditBalance(list.getTotalCreditBalance().add(data.getCredits()));					
					list.setAccountBalance(list.getAccountBalance().add(data.getCredits()));	
					Erp_ChartOfAccountsRep.save(list);
					String parentaccount1 = list.getParentaccount();
					
					while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		           	     
		           	     if (parentchartaccount1 != null) {		           	    	 
		           	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
		   	            	 parentchartaccount1.setTotalCreditBalance(parentchartaccount1.getTotalCreditBalance().add(data.getCredits()));
		   		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().add(data.getCredits()));		           	        
		           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		           	         parentaccount1 = parentchartaccount1.getParentaccount();
		           	     } else {
		           	         break; 
		           	     }
		           	 }
				}
				else if(data.getDebits()!=null && data.getDebits().compareTo(BigDecimal.ZERO)>0) {
					list.setTotalDebitBalance(list.getTotalDebitBalance().add(data.getDebits()));					
					list.setAccountBalance(list.getAccountBalance().subtract(data.getDebits()));	
					Erp_ChartOfAccountsRep.save(list);
					String parentaccount1 = list.getParentaccount();
					
					while (parentaccount1 != null && !parentaccount1.isEmpty()) {
		           	     Erp_ChartOfAccounts parentchartaccount1 = Erp_ChartOfAccountsRep.findsccount(parentaccount1);
		           	     
		           	     if (parentchartaccount1 != null) {		           	    	 
		           	    	// Erp_ChartOfAccounts parentchartaccount1=  Erp_ChartOfAccountsRep.findsccount(Cgstaccount.getParentaccount());
		   	            	 parentchartaccount1.setTotalDebitBalance(parentchartaccount1.getTotalDebitBalance().add(data.getDebits()));
		   		             parentchartaccount1.setAccountBalance(parentchartaccount1.getAccountBalance().subtract(data.getDebits()));		           	        
		           	         Erp_ChartOfAccountsRep.save(parentchartaccount1);
		           	         parentaccount1 = parentchartaccount1.getParentaccount();
		           	     } else {
		           	         break; 
		           	     }
		           	 }
				}
			}
			List<PO_invoice_entity> po =PO_invoice_reps.getbyid(data.getDescription());
			if(!po.isEmpty()) {
				for(PO_invoice_entity podata:po) {
					podata.setExtra_amount(podata.getExtra_amount().subtract(data.getDebits()));
					podata.setLast_extra_amount(podata.getLast_extra_amount().add(data.getDebits()));
					PO_invoice_reps.save(podata);
				}
				
			}
			CapitalTransRep.save(data);
			
		}
		msg=ResponseEntity.ok("Added sucessfully");
		return msg;
	}
	
	
	

	public BigDecimal get_po_total() {
		
		BigDecimal totalAmount = BigDecimal.ZERO; // Initialize total amount
		Set<String> processedPoIds = new HashSet<>(); // Track unique PO IDs
		List<ACCOUNT_LEDGER_PO_Entity> po_lists = ACCOUNT_LEDGER_PO_rep.findAll();
		for (ACCOUNT_LEDGER_PO_Entity up : po_lists) {
		    String poId = up.getPoId(); // Assuming poId is the unique identifier
		    BigDecimal total = up.getTotalAmount();

		    if (total != null && !processedPoIds.contains(poId)) {
		        totalAmount = totalAmount.add(total); // Add only if PO ID is not already processed
		        processedPoIds.add(poId); // Mark PO ID as processed
		    }
		}
		return totalAmount;
	}
	

	public BigDecimal sale_total() {
		
		BigDecimal totalAmount = BigDecimal.ZERO; // Initialize total amount
		Set<String> processedPoIds = new HashSet<>(); // Track unique PO IDs
		List<ACCOUNT_LEDGER_SALE_Entity> lists_sale =ACCOUNT_LEDGER_SALE_rep.findByAll();
		for (ACCOUNT_LEDGER_SALE_Entity up : lists_sale) {
		    String woId = up.getWoId(); // Assuming poId is the unique identifier
		    BigDecimal total = up.getTotalAmount();

		    if (total != null && !processedPoIds.contains(woId)) {
		        totalAmount = totalAmount.add(total); // Add only if PO ID is not already processed
		        processedPoIds.add(woId); // Mark PO ID as processed
		    }
		}
		return totalAmount;
	}

	@Autowired
	EmployeeSalaryRep EmployeeSalaryRep;
public BigDecimal wages_total() {
		
		BigDecimal totalAmount = BigDecimal.ZERO; // Initialize total amount
		List<EmployeeSalary> list_sal = EmployeeSalaryRep.getlist();

		
		for (EmployeeSalary up : list_sal) {
		    BigDecimal total = up.getNetSalary();

		    if (total != null ) {
		        totalAmount = totalAmount.add(total); // Add only if PO ID is not already processed
		    }
		}
		return totalAmount;
	}

public String addchartofaccount(Erp_ChartOfAccounts list,String loginuserid) {
	String msg="";
	Session session = sessionFactory.getCurrentSession();
	
		if(list.getParentaccount().equals("")) {
			 BigInteger generatedId = (BigInteger) session
			            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
			            .getSingleResult();
		}else {
			  
			  
			  String accountcount=Erp_ChartOfAccountsRep.parentcountnumber(list.getParentaccount());
			  String ending = list.getParentaccount().substring(list.getParentaccount().length()-1);
			
			if (Character.isDigit(ending.charAt(0))) {
				BigInteger accountcount1 = new BigInteger(accountcount);
				  String accountalphabet=bigIntegerToAlphabet(accountcount1);
				  
				list.setAccountNumber(list.getParentaccount()+accountalphabet);
			} else {
				list.setAccountNumber(list.getParentaccount()+accountcount);
			    System.out.println("Ending is a string: " + ending);
	
			}
		}
	
	 
	  if(list.getOpeningbalance()!=null) {
		  list.setAccountBalance(BigDecimal.ZERO);
	  }
	  else {
		  list.setAccountBalance(list.getOpeningbalance());
		  if(list.getParentaccount()!="" &&list.getOpeningbalance()!=null) {
		  Erp_ChartOfAccounts listacount=Erp_ChartOfAccountsRep.findsccount(list.getParentaccount());
		  listacount.setAccountBalance(listacount.getAccountBalance().add(list.getOpeningbalance()));
		  }
		  
	  }
	  list.setAccountBalance(BigDecimal.ZERO);
	  list.setTotalCreditBalance(BigDecimal.ZERO);
	  list.setTotalDebitBalance(BigDecimal.ZERO);
	  
	  Erp_ChartOfAccountsRep.save(list);
		        
        msg="Account Added Successfully.";
     
	return msg;
}

public String addgrouping(String groupname,String vendor_type) {
	Session session = sessionFactory.getCurrentSession();
	if (vendor_type.equals("PurchaseVendor")) {
        

        BigInteger generatedId = (BigInteger) session
	            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
	            .getSingleResult();
	  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
	  
	  list.setAccountName(groupname);
	  list.setAccountType("Liability");
	  list.setParentaccount("ACC00039");
	  list.setOwnership("PurchaseGroup");
	  Date curentdate= new Date();
	  list.setEntryDate(curentdate);
	  list.setAccountNumber("ACC000"+generatedId.toString());
	  list.setAccountBalance(BigDecimal.ZERO);
	  list.setTotalCreditBalance(BigDecimal.ZERO);
	  list.setTotalDebitBalance(BigDecimal.ZERO);
	  
	  Erp_ChartOfAccountsRep.save(list);
	}
    else {
        	        
        BigInteger generatedId = (BigInteger) session
	            .createNativeQuery("SELECT NEXT VALUE FOR chart_of_accounts_seq AS id")
	            .getSingleResult();
	  Erp_ChartOfAccounts list=new Erp_ChartOfAccounts();
	  
	  list.setAccountName(groupname);
	  list.setAccountType("Asset");
	  list.setParentaccount("ACC00042");
	  list.setOwnership("SaleGroup");
	  Date curentdate= new Date();
	  list.setEntryDate(curentdate);
	  list.setAccountNumber("ACC000"+generatedId.toString());
	  list.setAccountBalance(BigDecimal.ZERO);
	  list.setTotalCreditBalance(BigDecimal.ZERO);
	  list.setTotalDebitBalance(BigDecimal.ZERO);
	  
	  Erp_ChartOfAccountsRep.save(list);
       
    }
	
	return "Group Created Successfully";
	
}


public String salein(String woid) {
	
	 List<SalesOut> saleoutdata=salesOutRep.getparticular(woid);
	 for (SalesOut saleoutloop:saleoutdata) {
		 
		 String batch=saleoutloop.getBatch();
		
		 FINISHED_GOODS_ENTITY fg=FINISHED_GOODS_Rep.get_listbyFG_ID(batch);
		 
         BigDecimal qty = saleoutloop.getUsingqty();
         BigDecimal fgqty=fg.getUsingQuantity().add(qty);
         fg.setUsingQuantity(fgqty);
        
         
         FINISHED_GOODS_Rep.save(fg);
                 
         saleoutloop.setReturning_flg("Y");
         salesOutRep.save(saleoutloop);
	 }
	 
	return "Restocked Successfully";
	
}
	
}
