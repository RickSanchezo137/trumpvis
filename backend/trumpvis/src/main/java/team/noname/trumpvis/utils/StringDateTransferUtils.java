package team.noname.trumpvis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringDateTransferUtils {
    public static Date str2date_YM(String source, int tag){
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
        if(month == 12 && tag == 1){
            cal.set(Calendar.MONTH, 1);
        }else cal.set(Calendar.MONTH, month + tag);
        target = cal.getTime();
        return target;
    }

    public static Date str2date_YMD(String source){
        Date target = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            target = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return target;
    }

    public static String date2str_YM(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String YM = sdf.format(date);
        return YM;
    }
    public static String date2str_YMD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String YMD = sdf.format(date);
        return YMD;
    }
    public static Date addMonthFromStr(String datetime, int i){
        Date date = StringDateTransferUtils.str2date_YM(datetime, 0);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, i);
        Date addedDate = rightNow.getTime();
        return addedDate;
    }

    public static Date addDayFromStr(String datetime, int i){
        Date date = StringDateTransferUtils.str2date_YM(datetime, 0);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, i);
        Date addedDate = rightNow.getTime();
        return addedDate;
    }

    public static int getDateYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDateMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getDateDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }


}
