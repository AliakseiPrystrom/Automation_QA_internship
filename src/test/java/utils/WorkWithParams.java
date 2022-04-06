package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class WorkWithParams {
    private static final ISettingsFile environment = new JsonSettingsFile("testdata.json");

    public static String chooseTestParamsForLogin(boolean value) {
        if (value) {
            return environment.getValue("/testLogin").toString();
        }
        return LogPassGenerator.generate();
    }

    public static String chooseTestParamsForPass(boolean value) {
        if (value) {
            return environment.getValue("/testPassword").toString();
        }
        return LogPassGenerator.generate();
    }

}
