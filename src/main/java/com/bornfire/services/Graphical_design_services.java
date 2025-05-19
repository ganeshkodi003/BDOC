package com.bornfire.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entities.ERP_EXPENSES_ENTITY;
import com.bornfire.entities.ERP_EXPENSES_REPO;
import com.bornfire.entities.PO_invoice_entity;
import com.bornfire.entities.PO_invoice_rep;
import com.bornfire.entities.SALES_invoice_TABLE;
import com.bornfire.entities.SALES_invoice_TABLERep;

@Service
@Transactional
public class Graphical_design_services {


	@Autowired
	ERP_EXPENSES_REPO eRP_EXPENSES_REPO;
	@Autowired
	SALES_invoice_TABLERep SALES_invoice_TABLERep;
	@Autowired
	PO_invoice_rep PO_invoice_reps;

	
    public List<PO_invoice_entity> po_inv;
    public List<SALES_invoice_TABLE> sale_inv;
    public List<PO_invoice_entity> stock; // from getStockAndDate()
    public List<ERP_EXPENSES_ENTITY> Exp;
    
 // For Graphical Design
    public ResponseEntity<?> getalldesign(String branchId, String vendor_type, String vendor, Date fromDate, Date toDate) {

        Graphical_design_services dto = new Graphical_design_services();

        try {
            System.out.println("Received params - Branch: " + branchId + ", From: " + fromDate + ", To: " + toDate);

            branchId = (branchId != null && branchId.isEmpty()) ? null : branchId;
            vendor_type = (vendor_type != null && vendor_type.isEmpty()) ? null : vendor_type;
            vendor = (vendor != null && vendor.isEmpty()) ? null : vendor;

            if (vendor_type == null && vendor == null && branchId != null  && fromDate != null && toDate != null) {
                System.out.println("Particular date only in a Branch");
                dto.po_inv = PO_invoice_reps.getPOInvTotalAmount(branchId, fromDate, toDate);
                dto.sale_inv = SALES_invoice_TABLERep.getsaleInvTotalAmount(branchId, fromDate, toDate);
                dto.stock = PO_invoice_reps.getStockAndDate(branchId, fromDate, toDate);
                dto.Exp = eRP_EXPENSES_REPO.getAmtDate(branchId, fromDate, toDate);
            } else if ("purchase_vendor".equalsIgnoreCase(vendor_type)  && fromDate != null && toDate != null) {
                System.out.println("Purchase vendor: " + vendor);
                dto.po_inv = PO_invoice_reps.getPOuniq(vendor, branchId, fromDate, toDate);
                //dto.stock = PO_invoice_reps.getStockAndDate(branchId, fromDate, toDate);
                //dto.Exp = eRP_EXPENSES_REPO.getAmtDate(branchId, fromDate, toDate);
            } else if ("sale_vendor".equalsIgnoreCase(vendor_type) && fromDate != null && toDate != null) {
                System.out.println("Sale vendor: " + vendor);
                dto.sale_inv = SALES_invoice_TABLERep.getsaleuniq(vendor, branchId, fromDate, toDate);
                System.out.println("Sale total amount " + dto.sale_inv.get(0).getTotalAmount());
                //dto.stock = PO_invoice_reps.getStockAndDate(branchId, fromDate, toDate);
                //dto.Exp = eRP_EXPENSES_REPO.getAmtDate(branchId, fromDate, toDate);
            } else if (vendor_type != null && vendor != null && branchId != null && fromDate == null && toDate == null) {
                System.out.println("Without date  ");
                if(vendor_type.equalsIgnoreCase("purchase_vendor")) {
                    dto.po_inv = PO_invoice_reps.getPOuniqss(vendor, branchId);
                    dto.stock = PO_invoice_reps.getStockAndDatess(vendor, branchId);
                } else if(vendor_type.equalsIgnoreCase("sale_vendor")) {
                    dto.sale_inv = SALES_invoice_TABLERep.getsaleuniqss(vendor, branchId);
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid filter combination provided.");
            }

            return ResponseEntity.ok(dto);

        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response with an appropriate message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while processing your request.");
        }
    }

    
    
	 /* public List<PO_invoice_entity> getalldesign(String branchId,String vendor_type,String fromDate,String toDate,String vendor,String purchaseId,String saleId) {
	  
	  String s = "No Scan"; String cn1 = ""; if (!recNo.equals("")) { // FOR ONLY
	  RECEIPT_NO cn1 = " RECEIPT_NO='" + recNo +
	  "' AND  UPPER(BLOOD_TEST) = 'BLOOD TEST'";
	  
	  } else if (!payDate.equals("")) { // FOR DOB NOT NULL cn1 =
	  "  PAYMENT_DATE = '" + payDate + "' AND  UPPER(BLOOD_TEST) = 'BLOOD TEST'"; }
	  else if (!payMode.equals("")) { // FOR AGE NOT NULL cn1 = "PAYMENT_MODE='" +
	  payMode + "' AND  UPPER(BLOOD_TEST) = 'BLOOD TEST'"; } else if
	  (!payerName.equals("")) { // FOR DOB NOT NULL cn1 =
	  "UPPER(PAYER_NAME) LIKE '%" + payerName.toUpperCase() +
	  "%'  UPPER(BLOOD_TEST) = 'BLOOD TEST'"; }
	  
	  System.out.println(cn1);
	  
	  @SuppressWarnings("unchecked") List<PO_invoice_entity> list =
	  (List<PO_invoice_entity>) sessionFactory.getCurrentSession()
	  .createQuery(" from PO_INVOICE_TABLE where " + cn1).getResultList();
	  
	  
	  return list;
	  
	  
	  }
	 */

}
