package free.model;

import java.util.Date;


public class Free {
	private Integer no; 
	private String  id; 
	private String  title;	
	private String  content;
	private Date    wrday;
	private Date    moday;
	private int 	readCount;
	
	public Free(Integer no, String id, String title, String content, Date wrday, Date moday, int readCount) {
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wrday = wrday;
		this.moday = moday;
		this.readCount = readCount;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrday() {
		return wrday;
	}

	public void setWrday(Date wrday) {
		this.wrday = wrday;
	}

	public Date getModay() {
		return moday;
	}

	public void setModay(Date moday) {
		this.moday = moday;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "Free [no=" + no + ", id=" + id + ", title=" + title + ", content=" + content + ", wrday=" + wrday
				+ ", moday=" + moday + ", readCount=" + readCount + "]";
	}
	
	
}