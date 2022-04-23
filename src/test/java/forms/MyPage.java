package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import util.WorkWithPost;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//div[@id='profile_wall']"), "my page");
    }

    public boolean isAddLastWallPost(String value_1, String value_2, String locator, String nameLocator) {
        return WorkWithPost.checkMethodIsElemetnExist(value_1, value_2, locator, nameLocator);
    }

    public boolean checkTextInLastPost(String text) {
        return WorkWithPost.checkTextLastNoteOnWall(text);
    }

    public boolean isDataChangeAfterEdit(String editedText, String editedPostId, String userId) {
        return WorkWithPost.isDataChangeAfterEdit(editedText, editedPostId, userId);
    }

    public boolean isPhotoUpload(String value_1, String value_2, String locator, String nameLocator) {
        return WorkWithPost.checkMethodIsElemetnExist(value_1, value_2, locator, nameLocator);
    }

    public boolean isCommentAdd(String userId, String commentId) {
        return WorkWithPost.isCommentAdd(userId, commentId);
    }


}
