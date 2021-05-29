package free.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import free.dao.FreeDAO;
import free.model.Free;
import jdbc.connection.ConnectionProvider;

public class ListFreeService {
	private FreeDAO freeDao = new FreeDAO();
	private int size = 5;
	
	public FreePage getFreePage(int pageNum) {
		try {
			List<Free> content = null;

			Connection conn = ConnectionProvider.getConnection();
			
			int total = FreeDAO.selectCount(conn);
			content = FreeDAO.select(conn, (pageNum-1)*size, size);
			
			
			return new FreePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
