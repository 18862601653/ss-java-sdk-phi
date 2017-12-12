/**
 * DetectFaceResult class defines the response from the 'facedetect' API: 
 * a list of detected face(s).
 *
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

import java.util.List;

public class DetectFaceResult {
	private List<FaceResult> faces; // a list of detected face results

	public List<FaceResult> getFaces(){
		return faces;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DetectFaceResult{");
		sb.append("faces=").append(faces);
		sb.append('}');
		return sb.toString();
	}
}