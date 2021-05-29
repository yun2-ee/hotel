package qna.service;

import java.util.List;

import qna.model.QnaDTO;

public class QnaPage {

	private int total;
	private int currentPage;
	
	private List<QnaDTO> content;
	
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public QnaPage() {}
	
	public QnaPage(int total, int currentPage, int size, List<QnaDTO> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		if(total==0) {
			this.totalPages=0;
			this.startPage=0;
			this.endPage=0;
		}else {
			this.totalPages=total/size;
			if(total%size>0) {
				totalPages++;
			}
			int modVal = currentPage%5;
			this.startPage = currentPage/5*5+1;
			
			if(modVal==0) {
				this.startPage -=5;
				
			}
			this.endPage = startPage+4;
			
			if(endPage>totalPages) this.endPage = this.totalPages;
		}
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoQnas() {
		return total==0;
		
	}
	
	public boolean hasQnas() {
		return total>0;
		
	}
	public int getCurrentPage() {
		return currentPage;
		
	}
	
	public List<QnaDTO> getContent() {
		return content;
		
	}
	
	public int getTotalPages() {
		return totalPages;
		
	}
	
	public int getStartPage() {
		return startPage;
		
	}
	
	public int getEndPage() {
		return endPage;
		
	}
}
