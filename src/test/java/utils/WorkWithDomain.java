package utils;

import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class WorkWithDomain {

    private static final String domain = "//div[@class='dropdown__list-item' and contains(text(), '%s')]";

    public static By getDomain(String valueFromTestdata) {
        return By.xpath(String.format(domain, valueFromTestdata));
    }

    public static ILabel getDomainLabel(String valueFromTestdata){
       return getElementFactory().getLabel(getDomain(valueFromTestdata.toLowerCase()),"select domain value");
    }


}
