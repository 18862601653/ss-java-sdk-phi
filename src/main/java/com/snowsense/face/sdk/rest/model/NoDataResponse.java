package com.snowsense.face.sdk.rest.model;

public class NoDataResponse {
    protected Boolean success;
    protected String error;

    public boolean isSuccess() {
        if (success != null) {
            return success.booleanValue() ;
        } else {
            return error != null;
        }
    }

    public String getError() {
        return error;
    }
}
