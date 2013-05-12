package visualbookmarking.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class BookMark {
	private String id;
	private String name;
	private String path;
	private Timestamp captureDate;
	private float lat;
	private float lon;
	private String additionalInfo;
	private String sharingFlag;
	private byte[] image;

	public BookMark() {
	}

	public BookMark(String id, String name, String path, float lat, float lon,
			Timestamp captureDate, String additionalInfo, String sharingFlag,
			byte[] image) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.captureDate = captureDate;
		this.lat = lat;
		this.lon = lon;
		this.additionalInfo = additionalInfo;
		this.sharingFlag = sharingFlag;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Timestamp captureDate) {
		this.captureDate = captureDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getSharingFlag() {
		return sharingFlag;
	}

	public void setSharingFlag(String sharingFlag) {
		this.sharingFlag = sharingFlag;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageString() {
		return new String(this.image);
	}
}
