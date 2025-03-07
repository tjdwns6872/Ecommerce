package com.simple.ecommerce.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StringUtils {
 
    public static boolean isStringEmpty(String str) {
        return str == null || str.isBlank();    
    }

    public static JSONObject stringToJson(String str) throws ParseException{
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(str);
        return jsonObject;
    }

    public static Object getJsonValue(JSONObject json, String name){
        return json.get(name);
    }
}
