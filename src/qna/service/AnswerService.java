package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.QnaDAO;
import qna.model.QnaDTO;

public class AnswerService {

	QnaDAO qnaDao = new QnaDAO();

	public void modify(AnswerRequest ansReq) throws QnaNotFoundException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			QnaDTO qnaDto = qnaDao.selectById(conn, ansReq.getQnaNumber());
			if(qnaDto ==null) {
				throw new QnaNotFoundException();
			}
			qnaDao.update(conn, ansReq.getQnaNumber(),  ansReq.getRes());
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException (e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}

