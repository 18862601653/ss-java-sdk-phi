package com.snowsense.smartalbum.sdk;


import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
abstract public class SmartAlbumSDKTestBase {

    public static final String SERVER = "http://bj.xuecong.co:8656/api/";
    public static final String API_KEY = "placeholder";
    public static final String API_SECRET = "placeholder";

    abstract TestExecutor getTestExecutor();

    @BeforeClass
    public static void beforeClass() {
        SmartAlbumSDK.init(SERVER, API_KEY, API_SECRET);
    }

    @Test
    public void t01_testImageUrls() throws Exception {
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://github.com/SnowSense/archive/raw/master/test/road.jpg");
        imageUrls.add("http://a0.att.hudong.com/60/59/16300001051406139599596462216.jpg");
        ClassifySceneResult result = getTestExecutor().t01_testImageUrls(imageUrls);
        System.out.println(result);
    }

    @Test
    public void t02_testImageFiles() throws SnowSenseSdkException{
        ArrayList<File> files = new ArrayList<File>();
        files.add(TestUtils.getFileFromResource("test1.jpg"));
        files.add(TestUtils.getFileFromResource("test2.jpg"));
        ClassifySceneResult result = getTestExecutor().t02_testImageFiles(files);
        System.out.println(result);
    }

    @Test
    public void t03_testHeicImageUrls() throws SnowSenseSdkException, IOException {
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://github.com/SnowSense/ss-java-sdk-phi/blob/master/src/test/resources/ios.heic");
        ClassifySceneResult result = getTestExecutor().t03_testHeicImageUrls(imageUrls);
        System.out.println(result);
    }

    @Test
    public void t10_getExif() throws SnowSenseSdkException {
        ArrayList<File> files = new ArrayList<File>();
        files.add(TestUtils.getFileFromResource("test1.jpg"));
        files.add(TestUtils.getFileFromResource("test2.jpg"));
        ExifResult result = getTestExecutor().t10_getExif(files);
        System.out.println(result);
    }

    @Test
    public void testGetExif() throws SnowSenseSdkException {
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://raw.githubusercontent.com/SnowSense/archive/master/ios2.jpg");
        imageUrls.add("http://a0.att.hudong.com/60/59/16300001051406139599596462216.jpg");
        ExifResult result = getTestExecutor().testGetExif(imageUrls);
        System.out.println(result);
    }

    @Test
    public void t20_convertHeic() throws SnowSenseSdkException, IOException {
        JPGResponse result = getTestExecutor().t20_convertHeic("https://github.com/SnowSense/archive/raw/master/test/ios.heic");
        System.out.println(result);
    }

    @Test
    public void testResetCache() throws SnowSenseSdkException {
        ResetCacheString result = getTestExecutor().testResetCache();
        System.out.println(result);
    }


}
