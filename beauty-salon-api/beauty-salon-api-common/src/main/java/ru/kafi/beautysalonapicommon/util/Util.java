package ru.kafi.beautysalonapicommon.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss");

    public static LocalDateTime toLocalDateTime(String value) {
        return LocalDateTime.parse(value, dtf);
    }

    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static Timestamp toTimestamp(String value) {
        return toTimestamp(toLocalDateTime(value));
    }
}
