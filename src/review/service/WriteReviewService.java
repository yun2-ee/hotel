package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.WriteReviewDAO;

public class WriteReviewService {

	Connection conn = null;
	WriteReviewDAO writeReviewdao = new WriteReviewDAO();

	//후기 테이블에 입력
	public boolean insertReviewTable(WriteReviewRequest writeReReq) {
		System.out.println("insertReviewTable(ReserveRequest reserveReq) 진입"); //확인용
	
		//변수 
		int count = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//insert 하기 
			count = writeReviewdao.insertReviewTable(conn, writeReReq);
			
			conn.commit();
			
			if(count > 0) {
				return true;
			}
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}


		//insert 성공
		return false;
	}//insertReviewTable

	//예약번호에 해당하는 후기글 있는지 확인하는 함수 
	public boolean hasReview(int review_no) {
		System.out.println("hasReview(int review_no) 진입");
		
		//변수 
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();

			//예약번호에 해당하는 후기글 있는지 가져오기 
			result = writeReviewdao.selectReview(conn, review_no);
			if(result> 0) {
				return true;
			}
			return false;
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}//hasReview(int review_no)
}




