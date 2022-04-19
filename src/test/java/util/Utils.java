package util;

import api_for5step.User;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Utils {
    private static final ISettingsFile environment = new JsonSettingsFile("url.json");

    public static boolean isIdSorted_FirstStepTC() {
        String stringUntilSort = "";
        String stringAfterSort = "";

        try {
            HttpResponse response = Request.Get(Utils.getCurrentURL(environment.getValue("/postsRoute").toString())).execute().returnResponse();
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

    public static String getJsonString(String path) {
        String json = "";
        File file = new File(path);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            while (dis.available() != 0) {
                json += dis.readLine();
            }
            json = json.replace("null", "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return json;
    }

    public static User returnRootFromFiveStep(){
        List<User> users = given().
        when().
                contentType(JSON).
                get(Utils.getCurrentURL(environment.getValue("/PathForStepFive").toString())).
                then().
                log().all().
                extract().body().jsonPath().getList("", User.class);
        return users.get(4);
    }

    public static String getCurrentURL(String req){
        return String.format(environment.getValue("/ApiPath").toString()+"%s",req);
    }

}
