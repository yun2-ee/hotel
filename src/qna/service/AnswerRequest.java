package qna.service;

import java.util.Map;

public class AnswerRequest {
	private String userId;
	private int qnaNumber;
	private String res;
	
	public AnswerRequest() {}

	public AnswerRequest(String userId, int qnaNumber, String res) {
		this.userId = userId;
		this.qnaNumber = qnaNumber;
		this.res = res;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getQnaNumber() {
		return qnaNumber;
	}

	public void setQnaNumber(int qnaNumber) {
		this.qnaNumber = qnaNumber;
	}


	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	@Override
	public String toString() {
		return "AnswerRequest [userId=" + userId + ", qnaNumber=" + qnaNumber  + ", res=" + res + "]";
	}

	public void validate(Map<String, Boolean> errors) {

	}
	
}
