package com.snowsense;

public class SnowSenseSdkException extends Exception {
    public static int STATUS_UNKNOW_ERROR = 1;
    public static int STATUS_CLIENT_ERROR = 10;
    public static int STATUS_NETWORK_ERROR = 20;
    public static int STATUS_SERVER_ERROR = 500;

    private int status;

    public SnowSenseSdkException(int status) {
        this.status = status;
    }

    public SnowSenseSdkException(String message, int status) {
        super(message);
        this.status = status;
    }

    public SnowSenseSdkException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public SnowSenseSdkException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
