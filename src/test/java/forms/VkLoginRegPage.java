package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkLoginRegPage extends Form {
    private final IButton signIn = getElementFactory().getButton(By.xpath("//span[@class='FlatButton__content' and contains(text(),'Sign in')]"),"Sign In button");
    private final IButton signUp = getElementFactory().getButton(By.xpath("//span[@class='FlatButton__content' and contains(text(),'Sign up')]"),"Sign Up button");

    public VkLoginRegPage() {
        super(By.xpath("//div[@id='global_prg']"),"loginPage");
    }

    public void signInClick(){
        signIn.click();
    }
    public void signUpClick(){
        signUp.click();
    }

}
