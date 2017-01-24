package com.demo.himanshu.baseproject.model.callback;

/**
 * Created by himanshu on 24/01/17.
 */

public interface Callback<T> {
    void onSuccess(T t);
    void onError(Object error);
}
