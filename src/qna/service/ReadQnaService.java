package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import qna.QnaContentDAO;
import qna.QnaDAO;
import qna.model.QnaContent;
import qna.model.QnaDTO;
// throws QnaNotFoundException, QnaContentNotFoundException üũ
public class ReadQnaService {
	
	private QnaDAO qnaDao = new QnaDAO();
	private QnaContentDAO contentDao = new QnaContentDAO();
	
	public QnaData getQnaDTO(int QnaNum, boolean incrementReadCount) throws QnaNotFoundException, QnaContentNotFoundException {
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			QnaDTO qnaDto = qnaDao.selectById(conn, QnaNum);
			if(qnaDto==null) {
				throw new QnaNotFoundException();
			}
			QnaContent content = contentDao.selectById(conn, QnaNum);
			if(content == null) {
				throw new QnaContentNotFoundException();
			}
			if(incrementReadCount) {
				qnaDao.incrementReadCount(conn, QnaNum);
			}
			return new QnaData(qnaDto,content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
