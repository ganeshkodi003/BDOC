package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BHMSPaymentTableRep extends JpaRepository<BHMSPaymentTable, String>{
	
	
	@Query(value = "SELECT SUM (amount) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE =?1 GROUP BY  payment_date", nativeQuery = true)
    public BigDecimal regSum(String regDate);
	
	@Query(value = " SELECT ISNULL(SUM(amount),0) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE >= ?1 And PAYMENT_DATE <=?2 GROUP BY  payment_date", nativeQuery = true)
    public List<Object[]> regSumBetweenDates(String Start, String End);
	
	@Query(value = "SELECT  SUM(blood_test_amount) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE =?1 GROUP BY  payment_date", nativeQuery = true)
	public BigDecimal bloodSum(String regDate);
	
	@Query(value = "SELECT  SUM (scan_amount) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE =?1 GROUP BY  payment_date", nativeQuery = true)
	public BigDecimal scanSum(String regDate);
	
	@Query(value = "SELECT  SUM(total_bill_amt) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE =?1 GROUP BY  payment_date", nativeQuery = true)
	public BigDecimal dressingSum(String regDate);
	
	
	@Query(value = "SELECT  ISNULL(SUM(blood_test_amount),0) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE >= ?1 And PAYMENT_DATE <=?2 GROUP BY  payment_date", nativeQuery = true)
	public List<Object[]> bloodSumBetweenDates(String Start, String End);
	
	@Query(value = "SELECT  ISNULL(SUM(scan_amount),0) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE >= ?1 And PAYMENT_DATE <=?2 GROUP BY  payment_date", nativeQuery = true)
	public List<Object[]> scanSumBetweenDates(String Start, String End);
	
	@Query(value = "SELECT  ISNULL(SUM(total_bill_amt),0) AS amount FROM BHMSPaymentTable  WHERE  PAYMENT_DATE >= ?1 And PAYMENT_DATE <=?2 GROUP BY  payment_date", nativeQuery = true)
	public List<Object[]> dressingSumBetweenDates(String Start, String End);

	
}