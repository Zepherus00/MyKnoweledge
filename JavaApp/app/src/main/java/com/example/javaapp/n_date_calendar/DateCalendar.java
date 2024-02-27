package com.example.javaapp.n_date_calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalendar {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toString());

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.HOUR));
        System.out.println(cal.get(Calendar.AM_PM));

        cal.set(Calendar.HOUR_OF_DAY, 20);
        cal.add(Calendar.HOUR_OF_DAY, 3);
        cal.add(Calendar.HOUR_OF_DAY, -3);

        SimpleDateFormat sd = new SimpleDateFormat("y");//2020
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy");//2020
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy DD");//2020 335
    }
}
