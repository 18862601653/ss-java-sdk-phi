package com.snowsense.app;

import com.snowsense.Logger;
import com.snowsense.SnowSenseSdkException;
import com.snowsense.face.sdk.DetectFaceResult;
import com.snowsense.face.sdk.FaceResult;
import com.snowsense.face.sdk.FaceSDK;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SmartAlbumApp {
    public static final String TAG = "SmartAlbumApp";

    private FaceSDK faceSDK;

    private static SmartAlbumApp sSmartAlbumApp;

    private SmartAlbumApp(FaceSDK faceSDK) {
        this.faceSDK = faceSDK;
    }

    public static void init(FaceSDK faceSDK) {
        if (sSmartAlbumApp == null) {
            sSmartAlbumApp = new SmartAlbumApp(faceSDK);
        }
    }

    public static SmartAlbumApp get() {
        if (sSmartAlbumApp == null) {
            throw new IllegalStateException("Call init first");
        }

        return sSmartAlbumApp;
    }

    public Map<String, List<FaceResult>> batchDetect(File dir, String[] extensions, final boolean recursive, String[] tags) {
        Collection<File> files = FileUtils.listFiles(dir, extensions, recursive);

        // Detect and tag face
        Map<String, List<FaceResult>> detectionResult = new HashMap<>(files.size());
        for (File f : files) {
            Logger.get().info(TAG, "detecting face " + f.getPath());
            try {
                DetectFaceResult detectFaceResult = faceSDK.detectFace(f);
                if (detectFaceResult != null && detectFaceResult.getFaces() != null && !detectFaceResult.getFaces().isEmpty()) {
                    detectionResult.put(f.getAbsolutePath(), detectFaceResult.getFaces());

                    // Tag face
                    for (FaceResult faceResult : detectFaceResult.getFaces()) {
                        Logger.get().info(TAG, "tagging face " + faceResult.getFaceId());
                        faceSDK.tagFace(faceResult.getFaceId(), tags);
                    }
                }
            } catch (SnowSenseSdkException e) {
                Logger.get().error(TAG, "batchDetect.detectFace failed for file " + f.getPath(), e);
            }
        }

        return detectionResult;
    }

    public Map<String, List<String>> groupFaces(String tag) {
        // Group
        try {
            // Map<String, List<String>> groupResult = faceSDK.groupFaces(tag, null, null);
            Map<String, List<String>> groupResult = faceSDK.groupFaces(tag, "auto", null, null);
            if (groupResult != null && !groupResult.isEmpty()) {
                Logger.get().debug(TAG, "groupFaces result = " + groupResult.toString());
                return groupResult;
            } else {
                Logger.get().error(TAG, "groupFaces, no group is found");
            }
        } catch (SnowSenseSdkException e) {
            Logger.get().error(TAG, "groupFaces", e);
        }

        return null;
    }

    public void copyFileByGroup(Map<String, List<FaceResult>> faceDetectionResult, Map<String, List<String>> groupResult,
                                File destinationDir) {
        Logger.get().debug(TAG, "copyFileByGroup to " + destinationDir.getAbsolutePath());
        if (faceDetectionResult == null || faceDetectionResult.isEmpty() || groupResult == null || groupResult.isEmpty()) {
            return;
        }

        Map<String, String> faceIdToFileMap = new HashMap<>(faceDetectionResult.size());
        for (Map.Entry<String, List<FaceResult>> e : faceDetectionResult.entrySet()) {
            if (e.getValue() != null && !e.getValue().isEmpty()) {
                for (FaceResult f : e.getValue()) {
                    faceIdToFileMap.put(f.getFaceId(), e.getKey());
                }
            }
        }

        for (Map.Entry<String, List<String>> e : groupResult.entrySet()) {
            File groupDir = new File(destinationDir, e.getKey());
            if (!groupDir.exists()) {
                groupDir.mkdirs();
            }

            if (e.getValue() != null && !e.getValue().isEmpty()) {
                for (String f : e.getValue()) {
                    if (faceIdToFileMap.containsKey(f)) {
                        try {
                            FileUtils.copyFileToDirectory(new File(faceIdToFileMap.get(f)), groupDir);
                        } catch (IOException e1) {
                            Logger.get().error(TAG, "copyFileByGroup failed for file " + faceIdToFileMap.get(f), e1);
                        }
                    }
                }
            }
        }
    }
}
