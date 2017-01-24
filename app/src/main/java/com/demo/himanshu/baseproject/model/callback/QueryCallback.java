package com.demo.himanshu.baseproject.model.callback;

import java.util.ArrayList;

/**
 * Created by himanshu on 24/01/17.
 */

public interface QueryCallback<T> {
    void onSuccess(ArrayList<T> list);
    void onError(Object error);
}
