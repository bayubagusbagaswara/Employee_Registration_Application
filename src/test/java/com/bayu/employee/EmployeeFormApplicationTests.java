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

	@Test
	void stringToLong() {
		String s = "6 Bulan";
		String s1 = "12 Bulan";



//		GetHitungNisbahRs getHitungNisbahRs = calculateEfektifBagiHasil(
//				new BigDecimal(detailDeposito.getDepositoAmount()),
//				Long.valueOf(detailDeposito.getMudTenor().toUpperCase().replace(" BULAN", ""))
//		);

		Long number = Long.valueOf(s.toUpperCase().replace(" BULAN", ""));
		Long number1 = Long.valueOf(s1.toUpperCase().replace(" BULAN", ""));

		System.out.println(number);
		System.out.println(number1);

		BigDecimal itemPrice = new BigDecimal(10000000);
		BigDecimal fee = new BigDecimal(2000);

//		itemCost  = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
//		totalCost = totalCost.add(itemCost);
//		return totalCost;

		BigDecimal multiply = itemPrice.multiply(BigDecimal.valueOf(number));

		System.out.println(multiply);

		BigDecimal total = itemPrice.add(fee);

		System.out.println(total);
	}

	public static String formattedTransactionDescription(String soul, String totalMoney) {
		Integer soulInt = Integer.parseInt(soul);
		StringBuilder resultSoul = new StringBuilder();

		StringBuilder resultTotalMoney = new StringBuilder();
		StringBuilder resultString = new StringBuilder();

		if (soulInt > 0) {
			resultSoul.append(soulInt).append("Jiwa"); // 10Jiwa
		} else {
			resultSoul.append("0").append("Jiwa"); // 0Jiwa
		}

		if (totalMoney.length() <= 6) {
			resultTotalMoney.append("Rp").append(totalMoney, 0, totalMoney.length() - 3).append("Ribu");
		}

		if (totalMoney.length() > 6 ) {
			resultTotalMoney.append("Rp").append(totalMoney, 0, totalMoney.length() - 6).append("Juta");
		}

		// 10JiwaRp450Ribu-ZakatFitrah
		return String.valueOf(resultString.append(resultSoul).append(resultTotalMoney).append("-").append("ZakatFitrah"));
	}

	@Test
	void test() {
		String soul = "10";
		String money = "9000";
		System.out.println(formattedTransactionDescription(soul, money));
	}
}
