package com.snowsense.common;

import com.snowsense.Constants;
import com.snowsense.RetrofitProvider;
import com.snowsense.antiporn.sdk.rest.AntiPornService;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

/**
 * Base class for all SDKs
 */
public class BaseSDK {
    protected String serverUrl;
    protected String apiKey;
    protected String apiSecret;

    public BaseSDK(String serverUrl, String apiKey, String apiSecret) {
        this.serverUrl = serverUrl;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    protected MultipartBody.Part convertFileToBody(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        MultipartBody.Part body =  MultipartBody.Part.createFormData("image", "face",
                RequestBody.create(Constants.IMAGE_MEDIA_TYPE, file));
        return MultipartBody.Part.createFormData("image", "face",
                RequestBody.create(Constants.IMAGE_MEDIA_TYPE, file));
    }

    protected MultipartBody.Part convertVideoFileToBody(File file){
        if(file == null) {
            throw new IllegalArgumentException("file is null");
        }

        return MultipartBody.Part.createFormData("video",file.getName(),RequestBody.create(Constants.VIDEO_MEDIA_TYPE,file));
    }

    protected void ensureStringParameterNotEmpty(String paramName, String value) {
        if (TextUtils.isEmpty(value)) {
            throw new IllegalArgumentException(paramName + " is empty");
        }
    }

    protected void ensureParameterNotNull(String paramName, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(paramName + " is null");
        }
    }

    protected <T> T createService(final Class<T> serviceClass) {
        RetrofitProvider retrofitProvider = new RetrofitProvider(serverUrl, apiKey, apiSecret);
        return retrofitProvider.get().create(serviceClass);
    }
}
