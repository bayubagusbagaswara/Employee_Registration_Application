package com.bayu.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
