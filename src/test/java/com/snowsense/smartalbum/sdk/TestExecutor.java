package com.snowsense.smartalbum.sdk;

import com.snowsense.SnowSenseSdkException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface TestExecutor {
    public ClassifySceneResult t01_testImageUrls(List<String> imageUrls) throws SnowSenseSdkException, IOException;
    public ClassifySceneResult t02_testImageFiles(List<File> files) throws SnowSenseSdkException;
    public ClassifySceneResult t03_testHeicImageUrls(List<String> imageUrls) throws SnowSenseSdkException,IOException;
    public ExifResult t10_getExif(List<File> files) throws SnowSenseSdkException;
    public ExifResult testGetExif(List<String> imageUrls) throws SnowSenseSdkException;
    public JPGResponse t20_convertHeic(String imageUrl) throws SnowSenseSdkException, IOException ;
    public ResetCacheString testResetCache() throws SnowSenseSdkException;

}
