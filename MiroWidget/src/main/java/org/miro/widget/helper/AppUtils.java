package org.miro.widget.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AppUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String currentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    private AppUtils() {

    }
}
