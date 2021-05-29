package reserve.service;

public class ReserveCheckRequest {

	private String roomName;
	private String checkIn;
	private String checkOut;
	private int numofDogs;
	
	public ReserveCheckRequest(String roomName, String checkIn, String checkOut, String numofDogs) {
		this.roomName = roomName;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numofDogs = Integer.parseInt(numofDogs);
	}
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public int getNumofDogs() {
		return numofDogs;
	}
	public void setNumofDogs(String numofDogs) {
		this.numofDogs = Integer.parseInt(numofDogs);
	}
	
	
	

}
