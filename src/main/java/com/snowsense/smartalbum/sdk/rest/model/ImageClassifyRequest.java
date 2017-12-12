package com.snowsense.smartalbum.sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.snowsense.Base64Provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ImageClassifyRequest {
    private List<ImageEntry> images = new ArrayList<>();

    public static ImageClassifyRequest fromUrls(List<String> imageUrls) {
        ImageClassifyRequest request = new ImageClassifyRequest();
        if (imageUrls != null) {
            for (String s : imageUrls) {
                request.images.add(new ImageEntry(s));
            }
        }

        return request;
    }

    public static ImageClassifyRequest fromFiles(List<File> imageFiles) {
        ImageClassifyRequest request = new ImageClassifyRequest();
        if (imageFiles != null) {
            for (File f : imageFiles) {
                request.images.add(new ImageEntry(f));
            }
        }

        return request;
    }

    private static class ImageEntry extends BaseEntry {
        @Expose(serialize = false, deserialize = false)
        private static final String FILE_PREFIX = "data:image/jpeg;base64,";

        @Override
        protected String getPrefix() {
            return FILE_PREFIX;
        }

        public ImageEntry(String url) {
            super(url);
        }

        public ImageEntry(File file) {
            super(file);
        }
    }
}
