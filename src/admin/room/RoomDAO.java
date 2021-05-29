package admin.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class RoomDAO {

	public List<RoomDTO> getRoom(Connection conn) throws SQLException {
		String sql=null;
		ResultSet rs =null;
		Statement  stmt =null;
		
		sql="SELECT room_no,room_name,room_pet_max,room_status,room_price " + 
				"FROM room order by room_no";
		try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
				List<RoomDTO> result = new ArrayList<RoomDTO>();
				while(rs.next()) {
					RoomDTO roomDto = new RoomDTO(rs.getInt("room_no"), rs.getString("room_name"), rs.getInt("room_pet_max"), rs.getString("room_status"),rs.getInt("room_price"));
					result.add(roomDto);
				}
				return result;
				
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	public void changeValue(Connection conn, String changeNoArr, String roomStatusArr, String priceArr) {
		String sql=null;
		PreparedStatement stmt = null;
		try {
			sql="UPDATE room " + 
					"SET room_status=?, room_price="+priceArr+" "+
					"WHERE room_no="+changeNoArr; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,roomStatusArr);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

}
