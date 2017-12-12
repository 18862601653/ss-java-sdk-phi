package com.snowsense.face.sdk.rest;

import com.snowsense.face.sdk.*;
import com.snowsense.face.sdk.rest.model.*;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface FaceService {

    @FormUrlEncoded
    @POST("addperson")
    Call<AddPersonResponse> addPerson(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("addperson")
    Call<AddPersonResponse> updatePerson(@FieldMap Map<String, String> data);

    @GET("persons")
    Call<List<Map<String, String>>> searchPersonByName(@Query("keyword") String keyword);

    @GET("facedetect")
    Call<DetectFaceResult> detectFace(@Query("im_uri") String imageUrl);

    @POST("facedetect")
    @Multipart
    Call<DetectFaceResult> detectFace(@Part MultipartBody.Part file);

    @GET("facecompare/{fid1}/{fid2}")
    Call<CompareResult> compareFaces(@Path("fid1") String faceId1, @Path("fid2") String faceId2);

    @GET("faceidentify/{faceId}")
    Call<IdentifyResult> identifyFace(@Path("faceId") String faceId);

    @FormUrlEncoded
    @POST("labelface")
    Call<NoDataResponse> labelFace(@Field("personId") String personId, @Field("faceId") String faceId);

    @GET("person/{personId}/facelabels")
    Call<List<PersonLabel>> getPersonLabels(@Path("personId") String personId);

    @GET("face")
    Call<FaceResult> getFaceResult(@Query("faceId") String faceId);

    @GET("personmerge/{pid1}/{pid2}")
    Call<PersonMergeResult> mergePerson(@Path("pid1") String pid1, @Path("pid2") String pid2);

    @POST("face/{faceId}/tags")
    Call<NoDataResponse> tagFace(@Path("faceId") String faceId, @Query("tags_str") String tags);

    @GET("face/{faceId}/tags")
    Call<FaceTag> getFaceTags(@Path("faceId") String faceId);

    @GET("faces")
    Call<GetFacesByTagResponse> getFacesByTag(@Query("tag") String tag);

    @POST("group_faces")
    Call<Map<String, List<String>>> groupFaces(@Query("tag") String tag, @Query("model") String model, @Query("thres1") Float thres1, @Query("thres2") Float thres2);

    @POST("phi/group_faces")
    Call<Map<String, List<String>>> phicommGroupFaces(@Query("tag") String tag, @Query("model") String model, @Query("thres1") Float thres1, @Query("thres2") Float thres2);

    @POST("detectperson")
    @Multipart
    Call<DetectPersonResponse> detectPerson(@Part MultipartBody.Part file,
                                            @Part MultipartBody.Part imageUrl,
                                            @Part MultipartBody.Part facenessThres,
                                            @Part MultipartBody.Part similarityThres);

    @POST("resetcache")
    Call<String> resetCache();
}
