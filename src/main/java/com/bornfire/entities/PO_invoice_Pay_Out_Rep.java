


package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PO_invoice_Pay_Out_Rep  extends JpaRepository<PO_invoice_Pay_Out_entity,String> {
	

	@Query(value = " WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM PO_INVOICE_TABLE_PAY_OUT WHERE approved_flg='S')SELECT* FROM CTE WHERE ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
	List<PO_invoice_Pay_Out_entity> getPOlistbyapprovalnewPPAYOuT();

   @Query(value = "	WITH DuplicateCheck AS (\r\n" + 
   		"    SELECT \r\n" + 
   		"        *, -- Includes all related columns\r\n" + 
   		"        ROW_NUMBER() OVER (PARTITION BY PO_ID, PAID_AMOUNT ORDER BY PO_ID) AS RowNum\r\n" + 
   		"    FROM \r\n" + 
   		"        PO_INVOICE_TABLE_PAY_OUT\r\n" + 
   		")\r\n" + 
   		"SELECT \r\n" + 
   		"    *\r\n" + 
   		"FROM \r\n" + 
   		"    DuplicateCheck\r\n" + 
   		"WHERE \r\n" + 
   		"    RowNum = 1;", nativeQuery = true)
    List<PO_invoice_Pay_Out_entity> getbyid();


    @Query(value = "SELECT TOP 1 * FROM PO_INVOICE_TABLE_PAY_OUT WHERE CAST(PO_ID AS VARCHAR) = CAST(buy_Amount AS VARCHAR)", nativeQuery = true)
    Optional<PO_invoice_Pay_Out_entity> getSingleRowWherePoIdEqualsBuyAmount();


    @Query(value = "SELECT * FROM PO_INVOICE_TABLE_PAY_OUT WHERE PO_ID = ?1", nativeQuery = true)
     List<PO_invoice_Pay_Out_entity> getbyido(String poId);
     
     
     @Query(value = "WITH DuplicateCheck AS ( " +
             "    SELECT *, " +
             "           ROW_NUMBER() OVER (PARTITION BY PO_ID, PAID_AMOUNT ORDER BY PAID_AMOUNT DESC, PO_ID DESC) AS RowNum " +
             "    FROM PO_INVOICE_TABLE_PAY_OUT " +
             "    WHERE BRANCH_ID = ?1 " +
             ") " +
             "SELECT * FROM DuplicateCheck WHERE RowNum = 1",
     nativeQuery = true)
List<PO_invoice_Pay_Out_entity> getbranch_POHis(String BRANCH_ID);

     
    
    /* @Query(value = "SELECT * FROM hrms.PO_INVOICE_TABLE WHERE VENDOR_ID = ?1", nativeQuery = true)
    List<PO_invoice_entity>  vendorid(String vendorId);


    @Query(value = "SELECT cast(count(DISTINCT po_id ) as INT) FROM hrms.PO_INVOICE_TABLE", nativeQuery = true)
    int getPOcount();
    
    @Query(value = "SELECT * FROM hrms.PO_INVOICE_TABLE WHERE ITEM_CODE = ?1", nativeQuery = true)
    List<PO_invoice_entity>  getdetailbatch(String itemCode);
    
    @Query(value = "select count(*) from HRMS.PO_INVOICE_TABLE", nativeQuery = true)
    Integer getdetails();
    
    @Query(value = "select count(*) from HRMS.PO_INVOICE_TABLE where Assetcategory='Raw Materials' ", nativeQuery = true)
    Integer getdetailsRAWMATERIALS();
    
    @Query(value = "	select count(*) from HRMS.PO_INVOICE_TABLE where Assetcategory='Packing Materials' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsPACKING();
    
    @Query(value = "	select count(*) from HRMS.PO_INVOICE_TABLE where Assetcategory='Finished Goods' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsfinished();
    
    @Query(value = "select count(*) from HRMS.PO_INVOICE_TABLE where Assetcategory='Storesandspares' and approved_flg = 'S' ", nativeQuery = true)
    Integer getdetailsSS();

    @Query(value = "SELECT * FROM hrms.PO_INVOICE_TABLE WHERE ITEM = ?1", nativeQuery = true)
    List<PO_invoice_entity>  getbatchonly(String ITEM);
    
    @Query(value = "SELECT DISTINCT ITEM FROM hrms.PO_INVOICE_TABLE", nativeQuery = true)
    List<String> getItems();

    @Query(value = "SELECT * FROM hrms.PO_INVOICE_TABLE WHERE batch = ?1", nativeQuery = true)
    PO_invoice_entity  get_twos(String batch);
    
    
	@Query(value = "WITH CTE AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY po_id ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC) AS row_num FROM hrms.PO_INVOICE_TABLE WHERE approved_flg = 'S') SELECT * FROM CTE WHERE row_num = 1 ORDER BY CAST(SUBSTRING(po_id, PATINDEX('%[0-9]%', po_id), LEN(po_id)) AS BIGINT) DESC, po_id DESC", nativeQuery = true)
    List<PO_invoice_entity> getPOlistbyapprovalnew();*/
	
}