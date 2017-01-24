package com.demo.himanshu.baseproject.model;

import org.json.JSONObject;

/**
 * Created by himanshu on 24/01/17.
 */

public interface ModelInterface {
    JSONObject toJson();
    ModelInterface fromJson(JSONObject obj);
}
