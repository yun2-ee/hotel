package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.room.RoomDAO;
import admin.room.RoomDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class RoomManageService {
	private RoomDAO roomDao = new RoomDAO();
	
	
	public List<RoomDTO> getRoomManagePage() throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		List<RoomDTO> getRoomList = roomDao.getRoom(conn);
		
		
		
		JdbcUtil.close(conn);
		return getRoomList;
		
		
	}


	public int changeList(String[] changeNoArr, String[] roomStatusArr, String[] priceArr) throws SQLException {
		Connection conn=null;
		try{
				conn = ConnectionProvider.getConnection();
			for(int i=0;i<changeNoArr.length;i++) {
				 roomDao.changeValue(conn,changeNoArr[i],roomStatusArr[i],priceArr[i]);
			}
			return 1;
		}finally {
		JdbcUtil.close(conn);
		}
	}

}
