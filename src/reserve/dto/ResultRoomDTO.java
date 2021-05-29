package reserve.dto;

public class ResultRoomDTO {

	private Boolean isRoom;
	private int price;
	private int roomNum;
	
	public ResultRoomDTO(Boolean isRoom, int price, int roomNum) {
	
		this.isRoom = isRoom;
		this.price = price;
		this.roomNum = roomNum;
	}

	public Boolean getIsRoom() {
		return isRoom;
	}

	public void setIsRoom(Boolean isRoom) {
		this.isRoom = isRoom;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	
	
	
	
	
	
	
}
