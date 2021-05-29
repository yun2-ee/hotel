package free.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import free.dao.FreeDAO;
import free.model.Free;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteFreeService {
	
	private FreeDAO freeDao = new FreeDAO();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Free free = toFree(req);
			Free savedFree = freeDao.insert(conn, free);
			System.out.println("되나");
			if(savedFree == null) {
				throw new RuntimeException("fail to insert free");
			}
	
			
			conn.commit();
			return savedFree.getNo();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	

	private Free toFree(WriteRequest req) {
		Date now = new Date();
		
		return new Free(null, req.getId(), req.getContent(), req.getTitle(), now, now, 0);
	}
	
	

}
