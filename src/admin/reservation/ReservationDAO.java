package admin.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class ReservationDAO {
	
	
	/*
	//예약목록 조회(페이지별)
	public List<ReservationDTO> selectReservation(Connection conn,int startRow,int size) throws SQLException{
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		sql="select * from ("+
				"select rownum as rnum,reserve_no,mem_id,mem_call,mem_name,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
				"reserve_end " + 
				"from (select re.reserve_no,re.mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
				"reserve_end from reserve re,mem,room " + 
				"where re.mem_id=mem.mem_id and re.room_no=room.room_no " + 
				"order by reserve_start desc))where rnum between ? and ?";
		
		try {
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1, startRow);
				stmt.setInt(2, startRow+size-1);
				rs=stmt.executeQuery();
				List<ReservationDTO> result = new ArrayList<ReservationDTO>();
				while(rs.next()) {
					ReservationDTO reservationDto = new ReservationDTO(rs.getInt("reserve_no"), 
																										rs.getString("mem_id"), 
																										rs.getString("mem_name"), 
																										rs.getString("mem_call"), 
																										rs.getInt("reserve_price")	, 
																										rs.getString("reserve_method"),
																										rs.getInt("reserve_pet_number"), 
																										rs.getTimestamp("reserve_day")	, 
																										rs.getString("room_use"),
																										rs.getString("reserve_pay"), 
																										rs.getString("room_name"), 
																										rs.getTimestamp("reserve_start"), 
																										rs.getTimestamp("reserve_end"));
					result.add(reservationDto);
				}
				return result;
				
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
		
	}
*/
	public int selectReservationCount(Connection conn) {
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		sql= "select count(*) as cnt from reserve";
		try {
			stmt=conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			return 0;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {//자원반납
	         JdbcUtil.close(rs);
	         JdbcUtil.close(stmt);
	    }
	}
	
	public int selectReservationCount(Connection conn,String year, String month, String type,
			String text) {
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		sql= "select count(*) as cnt "
				+ "from reserve re,mem,room "
				+ "where re.mem_id=mem.mem_id and re.room_no=room.room_no and "+type+" like ? and to_char(re.reserve_start,'YYYY') like ? and to_char(re.reserve_start,'MM') like ?";
		try {
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, "%"+text+"%");
			stmt.setString(2,"%"+year+"%");
			stmt.setString(3, "%"+month+"%");
			rs= stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {//자원반납
	         JdbcUtil.close(rs);
	         JdbcUtil.close(stmt);
	    }
			
	}
	
	
	public List<ReservationDTO> selectReservation(Connection conn, int startRow, String year, String month, String type,
		String text, int size) throws SQLException {
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		sql="select * from (select rownum as rnum,reserve_no,mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
				"reserve_end " + 
				"from (select re.reserve_no,re.mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
				"reserve_end from reserve re,mem,room " + 
				"where re.mem_id=mem.mem_id and re.room_no=room.room_no and "+type+" like ? and to_char(re.reserve_start,'YYYY') like ? and to_char(re.reserve_start,'MM') like ? " + 
				"order by reserve_start desc)) where rnum between ? and ?";
		try {
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, "%"+text+"%");
				stmt.setString(2,"%"+year+"%");
				stmt.setString(3, "%"+month+"%");
				stmt.setInt(4, startRow);
				stmt.setInt(5, startRow+size-1);
				rs=stmt.executeQuery();
				List<ReservationDTO> result = new ArrayList<ReservationDTO>();
				while(rs.next()) {
					ReservationDTO reservationDto = new ReservationDTO(rs.getInt("reserve_no"), 
																										rs.getString("mem_id"), 
																										rs.getString("mem_name"), 
																										rs.getString("mem_call"), 
																										rs.getInt("reserve_price")	, 
																										rs.getString("reserve_method"),
																										rs.getInt("reserve_pet_number"), 
																										rs.getTimestamp("reserve_day")	, 
																										rs.getString("room_use"),
																										rs.getString("reserve_pay"), 
																										rs.getString("room_name"), 
																										rs.getTimestamp("reserve_start"), 
																										rs.getTimestamp("reserve_end"));
					result.add(reservationDto);
				}
				return result;
				
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	public void deleteReservation(Connection conn,int no) {
		String sql=null;
		PreparedStatement  stmt =null;
		sql="delete from reserve where reserve_no=?";
		try {
			conn.setAutoCommit(false);
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1,no);
			int result = stmt.executeUpdate();
			if(result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	public void depositX(Connection conn, String selectNo) {
		String sql=null;
		Statement  stmt =null;
		System.out.println(selectNo);
		sql="update reserve set reserve_pay ='결제대기중' where reserve_no in ("+selectNo+")";
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
		
	}

	public void depositOk(Connection conn, String selectNo) {
		String sql=null;
		Statement  stmt =null;
		sql="update reserve set reserve_pay ='결제완료' where reserve_no in ("+selectNo+")";
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	public void checkIn(Connection conn, String selectNo) {
		String sql=null;
		Statement  stmt =null;
		sql="update reserve set room_use ='이용중' where reserve_no in ("+selectNo+")";
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	public void checkOut(Connection conn, String selectNo) {
		String sql=null;
		Statement  stmt =null;
		sql="update reserve set room_use ='이용완료' where reserve_no in ("+selectNo+")";
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result>0) {
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	public ReservationDTO selectByNo(Connection conn, Integer changeNo) throws SQLException {
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		sql="select * from (select re.reserve_no,re.mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
				"reserve_end from reserve re,mem,room " + 
				"where re.mem_id=mem.mem_id and re.room_no=room.room_no and re.reserve_no= ?)";
		try {
				stmt=conn.prepareStatement(sql);
				stmt.setInt(1,changeNo);
				rs=stmt.executeQuery();
				ReservationDTO reservationDto = null;
				if(rs.next()) {
				reservationDto = new ReservationDTO(rs.getInt("reserve_no"), 
																			rs.getString("mem_id"), 
																			rs.getString("mem_name"), 
																			rs.getString("mem_call"), 
																			rs.getInt("reserve_price")	, 
																			rs.getString("reserve_method"),
																			rs.getInt("reserve_pet_number"), 
																			rs.getTimestamp("reserve_day")	, 
																			rs.getString("room_use"),
																			rs.getString("reserve_pay"), 
																			rs.getString("room_name"), 
																			rs.getTimestamp("reserve_start"), 
																			rs.getTimestamp("reserve_end"));
				}
				return reservationDto;
				
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
}
