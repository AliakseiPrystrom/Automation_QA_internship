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
import java.util.HashMap;
import java.util.Map;

public class VkApiTest extends BaseTest {
    private final ISettingsFile environment = new JsonSettingsFile("config.json");
    private final ISettingsFile testData = new JsonSettingsFile("testuserdata.json");
    private final ISettingsFile urls = new JsonSettingsFile("url.json");

    @BeforeMethod
    @Override
    protected void beforeMethod() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(environment.getValue("/homePage").toString());
        browser.waitForPageToLoad();
    }


    @Test
    public void vkApiTest() {
        String randomText = RandomGeneration.generate();

        Browser browser = AqualityServices.getBrowser();
        browser.waitForPageToLoad();
        VkLoginRegPage vklp = new VkLoginRegPage();
        vklp.signInClick();
        AuthPage ap = new AuthPage();
        ap.inputLoginAndSubmit(testData.getValue("/login").toString());
        ap.inputPasswordAndSubmit(testData.getValue("/password").toString());
        browser.waitForPageToLoad();
        VkMainPage vmp = new VkMainPage();
        vmp.myPageClick();

        Map<String, String> params_1 = new HashMap<>();
        params_1.put("owner_id", "720912025");
        params_1.put("message", randomText);
        params_1.put("v", "5.131");
        params_1.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        Response response_1 = VkApiUtils.doPost(urls.getValue("/wallPost").toString(), params_1);
        MyPage mp = new MyPage();

        Assert.assertTrue(mp.isAddLastWallPost(testData.getValue("/ownerId").toString(), response_1.then().extract().body().jsonPath().getString("response.post_id")), "post did not add");
        Assert.assertTrue(mp.checkTextInLastPost(randomText));


        ////////////////////ниже добовляем фото////////////////////


        Map<String, String> params_2 = new HashMap<>();
        params_2.put("owner_id", "720912025");
        params_2.put("v", "5.131");
        params_2.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        Response response_2 = VkApiUtils.doPost(urls.getValue("/serverPhoto").toString(), params_2);

        File testPhoto = new File("src/test/resources/test.jpg");
        String params_3 = "multipart/form-data";
        Response response_3 = VkApiUtils.doPostUpload(response_2.then().extract().body().jsonPath().getString("response.upload_url"), testPhoto, params_3);

        Map<String, String> params_4 = new HashMap<>();
        params_4.put("ownerId", "720912025");
        params_4.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        params_4.put("server", response_3.then().extract().body().jsonPath().getString("server"));
        params_4.put("photo", response_3.then().extract().body().jsonPath().getString("photo"));
        params_4.put("hash", response_3.then().extract().body().jsonPath().getString("hash"));
        params_4.put("v", "5.131");
        Response response_4 = VkApiUtils.doPost(urls.getValue("/saveWallPhoto").toString(), params_4);

        ////////////////////ниже добовляем редакт стены////////////////////

        Map<String, String> params_5 = new HashMap<>();
        params_5.put("owner_id", "720912025");
        params_5.put("v", "5.131");
        params_5.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        params_5.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_5.put("message", "EDITED");
        params_5.put("attachments", "photo720912025" + "_" + response_4.then().extract().body().jsonPath().getString("response.id").replace("[", "").replace("]", ""));
        Response response_5 = VkApiUtils.doPost(urls.getValue("/wallEdit").toString(), params_5);

        Assert.assertTrue(mp.isDataChangeAfterEditOrAdd(params_5.get("message"), response_5.then().extract().body().jsonPath().getString("response.post_id"), params_5.get("owner_id")));
        Assert.assertTrue(mp.isPhotoUpload(params_5.get("owner_id"), response_4.then().extract().body().jsonPath().getString("response.id").replace("[", "").replace("]", "")));

        ////////////////////ниже добовляем комент стены////////////////////

        Map<String, String> params_6 = new HashMap<>();
        params_6.put("owner_id", "720912025");
        params_6.put("v", "5.131");
        params_6.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        params_6.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        params_6.put("message", RandomGeneration.generate());
        Response response_6 = VkApiUtils.doPost(urls.getValue("/wallAddComment").toString(), params_6);

        Assert.assertTrue(mp.isCommentAdd(params_6.get("owner_id"), response_6.then().extract().body().jsonPath().getString("response.comment_id")));

        ////////////////////ставим лайк////////////////////
        LikePost.likePost(params_6.get("owner_id"), params_6.get("post_id"));

        ////////////////////ниже получаем лайки////////////////////

        Map<String, String> params_7 = new HashMap<>();
        params_7.put("owner_id", "720912025");
        params_7.put("v", "5.131");
        params_7.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        params_7.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));
        Response response_7 = VkApiUtils.doPost(urls.getValue("/wallGetLikes").toString(), params_7);

        Assert.assertTrue(CheckLastPostOnWall.checkLikeFromUser(response_7.then().extract().body().jsonPath().getString("response.count"), response_7.then().extract().body().jsonPath().getString("response.users[0].uid")));

        ////////////////////ниже удаляем запись////////////////////
        Map<String, String> params_8 = new HashMap<>();
        params_8.put("owner_id", "720912025");
        params_8.put("v", "5.131");
        params_8.put("access_token", "628799da657d3bfd75cf9fe6396b859d4840616d12c74026f657f0de567da1fcbafbbd97f08d8c63a01ed");
        params_8.put("post_id", response_1.then().extract().body().jsonPath().getString("response.post_id"));

        Response response_8 = VkApiUtils.doPost(urls.getValue("/wallDeletePost").toString(), params_8);

        Assert.assertTrue(CheckLastPostOnWall.isPostDeleted(response_8.then().extract().body().jsonPath().getString("response")));

    }
}