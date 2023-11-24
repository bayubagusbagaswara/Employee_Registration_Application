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

    private static String convertAmount1(String raw) {
        StringBuilder a = new StringBuilder();
        if (raw.length() <= 6) {
            if (raw.length() <= 3) {
                a.append("Rp.")
                        .append(raw, 0, raw.length() -3)
                        .append("k");
            } else {
                a.append("Rp.")
                        .append(a, 0, a.length() - 3)
                        .append(".")
                        .append(a, a.length() - 3, a.length())
                        .append("k");
            }
        } else {
            // a = 1.500.000k
            a
                    .append("Rp.")
                    .append(a, 0, a.length() - 6)
                    .append(".")
                    .append(a,a.length() - 6, a.length() - 3)
                    .append(".")
                    .append(a, a.length() - 3, a.length()) // 000
                    .append("k");
        }

        return String.valueOf(a);

    }

    private static String convertAmount2(String raw) {
        StringBuilder a = new StringBuilder();
        return String.valueOf(a);
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

    @Test
    void testAmount() {
        String amount = "560500000";

        StringBuilder a = new StringBuilder();

        if (amount.length() <= 6) {
            a.append("Rp.")
                    .append(amount, 0, amount.length() - 3) // amount.length=4, String dimulai dari 0
                    .append(".")
                    .append(amount, amount.length()-3, amount.length());
        } else {
          a.append("Rp.")
                  .append(amount, 0, amount.length() - 6)
                    .append(".")
                    .append(amount,amount.length() - 6, amount.length() - 3)
                    .append(".")
                    .append(amount, amount.length() - 3, amount.length());
        }
        System.out.println(a);
    }

    @Test
    void test() {
        StringBuilder a = new StringBuilder();
        a.append("BerbagiBerbukadanSahur"); //22

        System.out.println(a.length());
    }

//    @Test
//    void convertAmount1() {
//        String amount1 = "1000";
//        String result1 = convertAmount1(amount1);
//        System.out.println(result1);
//        assertEquals("Rp.1.000",result1);
//
//        String amount2 = "1500";
//        String result2 = convertAmount1(amount2);
//        System.out.println(result2);
//        assertEquals("Rp.1.500", result2);
//
//        String amount3 = "57000";
//        String result3 = convertAmount1(amount3);
//        System.out.println(result3);
//        assertEquals("Rp.57.000", result3);
//
//        String amount4 = "155000";
//        String result4 = convertAmount1(amount4);
//        System.out.println(result4);
//        assertEquals("Rp.155.000", result4);
//
//        String amount5 = "170500";
//        String result5 = convertAmount1(amount5);
//        System.out.println(result5);
//        assertEquals("Rp.170.500", result5);
//
//        String amount6 = "1205000";
//        String result6 = convertAmount1(amount6);
//        System.out.println(result6);
//        assertEquals("Rp.1.205.000", result6);
//
//        String amount7 = "15850000";
//        String result7 = convertAmount1(amount7);
//        System.out.println(result7);
//        assertEquals("Rp.15.850.000", result7);
//
//        String amount8 = "100250000";
//        String result8 = convertAmount1(amount8);
//        System.out.println(result8);
//        assertEquals("Rp.100.250.000", result8);
//    }

//    @Test
//    void convertAmount2() {
//        String amount1 = "1000";
//        String result1 = convertAmount2(amount1);
//        assertEquals("Rp1.000", result1);
//    }

    public static String upperCaseWords(String sentence) {
        if(sentence.isEmpty() || sentence.isBlank()){
            return "";
        } else if (sentence==null) {
            return "";
        }
        String words[] = sentence.replaceAll("\\s+", " ").trim().split(" ");
        String newSentence = "";
        if(sentence!=null) {
            for (String word : words) {
                for (int i = 0; i < word.length(); i++)
                    newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase() :
                            (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() : word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
            }
            newSentence = newSentence.substring(0,newSentence.length()-1);
        }
        return newSentence;
    }

    @Test
    void testUpperCaseWords() {
        String name = "bayu bagus bagaswara";

        String result = upperCaseWords(name);

        System.out.println(result);
    }

//    String words[] = str.split("\\s+");
//
//      for(String word: words)
//    {
//        firstchr = word.substring(0, 1);
//        firstchr = firstchr.toUpperCase();
//        remchrs = word.substring(1);
//        newstr = newstr + firstchr + remchrs + " ";
//    }

    public static String capitalizeSentences(String sentences) {
        String firstCharacter;
        String remchrs;
        StringBuilder newstr = new StringBuilder();
        String[] words = sentences.split("\\s+");

        for(String word: words)
        {
            firstCharacter = word.substring(0, 1);
            firstCharacter = firstCharacter.toUpperCase();
            remchrs = word.substring(1);
            newstr.append(firstCharacter).append(remchrs).append(" ");
        }
        return String.valueOf(newstr);
    }

    @Test
    void testCapitalize() {
        String name = "bayu bagus bagaswara";
        String result = capitalizeSentences(name);
        System.out.println(result);
    }

    public static String capitalize(String str) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i - 1) == ' ') {
                word.append(Character.toUpperCase(str.charAt(i)));
            }
            else {
                word.append(str.charAt(i));
            }
        }
        return word.toString();
    }

    @Test
    void testCap() {
        String name = "bayu bagus bagaswara";
        String capitalize = capitalize(name);
        System.out.println(capitalize);
        assertEquals("Bayu Bagus Bagaswara", capitalize);

        String name1 = "bagaswara";
        String capitalize1 = capitalize(name1);
        System.out.println(capitalize1);
        assertEquals("Bagaswara", capitalize1);
    }
}
