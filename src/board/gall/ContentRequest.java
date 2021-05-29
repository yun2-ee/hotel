package board.gall;

import java.util.Map;

public class ContentRequest {
	
	private String userId;
	private String userName;
	private String title;
	private String content;
	
	
	
	@Override
	public String toString() {
		return "ContentRequest [userId=" + userId + ", userName=" + userName + ", title=" + title + ", content="
				+ content + "]";
	}
	
	
	public ContentRequest() {}
	
	public ContentRequest(String userId, String userName, String title, String content) {
		this.userId = userId;
		this.userName = userName;
		this.title = title;
		this.content = content;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void validate(Map<String,Boolean> errors) {
		if(this.title==null||this.title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
	
}
