package com.siegfrield.hakuna_matata.model.data;

/**
 * Created by 欧阳夏昱 on 2017/4/27.
 */

public class ResultData<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
