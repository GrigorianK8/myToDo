package com.example.mytodo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("dd-MM-yyyy HH:mm");


    public static Date stringToDate(String dateStr) throws ParseException {
        try {
            return SDF_TIME.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Error parsing date:" + e.getMessage());
            throw e;
        }
    }

    public static String dateToString(Date date) {
        return SDF.format(date);
    }

    public static boolean isSameDay(Date day1, Date day2) {
        return SDF.format(day1).equals(SDF.format(day2));
    }
}
