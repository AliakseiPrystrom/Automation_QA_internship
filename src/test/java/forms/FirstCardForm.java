package forms;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FirstCardForm extends Form {

    private final ITextBox passwordInput = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password input");
    private final ITextBox emailInput = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "email input");
    private final ITextBox domainInput = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain input");
    private final IComboBox otherSelectCB = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__field']"), "other select CB");
    private final ICheckBox licenseCB = getElementFactory().getCheckBox(By.xpath("//span[@class='icon icon-check checkbox__check']"), "acceptLicence");
    private final ILink goNextCard = getElementFactory().getLink(By.xpath("//a[@class='button--secondary']"), "go next card");
    private static final String domain = "//div[@class='dropdown__list-item' and contains(text(), '%s')]";


    public FirstCardForm() {
        super(By.xpath("//div[@class='login-form__container']"), "loginCard");
    }

    public static By getDomain(String valueFromTestdata) {
        return By.xpath(String.format(domain, valueFromTestdata));
    }

    public ILabel getDomainLabel(String valueFromTestdata) {
        return getElementFactory().getLabel(getDomain(valueFromTestdata.toLowerCase()), "select domain value");
    }

    public void clearPasswordFieldAndType(String password) {
        passwordInput.clearAndType(password);
    }

    public void clearEmailFieldAndType(String email) {
        emailInput.clearAndType(email);
    }

    public void clearDomainAndTYpe(String damain) {
        domainInput.clearAndType(damain);
    }

    public void otherSelect() {
        otherSelectCB.click();
    }

    public void otherValueSelect(String domainValue) {

        getDomainLabel(domainValue).click();
    }

    public void useLicenseCheckBox() {
        licenseCB.click();
    }

    public void goNextCard() {
        goNextCard.click();
    }
}
