package com.snowsense;

import okhttp3.MediaType;

public interface Constants {
    MediaType IMAGE_MEDIA_TYPE = MediaType.parse("image/*");
    MediaType VIDEO_MEDIA_TYPE = MediaType.parse("video/*");
    String HEIC_IMG_FORMAT = "heic";
}
