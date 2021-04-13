package com.liferay.training.gradebook.web;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
public class Util {

    public static String getNum(JSONArray jsonArray){
        switch (getjson(jsonArray).getString("num", StringPool.BLANK)){
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                return StringPool.BLANK;

        }

    }

    public static JSONObject getjson(JSONArray jsonArray){
        return jsonArray.getJSONObject(0);
    }

    public static JSONObject createJson(){
        return JSONFactoryUtil.createJSONObject();
    }
}
