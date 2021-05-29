package qna.service;

import java.util.Map;

import qna.model.QnaDTO;
import qna.model.WriterDTO;

public class QnaWriterRequest {

	private WriterDTO writer;
	private String title;
	private String content;
	private String res;
	
	public QnaWriterRequest() {}
	public QnaWriterRequest(WriterDTO writer, String title, String content, String res) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.res = res;
	}
	public WriterDTO getWriter() {
		return writer;
	}
	public void setWriter(WriterDTO writer) {
		this.writer = writer;
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
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	@Override
	public String toString() {
		return "QnaWriterRequest [writer=" + writer + ", title=" + title + ", content=" + content + ", res=" + res
				+ "]";
	}
	public void validate(Map<String, Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
	
	
}
