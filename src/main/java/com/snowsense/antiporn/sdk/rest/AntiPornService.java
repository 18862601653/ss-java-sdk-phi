/** 
 * Antiporn
 * Created on 10/20/2017
 * @author: SnowSense
 */
 package com.snowsense.antiporn.sdk.rest;


import com.snowsense.antiporn.sdk.ClassifyResult;
import com.snowsense.antiporn.sdk.rest.model.ImageClassifyRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface AntiPornService {

    @POST("imgclassify/antiporn")
    Call<ClassifyResult> aduitImage(@Body ImageClassifyRequest request);

    @POST("videoclassify/antiporn")
    @Multipart
    Call<ClassifyResult> aduitVideo(@Part("video") MultipartBody.Part file, @Part("format") String format);

    @POST("resetcache")
    Call<String> resetCache();
}