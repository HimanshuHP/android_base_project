package com.demo.himanshu.baseproject.data.remote;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.himanshu.baseproject.data.models.HeaderValue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshu on 24/01/17.
 */

public class CustomJSONObjectRequest extends JsonObjectRequest {

    private ArrayList<HeaderValue> mHeaderValues;

    public ArrayList<HeaderValue> getHeaderValues() {
        return mHeaderValues;
    }

    public void setHeaderValues(ArrayList<HeaderValue> headerValues) {
        this.mHeaderValues = headerValues;
    }

    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public CustomJSONObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        ArrayList<HeaderValue> headerValues = getHeaderValues();
        Map<String, String> headerMap = new HashMap<>();
        for (HeaderValue headerValue : headerValues)
            headerMap.put(headerValue.name, headerValue.value);

        return headerMap;
    }
}
