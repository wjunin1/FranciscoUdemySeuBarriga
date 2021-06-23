package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHora {
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeHH() {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeMM() {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeHHmm() {
        DateFormat dateFormat = new SimpleDateFormat("HHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeMMss() {
        DateFormat dateFormat = new SimpleDateFormat("MMss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
