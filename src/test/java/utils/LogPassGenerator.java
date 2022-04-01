package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.util.Random;

public class LogPassGenerator {
    private static final String DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();
    private static final ISettingsFile configData = new JsonSettingsFile("config.json");

    public static String generate() {
        int length = Integer.parseInt(configData.getValue("/generateTestLogPass").toString());
        StringBuilder sb = new StringBuilder();

        for ( ; length > 0; --length )
            sb.append(DICT.charAt(random.nextInt(DICT.length())));

        return sb.toString();
    }
}
