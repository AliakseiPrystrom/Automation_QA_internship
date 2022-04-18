package tests;

import api_for5step.Root;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.FromJsonToObj_step5;
import util.Utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestCase1 {
    private final ISettingsFile  environment = new JsonSettingsFile("url.json");

    @Test(priority = 1)
    public void apiGetPostTest_StepOne(){
        when().
                get(environment.getValue("/PathForStepOne").toString()).
        then().
                log().all().
                statusCode(200).
                contentType(JSON);

        Assert.assertTrue(Utils.isIdSorted_FirstStepTC(),"Id in response not sorted");
    }

    @Test(priority = 2)
    public void apiGetPostTest_StepTwo(){
        when().
                get(environment.getValue("/PathForStepTwo").toString()).
        then().
                log().all().
                statusCode(200).
                body("userId",equalTo(10),
                        "id",equalTo(99),
                        "title.size()",greaterThan(0));
    }

    @Test(priority = 3)
    public void apiGetPostTest_StepThree(){
        when().
                get(environment.getValue("/PathForStepThree").toString()).
        then().
                log().all().
                statusCode(404).
                body("isEmpty()", Matchers.is(true));
    }

    @Test(priority = 4)
    public void apiGetPostTest_StepFour(){
        given().
                body("{\n" +
                        "    \"userId\": \"1\"    \n" +
                        "}").
                log().all().
        when().
                post(environment.getValue("/PathForStepFour").toString()).
        then().
                log().all().
                statusCode(201).
                body("id",equalTo(101));
    }

    @Test(priority = 5)
    public void apiGetPostTest_StepFive(){
        Root root = Utils.returnRootFromFiveStep();
        Assert.assertEquals(FromJsonToObj_step5.getTestDataUserForCompare(), root, "Data not equals");
    }

    @Test(priority = 6)
    public void apiGetPostTest_StepSix(){
        Root user = given().
            when().
                get(environment.getValue("/PathForStepSix").toString()).
            then().
                log().all().
                statusCode(200).
                extract().body().jsonPath().getObject("",Root.class);
        Assert.assertEquals(Utils.returnRootFromFiveStep(), user, "Users did not match");

    }
}
