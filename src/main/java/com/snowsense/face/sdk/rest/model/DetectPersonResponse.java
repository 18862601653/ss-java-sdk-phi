/**
 * DetectFaceResult class defines the response from the 'facedetect' API: 
 * a list of detected face(s).
 *
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk.rest.model;

import com.snowsense.face.sdk.FaceResult;

import java.util.List;

public class DetectPersonResponse {
	private List<FaceResult> persons; // a list of detected face results

	public List<FaceResult> getPersons(){
		return persons;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DetectPersonResponse{");
		sb.append("persons=").append(persons);
		sb.append('}');
		return sb.toString();
	}
}