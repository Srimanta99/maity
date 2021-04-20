package com.maity.maityspositiveliving.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utills {


        public static String dayName(String inputDate, String format){
            Date date = null;
            try {
                date = new SimpleDateFormat(format).parse(inputDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
        }

}
