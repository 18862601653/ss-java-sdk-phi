/**
 *
 *
 *
 * Created on 10/20/2017
 * @author: SnowSense
 */
package com.snowsense.smartalbum.sdk;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ExifResult {

	private ArrayList<Exif> exif = new ArrayList<Exif>();
	private String error;
	public String getError(){return error;}
	public ArrayList<Exif> getExif(){
		return exif;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("ExifResult{");
		if("".equals(error)) {
			sb.append("exif=").append(exif.get(0));
			for (int i = 1; i < exif.size(); i++) {
				sb.append(",exif=").append(exif.get(i));
			}
		}else{
			sb.append("error=").append(error);
		}
		sb.append('}');
		return sb.toString();
	}
}
class Exif implements Serializable{
	//	DateTime	string	拍摄日期和时间
	//	GPSInfo	string	GPS原始信息
	//	GPSLatitude	float	从GPS原始信息中转换成浮点数的经纬度坐标
	//	GPSLongitude	float	从GPS原始信息中转换成浮点数的经纬度坐标
	//	Make	string	相机制造商
	//	Model	string	相机型号
	//	Orientation	int	照片的方向，横竖
	//	ResolutionUnit	int	清晰度
	//	SceneCaptureType	int	TBA

	private String DateTime;
	private Map<String,Object> GPSInfo;
	private float GPSLatitude;
	private float GPSLongitude;
	private String Make;
	private String Model;
	private int Orientation;
	private int ResolutionUnit;
	private int SceneCaptureType;

	public Exif(){	}

	public String getDatetime(){
		return DateTime;
	}

	public Map<String,Object> getGPSInfo(){	return GPSInfo;	}

	public float getGPSLatitude(){
		return GPSLatitude;
	}

	public float getGPSLongitude(){
		return GPSLongitude;
	}

	public String getMake(){
		return Make;
	}

	public String getModel(){
		return Model;
	}

	public int getOrientation(){
		return Orientation;
	}

	public int getResolutionUnit(){
		return ResolutionUnit;
	}

	public int getSceneCaptureType(){
		return SceneCaptureType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Exif{");
		sb.append("DateTime=").append(DateTime);
		sb.append(", GPSInfo='").append(GPSInfo).append('\'');
		sb.append(", latitude='").append(GPSLatitude).append('\'');
		sb.append(", longitude=").append(GPSLongitude);
		sb.append(", maker='").append(Make).append('\'');
		sb.append(", model='").append(Model).append('\'');
		sb.append(", orientation=").append(Orientation);
		sb.append(", resolution=").append(ResolutionUnit);
		sb.append(",SceneCaptureType=").append(SceneCaptureType);
		sb.append('}');
		return sb.toString();
	}
}