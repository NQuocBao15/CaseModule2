package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtils {
    private static final String YEAR_PATTERN_FORMAT = "yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String dateToString(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static LocalDate parseDate(String sDate) {
        try {
            return LocalDate.parse(sDate, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi định dạng");
        }
        return null;
    }

    public static String dateToStringYear(LocalDate date) {
        return dateToStringYear(date, null);
    }

    public static String dateToStringYear(LocalDate date, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : YEAR_PATTERN_FORMAT);
        return formatter.format(date);
    }
}
