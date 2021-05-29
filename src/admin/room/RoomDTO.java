package admin.room;

public class RoomDTO {
	private Integer room_no;
	private String room_name;
	private Integer room_pet_max;
	private String room_status;
	private Integer room_price;
	
	@Override
	public String toString() {
		return "RoomDTO [room_no=" + room_no + ", room_name=" + room_name + ", room_pet_max=" + room_pet_max
				+ ", room_status=" + room_status + ", room_price=" + room_price + "]";
	}

	public RoomDTO() {}

	public RoomDTO(Integer room_no, String room_name, Integer room_pet_max, String room_status, Integer room_price) {
		this.room_no = room_no;
		this.room_name = room_name;
		this.room_pet_max = room_pet_max;
		this.room_status = room_status;
		this.room_price = room_price;
	}

	public Integer getRoom_no() {
		return room_no;
	}

	public void setRoom_no(Integer room_no) {
		this.room_no = room_no;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public Integer getRoom_pet_max() {
		return room_pet_max;
	}

	public void setRoom_pet_max(Integer room_pet_max) {
		this.room_pet_max = room_pet_max;
	}

	public String getRoom_status() {
		return room_status;
	}

	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}

	public Integer getRoom_price() {
		return room_price;
	}

	public void setRoom_price(Integer room_price) {
		this.room_price = room_price;
	}
	
	
}
