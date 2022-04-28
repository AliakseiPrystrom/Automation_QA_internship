package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthPage extends Form {
    private final ITextBox loginInput = getElementFactory().getTextBox(By.xpath("//input[@name='login']"), "login input");
    private final IButton submitLogin = getElementFactory().getButton(By.xpath("//div[@class='vkc__Button__title']"), "submit Auth");
    private final ITextBox passwordInput = getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "password input");
    private final IButton submitPassword = getElementFactory().getButton(By.xpath("//div[@class='vkc__Button__title']"), "submit Auth");


    public AuthPage() {
        super(By.xpath("//div[@class='vkc__Auth__pageBox']"), "authPAge");
    }


    public void inputLogin(String login) {
        loginInput.clearAndType(login);

    }

    public void afterLoginSubmit() {
        submitLogin.click();
    }

    public void inputPassword(String password) {
        passwordInput.clearAndType(password);

    }

    public void afterPasswordSubmit() {
        submitPassword.click();
    }

}
