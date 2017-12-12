/**
 * CompareResult class defines the response from the 'facecompare' API.
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

public class CompareResult {
	private boolean samePerson;  // whether the two faces belong to the same person
	private double similarity;   // the similarity between two faces

	public boolean isSamePerson(){
		return samePerson;
	}

	public double getSimilarity() {
		return similarity;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CompareResult{");
		sb.append("samePerson=").append(samePerson);
		sb.append(", similarity=").append(similarity);
		sb.append('}');
		return sb.toString();
	}
}
