package com.snowsense.smartalbum.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import okhttp3.ResponseBody;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void t10_getExif() throws SnowSenseSdkException {
        ExifResult result = SmartAlbumSDK.getInstance().getExifInfo(
                TestUtils.getFileFromResource("test1.jpg"),
                TestUtils.getFileFromResource("test2.jpg"));
        System.out.println(result);
    }


    @Test
    public void t20_convertHeic() throws SnowSenseSdkException {
        ResponseBody img_file = SmartAlbumSDK.getInstance().convertHeicUrl("https://github.com/SnowSense/archive/raw/master/test/ios.heic");
        // TODO: save this img file to local folder
    }

}
