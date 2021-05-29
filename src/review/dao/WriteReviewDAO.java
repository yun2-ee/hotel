package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import jdbc.JdbcUtil;
import review.service.WriteReviewRequest;

public class WriteReviewDAO {
/*
	public int selectReno(Connection conn) throws SQLException{
		int no = 0;
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select max(review_no) from review";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){ // 있으면 
				no = rs.getInt(1) + 1;
			}
			else{
				no = 1;
			}
			System.out.println(no); // 확인용
			return no;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}//selectReno */
	public int insertReviewTable(Connection conn, WriteReviewRequest writeReReq) throws SQLException{
		System.out.println("insertReviewTable(Connection conn, WriteReviewRequest writeReReq, int num)");
		Calendar today = Calendar.getInstance();
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql 
			= "insert into review(review_no,review_score,reserve_no,mem_id,review_title,review_content,review_wrday, review_readcount, review_isShow) " + 
			  "VALUES(sequence_review.nextval, ?, ?, ?, ?,?,?,0,?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, writeReReq.getScore());
			stmt.setInt(2, writeReReq.getNo());//reserve_no
			stmt.setString(3, writeReReq.getId());
			stmt.setString(4, writeReReq.getTitle());
			stmt.setString(5, writeReReq.getContent());
			stmt.setTimestamp(6, caltoTimestamp(today));//review_wrday
			stmt.setString(7, "Y");
			
			int count = stmt.executeUpdate();
			return count;
						
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}// insertReviewTable
	
	public Timestamp caltoTimestamp(Calendar i) {
		Date d = i.getTime();
		Timestamp ts = new Timestamp(d.getTime());
		return ts;
	}//caltoTimestamp

	public int selectReview(Connection conn, int review_no) throws SQLException{
		System.out.println("selectReview(Connection conn, int review_no)진입");

		int result = 0;// 후기 개수를 저장
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql = " select count(*) as cnt from review where reserve_no = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, review_no);
			rs = stmt.executeQuery();
			if(rs.next()){ // 있으면 
				result = rs.getInt("cnt");
			}
			System.out.println(result);
			return result;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}//selectReview(Connection conn, int review_no)

}
