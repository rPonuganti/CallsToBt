package com.rs.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
        private final static String TAG ="DateUtils";

        public static String formatDate(String format, Date date){
                SimpleDateFormat df = new SimpleDateFormat(format, java.util.Locale.getDefault());
                return df.format(date);
        }
        public static Date dateFromString(String inputFormat, String inputDate){
                Date date = null;
                SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
                try {
                        date = df_input.parse(inputDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                return date;
        }
}
