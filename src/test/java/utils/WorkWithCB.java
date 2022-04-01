package utils;

import aquality.selenium.elements.interfaces.ICheckBox;

import java.util.List;
import java.util.Random;

public class WorkWithCB {

    public static void dropCB(List<ICheckBox> list) {
        for (ICheckBox iCheckBox : list) {
            iCheckBox.click();
        }
    }

    public static void selectCB(List<ICheckBox> list, int randomNumber) {
        Random random = new Random();
        for (int i = 0; i < randomNumber; i++) {
            list.get(random.nextInt(list.size())).click();
        }
    }
}
