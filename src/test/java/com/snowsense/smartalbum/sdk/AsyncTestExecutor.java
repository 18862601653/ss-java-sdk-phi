package com.snowsense.smartalbum.sdk;
import com.snowsense.common.ResultCallback;

import com.snowsense.SnowSenseSdkException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTestExecutor implements TestExecutor{


    @Override
    public ClassifySceneResult t01_testImageUrls(List<String> imageUrls) throws SnowSenseSdkException, IOException {
        TestResultCallback<ClassifySceneResult> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().examineImageUrlsAsync(imageUrls,callback);
        return callback.waitForValue();
    }

    @Override
    public ClassifySceneResult t02_testImageFiles(List<File> files) throws SnowSenseSdkException{
        TestResultCallback<ClassifySceneResult> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().examineImageFiles(files,callback);
        return callback.waitForValue();
    }

    @Override
    public ClassifySceneResult t03_testHeicImageUrls(List<String> imageUrls) throws SnowSenseSdkException, IOException {
        TestResultCallback<ClassifySceneResult> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().examineImageUrlsAsync(imageUrls,callback);
        return callback.waitForValue();
    }

    @Override
    public ExifResult t10_getExif(List<File> files) throws SnowSenseSdkException {
        TestResultCallback<ExifResult> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().getExifInfoAsync(files, callback);
        return callback.waitForValue();
    }

    @Override
    public ExifResult testGetExif(List<String> imageUrls) throws SnowSenseSdkException {
        TestResultCallback<ExifResult> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().getExifInfoAsyn(imageUrls,callback);
        return callback.waitForValue();
    }

    @Override
    public JPGResponse t20_convertHeic(String imageUrl) throws SnowSenseSdkException, IOException {
        TestResultCallback<JPGResponse> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().convertHeicUrl(imageUrl,callback);
        return callback.waitForValue();
    }

    @Override
    public ResetCacheString testResetCache() throws SnowSenseSdkException {
        TestResultCallback<ResetCacheString> callback = new TestResultCallback();
        SmartAlbumSDK.getInstance().resetCache(callback);
        return callback.waitForValue();
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
