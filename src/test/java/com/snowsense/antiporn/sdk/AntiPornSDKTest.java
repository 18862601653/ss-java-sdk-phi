package com.snowsense.antiporn.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class AntiPornSDKTest {
    public static final String SERVER = "http://bj.xuecong.co:8654/api/";
    public static final String API_KEY = "placeholder";
    public static final String API_SECRET = "placeholder";

    @BeforeClass
    public static void beforeClass() {
        AntiPornSDK.init(SERVER, API_KEY, API_SECRET);
    }

    @Test
    public void testImageUrls() throws SnowSenseSdkException {
        ClassifyResult result = AntiPornSDK.getInstance().examineImageUrls("http://img1.gtimg.com/fashion/pics/hv1/120/157/1968/128009355.png",
                "http://images.takungpao.com/2015/0106/20150106094012851.jpg");
        System.out.println(result);
    }

    @Test
    public void testImageFiles() throws SnowSenseSdkException {
        ClassifyResult result = AntiPornSDK.getInstance().examineImageFiles(
                TestUtils.getFileFromResource("guo01.jpg"),
                TestUtils.getFileFromResource("test2.jpg")
        );
        System.out.println(result);
    }
}
