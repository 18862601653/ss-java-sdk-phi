package com.snowsense.common;

public interface ResponseTransformer<T, R> {
    R transform(T response);
}
