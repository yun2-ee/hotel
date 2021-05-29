package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.ModifyReviewDAO;

public class ModifyReviewService {

	public void modify(ModifyReviewRequest modReq) {
		System.out.println(" modify(ModifyReviewRequest modReq) 진입");
		Connection conn = null;
		ModifyReviewDAO modifyReviewdao = new ModifyReviewDAO();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //자동commit x
	
			modifyReviewdao.updateReview(conn, modReq);
			
			conn.commit(); //transaction commit
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //transaction rollback
			throw new RuntimeException(e); 
		}finally {
			JdbcUtil.close(conn);
		}
	}// modify(ModifyReviewRequest modReq)
}
