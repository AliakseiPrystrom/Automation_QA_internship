package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static util.Utils.getJsonString;

public class FromJsonToObj_step5 {

    public static <T> Object getTestDataUserForCompare(String path, Class<T> t){
        Gson gson=new GsonBuilder().create();
        return gson.fromJson(getJsonString(path), t);
    }
}
