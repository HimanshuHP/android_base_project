package com.demo.himanshu.baseproject.data.remote.customRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.demo.himanshu.baseproject.data.models.HeaderValue;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshu on 29/01/17.
 */

public class GsonArrayRequest<T> extends Request<T> {
    private final Gson gson = new Gson();

    private ArrayList<HeaderValue> mHeaderValues;
    Response.Listener<T> mListener;
    private Type mType;


    public void setHeaderValues(ArrayList<HeaderValue> headerValues) {
        this.mHeaderValues = headerValues;
    }

    public GsonArrayRequest(String url, Type type, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mType=type;
        this.mListener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        ArrayList<HeaderValue> headerValues = mHeaderValues;
        Map<String, String> headerMap = new HashMap<>();
        for (HeaderValue headerValue : headerValues)
            headerMap.put(headerValue.name, headerValue.value);

        return headerMap;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return (Response<T>) Response.success(gson.fromJson(json,new ListOfSomeClass<T>((Class<T>) mType)), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


}
