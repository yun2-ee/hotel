package free.service;

import java.sql.Connection;
import java.sql.SQLException;

import free.dao.FreeDAO;
import free.model.Free;
import jdbc.connection.ConnectionProvider;

public class ReadFreeService {
	
	private FreeDAO freeDao = new FreeDAO();

	public FreeData getFree(int freeNum, 
            boolean incrementReadCount) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			Free free = freeDao.selectById(conn, freeNum);
			if( free==null ) {
				throw new FreeNotFoundException();
			}
			
			
			if(incrementReadCount) {
				freeDao.incrementReadCount(conn,freeNum);
			}
			
			return  new FreeData(free);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
