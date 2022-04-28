package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import enums.ApiMethods;
import enums.Params;
import forms.AuthPage;
import forms.MyPage;
import forms.VkLoginRegPage;
import forms.VkMainPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.*;

import java.io.*;
import java.util.Map;

public class VkApiTest extends BaseTest {
    private final ISettingsFile testData = new JsonSettingsFile("testuserdata.json");

    @Test
    public void vkApiTest() {
        String randomText = RandomGenerationUtil.generate();
        String userId = testData.getValue("/ownerId").toString();

        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();

        VkLoginRegPage vkLogRegPage = new VkLoginRegPage();
        MyPage userPage = new MyPage();
        AuthPage authPage = new AuthPage();
        VkMainPage vkMainPage = new VkMainPage();

        vkLogRegPage.signInClick();
        authPage.inputLogin(testData.getValue("/login").toString());
        authPage.afterLoginSubmit();
        authPage.inputPassword(testData.getValue("/password").toString());
        authPage.afterPasswordSubmit();
        browser.waitForPageToLoad();
        vkMainPage.myPageClick();


        Map<String, String> params_1 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_1.put("message", randomText);
        Response response_1 = ApiUtils.doPost(ApiMethods.WALL_POST.getTitle(), params_1);

        Assert.assertTrue(userPage.checkPost(userId,
                ApiUtils.getResponse(response_1, "response.post_id"),
                "check is post added"), "post did not add");
        Assert.assertTrue(userPage.checkTextInLastPost(randomText), "text don't much");


        Map<String, String> params_2 = SetBasicParamsUtils.returnMapWithAuthParams();
        Response response_2 = ApiUtils.doPost(ApiMethods.SERVER_PHOTO.getTitle(), params_2);

        File testPhoto = new File("src/test/resources/test.jpg");
        Response response_3 = ApiUtils.doPostUpload(ApiUtils.getResponse(response_2, "response.upload_url"), testPhoto, Params.STEP_3.getTitle());

        Map<String, String> params_4 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_4.put("server", response_3.then().extract().body().jsonPath().getString("server"));
        params_4.put("photo", response_3.then().extract().body().jsonPath().getString("photo"));
        params_4.put("hash", response_3.then().extract().body().jsonPath().getString("hash"));
        Response response_4 = ApiUtils.doPost(ApiMethods.SAVE_WALL_PHOTO.getTitle(), params_4);


        Map<String, String> params_5 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_5.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_5.put("message", "EDITED");
        params_5.put("attachments", String.format("photo720912025_%s",ApiUtils.getResponse(response_4, "response.id")));
        Response response_5 = ApiUtils.doPost(ApiMethods.WALL_EDIT.getTitle(), params_5);

        Assert.assertTrue(userPage.isDataChangeAfterEdit(params_5.get("message"), ApiUtils.getResponse(response_5, "response.post_id"), params_5.get("owner_id")), "message text did not change");
        Assert.assertTrue(userPage.checkPhoto(params_5.get("owner_id"), ApiUtils.getResponse(response_4, "response.id"), "is photo uploaded"), "photo did not upload");


        Map<String, String> params_6 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_6.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_6.put("message", RandomGenerationUtil.generate());
        Response response_6 = ApiUtils.doPost(ApiMethods.WALL_ADD_COMMENT.getTitle(), params_6);

        Assert.assertTrue(userPage.isCommentAdd(params_6.get("owner_id"), ApiUtils.getResponse(response_6, "response.comment_id")), "comment did not add");

        userPage.likePost(params_6.get("owner_id"), params_6.get("post_id"));


        Map<String, String> params_7 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_7.put("post_id", ApiUtils.getResponse(response_1, "response.post_id"));
        Response response_7 = ApiUtils.doPost(ApiMethods.WALL_GET_LIKES.getTitle(), params_7);

        Assert.assertTrue(checkLikeFromUser(ApiUtils.getResponse(response_7, "response.count"), ApiUtils.getResponse(response_7, "response.users[0].uid")), "post did not have like from user");


        Map<String, String> params_8 = SetBasicParamsUtils.returnMapWithAuthParams();
        params_8.put("post_id", ApiUtils.getResponse(response_1, "response.post_id"));
        Response response_8 = ApiUtils.doPost(ApiMethods.WALL_DELETE_POST.getTitle(), params_8);

        Assert.assertTrue(isPostDeleted(ApiUtils.getResponse(response_8, "response")), "post did not delete");

    }

    private boolean checkLikeFromUser(String countLikes, String userId) {
        String testUser = testData.getValue("/ownerId").toString();
        return testUser.equals(userId) || Integer.parseInt(countLikes) > 0;
    }

    private boolean isPostDeleted(String responseFromDeleteApi) {
        return Integer.parseInt(responseFromDeleteApi) == 1;
    }
}