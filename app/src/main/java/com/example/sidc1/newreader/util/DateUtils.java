package com.example.sidc1.newreader.util;

import android.content.Context;

import com.google.firebase.crash.FirebaseCrash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sidc1 on 15-10-2017.
 */

public class DateUtils {
    private Context mContext;
    private static DateUtils dateUtils;

    public DateUtils(Context pContext) {
        this.mContext = pContext;
    }

    public static DateUtils getInstance(Context pContext){
        if(dateUtils == null){
            dateUtils = new DateUtils(pContext);
        }
        return dateUtils;
    }

    public String formatNewsApiDate(Object inputDate){
        if (inputDate == null) {
            return null;
        }
        try{
            String inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            String oututDateFormat = "EEE, dd MMM yyyy KK:mm";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(oututDateFormat);

            Date date = inputFormat.parse(String.valueOf(inputDate));
            return outputFormat.format(date);
        }catch (ParseException e){
            e.printStackTrace();
            FirebaseCrash.report(e);
        }
        return String.valueOf(inputDate);
    }
}
