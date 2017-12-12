package com.snowsense.face.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.face.sdk.rest.model.DetectPersonResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface TestExecutor {
    String addPerson(String name, Map<String, String> data) throws SnowSenseSdkException;

    String updatePerson(String personId, Map<String, String> data) throws SnowSenseSdkException;

    List<Person> searchPersonByName(String keyword) throws SnowSenseSdkException;

    PersonMergeResult mergePerson(String toPersonId, String fromPersonId) throws SnowSenseSdkException;

    DetectFaceResult detectFace(String imageUrl) throws SnowSenseSdkException;

    DetectFaceResult detectFace(File file) throws SnowSenseSdkException;

    CompareResult compareFaces(String faceId1, String faceId2) throws SnowSenseSdkException;

    IdentifyResult identifyFace(String faceId) throws SnowSenseSdkException;

    boolean labelFace(String personId, String faceId) throws SnowSenseSdkException;

    List<PersonLabel> getPersonLabels(String personId) throws SnowSenseSdkException;

    FaceResult getFaceResult(String faceId) throws SnowSenseSdkException;

    void tagFace(String faceId, String[] tags) throws SnowSenseSdkException;

    FaceTag getFaceTags(String faceId) throws SnowSenseSdkException;

    List<FaceTag> getFacesByTag(String tag) throws SnowSenseSdkException;

    Map<String, List<String>> groupFaces(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException;

    Map<String, List<String>> phicommGroupFaces(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException;

    List<FaceResult> detectPerson(String imageUrl, Float facenessThres, Float similarityThres)
            throws SnowSenseSdkException;

    List<FaceResult> detectPerson(File imageFile, Float facenessThres, Float similarityThres)
            throws SnowSenseSdkException;
}
