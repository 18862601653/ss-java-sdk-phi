package com.snowsense.face.sdk;

import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
abstract public class GrpFaceTestBase {

    public static final String SERVER = "http://bj.xuecong.co:8652/api/";
    public static final String API_KEY = "testkeyphicomm100";
    public static final String API_SECRET = "209e8934-dd70-4c00-aad0-019b21396eca";

    public static final String IMG01_LEONARDO_DICAPRIO = "335611-leonardo-dicaprio.jpg";
    public static final String IMG02_LEONARDO_DICAPRIO = "leonardo-dicaprio2.jpg";
    public static final String IMG03_GUO = "guo01.jpg";
    public static final String IMG04_GUO = "guo02.jpg";


    private static String faceId1 = null;
    private static String faceId2 = null;
    private static String faceId3 = null;
    private static String faceId4 = null;

    abstract TestExecutor getTestExecutor();

    @BeforeClass
    public static void beforeClass() {
        FaceSDK.init(SERVER, API_KEY, API_SECRET);
    }

    @Test
    public void t01_detect_face_file() throws SnowSenseSdkException {
        DetectFaceResult detectFaceResult1 = getTestExecutor().detectFace(TestUtils.getFileFromResource(IMG01_LEONARDO_DICAPRIO));
        assertEquals(1, detectFaceResult1.getFaces().size());
        faceId1 = detectFaceResult1.getFaces().get(0).getFaceId();
        getTestExecutor().tagFace(faceId1, new String[] {"faceset1045"});

        DetectFaceResult detectFaceResult2 = getTestExecutor().detectFace(TestUtils.getFileFromResource(IMG02_LEONARDO_DICAPRIO));
        assertEquals(1, detectFaceResult1.getFaces().size());
        faceId2 = detectFaceResult1.getFaces().get(0).getFaceId();
        getTestExecutor().tagFace(faceId2, new String[] {"faceset1045"});

        DetectFaceResult detectFaceResult3 = getTestExecutor().detectFace(TestUtils.getFileFromResource(IMG03_GUO));
        assertEquals(1, detectFaceResult3.getFaces().size());
        faceId3 = detectFaceResult3.getFaces().get(0).getFaceId();
        getTestExecutor().tagFace(faceId3, new String[] {"faceset1045"});

        DetectFaceResult detectFaceResult4 = getTestExecutor().detectFace(TestUtils.getFileFromResource(IMG04_GUO));
        assertEquals(1, detectFaceResult3.getFaces().size());
        faceId4 = detectFaceResult4.getFaces().get(0).getFaceId();
        getTestExecutor().tagFace(faceId4, new String[] {"faceset1045"});

        List<FaceTag> faceTags2 = getTestExecutor().getFacesByTag("faceset1045");
    }

    @Test
    public void t03_group_faces() throws SnowSenseSdkException {
        Map<String, List<String>> faceGroups = getTestExecutor().groupFaces("faceset1045", "auto", null, null);

    }

    @Test
    public void t04_phicomm_group_faces() throws SnowSenseSdkException {
        Map<String, List<String>> faceGroups = getTestExecutor().groupFaces("faceset1045", "auto", null, null);

    }
}