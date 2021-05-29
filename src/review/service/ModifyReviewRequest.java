package review.service;

import java.util.Map;

//수정하는 폼에 뿌려지는 내용 
public class ModifyReviewRequest{
	private String title;		//제목
	private String room_name;//객실
	private String name;//작성자명
	private int score;// 점수
	private String content;		//내용
	private int reviewNo;	//글번호
	
	public ModifyReviewRequest(String title, String room_name, String name, int score,
			String content, int no) {
		this.title = title;
		this.room_name = room_name;
		this.name = name;
		this.score = score;
		this.content = content;
		this.reviewNo = no;
	}

	public ModifyReviewRequest(String title,int score, String content, int no) {
		this.title = title;
		this.score=score;
		this.content=content;
		this.reviewNo=no;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
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

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public void validate(Map<String,Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}
	
	
	

}
