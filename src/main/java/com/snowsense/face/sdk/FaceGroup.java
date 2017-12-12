/**
 * FaceGroup class
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

import com.google.gson.annotations.SerializedName;

public class FaceGroup {
    private String faceId;
    @SerializedName("group_id")
    private int groupId;

    public String getFaceId() {
        return faceId;
    }

    public int getGroupId() {
        return groupId;
    }
}
