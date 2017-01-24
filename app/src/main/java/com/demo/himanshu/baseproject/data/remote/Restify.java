package com.demo.himanshu.baseproject.data.remote;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.demo.himanshu.baseproject.data.DataAccessInterface;
import com.demo.himanshu.baseproject.data.ModelInterface;
import com.demo.himanshu.baseproject.data.callback.Callback;
import com.demo.himanshu.baseproject.data.callback.QueryCallback;
import com.demo.himanshu.baseproject.data.models.HeaderValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by himanshu on 24/01/17.
 */

public class Restify<T extends ModelInterface> implements DataAccessInterface {


    private String url;
    private Callback mCallback;
    private QueryCallback mQueryCallback;
    private Class<T> classObj;
    private ArrayList<HeaderValue> mHeaderValues = null;

    public Restify( String url, Class<T> classObj, ArrayList<HeaderValue> headerValues) {
        this.url = url;
        this.classObj = classObj;
        mHeaderValues = headerValues;
    }

    @Override
    public void post(JSONObject obj, final Callback callback) {
        try {
            this.mCallback = callback;
            CustomJSONObjectRequest customJSONObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    GenericAsyncTask genericAsyncTask = new GenericAsyncTask(response);
                    genericAsyncTask.execute();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mCallback.onError(error);
                }
            });
            customJSONObjectRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customJSONObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            sendError(e);
        }

    }

    @Override
    public void put(JSONObject obj, Callback callback) {
        try {
            this.mCallback = callback;
            CustomJSONObjectRequest customJSONObjectRequest = new CustomJSONObjectRequest(Request.Method.PUT, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    GenericAsyncTask genericAsyncTask = new GenericAsyncTask(response);
                    genericAsyncTask.execute();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mCallback.onError(error);
                }
            });
            customJSONObjectRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customJSONObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
            sendError(e);
        }

    }

    @Override
    public void get(Callback callback) {
        try {
            this.mCallback = callback;
            CustomJSONObjectRequest customJSONObjectRequest = new CustomJSONObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    GenericAsyncTask genericAsyncTask = new GenericAsyncTask(response);
                    genericAsyncTask.execute();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mCallback.onError(error);
                }
            });
            customJSONObjectRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customJSONObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
            sendError(e);
        }
    }

    @Override
    public void query(QueryCallback queryCallback) {
        try {
            this.mQueryCallback = queryCallback;
            CustomStringRequest customStringRequest = new CustomStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        QueryAsyncTask queryAsyncTask = new QueryAsyncTask(new JSONArray(response));
                        queryAsyncTask.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mQueryCallback.onError(error);
                }
            });
            customStringRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customStringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                mQueryCallback.onError(new JSONObject().put("error", e.getMessage()));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Callback callback) {
        try {
            this.mCallback = callback;
            CustomJSONObjectRequest customJSONObjectRequest = new CustomJSONObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    GenericAsyncTask genericAsyncTask = new GenericAsyncTask(response);
                    genericAsyncTask.execute();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mCallback.onError(error);
                }
            });
            customJSONObjectRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customJSONObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
            sendError(e);
        }
    }

    @Override
    public void fetchAll(QueryCallback queryCallback) {
        try {
            this.mQueryCallback = queryCallback;
            CustomStringRequest customStringRequest = new CustomStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        QueryAsyncTask queryAsyncTask = new QueryAsyncTask(new JSONArray(response));
                        queryAsyncTask.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mQueryCallback.onError(error);
                }
            });
            customStringRequest.setHeaderValues(mHeaderValues);
            VolleyFactory.getInstance().addToRequestQueue(customStringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                mQueryCallback.onError(new JSONObject().put("error", e.getMessage()));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class GenericAsyncTask extends AsyncTask<Void, Void, Void> {

        JSONObject jsonObject, errorObj;
        T t;

        public GenericAsyncTask(JSONObject response) {
            this.jsonObject = response;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                errorObj = jsonObject.optJSONObject("error");

                if (errorObj == null) {
                    t = classObj.newInstance();
                    t.fromJson(jsonObject);
                } else
                    t = null;

            } catch (Exception e) {
                e.printStackTrace();
                t = null;
                try {
                    errorObj.put("error", e.getMessage());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sendCallback(errorObj, t);
        }

    }

    public void sendCallback(JSONObject errorObj, T t) {
        try {
            if (errorObj == null)
                mCallback.onSuccess(t);
            else
                mCallback.onError(errorObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class QueryAsyncTask extends AsyncTask<Void, Void, Void> {

        JSONArray jsonArray;
        JSONObject errorObj;
        ArrayList<T> list = new ArrayList<>();

        public QueryAsyncTask(JSONArray arr) {
            this.jsonArray = arr;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    T t = classObj.newInstance();
                    t.fromJson(jsonArray.optJSONObject(i));
                    list.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    errorObj.put("error", e.getMessage());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sendCallback(null, list);
        }
    }

    public void sendCallback(JSONObject errorObj, ArrayList<T> list) {
        try {
            if (errorObj == null)
                mQueryCallback.onSuccess(list);
            else
                mQueryCallback.onError(errorObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendError(Exception e) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", e.getMessage());
            mCallback.onError(jsonObject);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
