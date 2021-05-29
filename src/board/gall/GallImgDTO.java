package board.gall;

import java.util.List;

public class GallImgDTO {
	private int gall_no; /* 갤러리글번호 */
	private Integer img_no;  /* 이미지번호 */
	private String img_old;/* 이미지명 */
	private String img_new;/* 경로 */
	
	
	public GallImgDTO() {}

	

	public GallImgDTO(int gall_no, Integer img_no, String img_old, String img_new) {
		//super();
		this.gall_no = gall_no;
		this.img_no = img_no;
		this.img_old = img_old;
		this.img_new = img_new;
	}



	@Override
	public String toString() {
		return "GallImgDTO [gall_no=" + gall_no + ", img_no=" + img_no + ", img_old=" + img_old + ", img_new="
				+ img_new + "]";
	}



	public int getGall_no() {
		return gall_no;
	}



	public void setGall_no(int gall_no) {
		this.gall_no = gall_no;
	}



	public Integer getImg_no() {
		return img_no;
	}



	public void setImg_no(Integer img_no) {
		this.img_no = img_no;
	}



	public String getImg_old() {
		return img_old;
	}



	public void setImg_old(String img_old) {
		this.img_old = img_old;
	}



	public String getImg_new() {
		return img_new;
	}



	public void setImg_new(String img_new) {
		this.img_new = img_new;
	}


	
	
	
	
}
