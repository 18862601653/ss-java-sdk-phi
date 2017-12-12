/**
 * FaceResult class defines single face information associated with one
 * face ID returned from the 'facedetect' or 'face' web API.
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.antiporn.sdk;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassifyResult {

	private List<String> results;

	public List<String> getResults(){
		return results;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClassifyResult{");
		sb.append("results=").append(results);
		sb.append('}');
		return sb.toString();
	}
}
