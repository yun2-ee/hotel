package free.service;

import java.util.Map;

public class ModifyRequest {
	
	private String userId; 
	private int freeNo; 
	private String title;
	private String content;
	
	
	public ModifyRequest(String userId, int freeNo, String title, String content) {
		super();
		this.userId = userId;
		this.freeNo = freeNo;
		this.title = title;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public int getFreeNo() {
		return freeNo;
	}




	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
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

	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", freeNo=" + freeNo + ", title=" + title + ", content=" + content
				+ "]";
	}

	public void validate(Map<String,Boolean>errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}

}
