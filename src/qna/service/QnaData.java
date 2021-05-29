package qna.service;

import qna.model.QnaContent;
import qna.model.QnaDTO;

public class QnaData {
	
	private QnaDTO qnaDto;
	private QnaContent qnaContent;
	
	public QnaData() {}

	public QnaData(QnaDTO qnaDto, QnaContent qnaContent) {
		this.qnaDto = qnaDto;
		this.qnaContent = qnaContent;
	}

	public QnaDTO getQnaDto() {
		return qnaDto;
	}

	public void setQnaDto(QnaDTO qnaDto) {
		this.qnaDto = qnaDto;
	}

	public QnaContent getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(QnaContent qnaContent) {
		this.qnaContent = qnaContent;
	}

	@Override
	public String toString() {
		return "QnaData [qnaDto=" + qnaDto + ", qnaContent=" + qnaContent + "]";
	}
	
}
