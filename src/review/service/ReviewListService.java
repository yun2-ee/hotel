package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import review.dao.ReviewListDAO;
import review.dto.ReviewDTO;

public class ReviewListService {

	private ReviewListDAO reviewlistdao = new ReviewListDAO();
	private int size = 5; //한페이지에 출력할 게시물수
	
	public ReviewListPage getReviewPage(int pageNo, String year, String month, String type, String text) throws SQLException {
		System.out.println(" getReviewPage() 진입");
		Connection conn= ConnectionProvider.getConnection();
		int total = reviewlistdao.selectReviewCount(conn,year,month,type,text); // 
		System.out.println("total = " + total);
		
		List<ReviewDTO> content = null;
		content = reviewlistdao.selectReview(conn,(pageNo-1)*size+1,year,month,type,text,size);
		System.out.println(content);
		return new ReviewListPage(total,pageNo,size,content);
		
	}



}
