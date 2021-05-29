package board.gall;

public class ImgRequest {
	
	private String imgName; 
	private String imgPath;
	
	
	
	public ImgRequest() {}
	public ImgRequest(String imgName, String imgPath) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
	}
	
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	@Override
	public String toString() {
		return "ImgRequest [imgName=" + imgName + ", imgPath=" + imgPath + "]";
	} 
	
	
	
}
