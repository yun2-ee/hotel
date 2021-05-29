package board.gall;

import java.sql.Timestamp;

public class GallDTO {
	private Integer gall_no; /* 갤러리글번호 */
	private String mem_id; /* 회원아이디 */
	private String mem_name;
	private String gall_title;/* 글제목 */
	private String gall_content;/* 글내용 */
	private Timestamp gall_wrday;/* 작성일 */
	private Timestamp gall_moday;/* 수정일 */
	private int gall_readcount;/* 조회수 */
	
	
	
	

	@Override
	public String toString() {
		return "GallDTO [gall_no=" + gall_no + ", mem_id=" + mem_id + ", mem_name=" + mem_name + ", gall_title="
				+ gall_title + ", gall_content=" + gall_content + ", gall_wrday=" + gall_wrday + ", gall_moday="
				+ gall_moday + ", gall_readcount=" + gall_readcount + "]";
	}
	
	public GallDTO() {}
	
	public GallDTO(Integer gall_no,String mem_id,String mem_name,String gall_title,Timestamp gall_wrday) {
		this.gall_no = gall_no;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.gall_title = gall_title;
		this.gall_wrday = gall_wrday;
	}
	public GallDTO(Integer gall_no, String mem_id, String mem_name, String gall_title, String gall_content, Timestamp gall_wrday,
			Timestamp gall_moday, int gall_readcount) {
		this.gall_no = gall_no;
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.gall_title = gall_title;
		this.gall_content = gall_content;
		this.gall_wrday = gall_wrday;
		this.gall_moday = gall_moday;
		this.gall_readcount = gall_readcount;
	}
	
	
	
	public Integer getGall_no() {
		return gall_no;
	}
	public void setGall_no(int gall_no) {
		this.gall_no = gall_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	public Timestamp getGall_wrday() {
		return gall_wrday;
	}
	public void setGall_wrday(Timestamp gall_wrday) {
		this.gall_wrday = gall_wrday;
	}
	public Timestamp getGall_moday() {
		return gall_moday;
	}
	public void setGall_moday(Timestamp gall_moday) {
		this.gall_moday = gall_moday;
	}
	public int getGall_readcount() {
		return gall_readcount;
	}
	public void setGall_readcount(int gall_readcount) {
		this.gall_readcount = gall_readcount;
	}
	
	
}
