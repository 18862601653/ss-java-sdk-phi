package com.snowsense.antiporn.sdk;


import com.snowsense.SnowSenseSdkException;
import com.snowsense.common.ResultCallback;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTestExecutor implements TestExecutor{
    @Override
    public ClassifyResult testImageUrls(List<String> imageUrls) throws SnowSenseSdkException,IOException {
        TestResultCallback<ClassifyResult> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineImageUrls(imageUrls,callback);
        return callback.waitForValue();
    }

    @Override
    public ClassifyResult testImageFiles(List<File> files) throws SnowSenseSdkException {
        TestResultCallback<ClassifyResult> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineImageFilesAsync(files,callback);
        return callback.waitForValue();
    }

    @Override
    public ClassifyResult testHeicImageUrls(List<String> imageUrls) throws Exception {
        TestResultCallback<ClassifyResult> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineImageUrls(imageUrls,callback);
        return callback.waitForValue();
    }

    @Override
    public VideoClassifyResult testVideoFile(File file, String format) throws SnowSenseSdkException {
        TestResultCallback<VideoClassifyResult> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineVideoFile(file,format,callback);
        return callback.waitForValue();
    }

    @Override
    public VideoClassifyResult testVideoURL(String uri, String format) throws Exception{
        TestResultCallback<VideoClassifyResult> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineVideoUrls(uri,format,callback);
        return callback.waitForValue();
    }

    @Override
    public ResetCacheString testResetCache() throws SnowSenseSdkException {
        TestResultCallback<ResetCacheString> callback = new TestResultCallback();
        AntiPornSDK.getInstance().examineResetCache(callback);
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
                latch.await(6000, TimeUnit.MILLISECONDS);
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
