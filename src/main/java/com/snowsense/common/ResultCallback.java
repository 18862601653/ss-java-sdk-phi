package com.snowsense.common;

public interface ResultCallback<T> {
    void onResult(T result);

    void onError(int errorCode, String message);
}
