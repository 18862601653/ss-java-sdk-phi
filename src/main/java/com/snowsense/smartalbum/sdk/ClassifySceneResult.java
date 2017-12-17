/**
 *
 *
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.smartalbum.sdk;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassifySceneResult {

	private List<String> results;

	private String error;

	public List<String> ClassifySceneResult(){
		return results;
	}

	public String getError(){
		return error;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClassifySceneResult{");
		if("".equals(error)) {
			sb.append("results=").append(results);
		}else{
			sb.append("error=").append(error);
		}
		sb.append('}');
		return sb.toString();
	}
}
