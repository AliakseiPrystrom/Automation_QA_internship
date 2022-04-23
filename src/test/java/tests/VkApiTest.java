package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.AuthPage;
import forms.MyPage;
import forms.VkLoginRegPage;
import forms.VkMainPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.*;

import java.io.*;
import java.util.Map;

public class VkApiTest extends BaseTest {
    private final ISettingsFile config = new JsonSettingsFile("config.json");
    private final ISettingsFile testData = new JsonSettingsFile("testuserdata.json");
    private final ISettingsFile urls = new JsonSettingsFile("url.json");
    private final ISettingsFile locators = new JsonSettingsFile("locators.json");

    @BeforeMethod
    @Override
    protected void beforeMethod() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(config.getValue("/homePage").toString());
        browser.waitForPageToLoad();
    }


    @Test
    public void vkApiTest() {
        String randomText = RandomGeneration.generate();
        String userId = testData.getValue("/ownerId").toString();

        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();

        VkLoginRegPage vkLogRegPage = new VkLoginRegPage();
        MyPage userPage = new MyPage();
        AuthPage authPage = new AuthPage();
        VkMainPage vkMainPage = new VkMainPage();

        vkLogRegPage.signInClick();
        authPage.inputLoginAndSubmit(testData.getValue("/login").toString());
        authPage.inputPasswordAndSubmit(testData.getValue("/password").toString());
        browser.waitForPageToLoad();
        vkMainPage.myPageClick();

        //ниже добовляем первый пост

        Map<String, String> params_1 = ParamUtils.returnMapWithAuthParams();
        params_1.put("message", randomText);
        Response response_1 = ApiUtils.doPost(urls.getValue("/wallPost").toString(), params_1);

        Assert.assertTrue(userPage.isAddLastWallPost(userId,
                ApiUtils.getResponse(response_1, "response.post_id"),
                locators.getValue("/isAddPost").toString(),
                "check is post added"), "post did not add");
        Assert.assertTrue(userPage.checkTextInLastPost(randomText));

        //ниже добовляем фото

        Map<String, String> params_2 = ParamUtils.returnMapWithAuthParams();
        Response response_2 = ApiUtils.doPost(urls.getValue("/serverPhoto").toString(), params_2);

        File testPhoto = new File("src/test/resources/test.jpg");
        String params_3 = "multipart/form-data";
        Response response_3 = ApiUtils.doPostUpload(ApiUtils.getResponse(response_2, "response.upload_url"), testPhoto, params_3);

        Map<String, String> params_4 = ParamUtils.returnMapWithAuthParams();
        params_4.put("server", response_3.then().extract().body().jsonPath().getString("server"));
        params_4.put("photo", response_3.then().extract().body().jsonPath().getString("photo"));
        params_4.put("hash", response_3.then().extract().body().jsonPath().getString("hash"));
        Response response_4 = ApiUtils.doPost(urls.getValue("/saveWallPhoto").toString(), params_4);

        //ниже делаем редакт стены

        Map<String, String> params_5 = ParamUtils.returnMapWithAuthParams();
        params_5.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_5.put("message", "EDITED");
        params_5.put("attachments", "photo720912025" + "_" + ApiUtils.getResponse(response_4, "response.id"));
        Response response_5 = ApiUtils.doPost(urls.getValue("/wallEdit").toString(), params_5);

        Assert.assertTrue(userPage.isDataChangeAfterEdit(params_5.get("message"), ApiUtils.getResponse(response_5, "response.post_id"), params_5.get("owner_id")));
        Assert.assertTrue(userPage.isPhotoUpload(params_5.get("owner_id"), ApiUtils.getResponse(response_4, "response.id"), locators.getValue("/isPhotoUpload").toString(), "is photo uploaded"));

        //ниже добовляем комент к посту

        Map<String, String> params_6 = ParamUtils.returnMapWithAuthParams();
        params_6.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_6.put("message", RandomGeneration.generate());
        Response response_6 = ApiUtils.doPost(urls.getValue("/wallAddComment").toString(), params_6);

        Assert.assertTrue(userPage.isCommentAdd(params_6.get("owner_id"), ApiUtils.getResponse(response_6, "response.comment_id")));

        //ставим лайк посту

        LikePost.likePost(params_6.get("owner_id"), params_6.get("post_id"));

        //ниже получаем лайки

        Map<String, String> params_7 = ParamUtils.returnMapWithAuthParams();
        params_7.put("post_id", ApiUtils.getResponse(response_1, "response.post_id"));
        Response response_7 = ApiUtils.doPost(urls.getValue("/wallGetLikes").toString(), params_7);

        Assert.assertTrue(WorkWithPost.checkLikeFromUser(ApiUtils.getResponse(response_7, "response.count"), ApiUtils.getResponse(response_7, "response.users[0].uid")));

        //ниже удаляем пост
        Map<String, String> params_8 = ParamUtils.returnMapWithAuthParams();
        params_8.put("post_id", ApiUtils.getResponse(response_1, "response.post_id"));
        Response response_8 = ApiUtils.doPost(urls.getValue("/wallDeletePost").toString(), params_8);

        Assert.assertTrue(WorkWithPost.isPostDeleted(ApiUtils.getResponse(response_8, "response")));

    }
}