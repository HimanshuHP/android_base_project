package com.demo.himanshu.baseproject.data.remote;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.demo.himanshu.baseproject.BaseApplication;

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

    public static synchronized VolleyFactory getInstance() {
        return sVolleyInstance = sVolleyInstance == null ? new VolleyFactory() : sVolleyInstance;
    }

    public VolleyFactory() {
        mRequestQueue = (mRequestQueue == null) ? Volley.newRequestQueue(BaseApplication.getAppContext(), getHurlStack()) : mRequestQueue;
    }

    protected HurlStack getHurlStack() {
        return new HurlStack() {
            @Override
            protected HttpURLConnection createConnection(URL url) throws IOException {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
                try {
                    //you can set ssl settings here
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

    public <T> void addToRequestQueue(Request<T> request) {
        mRequestQueue.add(request);
    }
}
