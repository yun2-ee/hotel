package board.gallCommand;

import java.util.Date;

import board.gall.GallData;

public class ModifyForm {
	private int gall_no;
	private GallData gallData;
	

	@Override
	public String toString() {
		return "ModifyForm [gall_no=" + gall_no + ", gallData=" + gallData + "]";
	}



	public ModifyForm() {}
	
	
	
	public ModifyForm(int gall_no, GallData gallData) {
		this.gall_no = gall_no;
		this.gallData = gallData;
	}
	public int getGall_no() {
		return gall_no;
	}
	public void setGall_no(int gall_no) {
		this.gall_no = gall_no;
	}
	public GallData getGallData() {
		return gallData;
	}
	public void setGallData(GallData gallData) {
		this.gallData = gallData;
	}
	
	
	
	
}
