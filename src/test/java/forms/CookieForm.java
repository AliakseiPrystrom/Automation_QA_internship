package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {

    private final IButton acceptCookieButton = getElementFactory().getButton(By.xpath("//button[@class='button button--solid button--transparent' and contains (@type,'button') and contains(@name,'button')]"), "accept cookie");


    public CookieForm() {
        super(By.className("cookies"), "cookie form");
    }

    public void acceptCookie() {
        acceptCookieButton.click();
    }

}
