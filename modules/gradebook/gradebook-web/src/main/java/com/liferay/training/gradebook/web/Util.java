package com.liferay.training.gradebook.web;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
public class Util {

    public String getNum(JSONArray jsonArray){
        switch (getjson(jsonArray).getString("num", StringPool.BLANK)){
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                return StringPool.BLANK;

        }

    }

    public JSONObject getjson(JSONArray jsonArray){
        return jsonArray.getJSONObject(0);
    }
}
