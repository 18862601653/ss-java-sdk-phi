/**
 * The main SDK class defines the properties (API service's URL,
 * API key and seceret) and API methods
 * of the SnowSense API web service.
 *
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

import com.snowsense.Constants;
import com.snowsense.RetrofitResponseHandler;
import com.snowsense.SnowSenseSdkException;
import com.snowsense.common.BaseSDK;
import com.snowsense.common.ResponseTransformer;
import com.snowsense.common.ResultCallback;
import com.snowsense.common.TextUtils;
import com.snowsense.face.sdk.rest.FaceService;
import com.snowsense.face.sdk.rest.model.*;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FaceSDK extends BaseSDK {
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PERSON_ID = "personId";

    protected static FaceSDK sInstance;

    protected FaceService faceService;

    /**
     * Initialize the SDK with server configurations.
     * @param serverUrl
     * @param apiKey
     * @param apiSecret
     */
    public static void init(String serverUrl, String apiKey, String apiSecret) {
        if (sInstance == null) {
            sInstance = new FaceSDK(serverUrl, apiKey, apiSecret);
        }
    }

    protected FaceSDK(String serverUrl, String apiKey, String apiSecret) {
        super(serverUrl, apiKey, apiSecret);
    }

    /**
     * Get the SDK instance, call {@link FaceSDK#init(String, String, String)} first
     * @return
     */
    public static FaceSDK getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Call init first");
        }
        return sInstance;
    }

    /**
     * Add a person's name and ID to the web service.
     * @param name
     * @return personId
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public String addPerson(String name) throws SnowSenseSdkException {
        return addPerson(name, null);
    }

    /**
     * Add person information to the API server database by passing a name and other fields (e.g. person's information
     * like nickname, age, sex, nationality and etc.
     * @param name
     * @param data
     * @return personId
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public String addPerson(String name, Map<String, String> data) throws SnowSenseSdkException {
        return addOrUpdatePerson(true, name, data);
    }

    /**
     * Add person information to the API server database asynchronously by passing a name and other fields (e.g. person's information
     * like nickname, age, sex, nationality and etc.
     * @param name
     * @param data
     * @param callback
     * @throws IllegalArgumentException if a param does not comply
     */
    public void addPerson(String name, Map<String, String> data, ResultCallback<String> callback) {
        addOrUpdatePerson(true, name, data, callback);
    }

    /**
     * Update a person's data to the web service.
     * @param personId
     * @param data
     * @return personId
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public String updatePerson(String personId, Map<String, String> data) throws SnowSenseSdkException {
        return addOrUpdatePerson(false, personId, data);
    }

    /**
     * Update a person's data to the web service asynchronously.
     * @param personId
     * @param data
     * @param callback
     * @return personId
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void updatePerson(String personId, Map<String, String> data, ResultCallback<String> callback) throws SnowSenseSdkException {
        addOrUpdatePerson(false, personId, data, callback);
    }

    /**
     * Search person by name
     * @param keyword
     * @return
     * @throws SnowSenseSdkException
     */
    public List<Person> searchPersonByName(String keyword) throws SnowSenseSdkException {
        return transformPersonList(RetrofitResponseHandler.getResponse(getFaceService().searchPersonByName(keyword)));
    }

    /**
     * Search person by name asynchronously
     * @param keyword
     * @param callback
     */
    public void searchPersonByName(String keyword, ResultCallback<List<Person>> callback) {
        RetrofitResponseHandler.getResponse(getFaceService().searchPersonByName(keyword), callback,
                new ResponseTransformer<List<Map<String, String>>, List<Person>>() {
            @Override
            public List<Person> transform(List<Map<String, String>> response) {
                return transformPersonList(response);
            }
        });
    }

    private List<Person> transformPersonList(List<Map<String, String>> mapList) {
        List<Person> result = new ArrayList<>();
        if (mapList != null && !mapList.isEmpty()) {
            for (Map<String, String> map : mapList) {
                result.add(new Person(map.remove(FIELD_PERSON_ID), map.remove(FIELD_NAME), map));
            }
        }

        return result;
    }

    /**
     * Merge two personId.
     * @param toPersonId
     * @param fromPersonId
     * @return
     * @throws SnowSenseSdkException
     */
    public PersonMergeResult mergePerson(String toPersonId, String fromPersonId) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("fromPersonId", fromPersonId);
        ensureStringParameterNotEmpty("toPersonId", toPersonId);

        return RetrofitResponseHandler.getResponse(getFaceService().mergePerson(toPersonId, fromPersonId));
    }

    /**
     * Merge two personId asynchronously.
     * @param toPersonId
     * @param fromPersonId
     * @param callback
     * @return
     * @throws SnowSenseSdkException
     */
    public void mergePerson(String toPersonId, String fromPersonId, ResultCallback<PersonMergeResult> callback) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("fromPersonId", fromPersonId);
        ensureStringParameterNotEmpty("toPersonId", toPersonId);

        RetrofitResponseHandler.getResponse(getFaceService().mergePerson(toPersonId, fromPersonId), callback);
    }

    /**
     * Detect face(s) from a web image URL
     * @param imageUrl
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public DetectFaceResult detectFace(String imageUrl) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("imageUrl", imageUrl);

        return RetrofitResponseHandler.getResponse(getFaceService().detectFace(imageUrl));
    }

    /**
     * Detect face(s) from a web image URL asynchronously
     * @param imageUrl
     * @param callback
     */
    public void detectFace(String imageUrl, ResultCallback<DetectFaceResult> callback) {
        ensureStringParameterNotEmpty("imageUrl", imageUrl);

        RetrofitResponseHandler.getResponse(getFaceService().detectFace(imageUrl), callback);
    }

    /**
     * Upload a local image file to the web service and detect it
     * faces in it.
     * @param file
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public DetectFaceResult detectFace(File file) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getFaceService().detectFace(convertFileToBody(file)));
    }

    /**
     * Upload a local image file to the web service and detect it asynchronously
     * faces in it.
     * @param file
     * @param callback
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void detectFace(File file, ResultCallback<DetectFaceResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getFaceService().detectFace(convertFileToBody(file)), callback);
    }

    /**
     * Compare two faces specified by two face IDs. Face ID is an unique
     * ID stored in the web service; each face region has one unique face ID.
     * @param faceId1
     * @param faceId2
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public CompareResult compareFaces(String faceId1, String faceId2) throws SnowSenseSdkException {
        validateCompareParameters(faceId1, faceId2);

        return RetrofitResponseHandler.getResponse(getFaceService().compareFaces(faceId1, faceId2));
    }

    /**
     * * Compare two faces specified by two face IDs asynchronously. Face ID is an unique
     * ID stored in the web service; each face region has one unique face ID.
     * @param faceId1
     * @param faceId2
     * @param callback
     */
    public void compareFaces(String faceId1, String faceId2, ResultCallback<CompareResult> callback) {
        validateCompareParameters(faceId1, faceId2);
        RetrofitResponseHandler.getResponse(getFaceService().compareFaces(faceId1, faceId2), callback);
    }

    private void validateCompareParameters(String faceId1, String faceId2) {
        ensureStringParameterNotEmpty("faceId1", faceId1);
        ensureStringParameterNotEmpty("faceId2", faceId2);
    }

    /**
     * Identify the person by face given by face ID
     * @param faceId
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public IdentifyResult identifyFace(String faceId) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("faceId", faceId);

        return RetrofitResponseHandler.getResponse(getFaceService().identifyFace(faceId));
    }

    /**
     * Identify the person by face given by face ID asynchronously
     * @param faceId
     * @param callback
     */
    public void identifyFace(String faceId, ResultCallback<IdentifyResult> callback) {
        ensureStringParameterNotEmpty("faceId", faceId);

        RetrofitResponseHandler.getResponse(getFaceService().identifyFace(faceId), callback);
    }

    /**
     * Link a person to a face. Also known as 'label a face'.
     * @param faceId
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public boolean labelFace(String personId, String faceId) throws SnowSenseSdkException {
        validateLabelFaceParameters(personId, faceId);

        return transformLabelFaceResponse(RetrofitResponseHandler.getResponse(getFaceService().labelFace(personId, faceId)));
    }

    /**
     * Link a person to a face. Also known as 'label a face' asynchronously
     * @param personId
     * @param faceId
     * @param callback
     */
    public void labelFace(String personId, String faceId, ResultCallback<Boolean> callback) {
        validateLabelFaceParameters(personId, faceId);
        RetrofitResponseHandler.getResponse(getFaceService().labelFace(personId, faceId), callback, new ResponseTransformer<NoDataResponse, Boolean>() {
            @Override
            public Boolean transform(NoDataResponse response) {
                return transformLabelFaceResponse(response);
            }
        });
    }

    private void validateLabelFaceParameters(String personId, String faceId) {
        ensureStringParameterNotEmpty("personId", personId);
        ensureStringParameterNotEmpty("faceId", faceId);
    }

    private Boolean transformLabelFaceResponse(NoDataResponse response) {
        return response != null ? response.isSuccess() : false;
    }

    /**
     * Use person_id to find all face IDs associated (linked) with
     * this person in the web service system.
     * @param personId
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public List<PersonLabel> getPersonLabels(String personId) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("personId", personId);

        return RetrofitResponseHandler.getResponse(getFaceService().getPersonLabels(personId));
    }

    /**
     * Use person_id to find all face IDs associated (linked) with
     * this person in the web service system asynchronously.
     * @param personId
     * @param callback
     */
    public void getPersonLabels(String personId, ResultCallback<List<PersonLabel>> callback) {
        ensureStringParameterNotEmpty("personId", personId);

        RetrofitResponseHandler.getResponse(getFaceService().getPersonLabels(personId), callback);
    }

    /**
     * Retrieve detected face information (stored in API server)
     * by the given faceId.
     * @param faceId
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public FaceResult getFaceResult(String faceId) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("faceId", faceId);

        return RetrofitResponseHandler.getResponse(getFaceService().getFaceResult(faceId));
    }

    public void getFaceResult(String faceId, ResultCallback<FaceResult> callback) {
        ensureStringParameterNotEmpty("faceId", faceId);

        RetrofitResponseHandler.getResponse(getFaceService().getFaceResult(faceId), callback);
    }

    /**
     * 给指定人脸ID打上一个或多个标签 (Tags)
     *
     * @param faceId
     * @param tags: tag的字符串数组, (不区分大小写，字符串末尾的空格自动忽略)
     *
     * @return
     * @throws SnowSenseSdkException
     */
    public void tagFace(String faceId, String[] tags) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("faceId", faceId);
        if (tags == null || tags.length < 1) {
            throw new IllegalArgumentException("tags is empty");
        }

        NoDataResponse response = RetrofitResponseHandler.getResponse(getFaceService().tagFace(faceId, TextUtils.join(tags, ",")));

        if (response == null || !response.isSuccess()) {
            throw new SnowSenseSdkException(response != null ? response.getError() : null, SnowSenseSdkException.STATUS_SERVER_ERROR);
        }
    }

    /**
     * 获取指定人脸ID所相关联的 Tag(s)
     *
     * @param faceId
     *
     * @return
     * @throws SnowSenseSdkException
     */
    public FaceTag getFaceTags(String faceId) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("faceId", faceId);
        return RetrofitResponseHandler.getResponse(getFaceService().getFaceTags(faceId));
    }

    /**
     * 获取指定的 tag 中所有的人脸ID
     *
     * @param tag, Tag的名称 (不区分大小写，字符串末尾的空格自动忽略)
     *
     * @return
     * @throws SnowSenseSdkException
     */
    public List<FaceTag> getFacesByTag(String tag) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("tag", tag);
        GetFacesByTagResponse facesByTagResult = RetrofitResponseHandler.getResponse(getFaceService().getFacesByTag(tag));
        if (facesByTagResult == null || facesByTagResult.getError() != null) {
            throw new SnowSenseSdkException(facesByTagResult != null ? facesByTagResult.getError() : null, SnowSenseSdkException.STATUS_SERVER_ERROR);
        } else {
            return facesByTagResult.getFaces();
        }
    }

    /**
     * 将指定的 tag 中所有的人脸做无底库分组，并返回分组结果：组的标识，以及其中的人脸ID
     *
     * @param tag, Tag的名称 (不区分大小写，字符串末尾的空格自动忽略)
     * @param model
     *          'auto' or 'default'; 如不指定 model参数，系统默值 'default'
     * @param thres1
     *          仅在 model为'default'下有效; 系统默认值 0.8
     * @param thres2
     *          仅在 model为'default'下有效; 系统默认值 0.7
     *
     * @return
     * @throws SnowSenseSdkException
     */
    public Map<String, List<String>> groupFaces(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("tag", tag);
        return RetrofitResponseHandler.getResponse(getFaceService().groupFaces(tag, model, thres1, thres2));
    }

    /**
     * 将指定的 tag 中所有的人脸做无底库分组，根据分组结果创建 personId，将相关的 faceId 与该personId关联起来；
     * 最后返回分组结果：租的标识为personId, 以及其中的人脸ID
     *
     * @param tag
     * @param model
     *          'auto' or 'default'; 如不指定 model参数，系统默值 'default'
     * @param thres1
     *          仅在 model为'default'下有效; 系统默认值 0.8
     * @param thres2
     *          仅在 model为'default'下有效; 系统默认值 0.7
     * @return
     * @throws SnowSenseSdkException
     */
    public Map<String, List<String>> groupFacesPhicomm(String tag, String model, Float thres1, Float thres2) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("tag", tag);
        return RetrofitResponseHandler.getResponse(getFaceService().phicommGroupFaces(tag, model, thres1, thres2));
    }

    /**
     * 从照片中检测人脸，按指定的阀值寻找最近似的人物 (personId), 并将 faceId与该personId相关联；如果找不到近似的人物，
     * 则创建一个新的personId，并将 faceId与该personId相关联。
     *
     * @param imageUrl
     * @param facenessThres
     *          检测人脸时确认是人脸的最小阀值, optional, default 0.4
     * @param similarityThres
     *          人脸识别时确认是一个人最小的相似度阀值, optional, default 0.77
     * @return
     * @throws SnowSenseSdkException
     */
    public List<FaceResult> detectPerson(String imageUrl, Float facenessThres, Float similarityThres) throws SnowSenseSdkException {
        ensureStringParameterNotEmpty("imageUrl", imageUrl);

        return convertDetectPersonResponse(imageUrl, null, facenessThres, similarityThres);
    }

    /**
     * 从照片中检测人脸，按指定的阀值寻找最近似的人物 (personId), 并将 faceId与该personId相关联；如果找不到近似的人物，
     * 则创建一个新的personId，并将 faceId与该personId相关联。
     *
     * @param imageFile
     * @param facenessThres
     *          检测人脸时确认是人脸的最小阀值, optional, default 0.4
     * @param similarityThres
     *          人脸识别时确认是一个人最小的相似度阀值, optional, default 0.77
     * @return
     * @throws SnowSenseSdkException
     */
    public List<FaceResult> detectPerson(File imageFile, Float facenessThres, Float similarityThres)
            throws SnowSenseSdkException {
        ensureParameterNotNull("imageFile", imageFile);
        return convertDetectPersonResponse(null, imageFile, facenessThres, similarityThres);
    }


    /**
     * 清除 Web服务侧的缓存临时文件。 Web服务侧API都有自动清除临时文件，在runtime错误的情况
     * 下可能有部分文件无法自动清除的情况，因此提供本接口。resetCache无需频繁调用。
     * 
     * @return
     * @throws SnowSenseSdkException
     *
     */
    public String resetCache() throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getFaceService().resetCache());
    }


    private List<FaceResult> convertDetectPersonResponse(String imageUrl, File imageFile,
                                                         Float facenessThres, Float similarityThres) throws SnowSenseSdkException {
        MultipartBody.Part filePart = imageFile != null ? convertFileToBody(imageFile) : null;
        MultipartBody.Part urlPart = imageUrl != null ? MultipartBody.Part.createFormData("im_uri", imageUrl) : null;
        MultipartBody.Part facenessThresPart = facenessThres != null ? MultipartBody.Part.createFormData("faceness_thres", facenessThres.toString()) : null;
        MultipartBody.Part similarityThresPart = similarityThres != null ? MultipartBody.Part.createFormData("similarity_thres", similarityThres.toString()) : null;

        DetectPersonResponse response = RetrofitResponseHandler.getResponse(getFaceService().detectPerson(filePart,
                urlPart, facenessThresPart, similarityThresPart));

        if (response != null) {
            return response.getPersons();
        }

        return Collections.emptyList();
    }

    private Map<String, String> validateAddOrUpdatePerson(boolean isAdd, String personKey, Map<String, String> data) {
        String key = isAdd ? FIELD_NAME : FIELD_PERSON_ID;
        ensureStringParameterNotEmpty(key, personKey);

        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, personKey);

        return data;
    }

    private String addOrUpdatePerson(boolean isAdd, String personKey, Map<String, String> data) throws SnowSenseSdkException {
        AddPersonResponse addPersonResponse = RetrofitResponseHandler.getResponse(getFaceService().addPerson(
                validateAddOrUpdatePerson(isAdd, personKey, data)));
        return addPersonResponse != null ? addPersonResponse.getPersonId() : null;
    }

    private void addOrUpdatePerson(boolean isAdd, String personKey, Map<String, String> data,
                                     ResultCallback<String> callback) {
        RetrofitResponseHandler.getResponse(getFaceService().addPerson(validateAddOrUpdatePerson(isAdd, personKey, data)), callback,
                new ResponseTransformer<AddPersonResponse, String>() {
                    @Override
                    public String transform(AddPersonResponse response) {
                        return response != null ? response.getPersonId() : null;
                    }
                });
    }

    private FaceService getFaceService() {
        if (faceService == null) {
            faceService = createService(FaceService.class);
        }

        return faceService;
    }
}
