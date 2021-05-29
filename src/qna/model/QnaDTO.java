package qna.model;

import java.util.Date;


public class QnaDTO {
	private Integer no;
	private WriterDTO writer;
	private String title;
	private String check;
	private Date wrday;
	private int readcount;
	private String res;
	private String content;
	
	public QnaDTO() {}
	public QnaDTO(Integer no,  WriterDTO writer, String title, String check, Date wrday, int readcount, String res, String content) {
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.check = check;
		this.wrday = wrday;
		this.readcount = readcount;
		this.res = res;
		this.content = content;
	}
	public QnaDTO(Integer no, WriterDTO writer, String title, String content, Date wrday,  int readcount, String res) {
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.wrday = wrday;
		this.readcount = readcount;
		this.content = content;
		this.res = res;
	}
	public int getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "QnaDTO [no=" + no + ", writer=" + writer + ", title=" + title + ", wrday=" + wrday + ", check=" + check
				+ ", readcount=" + readcount + ", res=" + res + ", content=" + content + "]";
	}
	
	
}
