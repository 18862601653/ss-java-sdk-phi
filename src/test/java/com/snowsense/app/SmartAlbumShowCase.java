package com.snowsense.app;

import com.snowsense.Logger;
import com.snowsense.TestUtils;
import com.snowsense.face.sdk.FaceResult;
import com.snowsense.face.sdk.FaceSDK;
import com.snowsense.face.sdk.FaceSDKTestBase;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SmartAlbumShowCase {

    private static File groupDestinationDir = new File("SmartAlbumShowCase" + System.currentTimeMillis());

    @BeforeClass
    public static void setup() {
        FaceSDK.init(FaceSDKTestBase.SERVER, FaceSDKTestBase.API_KEY, FaceSDKTestBase.API_SECRET);
        SmartAlbumApp.init(FaceSDK.getInstance());
        Logger.get().setLogLevel(Logger.LogLevel.Debug);
    }

    @AfterClass
    public static void teardown() {
        //TODO keep the dir to visual check for now
//        try {
//            FileUtils.deleteDirectory(groupDestinationDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testBatchDetection() {
        String tag = "autotag" + String.valueOf(System.currentTimeMillis()) + new Random().nextInt();
        String[] tags = new String[] {tag};
        Map<String, List<FaceResult>> detectedFaces = SmartAlbumApp.get().batchDetect(getTestResourcesDir(), new String[] {"jpg"}, false, tags);
        Map<String, List<String>> groupResult = SmartAlbumApp.get().groupFaces(tag);

        SmartAlbumApp.get().copyFileByGroup(detectedFaces, groupResult, groupDestinationDir);
    }

    private File getTestResourcesDir() {
        // use resource folder as input folder
        File imageFile = TestUtils.getFileFromResource(FaceSDKTestBase.LEONARDO_DICAPRIO_JPG);
        System.out.println("getTestResourcesDir = " + imageFile.getAbsolutePath());

        if (imageFile != null) {
            return imageFile.getParentFile();
        }

        // or simply specify a test input folder here:
        return new File("/path/to/your/dir");
    }
}
