package util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

public class ApiUtils {
    private static final ISettingsFile config = new JsonSettingsFile("config.json");

    public static Response doPost(String url, Map<String, String> params) {
        return RestAssured
                .given()
                .baseUri(config.getValue("/baseApiUrl").toString())
                .params(params)
                .when()
                .post(url);
    }

    public static Response doPostUpload(String mediaUrl, File file, String param) {
        return RestAssured
                .given()
                .baseUri(config.getValue("/baseApiUrl").toString())
                .header("photo", param)
                .multiPart("file", file)
                .when()
                .post(mediaUrl);
    }

    public static String getResponse(Response response, String jsonPathGetString){
        return response.then().extract().body().jsonPath().getString(jsonPathGetString).replace("[", "").replace("]", "");
    }
}