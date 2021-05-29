package admin.service;

import java.util.List;

import admin.reservation.ReservationDTO;

public class ReservationListPage {

	private int total;
	private int currentPage;
	private List<ReservationDTO> content;
	
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public ReservationListPage() {}
	
	public ReservationListPage(int total, int pageNo, int size, List<ReservationDTO> content) {
		this.total=total;
		this.currentPage=pageNo;
		this.content = content;
		
		if(total==0) {
			this.totalPage=0;
			this.startPage=0;
			this.endPage=0;
		}else {
			this.totalPage=total/size;
			if(total%size>0) {
				this.totalPage++;
			}
			int modPage= currentPage%10;
			this.startPage=(currentPage/10)*10+1; //실수제거
			if(modPage==0) {
				this.startPage-=10;
			}
			this.endPage=startPage+9;
			
			if(endPage>totalPage) this.endPage=totalPage;
		}
	}
	
	
	
	public int getTotal() {
		return total;
	}
	public boolean hasNoReservation() {
		return total==0;
	}
	
	public boolean hasReservation() {
		return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public List<ReservationDTO> getContent() {
		return content;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}
