package reserve.dto;

import java.sql.Timestamp;

public class MyReservationDTO {
	
		private int rownum;
		private int reserve_no;
		private String mem_id;
		private String mem_name;
		private String mem_call;
		private int reserve_price;
		private String reserve_method;
		private int reserve_pet_number;
		private Timestamp reserve_day;
		private String reserve_pay; //결제상태
		private String room_name;
		private Timestamp reserve_start; /* reserved테이블 */
		private Timestamp reserve_end; /* reserved테이블 */
		private String room_use;
		
		public MyReservationDTO(int rownum,int reserve_no, String mem_id, String mem_name, String mem_call, int reserve_price,
				String reserve_method, int reserve_pet_number, Timestamp reserve_day, 
				String reserve_pay, String room_name, Timestamp reserve_start, Timestamp reserve_end, String room_use) {
		
			this.rownum=rownum;
			this.reserve_no = reserve_no;
			this.mem_id = mem_id;
			this.mem_name = mem_name;
			this.mem_call = mem_call;
			this.reserve_price = reserve_price;
			this.reserve_method = reserve_method;
			this.reserve_pet_number = reserve_pet_number;
			this.reserve_day = reserve_day;
			this.reserve_pay = reserve_pay;
			this.room_name = room_name;
			this.reserve_start = reserve_start;
			this.reserve_end = reserve_end;
			this.room_use = room_use;
		}

		public int getRownum() {
			return rownum;
		}

		public void setRownum(int rownum) {
			this.rownum = rownum;
		}

		public int getReserve_no() {
			return reserve_no;
		}

		public void setReserve_no(int reserve_no) {
			this.reserve_no = reserve_no;
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

		public String getMem_call() {
			return mem_call;
		}

		public void setMem_call(String mem_call) {
			this.mem_call = mem_call;
		}

		public int getReserve_price() {
			return reserve_price;
		}

		public void setReserve_price(int reserve_price) {
			this.reserve_price = reserve_price;
		}

		public String getReserve_method() {
			return reserve_method;
		}

		public void setReserve_method(String reserve_method) {
			this.reserve_method = reserve_method;
		}

		public int getReserve_pet_number() {
			return reserve_pet_number;
		}

		public void setReserve_pet_number(int reserve_pet_number) {
			this.reserve_pet_number = reserve_pet_number;
		}

		public Timestamp getReserve_day() {
			return reserve_day;
		}

		public void setReserve_day(Timestamp reserve_day) {
			this.reserve_day = reserve_day;
		}

		public String getReserve_pay() {
			return reserve_pay;
		}

		public void setReserve_pay(String reserve_pay) {
			this.reserve_pay = reserve_pay;
		}

		public String getRoom_name() {
			return room_name;
		}

		public void setRoom_name(String room_name) {
			this.room_name = room_name;
		}

		public Timestamp getReserve_start() {
			return reserve_start;
		}

		public void setReserve_start(Timestamp reserve_start) {
			this.reserve_start = reserve_start;
		}

		public Timestamp getReserve_end() {
			return reserve_end;
		}

		public void setReserve_end(Timestamp reserve_end) {
			this.reserve_end = reserve_end;
		}

		public String getRoom_use() {
			return room_use;
		}

		public void setRoom_use(String room_use) {
			this.room_use = room_use;
		}

		public MyReservationDTO() {		}

		
		
		

	}
