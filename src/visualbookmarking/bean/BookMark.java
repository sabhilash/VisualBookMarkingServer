package visualbookmarking.bean;

public class BookMark {
	private int id;
	private byte[] image;
	private String fileName;
	private String location;
	private String captureDate;
	private String additionalInfo;
	
	public BookMark() {
	}
	
	public BookMark(int id, byte[] image, String fileName, String location, String captureDate,  String additionalInfo) {
		this.id = id;
		this.image = image;
		this.fileName = fileName;
		this.location = location;
		this.captureDate = captureDate;
		this.additionalInfo = additionalInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(String captureDate) {
		this.captureDate = captureDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
