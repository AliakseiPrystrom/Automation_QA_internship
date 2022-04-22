package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class VkMainPage extends Form {
    private final IButton myPage = getElementFactory().getButton(By.xpath("//li[@id='l_pr']"),"my page");

    public VkMainPage() {
        super(By.xpath("//div[@id='main_feed']"),"main page");
    }

    public void myPageClick(){
        myPage.click();
    }
}
