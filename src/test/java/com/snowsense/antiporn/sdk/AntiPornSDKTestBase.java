package com.snowsense.antiporn.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AntiPornSDKTestBase
{
    public static final String SERVER = "http://bj.xuecong.co:8654/api/";
    public static final String API_KEY = "placeholder";
    public static final String API_SECRET = "placeholder";
    public static final String SHORT_MP4 = "short.mp4";

    abstract TestExecutor getTestExecutor();

    @BeforeClass
    public static void beforeClass() {
        AntiPornSDK.init(SERVER, API_KEY, API_SECRET);
    }

    @Test
    public void testImageUrls() throws SnowSenseSdkException, IOException {
        List<String> imageUrls = new ArrayList<String>();
        imageUrls.add("http://img1.gtimg.com/fashion/pics/hv1/120/157/1968/128009355.png");
        imageUrls.add("http://images.takungpao.com/2015/0106/20150106094012851.jpg");
        ClassifyResult result = getTestExecutor().testImageUrls(imageUrls);
        System.out.println(result);
    }

    @Test
    public void testImageFiles() throws SnowSenseSdkException {
        List<File> files = new ArrayList<File>();
        files.add(TestUtils.getFileFromResource("guo01.jpg"));
        files.add(TestUtils.getFileFromResource("test2.jpg"));
        ClassifyResult result = getTestExecutor().testImageFiles(files);
        System.out.println(result);
    }

    @Test
    public void testHeicImageUrls() throws Exception{
        List<String> imageUrls = new ArrayList<String>();
        imageUrls.add("https://github.com/SnowSense/ss-java-sdk-phi/blob/master/src/test/resources/ios.heic");
        ClassifyResult result = getTestExecutor().testHeicImageUrls(imageUrls);
        System.out.println(result);
    }

    @Test
    public void testVideoFile() throws SnowSenseSdkException {
        VideoClassifyResult result = getTestExecutor().testVideoFile(TestUtils.getFileFromResource(SHORT_MP4),"mp4");
        System.out.println(result);
    }

    @Test
    public void testVideoURL() throws Exception{
        VideoClassifyResult result = getTestExecutor().testVideoURL("http://hc21.aipai.com/user/984/21363984/5251138/card/21692976/card.mp4","mp4");
        System.out.println(result);
    }


    @Test
    public void testResetCache() throws SnowSenseSdkException{
        ResetCacheString result = getTestExecutor().testResetCache();
        System.out.println(result);
    }
}
