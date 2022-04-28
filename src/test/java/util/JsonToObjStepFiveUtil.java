package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static util.JsonUtil.getJsonString;

public class JsonToObjStepFiveUtil {

    public static <T> Object getTestDataUserForCompare(String path, Class<T> t){
        Gson gson=new GsonBuilder().create();
        return gson.fromJson(getJsonString(path), t);
    }
}
