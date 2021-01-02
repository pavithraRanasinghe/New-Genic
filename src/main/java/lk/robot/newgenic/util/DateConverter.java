package lk.robot.newgenic.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    public static Date localTimeToUtil(LocalTime localtime){
        Instant instant = localtime.atDate(LocalDate.now())
                .atZone(ZoneId.systemDefault()).toInstant();
        Date time = Date.from(instant);
        return time;
    }

    public static Date localDateToUtil(LocalDate localDate){
        java.util.Date date = java.sql.Date.valueOf(localDate);
        return date;
    }
}
