package utils;

import aquality.selenium.elements.interfaces.ILabel;

public class WorkWithTimer {
    public static boolean checkTimer(ILabel timer){
        String getValueFromPage = timer.getAttribute("innerText");
        String compareValue = "00:00:00";
        System.out.println(getValueFromPage);
        return getValueFromPage.equals(compareValue);
    }
}
