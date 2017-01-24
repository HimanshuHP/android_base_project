package com.demo.himanshu.baseproject.data.remote;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Created by himanshu on 24/01/17.
 */

public class VolleyFactory {

    private static VolleyFactory sVolleyInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public static synchronized VolleyFactory getInstance(Context context) {
        return sVolleyInstance = sVolleyInstance == null ? new VolleyFactory(context) : sVolleyInstance;
    }

    public VolleyFactory(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    protected HurlStack getHurlStack() {
        return new HurlStack() {
            @Override
            protected HttpURLConnection createConnection(URL url) throws IOException {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
                try {
                    httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return hostname.contains("localhost");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return httpsURLConnection;
            }
        };
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue = (mRequestQueue == null) ? Volley.newRequestQueue(mCtx.getApplicationContext(), getHurlStack()) : mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
