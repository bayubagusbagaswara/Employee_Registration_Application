package com.bayu.employee.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    private static final String PATTERN_FORMAT = "dd-MM-yyyy";

    public static String formattedInstantToString(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.of("Asia/Jakarta"));
        return formatter.format(instant);
    }
}
