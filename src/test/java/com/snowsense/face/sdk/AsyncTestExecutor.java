package com.snowsense.face.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.common.ResultCallback;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTestExecutor implements TestExecutor {
    @Override
    public String addPerson(String name, Map<String, String> data) throws SnowSenseSdkException {
        TestResultCallback<String> callback = new TestResultCallback();
        FaceSDK.getInstance().addPerson(name, data, callback);
        return callback.waitForValue();
    }

    @Override
    public String updatePerson(String personId, Map<String, String> data) throws SnowSenseSdkException {
        TestResultCallback<String> callback = new TestResultCallback();
        FaceSDK.getInstance().updatePerson(personId, data, callback);
        return callback.waitForValue();
    }

    @Override
    public List<Person> searchPersonByName(String keyword) throws SnowSenseSdkException {
        TestResultCallback<List<Person>> callback = new TestResultCallback();
        FaceSDK.getInstance().searchPersonByName(keyword, callback);
        return callback.waitForValue();
    }

    @Override
    public PersonMergeResult mergePerson(String toPersonId, String fromPersonId) throws SnowSenseSdkException {
        TestResultCallback<PersonMergeResult> callback = new TestResultCallback();
        FaceSDK.getInstance().mergePerson(toPersonId, fromPersonId, callback);
        return callback.waitForValue();
    }

    @Override
    public DetectFaceResult detectFace(String imageUrl) throws SnowSenseSdkException {
        TestResultCallback<DetectFaceResult> callback = new TestResultCallback();
        FaceSDK.getInstance().detectFace(imageUrl, callback);
        return callback.waitForValue();
    }

    @Override
    public DetectFaceResult detectFace(File file) throws SnowSenseSdkException {
        TestResultCallback<DetectFaceResult> callback = new TestResultCallback();
        FaceSDK.getInstance().detectFace(file, callback);
        return callback.waitForValue();
    }

    @Override
    public CompareResult compareFaces(String faceId1, String faceId2) throws SnowSenseSdkException {
        TestResultCallback<CompareResult> callback = new TestResultCallback();
        FaceSDK.getInstance().compareFaces(faceId1, faceId2, callback);
        return callback.waitForValue();
    }

    @Override
    public IdentifyResult identifyFace(String faceId) throws SnowSenseSdkException {
        TestResultCallback<IdentifyResult> callback = new TestResultCallback();
        FaceSDK.getInstance().identifyFace(faceId, callback);
        return callback.waitForValue();
    }

    @Override
    public boolean labelFace(String personId, String faceId) throws SnowSenseSdkException {
        TestResultCallback<Boolean> callback = new TestResultCallback();
        FaceSDK.getInstance().labelFace(personId, faceId, callback);
        return callback.waitForValue();
    }

    @Override
    public List<PersonLabel> getPersonLabels(String personId) throws SnowSenseSdkException {
        TestResultCallback<List<PersonLabel>> callback = new TestResultCallback();
        FaceSDK.getInstance().getPersonLabels(personId, callback);
        return callback.waitForValue();
    }

    @Override
    public FaceResult getFaceResult(String faceId) throws SnowSenseSdkException {
        TestResultCallback<FaceResult> callback = new TestResultCallback();
        FaceSDK.getInstance().getFaceResult(faceId, callback);
        return callback.waitForValue();
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

    static class TestResultCallback<T> implements ResultCallback<T> {
        private CountDownLatch latch = new CountDownLatch(1);
        private ValueHolder<T> valueHolder = new ValueHolder<>();

        @Override
        public void onResult(T result) {
            valueHolder.value = result;
            latch.countDown();
        }

        @Override
        public void onError(int errorCode, String message) {
            latch.countDown();
        }

        public T waitForValue() throws SnowSenseSdkException {
            try {
                latch.await(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new SnowSenseSdkException(e, SnowSenseSdkException.STATUS_NETWORK_ERROR);
            }
            return valueHolder.value;
        }

        class ValueHolder<T> {
            volatile T value;
        }
    }
}
