/**
 * RunnerUp class defines the part of the data structure inside 
 * the overall response returning from faceidentify/{faceId} API. 
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

public class RunnerUp{
	private String similarity;      // the similarity between search result and the face inquired
	private String name;            // person's name
	private String personId;        // the uniuqe ID for one person

	public String getSimilarity(){
		return similarity;
	}

	public String getName(){
		return name;
	}

	public String getPersonId(){
		return personId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("RunnerUp{");
		sb.append("similarity='").append(similarity).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", personId='").append(personId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
