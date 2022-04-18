package util;

import api_for5step.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static util.Utils.getJsonString;

public class FromJsonToObj_step5 {

    public static Root getTestDataUserForCompare(){
        Gson gson=new GsonBuilder().create();
        return gson.fromJson(getJsonString("src/test/resources/testuser_step5.json"),Root.class);
    }
}
