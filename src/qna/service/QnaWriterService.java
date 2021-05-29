package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import qna.QnaContentDAO;
import qna.QnaDAO;
import qna.model.QnaContent;
import qna.model.QnaDTO;

public class QnaWriterService {

	private QnaDAO qnaDao = new QnaDAO();
	
	public Integer writer(QnaWriterRequest qnaWriterReq) {
		System.out.println("QnaWriterService의 writer(qnaWriterReq)="+qnaWriterReq);
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			QnaDTO qnaDto = toQna(qnaWriterReq);
			System.out.println("QnaDTO qnaDto = "+qnaDto);
			
			QnaDTO savedQna = qnaDao.insert(conn, qnaDto);//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			System.out.println("QnaWriterService의 QnaDTO savedQna = "+savedQna);
			if(savedQna==null) {
				throw new RuntimeException("failt to insert qna");
			}
//			QnaContent content = new QnaContent(savedQna.getNo(), qnaWriterReq.getContent(), savedQna.getTitle(), savedQna.getWrday(),qnaWriterReq.getWriter().getId());
//			System.out.println("QnaWriterService의 QnaContent content =  "+content);
//			
//			QnaContent savedContent = contentDao.insert(conn, content);
//			System.out.println("QnaWriterService의 QnaContent savedContent = "+savedContent);
//			if(savedContent==null) {
//				throw new RuntimeException("Failt to insert content");
//			}
			conn.commit();
				
			return savedQna.getNo();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private QnaDTO toQna(QnaWriterRequest qnaWriterReq) {
		
		Date now = new Date();	
		return new QnaDTO(null,
					qnaWriterReq.getWriter(), 
					qnaWriterReq.getTitle(), 
					qnaWriterReq.getContent(),  
					now, 0,
					qnaWriterReq.getRes());
	}
	
}
