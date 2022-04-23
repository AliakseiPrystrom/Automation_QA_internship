package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkLoginRegPage extends Form {
    private final IButton signIn = getElementFactory().getButton(By.xpath("//span[@class='FlatButton__content' and contains(text(),'Sign in')]"), "Sign In button");

    public VkLoginRegPage() {
        super(By.xpath("//div[@id='global_prg']"), "loginPage");
    }

    public void signInClick() {
        signIn.click();
    }


}
