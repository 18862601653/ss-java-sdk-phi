/**
 * IdentifyResult class defines the response from the 
 * faceidentify/{faceId} API.
 * 
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

public class IdentifyResult {
	private boolean identified;
	private String personId;   
	private double similarity; 
	private String name;
	private String mostSimilarPersonId;
	private double maxSimilarity;
	private String mostSimilarPersonName;
	private RunnerUp runnerUp;

	public boolean isIdentified(){
		return identified;
	}

	public double getMaxSimilarity(){
		return maxSimilarity;
	}

	public double getSimilarity(){
		return similarity;
	}

	public String getName(){
		return name;
	}

	public String getPersonId(){
		return personId;
	}

	public String getMostSimilarPersonId(){
		return mostSimilarPersonId;
	}

	public String getMostSimilarPersonName(){
		return mostSimilarPersonName;
	}	
	
	public RunnerUp getRunnerUp(){
		return runnerUp;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("IdentifyResult{");
		sb.append("identified=").append(identified);
		sb.append(", personId='").append(personId).append('\'');
		sb.append(", similarity=").append(similarity);
		sb.append(", name='").append(name).append('\'');
		sb.append(", mostSimilarPersonId='").append(mostSimilarPersonId).append('\'');
		sb.append(", maxSimilarity=").append(maxSimilarity);
		sb.append(", mostSimilarPersonName='").append(mostSimilarPersonName).append('\'');
		sb.append(", runnerUp=").append(runnerUp);
		sb.append('}');
		return sb.toString();
	}
}
