package org.miro.widget.helper;

import java.util.UUID;

public class AppUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    private AppUtils() {

    }
}
