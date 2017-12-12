/**
 * PersonLabel class defines Person ID and detected face (faceId);
 * Typically used by the 'labelface' API.
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

public class PersonLabel {
    private String personId;    // the uniuqe ID for one person who may have many detected faces (face ID) stored in the web service system
    private String faceId;      // the uniuqe face ID; 1 face instance 1 ID

    public String getPersonId() {
        return personId;
    }

    public String getFaceId() {
        return faceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonLabel{");
        sb.append("personId='").append(personId).append('\'');
        sb.append(", faceId='").append(faceId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
