package utils;

import aquality.selenium.elements.interfaces.IElement;

import java.util.List;
import java.util.Random;

public class WorkWithCB {

    public static void clickAllElementsFromList(List<IElement> list) {
        for (IElement element : list) {
            element.click();
        }
    }

    public static void selectCB(List<IElement> list, int randomNumber) {
        Random random = new Random();
        for (int i = 0; i < randomNumber; i++) {
            list.get(random.nextInt(list.size())).click();
        }
    }
}
