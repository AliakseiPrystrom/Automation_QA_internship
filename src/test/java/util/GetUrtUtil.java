package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class GetUrtUtil {
    private static final ISettingsFile environment = new JsonSettingsFile("url.json");

    public static String getCurrentURL(String req) {
        return String.format(environment.getValue("/ApiPath").toString() + "%s", req);
    }
}
