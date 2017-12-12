package com.snowsense;

import com.snowsense.common.NoOpTransformer;
import com.snowsense.common.ResponseTransformer;
import com.snowsense.common.ResultCallback;
import retrofit2.Call;
import retrofit2.Callback;

import java.io.IOException;

public class RetrofitResponseHandler {
    private static final String TAG = RetrofitResponseHandler.class.getSimpleName();

    public static  <T> T getResponse(Call<T> call) throws SnowSenseSdkException {
        retrofit2.Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            Logger.get().error(TAG, "getResponse", e);
            throw new SnowSenseSdkException(e, SnowSenseSdkException.STATUS_CLIENT_ERROR);
        }

        if (response != null && response.isSuccessful()) {
            return response.body();
        } else {
            String errorMessage = null;
            if (response != null) {
                errorMessage = "StatusCode = " + response.code() + ", errorMessage=";
                try {
                    errorMessage += response.errorBody().string();
                } catch (IOException e) {
                }
            } else {
                errorMessage = "Unknown";
            }
            Logger.get().error(TAG, "Service failed : " + errorMessage);
            throw new SnowSenseSdkException(errorMessage, response.code());
        }
    }

    public static <T, R> void getResponse(Call<T> call, final ResultCallback<R> callback) {
        getResponse(call, callback, new NoOpTransformer<T, R>());
    }

    public static <T, R> void getResponse(Call<T> call, final ResultCallback<R> callback,
                                    final ResponseTransformer<T, R> responseTransformer) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(final Call<T> call, retrofit2.Response<T> response) {
                if (callback != null) {
                    if (response != null && response.isSuccessful()) {
                        callback.onResult(responseTransformer.transform(response.body()));
                    } else {
                        Logger.get().error("getResponse", "Callback.onRespons, code=" + response.code() + ", body=" + response.errorBody());
                        callFailure(response, null);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Logger.get().error("getResponse", "Callback.onFailure", t);
                callFailure(null, t);
            }

            private void callFailure(retrofit2.Response<T> response, Throwable t) {
                int code = 500;
                String message = "Unknown";
                if (response != null) {
                    code = response.code();
                    message = response.message();
                    if (response.errorBody() != null && response.errorBody().source() != null) {
                        try {
                            message = message + " " + response.errorBody().source().readUtf8();
                        } catch (IOException e) {
                            //e.printStackTrace();
                        }
                    }
                } else if (t != null) {
                    message = t.getMessage();
                }
                callback.onError(code, message);
            }
        });
    }
}
