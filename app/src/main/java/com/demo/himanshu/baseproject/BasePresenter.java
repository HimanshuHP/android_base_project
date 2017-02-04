package com.demo.himanshu.baseproject;

/**
 * Created by himanshu on 04/02/17.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
