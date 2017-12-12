/**
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
 package com.snowsense.smartalbum.sdk.rest;


import com.snowsense.smartalbum.sdk.ClassifySceneResult;
import com.snowsense.smartalbum.sdk.ExifResult;
import com.snowsense.smartalbum.sdk.rest.model.ImageClassifyRequest;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface SmartAlbumService {

    @POST("imgclassify/scene")
    Call<ClassifySceneResult> classifyScene(@Body ImageClassifyRequest request);

    @POST("img/heic2jpg")
    @Multipart
    Call<ResponseBody> convertHeic(@Query("img_uri") String imgUri);

    @POST("img/getexif")
    Call<ExifResult> getExifInfo(@Body ImageClassifyRequest request);
        
    @POST("resetcache")
    Call<String> resetCache();
}