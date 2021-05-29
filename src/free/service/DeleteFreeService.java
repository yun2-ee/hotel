package free.service;

import java.sql.Connection;
import java.sql.SQLException;

import free.dao.FreeDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteFreeService {
	
	private FreeDAO freeDao = new FreeDAO();
	private FreeDAO contentDao = new FreeDAO();
	
	public int delete(int no) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); 
			
			int result = freeDao.delete(conn,no);
			if( result==1 ) {
				System.out.println("free테이블에서 "+no+"번의 삭제(비노출로 변경)성공");
			}else {
				System.out.println("free테이블에서 "+no+"번의 삭제(비노출로 변경)실패");
			}
			result = contentDao.delete(conn,no);
			if( result==1 ) {
				System.out.println("free테이블에서 "+no+"번의 삭제(비노출로 변경)성공");
			}else {
				System.out.println("free테이블에서 "+no+"번의 삭제(비노출로 변경)실패");
			}
			
			conn.commit();
			return result;
			
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); 
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}//delete

}
