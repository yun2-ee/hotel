package review.dto;

import java.sql.Timestamp;

public class ReviewDTO {

	private int review_no;
	private int review_score;
	private int reserve_no; //예약 테이블
	private String mem_id;
	private String review_title;
	private String review_content;
	private Timestamp  review_wrday;
	private char review_isShow;
	private int readcount;
	private int rownum;
	private String room_name;
	private String mem_name;
	
	
	public ReviewDTO() {}
	
	public ReviewDTO(int review_no, int review_score, int reserve_no, String review_title, String review_content,
			Timestamp review_wrday, String review_isShow) {
		this.review_no = review_no;
		this.review_score = review_score;
		this.reserve_no = reserve_no;
		this.review_title = review_title;
		this.review_content = review_content;
		this.review_wrday = review_wrday;
		this.review_isShow = review_isShow.charAt(0);
	}
	//reviewList에서 사용
	public ReviewDTO(int review_no, int review_score, String review_title,Timestamp review_wrday, int readcount, int rownum,
			String room_name,String mem_name) {
		this.rownum = rownum;
		this.review_no = review_no;
		this.room_name = room_name;
		this.mem_name = mem_name;
		this.review_score = review_score;
		this.review_title = review_title;
		this.review_wrday = review_wrday;
		this.readcount = readcount;
	}

	//detailReview에서 사용
	public ReviewDTO(int review_no, int review_score, String mem_id, String review_title, String review_content,
			Timestamp review_wrday, String review_isShow, int readcount, String room_name, String mem_name) {
		this.review_no = review_no;
		this.review_score = review_score;
		this.mem_id = mem_id;
		this.review_title = review_title;
		this.review_content = review_content;
		this.review_wrday = review_wrday;
		this.review_isShow = review_isShow.charAt(0);
		this.readcount = readcount;
		this.room_name = room_name;
		this.mem_name = mem_name;
	}

	public String getMem_name() {
		return mem_name;
	}

	
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public int getReview_score() {
		return review_score;
	}

	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}

	public int getReserve_no() {
		return reserve_no;
	}

	public void setReserve_no(int reserve_no) {
		this.reserve_no = reserve_no;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public Timestamp getReview_wrday() {
		return review_wrday;
	}

	public void setReview_wrday(Timestamp review_wrday) {
		this.review_wrday = review_wrday;
	}

	public char getReview_isShow() {
		return review_isShow;
	}

	public void setReview_isShow(char review_isShow) {
		this.review_isShow = review_isShow;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
	
}
