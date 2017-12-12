package com.snowsense.face.sdk.rest.model;

import com.snowsense.face.sdk.FaceTag;

import java.util.List;

public class GetFacesByTagResponse extends NoDataResponse {
    private List<FaceTag> faces;

    public List<FaceTag> getFaces() {
        return faces;
    }
}
