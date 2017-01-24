package com.demo.himanshu.baseproject.model;

import com.demo.himanshu.baseproject.model.callback.Callback;
import com.demo.himanshu.baseproject.model.callback.QueryCallback;

import org.json.JSONObject;

/**
 * Created by himanshu on 24/01/17.
 */

public interface DataAccessInterface {
    void post(JSONObject obj, Callback callback);

    void put(JSONObject obj, Callback callback);

    void get(Callback callback);

    void query(QueryCallback queryCallback);

    void delete(Callback callback);

    void fetchAll(QueryCallback queryCallback);

}
