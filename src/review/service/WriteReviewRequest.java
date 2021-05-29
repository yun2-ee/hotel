package review.service;

public class WriteReviewRequest {

	private String id;
	private String title;
	private String roomName;
	private int score;
	private String content;
	private int no;
	
	public WriteReviewRequest(String id, String title, String roomName, int  score, String content, int no) {
		this.id = id;
		this.title = title;
		this.roomName = roomName;
		this.score = score;
		this.content = content;
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int  getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	
}
