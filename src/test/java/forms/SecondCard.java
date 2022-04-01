package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.WorkWithCB;
import utils.WorkWithUpload;

import java.util.List;

public class SecondCard extends Form {

    private final List<ICheckBox> listInterests = getElementFactory().findElements(By.xpath("//span[@class='checkbox__box']"), ElementType.CHECKBOX);
    private final ITextBox uploadFile = getElementFactory().getTextBox(By.xpath("//a[@class='avatar-and-interests__upload-button']"),"upload");
    private final IButton goNext = getElementFactory().getButton(By.xpath("//button[@name='button' and text()='Next']"),"go next");

    public SecondCard() {
        super(By.xpath("//div[@class='help-form__container']"), "meCard");
    }

    public void resetAllCB() {
        WorkWithCB.dropCB(listInterests);
    }

    public void selectCB(int randomNumber) {
        WorkWithCB.selectCB(listInterests,randomNumber);
    }

    public void clickUpload(){
        uploadFile.click();
    }

    public void uploadFile(String nameFile){
        WorkWithUpload.uploadFile(nameFile);
    }

    public void goNextCard(){
        goNext.click();
    }


}
