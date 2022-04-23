package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.util.HashMap;
import java.util.Map;

public class ParamUtils {
    private static final ISettingsFile testData = new JsonSettingsFile("testuserdata.json");

    public static Map<String, String> returnMapWithAuthParams() {
        Map<String, String> map = new HashMap<>();
        map.put("owner_id", testData.getValue("/ownerId").toString());
        map.put("v", "5.131");
        map.put("access_token", testData.getValue("/token").toString());
        return map;
    }
}
