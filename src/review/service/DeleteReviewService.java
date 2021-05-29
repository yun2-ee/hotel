package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.DeleteReviewDAO;


public class DeleteReviewService {

	public Boolean deleteReview(int reviewNo)throws SQLException  {
		System.out.println("deleteReview 진입");
		
		DeleteReviewDAO deleteReviewdao = new DeleteReviewDAO();
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			int count = deleteReviewdao.deleteReview(conn, reviewNo);
		
			if(count> 0) {
				System.out.println("성공");
				conn.commit();
				return true;
			}
			else {
				JdbcUtil.rollback(conn); //transaction rollback
				System.out.println("실패");
				return false;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
