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

	public List<String> ClassifySceneResult(){
		return results;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClassifySceneResult{");
		sb.append("results=").append(results);
		sb.append('}');
		return sb.toString();
	}
}
