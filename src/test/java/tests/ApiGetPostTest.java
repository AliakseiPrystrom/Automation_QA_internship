package tests;

import api_for5step.User;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import enums.ContentType;
import io.restassured.response.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiGetPostTest {
    private final ISettingsFile environment = new JsonSettingsFile("url.json");
    private final ISettingsFile testdata = new JsonSettingsFile("testdata.json");


    @Test
    public void apiGetPostTest() {

        Response stepOne = WorkWithApiUtil.doGet(GetUrtUtil.getCurrentURL(environment.getValue("/postsRoute").toString()));
        Assert.assertEquals(stepOne.getStatusCode(), HttpStatus.SC_OK, "Response code not match");
        Assert.assertEquals(ContentType.JSON.getTitle(), stepOne.getContentType(), "Response body not match");
        Assert.assertTrue(isIdSorted_FirstStepTC(), "Id in response not sorted");

        Response stepTwo = WorkWithApiUtil.doGet(GetUrtUtil.getCurrentURL(String.format(environment.getValue("/postWithValue").toString(), testdata.getValue("/99post"))));
        Assert.assertEquals(stepTwo.getStatusCode(), HttpStatus.SC_OK, "Response code not match");
        Assert.assertEquals(stepTwo.jsonPath().get("userId"), 10, "Response data (userId) not match");
        Assert.assertEquals(stepTwo.jsonPath().get("id"), 99, "Response data (id) not match");
        Assert.assertTrue(stepTwo.then().extract().response().asString().length() > 2, "Body is empty");

        Response stepThree = WorkWithApiUtil.doGet(GetUrtUtil.getCurrentURL(String.format(environment.getValue("/postWithValue").toString(), testdata.getValue("/150post"))));
        Assert.assertEquals(stepThree.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Response code not match");
        Assert.assertEquals(stepThree.then().extract().response().asString().length(), 2, "Body not empty");

        Response stepFour = WorkWithApiUtil.doPost(GetUrtUtil.getCurrentURL(environment.getValue("/postsRoute").toString()), JsonUtil.getJsonString("src/test/resources/user_step_four.json"));
        Assert.assertEquals(stepFour.getStatusCode(), HttpStatus.SC_CREATED, "Response code not match");
        Assert.assertEquals(stepFour.jsonPath().get("id"), testdata.getValue("/stepFourExpectedId"), "Body (id) empty");

        Response stepFive = WorkWithApiUtil.doGet(GetUrtUtil.getCurrentURL(environment.getValue("/PathForStepFive").toString()));
        Assert.assertEquals(stepFive.getStatusCode(), HttpStatus.SC_OK, "Response code not match");
        User user = returnUserFiveStep();
        Assert.assertEquals(JsonToObjStepFiveUtil.getTestDataUserForCompare("src/test/resources/user_step_five.json", User.class), user, "Data not equals");

        Response stepSix = WorkWithApiUtil.doGet(GetUrtUtil.getCurrentURL(environment.getValue("/PathForStepSix").toString()));
        Assert.assertEquals(stepSix.getStatusCode(), HttpStatus.SC_OK, "Response code not match");
        User userFromResponse = stepSix.then().extract().body().jsonPath().getObject("", User.class);
        Assert.assertEquals(returnUserFiveStep(), userFromResponse, "Users did not match");
    }

    private boolean isIdSorted_FirstStepTC() {
        String stringUntilSort = "";
        String stringAfterSort = "";

        try {
            HttpResponse response = Request.Get(GetUrtUtil.getCurrentURL(environment.getValue("/postsRoute").toString())).execute().returnResponse();
            List<Integer> listId = new ArrayList<>();
            JSONArray jsonResponse = new JSONArray(IOUtils.toString(response.getEntity().getContent()));

            for (int i = 0; i < jsonResponse.length(); i++) {
                listId.add((int) jsonResponse.getJSONObject(i).get("id"));
            }
            stringUntilSort = listId.toString();
            Collections.sort(listId);
            stringAfterSort = listId.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringUntilSort.equals(stringAfterSort);
    }

    private User returnUserFiveStep() {
        List<User> users = given().
                when().
                contentType(JSON).
                get(GetUrtUtil.getCurrentURL(environment.getValue("/PathForStepFive").toString())).
                then().
                log().all().
                extract().body().jsonPath().getList("", User.class);
        return users.get(4);
    }
}
