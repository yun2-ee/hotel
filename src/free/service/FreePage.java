package free.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import free.model.Free;
import jdbc.JdbcUtil;

public class FreePage {
	private int total;
	private int currentPage;
	
	private List<Free> content;
	
	private int totalPages;		//전체 페이지수
	private int startPage;		//시작 페이지
	private int endPage;
	
	public FreePage() {}
	
	public FreePage(int total, int currentPage, int size, List<Free> content) {
		this.total = total;				
		this.currentPage = currentPage;	
		this.content = content;		
		if(total==0) {
			this.totalPages=0;		//전체 페이지수
			this.startPage=0;		//시작 페이지
			this.endPage=0;		//끝   페이지
		}else {
			
			this.totalPages=total/size;	//전체 페이지수
			
			if( total%size > 0 ) {
				totalPages++;  //전체페이수 1증가
			}
			
			int modVal = currentPage%5;   //11%5은 11을 5로 나눈 나머지가 1
										  //15%5은 15을 5로 나눈 나머지가 0
			
			this.startPage = currentPage/5*5+1;		//시작 페이지
			                       //   11/5*5+1
			                       //   15/5*5+1   3*5+1
			if(modVal==0) {
				this.startPage -= 5;  //this.startPage=startPage-5;
				                      //             11=16-5
			}
			
			
			this.endPage= startPage+4;		//끝   페이지
			
			if( endPage>totalPages) { this.endPage= totalPages;}
		}
				  
	}//parameter가 있는 생성자(constructor)
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoFree() {

		return total==0; 
	}
	
	public boolean hasFree() {

		return total>0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Free> getContent() {
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