package com.bayu.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StringUtilTests {

    private static String convertAmountK(String raw) {
        StringBuilder a = new StringBuilder();
        a.append(raw, 0, raw.length() - 3); // menghilangkan 3 digit paling belakang
        // 150.000 juta
        // 1.500.000 juta length > 6

        StringBuilder b = new StringBuilder();

        if (a.length() <= 6) {
            if (a.length() <= 3) {
                b.append("Rp.")
                        .append(a)
                        .append("k");
            } else {
                b.append("Rp.")
                        .append(a, 0, a.length() - 3)
                        .append(".")
                        .append(a, a.length() - 3, a.length())
                        .append("k");
            }
        } else {
            // a = 1.500.000k
            b
                    .append("Rp.")
                    .append(a, 0, a.length() - 6)
                    .append(".")
                    .append(a,a.length() - 6, a.length() - 3)
                    .append(".")
                    .append(a, a.length() - 3, a.length()) // 000
                    .append("k");
        }

        return String.valueOf(b);
    }

    @Test
    void convertAmountK() {
        String value5 = "950000";
        String resultConvertValue5 = convertAmountK(value5);
        System.out.println(resultConvertValue5);
        assertEquals("Rp.950k", resultConvertValue5);

        // satuan juta (2 juta ...)
        String value = "2500000"; // 2.500k
        String resultConvertValue = convertAmountK(value);
        System.out.println(resultConvertValue);
        assertEquals("Rp.2.500k", resultConvertValue);

        String value1 = "2850000"; // 2.850k
        String resultConvertValue1 = convertAmountK(value1);
        System.out.println(resultConvertValue1);
        assertEquals("Rp.2.850k", resultConvertValue1);

        // puluhan juta (21 juta ...)
        String value2 = "21500000"; // Rp.21.500K
        String resultConvertValue2 = convertAmountK(value2);
        System.out.println(resultConvertValue2);
        assertEquals("Rp.21.500k", resultConvertValue2);

        // ratusan juta (150 juta ....)
        String value3 = "150000000"; // Rp. 150.000k
        String resultConvertValue3 = convertAmountK(value3);
        System.out.println(resultConvertValue3);
        assertEquals("Rp.150.000k", resultConvertValue3);

        // miliar (1 miliar ....)
        String value4 = "1500000000"; // Rp.1.500.000
        String resultConvertValue4 = convertAmountK(value4);
        System.out.println(resultConvertValue4);
        assertEquals("Rp.1.500.000k", resultConvertValue4);

    }
}
