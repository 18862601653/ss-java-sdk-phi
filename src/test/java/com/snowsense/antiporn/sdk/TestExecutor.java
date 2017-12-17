package com.snowsense.antiporn.sdk;

import com.snowsense.SnowSenseSdkException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface TestExecutor{


    public ClassifyResult testImageUrls(List<String> imageUrls) throws SnowSenseSdkException,IOException;

    public ClassifyResult testImageFiles(List<File> files) throws SnowSenseSdkException;

    public ClassifyResult testHeicImageUrls(List<String> imageUrls) throws Exception;

    public VideoClassifyResult testVideoFile(File file, String format) throws SnowSenseSdkException;

    public VideoClassifyResult testVideoURL(String uri, String format)throws Exception;

    public com.snowsense.antiporn.sdk.ResetCacheString testResetCache() throws SnowSenseSdkException;



}
