package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import util.CheckLastPostOnWall;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//div[@id='profile_wall']"), "my page");
    }

    public boolean isAddLastWallPost(String userId, String returnedLastId) {
        return CheckLastPostOnWall.isAddLastWallPost(userId, returnedLastId).state().isExist();
    }

    public boolean checkTextInLastPost(String text) {
        return CheckLastPostOnWall.checkTextLastNoteOnWall(text);
    }

    public boolean isDataChangeAfterEditOrAdd(String editedText, String editedPostId, String userId) {
        return CheckLastPostOnWall.isDataChangeAfterEditOrAdd(editedText, editedPostId, userId);
    }

    public boolean isPhotoUpload(String userId, String photoId) {
        return CheckLastPostOnWall.isPhotoUpload(userId, photoId);
    }

    public boolean isCommentAdd(String userId, String commentId){
        return CheckLastPostOnWall.isCommentAdd(userId,commentId);
    }


}
