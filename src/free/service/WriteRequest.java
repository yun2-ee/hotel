package free.service;

import java.util.Map;


//로그인한 유저가 작성한 제목, 내용을 보관하기 위한 기능을 제공하는 클래스
public class WriteRequest {
	
	private String id;
	private String title;
	private String content;
	
	public WriteRequest() {}
	public WriteRequest(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WriteRequest [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	public void validate(Map<String, Boolean> errors) {
		if( title==null || title.trim().isEmpty() ) {
			errors.put("title", Boolean.TRUE);
		}
	}
		

}
