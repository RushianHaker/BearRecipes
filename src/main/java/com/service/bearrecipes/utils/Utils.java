package com.service.bearrecipes.utils;

import jakarta.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Utils {
    public static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    public static final String TIME_ZONE_GMT3 = "GMT+3";

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT;

    static {
        SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
        SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_GMT3));
    }

    @Nullable
    public static Date parseDate(@Nullable String date) {
        if (date == null) {
            return null;
        }

        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
