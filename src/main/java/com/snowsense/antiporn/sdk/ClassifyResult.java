/**
 * FaceResult class defines single face information associated with one
 * face ID returned from the 'facedetect' or 'face' web API.
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.antiporn.sdk;

import java.util.List;
import java.util.Map;

public class ClassifyResult {

	private Map<String,List<String>> results;
	private String error;

	public Map<String,List<String>> getResults(){
		return results;
	}
	public String getError(){return error;}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClassifyResult{");
		if("".equals(error)) {
			sb.append("results=").append(results);
		}else{
			sb.append("error=").append(error);
		}
		sb.append('}');
		return sb.toString();
	}
}
