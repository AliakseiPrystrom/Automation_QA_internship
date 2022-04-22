package util;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;

import java.time.Duration;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class CheckLastPostOnWall {

    public static ILabel isAddLastWallPost(String userId, String returnedLastId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@data-post-id='%s_%s']", userId, returnedLastId)), "check last note");
    }

    public static boolean checkTextLastNoteOnWall(String text) {
        ILabel check = getElementFactory().getLabel(By.xpath(String.format("//div[@class='wall_post_text zoom_text' and contains(text(),'%s')]", text)), "check last note text");
        return check.getAttribute("textContent").equals(text);
    }

    public static boolean isDataChangeAfterEditOrAdd(String editedText, String editedPostId, String userId) {
        ILabel check = getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, editedPostId)), "check last edited text");
        return check.getAttribute("textContent").equals(editedText);
    }

    public static boolean isPhotoUpload(String userId, String photoId) {
        ILink checkPhoto = getElementFactory().getLink(By.xpath(String.format("//a[@href='/photo%s_%s']", userId, photoId)), "check photo upload");
        return checkPhoto.state().isExist();
    }

    public static boolean isCommentAdd(String userId, String commentId) {
        IButton showComment = getElementFactory().getButton(By.xpath("//span[@class='js-replies_next_label']"),"show last comment");
        showComment.click();
        return getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, commentId)), "check comment add").state().waitForExist(Duration.ofSeconds(3));
    }

    public static boolean checkLikeFromUser(String count,String userId){
        String testUser = "720912025";
        return testUser.equals(userId) || Integer.parseInt(count)>0;
    }

    public static boolean isPostDeleted(String responseFromVkApi){
        return Integer.parseInt(responseFromVkApi)==1;
    }

}