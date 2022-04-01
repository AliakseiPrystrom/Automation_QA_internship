package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {

    private final IButton acceptCookie = getElementFactory().getButton(By.xpath("//button[@class='button button--solid button--transparent']"), "accept cookie");


    public CookieForm() {
        super(By.xpath("//div[@class='cookies']"), "cookie form");
    }

    public void acceptCookie() {
        acceptCookie.click();
    }

}
