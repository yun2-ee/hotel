package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.QnaContentDAO;
import qna.QnaDAO;

public class QnaDeleteService {

	private QnaDAO qnaDao = new QnaDAO();
	
	public int delete(int no) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = qnaDao.delete(conn, no);
			if(result==1) {System.out.println("QnaDAO변경 성공");}
			
			conn.commit();
			return result;
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
