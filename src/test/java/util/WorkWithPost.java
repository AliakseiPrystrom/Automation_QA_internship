package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

import java.time.Duration;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class WorkWithPost {
    private static final ISettingsFile testData = new JsonSettingsFile("testuserdata.json");

    public static boolean checkMethodIsElemetnExist(String value_1, String value_2, String locator, String nameLocator) {
        String xPath = String.format(locator, value_1, value_2);
        return getElementFactory().getLabel(By.xpath(xPath), nameLocator).state().isExist();
    }


    public static boolean checkTextLastNoteOnWall(String text) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@class='wall_post_text zoom_text' and contains(text(),'%s')]", text)), "check last note text").getAttribute("textContent").equals(text);
    }

    public static boolean isDataChangeAfterEdit(String editedText, String editedPostId, String userId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, editedPostId)), "check last edited text").getAttribute("textContent").equals(editedText);
    }

    public static boolean isCommentAdd(String userId, String commentId) {
        IButton showComment = getElementFactory().getButton(By.xpath("//span[@class='js-replies_next_label']"), "show last comment");
        showComment.click();
        return getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, commentId)), "check comment add").state().waitForExist(Duration.ofSeconds(3));
    }

    public static boolean checkLikeFromUser(String countLikes, String userId) {
        String testUser = testData.getValue("/ownerId").toString();
        return testUser.equals(userId) || Integer.parseInt(countLikes) > 0;
    }

    public static boolean isPostDeleted(String responseFromDeleteApi) {
        return Integer.parseInt(responseFromDeleteApi) == 1;
    }

}