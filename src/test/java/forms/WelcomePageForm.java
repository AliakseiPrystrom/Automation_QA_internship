package forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePageForm extends Form {

    private final ILink goGameLink = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "goGameLink");

    public WelcomePageForm() {
        super(By.xpath("//div[@class='logo']"), "Welcome Page");
    }

    public void goGameLink() {
        goGameLink.click();
    }


}
