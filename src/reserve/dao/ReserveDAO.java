package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Date;

import jdbc.JdbcUtil;
import reserve.service.ReserveChangeRequest;
import reserve.service.ReserveRequest;

public class ReserveDAO {
	public int selectReno(Connection conn) throws SQLException{
		int no = 0;
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql = "select max(reserve_no) from reserve";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){ // 있으면 
				no = rs.getInt(1) + 1;
			}else{
				no = 1;
			}
			System.out.println(no); // 확인용
			return no;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}//selectReno


	public int insertReserveTable(Connection conn, ReserveRequest reserveReq,int num, Calendar start, Calendar last) throws SQLException{
		System.out.println("reservedao.insertReserveTable() 진입");
		
		String sql = null;
		PreparedStatement stmt = null;
		
		int count = 0;
		try {
			sql 
			= "insert into reserve(reserve_no, room_no, mem_id, reserve_price, reserve_method, reserve_pet_number, reserve_day,reserve_pay, reserve_start , reserve_end, room_use) " + 
			  "VALUES(?,?,?,?,?,?,sysdate,?,?,?,?)";
			
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, num);
			stmt.setInt(2,reserveReq.getReservationdto().getResultRoomdto().getRoomNum());
			stmt.setString(3,reserveReq.getReservationdto().getUser().getId());
			stmt.setInt(4, reserveReq.getReservationdto().getResultRoomdto().getPrice());
			stmt.setString(5,reserveReq.getPaymethod());
			stmt.setInt(6,reserveReq.getReservationdto().getUserReq().getNumofDogs());
			stmt.setString(7, "결제전");// 관리자랑 연계되면..변경?일단 기본값 넣어둠
			stmt.setTimestamp(8,caltoTimestamp(start));
			stmt.setTimestamp(9,caltoTimestamp(last));
			stmt.setString(10, "이용전");
			
			System.out.println("setting 완료");
			count = stmt.executeUpdate();
			System.out.println("count = " + count);
			return count;
						
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}//insertReserveTable
	
	public int insertReservedTable(Connection conn, ReserveRequest reserveReq, int no, Calendar day) throws SQLException {
		System.out.println("reservedao.insertReservedTable() 진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql 
			= "insert into reserved(room_no, reserve_no, room_name, room_condition, reserve_period) " + 
			  "VALUES(?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,reserveReq.getReservationdto().getResultRoomdto().getRoomNum());	//?세팅	
			stmt.setInt(2,no);
			stmt.setString(3,reserveReq.getReservationdto().getUserReq().getRoomName());
			stmt.setString(4,"예약");
			stmt.setTimestamp(5, caltoTimestamp(day));
			
			int count = stmt.executeUpdate();
			return count;
						
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
		
	}//insertReservedTable
	
	public int insertReservedTable(Connection conn, ReserveChangeRequest reserveReq, int no, Calendar day) throws SQLException {
		System.out.println("reservedao.insertReservedTable() 진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql 
			= "insert into reserved(room_no, reserve_no, room_name, room_condition, reserve_period) " + 
			  "VALUES(?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,reserveReq.getReservationdto().getResultRoomdto().getRoomNum());	//?세팅	
			stmt.setInt(2,no);
			stmt.setString(3,reserveReq.getReservationdto().getUserReq().getRoomName());
			stmt.setString(4,"예약");
			stmt.setTimestamp(5, caltoTimestamp(day));
			
			int count = stmt.executeUpdate();
			return count;
						
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
		
	}//insertReservedTable
	public Timestamp caltoTimestamp(Calendar i) {
		Date d = i.getTime();
		Timestamp ts = new Timestamp(d.getTime());
		return ts;
	}//caltoTimestamp

	
	public int updateReserveTable(Connection conn, ReserveChangeRequest reserveReq,int num, Calendar start, Calendar last) throws SQLException{
		System.out.println("reservedao.insertReserveTable() 진입");
		
		String sql = null;
		PreparedStatement stmt = null;
		
		int count = 0;
		try {
			sql =
				 "UPDATE reserve " + 
				"SET room_no=?,reserve_price=?,reserve_pet_number=?,reserve_start=?,reserve_end=? " + 
				"WHERE reserve_no=?";
			
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1,reserveReq.getReservationdto().getResultRoomdto().getRoomNum());
			stmt.setInt(2, reserveReq.getReservationdto().getResultRoomdto().getPrice());
			stmt.setInt(3,reserveReq.getReservationdto().getUserReq().getNumofDogs());
			stmt.setTimestamp(4,caltoTimestamp(start));
			stmt.setTimestamp(5,caltoTimestamp(last));
			stmt.setInt(6, num);
			
			System.out.println("setting 완료");
			count = stmt.executeUpdate();
			System.out.println("count = " + count);
			return count;
						
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}//updateReserveTable


	public int deleteReservedTable(Connection conn, int resultNo) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		int count=0;
		try {
			sql="DELETE " + 
					"FROM reserved " + 
					"WHERE reserve_no=?";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,resultNo);
			count = stmt.executeUpdate();
			return count;
		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
	}


}
