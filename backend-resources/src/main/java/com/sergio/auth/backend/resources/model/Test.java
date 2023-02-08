package com.sergio.auth.backend.resources.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Calendar calendar = new GregorianCalendar(2020, 12, random.nextInt(27));
            System.out.println(format1.format(calendar.getTime()));
        }

    }
}
