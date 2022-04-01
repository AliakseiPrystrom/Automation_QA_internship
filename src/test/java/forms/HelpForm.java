package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IButton hideForm = getElementFactory().getButton(By.xpath("//button[@class='button button--solid button--blue help-form__send-to-bottom-button']"),"hide form");
    private final ILabel isHidden = getElementFactory().getLabel(By.xpath("//div[@class='help-form is-hidden']"),"form is hidden");
    public HelpForm() {
        super(By.xpath("class='help-form__container'"), "help form");
    }

    public void hideHelpForm(){
        hideForm.click();
    }

    public boolean isHidden(){
        return isHidden.state().isExist();
    }

}
