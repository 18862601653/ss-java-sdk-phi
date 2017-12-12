package com.snowsense;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitProvider {
    private static final String PARAM_API_KEY = "apikey";
    private static final String PARAM_API_SECRET = "secretkey";

    private Retrofit retrofit;
    private String url;
    private String apiKey;
    private String apiSecret;

    public RetrofitProvider(String url, String apiKey, String apiSecret) {
        this.url = url;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public Retrofit get() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }

        return retrofit;
    }

    private synchronized Retrofit createRetrofit() {
        if (retrofit != null) {
            return retrofit;
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                //Add API Key to request
                Request request = chain.request();
                return chain.proceed(request.newBuilder()
                        .url(request.url().newBuilder()
                                .addQueryParameter(PARAM_API_KEY, apiKey)
                                .addQueryParameter(PARAM_API_SECRET, apiSecret)
                                .build()
                        )
                        .build());
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
