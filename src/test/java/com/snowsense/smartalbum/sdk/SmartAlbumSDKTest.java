package com.snowsense.smartalbum.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

public class SmartAlbumSDKTest {
    public static final String SERVER = "http://bj.xuecong.co:8656/api/";
    public static final String API_KEY = "placeholder";
    public static final String API_SECRET = "placeholder";

    @BeforeClass
    public static void beforeClass() {
        SmartAlbumSDK.init(SERVER, API_KEY, API_SECRET);
    }

    @Test
    public void t01_testImageUrls() throws SnowSenseSdkException {
        ClassifySceneResult result = SmartAlbumSDK.getInstance().examineImageUrls("https://github.com/SnowSense/archive/raw/master/test/road.jpg",
                "http://a0.att.hudong.com/60/59/16300001051406139599596462216.jpg");
        System.out.println(result);
    }

    @Test
    public void t02_testImageFiles() throws SnowSenseSdkException {
        ClassifySceneResult result = SmartAlbumSDK.getInstance().examineImageFiles(
                TestUtils.getFileFromResource("test1.jpg"),
                TestUtils.getFileFromResource("test2.jpg")
        );
        System.out.println(result);
    }

    @Test
    public void t03_testHeicImageUrls() throws SnowSenseSdkException {
        ClassifySceneResult result = SmartAlbumSDK.getInstance().examineImageUrls("https://github.com/SnowSense/ss-java-sdk-phi/blob/master/src/test/resources/ios.heic");
        System.out.println(result);
    }

    @Test
    public void t10_getExif() throws SnowSenseSdkException {
        ExifResult result = SmartAlbumSDK.getInstance().getExifInfo(
                TestUtils.getFileFromResource("test1.jpg"),
                TestUtils.getFileFromResource("test2.jpg"));
        System.out.println(result);
    }

    @Test
    public void testGetExif() throws SnowSenseSdkException {
        ExifResult result = SmartAlbumSDK.getInstance().getExifInfo("https://raw.githubusercontent.com/SnowSense/archive/master/ios2.jpg","http://i3.bbswater.fd.zol-img.com.cn/t_s1200x5000/g5/M00/01/0E/ChMkJ1ZNu6uIGENLAA-gXhD2jzcAAFG1gNG2GgAD6B2489.jpg");
        System.out.println(result);
    }

    @Test
    public void t20_convertHeic() throws SnowSenseSdkException, IOException {
        JPGResponse file = SmartAlbumSDK.getInstance().convertHeicUrl("https://github.com/SnowSense/archive/raw/master/test/ios.heic");

        // TODO: save this img file to local folder
    }

    @Test
    public void testResetCache() throws SnowSenseSdkException {
        ResetCacheString result = SmartAlbumSDK.getInstance().resetCache();
        System.out.println(result);
    }

}
