package qna.model;

import java.util.Date;

public class QnaContent {

	private int no;
	private String content;
	private String title;
	private Date wrday;
	private WriterDTO writer;
	
	public QnaContent() {}
	
	public QnaContent(int no, String content, String title, Date wrday, WriterDTO writer) {
		this.no = no;
		this.content = content;
		this.title = title;
		this.wrday = wrday;
		this.writer = writer;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getWrday() {
		return wrday;
	}

	public void setWrday(Date wrday) {
		this.wrday = wrday;
	}

	@Override
	public String toString() {
		return "QnaContent [no=" + no + ", content=" + content + ", writer=" + writer + ", title=" + title + ", wrday="
				+ wrday + "]";
	}

		
}
