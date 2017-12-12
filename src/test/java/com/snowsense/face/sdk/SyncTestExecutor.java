package com.snowsense.face.sdk;

import com.snowsense.SnowSenseSdkException;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SyncTestExecutor implements TestExecutor {
    @Override
    public String addPerson(String name, Map<String, String> data) throws SnowSenseSdkException {
        return FaceSDK.getInstance().addPerson(name, data);
    }

    @Override
    public String updatePerson(String personId, Map<String, String> data) throws SnowSenseSdkException {
        return FaceSDK.getInstance().updatePerson(personId, data);
    }

    @Override
    public List<Person> searchPersonByName(String keyword) throws SnowSenseSdkException {
        return FaceSDK.getInstance().searchPersonByName(keyword);
    }

    @Override
    public PersonMergeResult mergePerson(String toPersonId, String fromPersonId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().mergePerson(toPersonId, fromPersonId);
    }

    @Override
    public DetectFaceResult detectFace(String imageUrl) throws SnowSenseSdkException {
        return FaceSDK.getInstance().detectFace(imageUrl);
    }

    @Override
    public DetectFaceResult detectFace(File file) throws SnowSenseSdkException {
        return FaceSDK.getInstance().detectFace(file);
    }

    @Override
    public CompareResult compareFaces(String faceId1, String faceId2) throws SnowSenseSdkException {
        return FaceSDK.getInstance().compareFaces(faceId1, faceId2);
    }

    @Override
    public IdentifyResult identifyFace(String faceId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().identifyFace(faceId);
    }

    @Override
    public boolean labelFace(String personId, String faceId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().labelFace(personId, faceId);
    }

    @Override
    public List<PersonLabel> getPersonLabels(String personId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().getPersonLabels(personId);
    }

    @Override
    public FaceResult getFaceResult(String faceId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().getFaceResult(faceId);
    }

    @Override
    public void tagFace(String faceId, String[] tags) throws SnowSenseSdkException {
        FaceSDK.getInstance().tagFace(faceId, tags);
    }

    @Override
    public FaceTag getFaceTags(String faceId) throws SnowSenseSdkException {
        return FaceSDK.getInstance().getFaceTags(faceId);
    }

    @Override
    public List<FaceTag> getFacesByTag(String tag) throws SnowSenseSdkException {
        return FaceSDK.getInstance().getFacesByTag(tag);
    }

    @Override
    public Map<String, List<String>> groupFaces(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException {
        return FaceSDK.getInstance().groupFaces(tag, model, thres1, thres2);
    }

    @Override
    public Map<String, List<String>> phicommGroupFaces(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException {
        return FaceSDK.getInstance().groupFacesPhicomm(tag, model, thres1, thres2);
    }

    @Override
    public List<FaceResult> detectPerson(String imageUrl, Float facenessThres, Float similarityThres) throws SnowSenseSdkException {
        return FaceSDK.getInstance().detectPerson(imageUrl, facenessThres, similarityThres);
    }

    @Override
    public List<FaceResult> detectPerson(File imageFile, Float facenessThres, Float similarityThres) throws SnowSenseSdkException {
        return FaceSDK.getInstance().detectPerson(imageFile, facenessThres, similarityThres);
    }
}
