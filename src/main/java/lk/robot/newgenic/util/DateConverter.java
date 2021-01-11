package lk.robot.newgenic.util;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static Time localTimeToSql(LocalTime localtime){
        return Time.valueOf(localtime);
    }

    public static Date localDateToSql(LocalDate localDate){
        return Date.valueOf(localDate);
    }

    public static Date stringToDate(String stringDate){
        return java.sql.Date.valueOf(stringDate);
    }

    public static String localDateToString(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String format = localDate.format(formatter);
        return format;
    }

    public static String localTimeToString(LocalTime localTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
        String format = localTime.format(formatter);
        return format;
    }
}
