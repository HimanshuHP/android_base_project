package com.demo.himanshu.baseproject.model;

import com.demo.himanshu.baseproject.model.callback.Callback;
import com.demo.himanshu.baseproject.model.callback.QueryCallback;

import org.json.JSONObject;

/**
 * Created by himanshu on 24/01/17.
 */

public class Restify<T extends ModelInterface> implements DataAccessInterface {

    @Override
    public void post(JSONObject obj, Callback callback) {

    }

    @Override
    public void put(JSONObject obj, Callback callback) {

    }

    @Override
    public void get(Callback callback) {

    }

    @Override
    public void query(QueryCallback queryCallback) {

    }

    @Override
    public void delete(Callback callback) {

    }

    @Override
    public void fetchAll(QueryCallback queryCallback) {

    }
}
