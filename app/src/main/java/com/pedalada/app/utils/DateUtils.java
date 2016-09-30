package com.pedalada.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE dd MMM yyyy, HH:mm", Locale
            .getDefault());

    public static String formatDate(Date date) {

        return DATE_FORMAT.format(date);
    }
}
