package com.snowsense.face.sdk.rest.model;

import com.snowsense.face.sdk.FaceGroup;

import java.util.List;

public class GroupFacesResponse extends NoDataResponse {
    private List<FaceGroup> faces;

    public List<FaceGroup> getFaces() {
        return faces;
    }
}
