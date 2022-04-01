package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class WorkWithParams {
    private static final ISettingsFile environment = new JsonSettingsFile("testdata.json");

    private static final String valueFromCI = System.getProperty("isParamTests");

    public static String chooseTestParamsForLogin() {
        if ("true".equals(valueFromCI)) {
            return environment.getValue("/testLogin").toString();
        }return LogPassGenerator.generate();
    }

    public static String chooseTestParamsForPassword() {
        if ("true".equals(valueFromCI)) {
            return environment.getValue("/testPassword").toString();
        }return LogPassGenerator.generate();
    }




}

