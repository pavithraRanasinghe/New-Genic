package lk.robot.newgenic.util;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

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
}
