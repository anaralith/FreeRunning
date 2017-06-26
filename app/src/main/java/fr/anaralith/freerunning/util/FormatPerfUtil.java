package fr.anaralith.freerunning.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by CMARTIN on 26/06/2017.
 */

public class FormatPerfUtil {

    public static String formatRythme(double rythme){

        int second = (int)rythme%60;
        int minute = (int)(rythme-second)/60;

        return "" + minute + "\' " + second + "\" /Km";
    }

    public static String formatDate(String date){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(date));

        SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.getDefault());

        return format.format(c.getTime());
    }

    public static String formatDistance(double distance){
        return "" + (double)Math.round(distance) + " Km";
    }
}
