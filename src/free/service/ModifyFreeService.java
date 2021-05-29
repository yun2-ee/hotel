package free.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import free.dao.FreeDAO;
import free.model.Free;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyFreeService {
	
	private FreeDAO freeDao = new FreeDAO();
	
	//특정글 수정하기
	public void modify(ModifyRequest modReq) {
			Connection conn = null;
			
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Free free = freeDao.selectById(conn, modReq.getFreeNo());
			if(free==null) {
				throw new FreeNotFoundException();
			}
			
			boolean reuslt = canModify(modReq.getUserId(),free);
			if(!reuslt) {
				throw new PermissionDeniedException();
			}
			
			freeDao.update(conn, modReq.getFreeNo(), modReq.getTitle(), modReq.getContent(), new Date());
			
			conn.commit();
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}//modify
	
	private boolean canModify(String userId,Free free) {
		String Id 
		= free.getId();
		return  Id.equals(userId);
	}

}
