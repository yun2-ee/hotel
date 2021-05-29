package board.gallCommand;

import java.sql.Timestamp;
import java.util.Map;

public class ModifyRequest {
	private String mem_id;
	private int gall_no;
	private String gall_title;
	private String gall_content;
	private Timestamp gall_moday;
	
	public ModifyRequest() {}
	


	public ModifyRequest(String mem_id, int gall_no, String gall_title, String gall_content, Timestamp gall_moday) {
		this.mem_id = mem_id;
		this.gall_no = gall_no;
		this.gall_title = gall_title;
		this.gall_content = gall_content;
		this.gall_moday = gall_moday;
	}



	public String getMem_id() {
		return mem_id;
	}



	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}



	public int getGall_no() {
		return gall_no;
	}



	public void setGall_no(int gall_no) {
		this.gall_no = gall_no;
	}



	public String getGall_title() {
		return gall_title;
	}



	public void setGall_title(String gall_title) {
		this.gall_title = gall_title;
	}



	public String getGall_content() {
		return gall_content;
	}



	public void setGall_content(String gall_content) {
		this.gall_content = gall_content;
	}



	public Timestamp getGall_moday() {
		return gall_moday;
	}



	public void setGall_moday(Timestamp gall_moday) {
		this.gall_moday = gall_moday;
	}



	@Override
	public String toString() {
		return "ModifyRequest [mem_id=" + mem_id + ", gall_no=" + gall_no + ", gall_title=" + gall_title
				+ ", gall_content=" + gall_content + ", gall_moday=" + gall_moday + "]";
	}


	public void validate(Map<String, Boolean> errors) {
		if(gall_title==null||gall_title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
	
	
	
}
