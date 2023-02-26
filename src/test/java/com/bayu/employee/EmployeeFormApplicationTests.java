package com.bayu.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class EmployeeFormApplicationTests {

	@Test
	void contextLoads() {
		Date date = new Date();
		System.out.println("Original Date: "+date);

		// Specify format as "yyyy-MM-dd"
//		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");

		// Use format method on SimpleDateFormat
		String formattedDateStr = dmyFormat.format(date);
		System.out.println("Formatted Date in String format: "+formattedDateStr);
	}

	@Test
	void currencyBigDecimalToString() {
//		public static String format(BigDecimal num) {
//			java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
//			return df.format(num);
//		}
		BigDecimal num = new BigDecimal(5000000);
		DecimalFormat df = new DecimalFormat();

		String format = df.format(num);

		System.out.println(format); // 5.000.000
	}

	@Test
	void currencyStringToBigDecimal() {
		String num = "5000000";

		BigDecimal bigDecimal = new BigDecimal(num);

		System.out.println(bigDecimal); // 5000000
	}
}
