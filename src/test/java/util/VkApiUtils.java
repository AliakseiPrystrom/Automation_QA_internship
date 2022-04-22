package util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

public class VkApiUtils {
    public static Response doPost(String url, Map<String, String> params) {
        return RestAssured
                .given()
                .params(params)
                .when()
                .post(url);
    }

    public static Response doPostUpload(String mediaUrl, File file, String param) {
        return RestAssured
                .given()
                .header("photo", param)
                .multiPart("file", file)
                .when()
                .post(mediaUrl);
    }
}