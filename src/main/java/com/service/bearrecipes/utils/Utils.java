package com.service.bearrecipes.utils;

import jakarta.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Utils {
    public static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";
    public static final String TIME_ZONE_GMT3 = "GMT+3";
    public static final SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT3));
    }

    @Nullable
    public static Date parseDate(@Nullable String date) {
        if (date == null) return null;

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
