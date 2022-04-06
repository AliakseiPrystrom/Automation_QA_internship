package forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GamePageForm extends Form {

    private final ILabel timer = getElementFactory().getLabel(By.xpath("//div[@class='timer timer--white timer--center']"), "timer");

    public GamePageForm() {
        super(By.xpath("//div[@class='logo']"), "Game Page");
    }

    public String getTimerValue(){
        return timer.getAttribute("innerText");
    }

}
