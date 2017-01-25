package com.demo.himanshu.baseproject.data.remote.customRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.demo.himanshu.baseproject.data.models.HeaderValue;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshu on 25/01/17.
 */

public class CustomJSONArrayRequest extends JsonArrayRequest {

    private ArrayList<HeaderValue> mHeaderValues;

    public ArrayList<HeaderValue> getHeaderValues() {
        return mHeaderValues;
    }

    public void setHeaderValues(ArrayList<HeaderValue> headerValues) {
        this.mHeaderValues = headerValues;
    }

    public CustomJSONArrayRequest(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public CustomJSONArrayRequest(int method, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
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
