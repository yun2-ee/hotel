package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import reserve.dto.RoomDTO;

public class ReserveCheckDAO {

	public List<RoomDTO> selectRoomTable(Connection conn) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<RoomDTO> list = new ArrayList();
		try {
			sql ="select distinct room_name, room_pet_max from room";
			            
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) { //특정 회원의 정보가 있으면
				list.add(new RoomDTO (rs.getString("room_name"), 
						              rs.getInt("room_pet_max")            
						   ));
				
			}
			return list;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		//return null;
	}//selectRoomTable

	public ArrayList<Integer> selectReservedRoom(Connection conn, String roomName, Calendar i) throws SQLException{
		
		System.out.println("selectReservedRoom 진입");
		Timestamp checkdate = caltoTimestamp(i);
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> roomList  = new ArrayList<Integer>();
		System.out.println(" roomName" +  roomName + "i=" + i);
		try {
	
			sql = "select re.room_no " +
				  " from reserved re,room " +
				  " where re.room_no = room.room_no and re.room_name = ? and reserve_period = ? and room_status='이용가능'";
			            
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomName);
			stmt.setTimestamp(2, checkdate);//?세팅	
			rs = stmt.executeQuery();
			if(rs.next()){ // 있으면 
				System.out.println("이미 방 존재해요!");
				do{
					roomList.add(rs.getInt("room_no"));
				}while(rs.next());
			}else{
				System.out.println("없어요~");
				roomList.add(0); // 없으면 0을 저장
			}
		
			return roomList;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int selectRoomPrice(Connection conn, String roomName) throws SQLException {
		
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int price =0;
		try {
			sql = "select distinct room_price " +
				  " from room " + 
				  " where room_name = ?"; 
			            
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roomName);	//?세팅	
			rs = stmt.executeQuery();
			if(rs.next()) { //특정 회원의 정보가 있으면
				 price = rs.getInt("room_price");
			}
		return  price ;
		}finally{ 
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}


	public Timestamp caltoTimestamp(Calendar i) {
		Date d = i.getTime();
		Timestamp ts = new Timestamp(d.getTime());
		return ts;
	}




}
