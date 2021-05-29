package qna.service;

import java.util.Map;

public class ModifyQnaRequest {

	private String id;
	private int qnaNumber;
	private String title;
	private String content;
	
	public ModifyQnaRequest() {}

	public ModifyQnaRequest(String id, int qnaNumber, String title, String content) {
		this.id = id;
		this.qnaNumber = qnaNumber;
		this.title = title;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQnaNumber() {
		return qnaNumber;
	}

	public void setQnaNumber(int qnaNumber) {
		this.qnaNumber = qnaNumber;
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
		return "ModifyQnaRequest [id=" + id + ", qnaNumber=" + qnaNumber + ", title=" + title + ", content=" + content
				+ "]";
	}

	public void validate(Map<String, Boolean> errors) {
		if(title==null|| title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		
	}
	
}
