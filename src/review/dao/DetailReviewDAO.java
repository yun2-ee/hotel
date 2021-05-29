package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import review.dto.ReviewDTO;

public class DetailReviewDAO {

	public void incrementReadCount(Connection conn, int reviewNo) throws SQLException {
			System.out.println("incrementReadCount(Connection conn, int reviewNo) 진입");
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "update review " +
						"set review_readcount = review_readcount + 1 " +
						
						"where review_no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, reviewNo);	//?세팅		
				stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil.close(stmt);
			}		
		}//incrementReadCount


	public ReviewDTO selectReview(Connection conn, int reviewNo) throws SQLException {
		System.out.println("selectReview(Connection conn, int reviewNo) 진입");
		
		String sql = " select re.review_no, review_score,review_title,review_content,review_wrday,review_readcount,review_isshow, re.mem_id, " + 
						" room.room_name,mem.mem_name " + 
						" from review re,mem,reserve,room " + 
						" WHERE review_no = ? and re.reserve_no = reserve.reserve_no  and re.mem_id = mem.mem_id and reserve.room_no = room.room_no";
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//쿼리실행-객체준비,실행
			stmt = conn.prepareStatement(sql);
			//?셋팅
			stmt.setInt(1, reviewNo);
			rs = stmt.executeQuery();
			ReviewDTO result = null;
			System.out.println("여기까지 진행");
			if( rs.next() ) {  //select결과가 존재한다면
				result = new ReviewDTO(
												rs.getInt("review_no"),
												rs.getInt("review_score"),
												rs.getString("mem_id"),
												rs.getString("review_title"),
												rs.getString("review_content"),
												rs.getTimestamp("review_wrday"),
												rs.getString("review_isshow"),
												rs.getInt("review_readcount"),
												rs.getString("room_name"),
												rs.getString("mem_name")
	
						);
			}
			return result;
		}finally {//자원반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		}

}
