package com.snowsense.common;

public class NoOpTransformer<T, R> implements ResponseTransformer<T, R> {

    @Override
    public R transform(T response) {
        return (R) response;
    }
}
