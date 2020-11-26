package team.noname.trumpvis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class String2Date {
    public static Date str2YM(String source){
        Date target = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            target = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(target);
        int month = cal.get(Calendar.MONTH);
        if(month == 12){
            cal.set(Calendar.MONTH, 1);
        }else cal.set(Calendar.MONTH, month + 1);
        target = cal.getTime();
        return target;
    }

    public static Date str2YMd(String source){
        Date target = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            target = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return target;
    }
}
