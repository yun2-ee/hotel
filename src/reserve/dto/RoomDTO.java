package reserve.dto;

import java.util.Date;

public class RoomDTO {

	private int r_no; /* 객실번호 */
	private String r_name; /* 객실명 */
	private int r_pet_max;  /* 객실최대투숙수 */
	private String r_status;  /* 객실상태(사용중, 사용가능) */
	private int  r_price;/* 객실금액 */
	private Date r_date; /* 시간 */

	public RoomDTO(int r_no, String r_name, int r_pet_max, String r_status, int r_price, Date r_date) {

		this.r_no = r_no;
		this.r_name = r_name;
		this.r_pet_max = r_pet_max;
		this.r_status = r_status;
		this.r_price = r_price;
		this.r_date = r_date;
	}

	public RoomDTO() {
		// TODO Auto-generated constructor stub
	}

	public RoomDTO(String r_name, int r_pet_max) {
		this.r_name = r_name;
		this.r_pet_max = r_pet_max;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public int getR_pet_max() {
		return r_pet_max;
	}

	public void setR_pet_max(int r_pet_max) {
		this.r_pet_max = r_pet_max;
	}

	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
		this.r_status = r_status;
	}

	public int getR_price() {
		return r_price;
	}

	public void setR_price(int r_price) {
		this.r_price = r_price;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	
	
}
