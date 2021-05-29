package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import qna.QnaDAO;
import qna.model.QnaDTO;

public class ListQnaService {

	private QnaDAO qnaDao = new QnaDAO();
	private int size = 10;
	
	public QnaPage getQnaPage(int pageNum, String qnaseach) {
		
		try {
			List<QnaDTO> content = null;
			Connection conn = ConnectionProvider.getConnection();
			int total = qnaDao.selectCount(conn);
			content = qnaDao.select(conn, ((pageNum-1)*size)+1, ((pageNum-1)*size)+size, qnaseach);
			return new QnaPage(total, pageNum, size, content);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
