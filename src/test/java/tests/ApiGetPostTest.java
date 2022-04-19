package tests;

import api_for5step.User;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.FromJsonToObj_step5;
import util.Utils;
import util.WorkWithApi;

public class ApiGetPostTest {
    private final ISettingsFile environment = new JsonSettingsFile("url.json");

    @Test
    public void apiGetPostTest() {

        Response stepOne = WorkWithApi.doGet(Utils.getCurrentURL(environment.getValue("/postsRoute").toString()));
        Assert.assertEquals(stepOne.getStatusCode(), 200, "Response code not match");
        Assert.assertTrue(stepOne.getContentType().contains("json"), "Response body not match");
        Assert.assertTrue(Utils.isIdSorted_FirstStepTC(), "Id in response not sorted");

        Response stepTwo = WorkWithApi.doGet(Utils.getCurrentURL(environment.getValue("/99post").toString()));
        Assert.assertEquals(stepTwo.getStatusCode(), 200, "Response code not match");
        Assert.assertEquals(stepTwo.jsonPath().get("userId"), 10, "Response data (userId) not match");
        Assert.assertEquals(stepTwo.jsonPath().get("id"), 99, "Response data (id) not match");
        Assert.assertTrue(stepTwo.then().extract().response().asString().length() > 2, "Body is empty");

        Response stepThree = WorkWithApi.doGet(Utils.getCurrentURL(environment.getValue("/150post").toString()));
        Assert.assertEquals(stepThree.getStatusCode(), 404, "Response code not match");
        Assert.assertEquals(stepThree.then().extract().response().asString().length(), 2, "Body not empty");

        Response stepFour = WorkWithApi.doPost(Utils.getCurrentURL(environment.getValue("/postsRoute").toString()),Utils.getJsonString("src/test/resources/user_step_four.json"));
        Assert.assertEquals(stepFour.getStatusCode(), 201, "Response code not match");
        Assert.assertEquals(stepFour.jsonPath().get("id"), 101, "Body (id) empty");

        Response stepFive = WorkWithApi.doGet(Utils.getCurrentURL(environment.getValue("/PathForStepFive").toString()));
        Assert.assertEquals(stepFive.getStatusCode(), 200, "Response code not match");
        User user = Utils.returnRootFromFiveStep();
        Assert.assertEquals(FromJsonToObj_step5.getTestDataUserForCompare("src/test/resources/user_step_five.json", User.class), user, "Data not equals");

        Response stepSix = WorkWithApi.doGet(Utils.getCurrentURL(environment.getValue("/PathForStepSix").toString()));
        Assert.assertEquals(stepSix.getStatusCode(), 200, "Response code not match");
        User userFromResponse = stepSix.then().extract().body().jsonPath().getObject("", User.class);
        Assert.assertEquals(Utils.returnRootFromFiveStep(), userFromResponse, "Users did not match");
    }
}
