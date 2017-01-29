package com.demo.himanshu.baseproject.data.remote.customRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by himanshu on 29/01/17.
 */

public class ListOfSomeClass<X> implements ParameterizedType {

    private Class<?> wrapped;

    public ListOfSomeClass(Class<X> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[] {wrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }

}
