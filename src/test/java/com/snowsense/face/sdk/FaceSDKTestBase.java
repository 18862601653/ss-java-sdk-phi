package com.snowsense.face.sdk;

import com.snowsense.Logger;
import com.snowsense.SnowSenseSdkException;
import com.snowsense.TestUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
abstract public class FaceSDKTestBase {

    public static final String SERVER = "http://bj.xuecong.co:8652/api/";
    public static final String API_KEY = "testkeyphicomm100";
    public static final String API_SECRET = "209e8934-dd70-4c00-aad0-019b21396eca";
    public static final String IMAGE_URL = "http://img1.gtimg.com/ent/pics/hv1/232/199/1996/129840877.jpg";
    public static final String LEONARDO_DICAPRIO_JPG = "335611-leonardo-dicaprio.jpg";

    private static String personId = null;
    private static String faceId1 = null;
    private static String faceId2 = null;
    private static String firstName = null;

    abstract TestExecutor getTestExecutor();

    @BeforeClass
    public static void beforeClass() {
        FaceSDK.init(SERVER, API_KEY, API_SECRET);

        firstName = "Joe" + System.currentTimeMillis();
    }

    @Test
    public void t010_add_person() throws SnowSenseSdkException {
        personId = doAddPerson(firstName + " Deo");
        assertNotNull(personId);
    }

    @Test
    public void t011_update_person() throws SnowSenseSdkException {
        Map<String, String> data = new HashMap<>();
        data.put("foo", "bar2");
        data.put("sex", "M");
        data.put("name", firstName + " Deo");
        personId = getTestExecutor().updatePerson(personId, data);
        assertNotNull(personId);
    }

    @Test
    public void t012_search_person() throws SnowSenseSdkException {
        List<Person> result = getTestExecutor().searchPersonByName(firstName);
        verifyPersonSearchResult(result);

        result = getTestExecutor().searchPersonByName("dd");
        assertEquals(0, result.size());
    }

    private void verifyPersonSearchResult(List<Person> result) {
        assertEquals(1, result.size());
        assertEquals(firstName + " Deo", result.get(0).getPersonName());
        assertEquals(personId, result.get(0).getPersonId());
        assertEquals(2, result.get(0).getData().size());
        assertEquals("bar2", result.get(0).getData().get("foo"));
    }

    @Test
    public void t02_detect_face_url() throws SnowSenseSdkException {
        DetectFaceResult detectFaceResult = getTestExecutor().detectFace(IMAGE_URL);
        assertEquals(1, detectFaceResult.getFaces().size());
        faceId1 = detectFaceResult.getFaces().get(0).getFaceId();
    }

    @Test
    public void t03_detect_face_file() throws SnowSenseSdkException {
        DetectFaceResult detectFaceResult = getTestExecutor().detectFace(TestUtils.getFileFromResource(LEONARDO_DICAPRIO_JPG));
        assertEquals(1, detectFaceResult.getFaces().size());
        faceId2 = detectFaceResult.getFaces().get(0).getFaceId();
    }

    @Test
    public void t04_compare_faces() throws SnowSenseSdkException {
        CompareResult compareResult = getTestExecutor().compareFaces(faceId1, faceId2);
        assertTrue(compareResult.isSamePerson());
    }

    @Test
    public void t05_label_person() throws SnowSenseSdkException {
        boolean labelResult1 = getTestExecutor().labelFace(personId, faceId1);
        assertTrue(labelResult1);
        boolean labelResult2 = getTestExecutor().labelFace(personId, faceId2);
        assertTrue(labelResult2);
    }

    @Test
    public void t06_identify_face() throws SnowSenseSdkException {
        IdentifyResult identifyResult = getTestExecutor().identifyFace(faceId1);
        //assertEquals(personId, identifyResult.getMostSimilarPersonId());
    }

    @Test
    public void t07_get_person_labels() throws SnowSenseSdkException {
        List<PersonLabel> labels = getTestExecutor().getPersonLabels(personId);
        assertEquals(2, labels.size());
    }

    @Test
    public void t08_get_face_info() throws SnowSenseSdkException {
        FaceResult result = getTestExecutor().getFaceResult(faceId1);
        assertEquals(faceId1, result.getFaceId());
    }

    @Test
    public void t09_merge_person() throws SnowSenseSdkException {
        String personId2 = doAddPerson(firstName + " Deo22");
        PersonMergeResult result = getTestExecutor().mergePerson(personId, personId2);
        assertEquals(personId, result.getPersonId());
        assertEquals(0, result.getCounter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void t20_add_person_invalid_name() throws SnowSenseSdkException {
        getTestExecutor().addPerson("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void t21_compare_faces_invalid_face1() throws SnowSenseSdkException {
        getTestExecutor().compareFaces(faceId1, null);
    }

    @Test
    public void t30_tag_face() throws SnowSenseSdkException {
        getTestExecutor().tagFace(faceId1, new String[] {"foo", "bar"});
        getTestExecutor().tagFace(faceId2, new String[] {"foo"});
    }

    @Test
    public void t31_get_face_tags() throws SnowSenseSdkException {
        FaceTag faceTag = getTestExecutor().getFaceTags(faceId1);
        assertEquals(faceId1, faceTag.getFaceId());
        assertEquals(2, faceTag.getTags().length);
        assertEquals("foo", faceTag.getTags()[0]);
        assertEquals("bar", faceTag.getTags()[1]);
    }

    @Test
    public void t32_get_faces_by_tag() throws SnowSenseSdkException {
        List<FaceTag> faceTags = getTestExecutor().getFacesByTag("foo");
        assertTrue(faceTags.size() > 0);
        assertEquals(faceId1, faceTags.get(0).getFaceId());
        assertEquals(2, faceTags.get(0).getTags().length);
        Logger.get().info("t32_get_faces_by_tag", faceId1 + ": " + faceTags.get(0).getTags()[0] + " " + faceTags.get(0).getTags()[1] );
    }

    @Test
    public void t40_group_faces() throws SnowSenseSdkException {
        Map<String, List<String>> faceGroups = getTestExecutor().groupFaces("foo", "auto", null, null);
        assertNotNull(faceGroups.size());
        assertTrue(faceGroups.size() >= 1);
    }

    @Test
    public void t41_group_faces() throws SnowSenseSdkException {
        Map<String, List<String>> faceGroups = getTestExecutor().groupFaces("foo", "default", 0.85f, 0.75f);
        assertNotNull(faceGroups.size());
        assertTrue(faceGroups.size() >= 1);
    }

    @Test
    public void t42_phicomm_group_faces() throws SnowSenseSdkException {
        Map<String, List<String>> faceGroups = getTestExecutor().phicommGroupFaces("foo", "auto", null, null);
        assertNotNull(faceGroups.size());
        assertTrue(faceGroups.size() >= 1);
    }
//
//    @Test
//    public void t50_detect_person_url() throws SnowSenseSdkException {
//        List<FaceResult> detectPersonResult = getTestExecutor().detectPerson(IMAGE_URL, null, null);
//        assertEquals(1, detectPersonResult.size());
//        assertNotNull(detectPersonResult.get(0).getFaceId());
//        assertNotNull(detectPersonResult.get(0).getPersonId());
//    }
//
//    @Test
//    public void t50_detect_person_file() throws SnowSenseSdkException {
//        List<FaceResult> detectPersonResult = getTestExecutor().detectPerson(TestUtils.getFileFromResource(LEONARDO_DICAPRIO_JPG), null, null);
//        assertEquals(1, detectPersonResult.size());
//    }

    private String doAddPerson(String name) throws SnowSenseSdkException {
        Map<String, String> data = new HashMap<>();
        data.put("foo", "bar");
        data.put("name", name);
        return getTestExecutor().addPerson(firstName + " Deo", data);
    }
}
