package it.max.android.domusalberti.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class DatabaseUtils {
    private InternetUtils internetUtils = null;

    private Properties properties = null;

    public DatabaseUtils(Properties properties) {
        this.properties = properties;

        internetUtils = new InternetUtils(properties);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
