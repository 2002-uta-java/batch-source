package com.revature.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseJSON {
    static String json = "...";
    public static void main(String[] args) {
    	
    	
    	
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }
    }
}
