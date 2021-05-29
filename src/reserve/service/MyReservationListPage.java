package reserve.service;

import java.util.List;

import reserve.dto.MyReservationDTO;

public class MyReservationListPage {
	private int total;
	private int currentPage;
	private List<MyReservationDTO> content;
	
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public  MyReservationListPage() {}
	
	public  MyReservationListPage(int total, int pageNo, int size, List<MyReservationDTO> content) {
		this.total=total;
		this.currentPage=pageNo;
		this.content = content;
		
		if(total==0) { //예약내역 건수가 없음
			this.totalPage=0;
			this.startPage=0;
			this.endPage=0;
		}else {
			this.totalPage=total/size;
			if(total%size>0) {//딱 맞게 안떨어지는다는 건 내역이 더 있음. -> 그래서 잉여 내역들을 담아줄 페이지 하나 더 추가해줌
				this.totalPage++;
			}
			int modPage= currentPage % 10;
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
		return total == 0;
	}
	
	public boolean hasReservation() {
		return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public List<MyReservationDTO> getContent() {
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
