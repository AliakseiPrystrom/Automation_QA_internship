package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.time.Duration;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//div[@id='profile_wall']"), "my page");
    }

    public boolean checkPost(String value_1, String value_2, String nameLocator) {
        String xPath = String.format("//div[@data-post-id='%s_%s']", value_1, value_2);
        return getElementFactory().getLabel(By.xpath(xPath), nameLocator).state().isExist();
    }

    public boolean checkPhoto(String value_1, String value_2, String nameLocator) {
        String xPath = String.format("//a[@href='/photo%s_%s']", value_1, value_2);
        return getElementFactory().getLabel(By.xpath(xPath), nameLocator).state().isExist();
    }

    public boolean checkTextInLastPost(String text) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@class='wall_post_text zoom_text' and contains(text(),'%s')]", text)), "check last note text").getAttribute("textContent").equals(text);
    }

    public boolean isDataChangeAfterEdit(String editedText, String editedPostId, String userId) {
        return getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, editedPostId)), "check last edited text").getAttribute("textContent").equals(editedText);
    }

    public boolean isCommentAdd(String userId, String commentId) {
        IButton showComment = getElementFactory().getButton(By.xpath("//span[@class='js-replies_next_label']"), "show last comment");
        showComment.click();
        return getElementFactory().getLabel(By.xpath(String.format("//div[@id='wpt%s_%s']", userId, commentId)), "check comment add").state().waitForExist(Duration.ofSeconds(3));
    }

    public void likePost(String idUser,String idPost){
        IButton likeButton = getElementFactory().getButton
                (By.xpath(String.format("//div[@class='like_wrap _like_wall%s_%s ']/descendant::div[@onclick='Reactions.onReactionClick(this, event)']",idUser,idPost)),"like post");
        likeButton.click();
    }


}
