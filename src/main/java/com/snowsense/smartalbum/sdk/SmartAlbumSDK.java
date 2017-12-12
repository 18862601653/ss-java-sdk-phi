/**
 * The main SDK class defines the properties (API service's URL,
 * API key and secret) and API methods of the SnowSense Antiporn API web service.
  *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.smartalbum.sdk;

import com.snowsense.RetrofitResponseHandler;
import com.snowsense.SnowSenseSdkException;
import com.snowsense.smartalbum.sdk.rest.SmartAlbumService;
import com.snowsense.smartalbum.sdk.rest.model.ImageClassifyRequest;
import com.snowsense.common.BaseSDK;
import com.snowsense.common.ResultCallback;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class SmartAlbumSDK extends BaseSDK {
    private static final String PARAM_API_KEY = "apikey";
    private static final String PARAM_API_SECRET = "secretkey";

    private static SmartAlbumSDK sInstance;

    private static MediaType IMAGE_MEDIA_TYPE = MediaType.parse("image/*");

    private SmartAlbumService smartalbumService;

    /**
     * Initialize the SDK with server configurations.
     * @param serverUrl
     * @param apiKey
     * @param apiSecret
     */
    public static void init(String serverUrl, String apiKey, String apiSecret) {
        sInstance = new SmartAlbumSDK(serverUrl, apiKey, apiSecret);
    }

    private SmartAlbumSDK(String serverUrl, String apiKey, String apiSecret) {
        super(serverUrl, apiKey, apiSecret);
    }

    /**
     * Get the SDK instance, call {@link SmartAlbumSDK#init(String, String, String)} first
     * @return
     */
    public static SmartAlbumSDK getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Call init first");
        }
        return sInstance;
    }

    /**
     * Examine (classify) whether the images contain porn information.
     * @param imageUrls
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ClassifySceneResult examineImageUrls(String... imageUrls) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().classifyScene(
                ImageClassifyRequest.fromUrls(Arrays.asList(imageUrls))));
    }

    /**
     * Examine (classify) whether the images contain porn information asynchronously
     *
     * @param imageUrls
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void examineImageUrls(List<String> imageUrls, ResultCallback<ClassifySceneResult> callback) throws IOException {
        RetrofitResponseHandler.getResponse(getService().classifyScene(ImageClassifyRequest.fromUrls(imageUrls)), callback);
    }

    /**
     * Load local image files, encode each file to BASE64 string, post JSON-alike string (payload) to the web service,
     * and examine (classify) whether the images contain porn information.
     *
     * @param files array of local image file paths
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ClassifySceneResult examineImageFiles(File... files) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().classifyScene(ImageClassifyRequest.fromFiles(Arrays.asList(files))));
    }

    /**
     * asynchronously
     *
     * @param files
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void examineImageFiles(List<File> files, ResultCallback<ClassifySceneResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().classifyScene(ImageClassifyRequest.fromFiles(files)), callback);
    }



    /**
     *
     * @param heicUrl
     *
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ResponseBody convertHeicUrl(String heicUrl) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().convertHeic(heicUrl));
    }


    /**
     * asynchronously
     *
     * @param heicUrl
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void convertHeicUrl(String heicUrl, ResultCallback<ClassifySceneResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().convertHeic(heicUrl), callback);
    }


    /**
     *
     *
     *
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ExifResult getExifInfo(File... files) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().getExifInfo(ImageClassifyRequest.fromFiles(Arrays.asList(files))));
    }


    /**
     * asynchronously
     *
     * @param files
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void getExifInfo(List<File> files, ResultCallback<ClassifySceneResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().getExifInfo(ImageClassifyRequest.fromFiles(files)), callback);
    }

    /**
     *
     *
     *
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public String resetCache() throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().resetCache());
    }


    /**
     * asynchronously
     *
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void resetCache(ResultCallback<ClassifySceneResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().resetCache(), callback);
    }

    private SmartAlbumService getService() {
        if (smartalbumService == null) {
            smartalbumService = createService(SmartAlbumService.class);
        }

        return smartalbumService;
    }

    private void ensureUrlIsValid(String paramName, String value) {
        // ensure URL has valid URL format
    }
}
