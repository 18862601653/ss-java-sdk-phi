/**
 * The main SDK class defines the properties (API service's URL,
 * API key and secret) and API methods of the SnowSense Antiporn API web service.
  *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.antiporn.sdk;

import com.snowsense.RetrofitResponseHandler;
import com.snowsense.SnowSenseSdkException;
import com.snowsense.antiporn.sdk.rest.AntiPornService;
import com.snowsense.antiporn.sdk.rest.model.ImageClassifyRequest;
import com.snowsense.common.BaseSDK;
import com.snowsense.common.ResultCallback;
import okhttp3.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AntiPornSDK extends BaseSDK {
    private static final String PARAM_API_KEY = "apikey";
    private static final String PARAM_API_SECRET = "secretkey";

    private static AntiPornSDK sInstance;

    private static MediaType IMAGE_MEDIA_TYPE = MediaType.parse("image/*");

    private AntiPornService antiPornService;

    /**
     * Initialize the SDK with server configurations.
     * @param serverUrl
     * @param apiKey
     * @param apiSecret
     */
    public static void init(String serverUrl, String apiKey, String apiSecret) {
        sInstance = new AntiPornSDK(serverUrl, apiKey, apiSecret);
    }

    private AntiPornSDK(String serverUrl, String apiKey, String apiSecret) {
        super(serverUrl, apiKey, apiSecret);
    }

    /**
     * Get the SDK instance, call {@link AntiPornSDK#init(String, String, String)} first
     * @return
     */
    public static AntiPornSDK getInstance() {
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
    public ClassifyResult examineImageUrls(String... imageUrls) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().aduitImage(
                ImageClassifyRequest.fromUrls(Arrays.asList(imageUrls))));
    }

    /**
     * Examine (classify) whether the images contain porn information asynchronously
     * @param imageUrls
     * @param callback
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void examineImageUrls(List<String> imageUrls, ResultCallback<ClassifyResult> callback) throws IOException {
        RetrofitResponseHandler.getResponse(getService().aduitImage(ImageClassifyRequest.fromUrls(imageUrls)), callback);
    }

    /**
     * Load local image files, encode each file to BASE64 string, post JSON-alike string (payload) to the web service,
     * and examine (classify) whether the images contain porn information.
     * @param files array of local image file paths
     * @return
     * @throws IOException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ClassifyResult examineImageFiles(File... files) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().aduitImage(ImageClassifyRequest.fromFiles(Arrays.asList(files))));
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
    public void examineImageFiles(List<File> files, ResultCallback<ClassifyResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().aduitImage(ImageClassifyRequest.fromFiles(files)), callback);
    }


    /**
     *
     * 
     * @param file:
     * @param format:
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public ClassifyResult examineVideoFile(File file, String format) throws SnowSenseSdkException {
        return RetrofitResponseHandler.getResponse(getService().aduitVideo(convertFileToBody(file), format));
    }

    /**
     * asynchronously
     * 
     * @param file:
     * @param format: 
     * @return
     * @throws SnowSenseSdkException
     * @throws IllegalArgumentException if a param does not comply
     */
    public void examineVideoFile(File file, String format, ResultCallback<ClassifyResult> callback) throws SnowSenseSdkException {
        RetrofitResponseHandler.getResponse(getService().aduitVideo(convertFileToBody(file), format), callback);
    }

    private AntiPornService getService() {
        if (antiPornService == null) {
            antiPornService = createService(AntiPornService.class);
        }

        return antiPornService;
    }

    private void ensureUrlIsValid(String paramName, String value) {
        // ensure URL has valid URL format
    }
}
