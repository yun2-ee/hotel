package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class DeleteReviewDAO {

	public int deleteReview(Connection conn, int reviewNo)throws SQLException {
		System.out.println("deleteReview 진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = " DELETE FROM review " + 
					"WHERE review_no = ?";		
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reviewNo);	//?세팅		
			return stmt.executeUpdate();
			//update(즉 삭제에 해당하는 부분이) 성공되면
			//1를 return 한다
			//실패시 0을 return 한다
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}//delete
}
