/**
 * FaceResult class defines single face information associated with one
 * face ID returned from the 'facedetect' or 'face' web API. 
 *
 * Created on 5/26/2017
 * @author: SnowSense
 */
package com.snowsense.face.sdk;

import com.google.gson.annotations.SerializedName;

public class FaceResult{
	private String personId;
    private String faceId;      // the uniuqe face ID; 1 face instance 1 ID
    private double faceness;    // the likelyhood of detect region is a face
    private int imageHeight;
    private int imageWidth;
    private int bottom;
    private int right;
    private int top;
    private int left;
    private double sideness;    // the degree of sideness for the detected face
	@SerializedName("right_eye_height")
    private double rightEyeHeight;
	@SerializedName("left_eye_height")
    private double leftEyeHeight;
	@SerializedName("left_boundary_to_nose")
    private double leftBoundaryToNose;
	@SerializedName("top_boundary_to_nose")
    private double topBoundaryToNose;
	@SerializedName("bottom_boundary_to_nose")
    private double bottomBoundaryToNose;
	@SerializedName("right_boundary_to_nose")
    private double rightBoundaryToNose;

	public String getPersonId() {
		return personId;
	}

	public int getImageWidth(){
		return imageWidth;
	}

	public double getRightEyeHeight(){
		return rightEyeHeight;
	}

	public double getLeftBoundaryToNose(){
		return leftBoundaryToNose;
	}

	public double getTopBoundaryToNose(){
		return topBoundaryToNose;
	}

	public int getBottom(){
		return bottom;
	}

	public String getFaceId(){
		return faceId;
	}

	public int getRight(){
		return right;
	}

	public double getBottomBoundaryToNose(){
		return bottomBoundaryToNose;
	}

	public int getImageHeight(){
		return imageHeight;
	}

	public double getFaceness(){
		return faceness;
	}

	public double getSideness(){
		return sideness;
	}

	public int getTop(){
		return top;
	}

	public int getLeft(){
		return left;
	}

	public double getLeftEyeHeight(){
		return leftEyeHeight;
	}

	public double getRightBoundaryToNose(){
		return rightBoundaryToNose;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FaceResult{");
		sb.append("personId='").append(personId).append('\'');
		sb.append("faceId='").append(faceId).append('\'');
		sb.append(", faceness=").append(faceness);
		sb.append(", imageHeight=").append(imageHeight);
		sb.append(", imageWidth=").append(imageWidth);
		sb.append(", bottom=").append(bottom);
		sb.append(", right=").append(right);
		sb.append(", top=").append(top);
		sb.append(", left=").append(left);
		sb.append(", sideness=").append(sideness);
		sb.append(", rightEyeHeight=").append(rightEyeHeight);
		sb.append(", leftEyeHeight=").append(leftEyeHeight);
		sb.append(", leftBoundaryToNose=").append(leftBoundaryToNose);
		sb.append(", topBoundaryToNose=").append(topBoundaryToNose);
		sb.append(", bottomBoundaryToNose=").append(bottomBoundaryToNose);
		sb.append(", rightBoundaryToNose=").append(rightBoundaryToNose);
		sb.append('}');
		return sb.toString();
	}
}
