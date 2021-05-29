package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import review.service.ModifyReviewRequest;

public class ModifyReviewDAO {

	public void updateReview(Connection conn, ModifyReviewRequest modReq) throws SQLException {
		System.out.println("updateReview()진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "update  review " +
					" set review_title = ?, review_wrday=sysdate , review_score=?, review_content=? " +
					" where review_no= ? ";	
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, modReq.getTitle());	//?세팅
			stmt.setInt(2, modReq.getScore());
			stmt.setString(3, modReq.getContent());
			stmt.setInt(4, modReq.getReviewNo());
			System.out.println("담기 성공");
			stmt.executeUpdate();
			System.out.println("실행성공!");
			
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}//update

}
