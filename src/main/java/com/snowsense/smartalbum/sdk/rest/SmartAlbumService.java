/**
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
 package com.snowsense.smartalbum.sdk.rest;


import com.snowsense.smartalbum.sdk.ClassifySceneResult;
import com.snowsense.smartalbum.sdk.ExifResult;
import com.snowsense.smartalbum.sdk.JPGResponse;
import com.snowsense.smartalbum.sdk.ResetCacheString;
import com.snowsense.smartalbum.sdk.rest.model.ImageClassifyRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface SmartAlbumService {

    @POST("imgclassify/scene")
    Call<ClassifySceneResult> classifyScene(@Body ImageClassifyRequest request);

    @POST("img/heic2jpg")
    Call<JPGResponse> convertHeic(@Query("img_uri") String imgUri);

    @POST("img/getexif")
    Call<ExifResult> getExifInfo(@Body ImageClassifyRequest request);
        
    @POST("resetcache")
    Call<ResetCacheString> resetCache();
}
