package util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WorkWithApiUtil {

    public static Response doPost(String url,String body){
        return RestAssured.
                given().
                log().all().
                body(body).
                when().
                log().all().
                post(url);
    }

    public static Response doGet(String url){
        return RestAssured.
                when().
                get(url);
    }
}
