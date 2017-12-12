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

public class ExifResult {

	//	DateTime	string	拍摄日期和时间
	//	GPSInfo	string	GPS原始信息
	//	GPSLatitude	float	从GPS原始信息中转换成浮点数的经纬度坐标
	//	GPSLongitude	float	从GPS原始信息中转换成浮点数的经纬度坐标
	//	Make	string	相机制造商
	//	Model	string	相机型号
	//	Orientation	int	照片的方向，横竖
	//	ResolutionUnit	int	清晰度
	//	SceneCaptureType	int	TBA

	private String datetime;
	private float latitude;
	private float longitude;
	private String maker;
	private String model;
	private int orientation;
	private int resolution;

	public String getDatetime(){
		return datetime;
	}

	public float getLatitude(){
		return latitude;
	}

	public float getLongitude(){
		return longitude;
	}

	public String getMaker(){
		return maker;
	}

	public String getModel(){
		return model;
	}

	public int getOrientation(){
		return orientation;
	}

	public int getResolution(){
		return resolution;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ExifResult{");
		sb.append("datetime=").append(datetime);
		sb.append(", latitude='").append(latitude).append('\'');
		sb.append(", longitude=").append(longitude);
		sb.append(", maker='").append(maker).append('\'');
		sb.append(", model='").append(model).append('\'');
		sb.append(", orientation=").append(orientation);
		sb.append(", resolution=").append(resolution);
		sb.append('}');
		return sb.toString();
	}
}
