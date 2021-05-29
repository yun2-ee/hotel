package board.gallService;

import java.util.List;

import board.gall.GallDTO;
import board.gall.GallImgDTO;

public class GetGallListPage {
	
	private int total;
	private int currentPage;
	private List<GallDTO> content;
	private List<GallImgDTO> img;
	
	private int totalPage;
	private int startPage;
	private int endPage;
	
	
	
	public GetGallListPage() {}
	public GetGallListPage(int total, int pageNo, int size, List<GallDTO> content, List<GallImgDTO> img) {
		this.total=total;
		this.currentPage=pageNo;
		this.content=content;
		this.img = img;
		if(total==0) {
			this.totalPage=0;
			this.startPage=0;
			this.endPage=0;
		}else {
			this.totalPage=total/size;
			if(total%size>0) {
				this.totalPage++;
			}
			int modPage=currentPage%10;
			this.startPage=(currentPage/10)*10+1;
			if(modPage==0) {
				this.startPage-=10;
			}
			this.endPage=this.startPage+9;
			if(endPage>totalPage) {
				this.endPage=this.totalPage;
			}
			
		}
	}
	
	
	@Override
	public String toString() {
		return "GetGallListPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", img="
				+ img + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<GallDTO> getContent() {
		return content;
	}
	public void setContent(List<GallDTO> content) {
		this.content = content;
	}
	public List<GallImgDTO> getImg() {
		return img;
	}
	public void setImg(List<GallImgDTO> img) {
		this.img = img;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean hasNoGallArticle() {
		return total==0;
	}
	public boolean hasGallArticle() {
		return total!=0;
	}	
	

}
