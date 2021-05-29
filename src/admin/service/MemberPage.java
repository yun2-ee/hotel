package admin.service;

import java.util.List;

import admin.model.MemberListDTO;


public class MemberPage {
	private int total;
	private int currentPage;
	private List<MemberListDTO> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public MemberPage() {}
	public MemberPage(int total, int currentPage, int size, List<MemberListDTO> content) {
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
	public boolean hasNoMembers() {
		return total==0;
	}
	public boolean hasMembers() {
		return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<MemberListDTO> getContent() {
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
