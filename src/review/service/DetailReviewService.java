package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import review.dao.DetailReviewDAO;
import review.dto.ReviewDTO;

public class DetailReviewService {

	public ReviewDTO getReview(int reviewNo, boolean incrementReadCount) throws SQLException  {
		System.out.println("getReview() 진입");
		
		DetailReviewDAO detailReviewdao = new DetailReviewDAO();
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			//1. 조회수 늘리기
			if(incrementReadCount) {
				detailReviewdao.incrementReadCount(conn, reviewNo);
			}
			//2. reviewTable에서 select하기
			ReviewDTO reviewdto = detailReviewdao.selectReview(conn,reviewNo);
			//3. 이 모든 정보를 가진 Reviewdto 반환하기 
			
			System.out.println("성공");
			return reviewdto;			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	
		
	}

}
