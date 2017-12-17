/** 
 * Antiporn
 * Created on 10/20/2017
 * @author: SnowSense
 */
 package com.snowsense.antiporn.sdk.rest;


import com.snowsense.antiporn.sdk.ClassifyResult;
import com.snowsense.antiporn.sdk.ResetCacheString;
import com.snowsense.antiporn.sdk.VideoClassifyResult;
import com.snowsense.antiporn.sdk.rest.model.ImageClassifyRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.awt.*;

public interface AntiPornService {

    @POST("dbg/imgclassify/antiporn")
    Call<ClassifyResult> aduitImage(@Body ImageClassifyRequest request);

    @POST("videoclassify/antiporn")
    @Multipart
    Call<VideoClassifyResult> aduitVideo(@Part MultipartBody.Part file, @Query("format") String format);

    @POST("resetcache")
    Call<ResetCacheString> resetCache();
}