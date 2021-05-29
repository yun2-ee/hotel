package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import reserve.dto.MyReservationDTO;

public class MyReservationDAO {

		public int selectReservationCount(Connection conn, String year, String month, String type, String text,String userId) {
			System.out.println("selectReservationCount(Connection conn, String year, String month, String type, String text,String userId)진입 ");
			System.out.println(userId);
			String sql=null;
			ResultSet rs =null;
			PreparedStatement  stmt =null;
			
			sql= "select count(*) as cnt "
					+ "from reserve re, mem, room "
					+ "where re.mem_id  = ? and re.mem_id=mem.mem_id and re.room_no=room.room_no and " + type+" like ? and to_char(re.reserve_start,'YYYY') like ? and to_char(re.reserve_start,'MM') like ?";
							
			try {
				
				stmt=conn.prepareStatement(sql);
				stmt.setString(1,userId);
				stmt.setString(2, "%"+text+"%");
				stmt.setString(3,"%"+year+"%");
				stmt.setString(4, "%"+month+"%");
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
		}// selectReservationCount(Connection conn, String year, String month, String type, String text,String userId) 

		public List<MyReservationDTO> selectReservation(Connection conn, int startRow, String year, String month, String type, String text, int size, String userId) throws SQLException {
			System.out.println("selectReservation(Connection conn, int i, String year, String month, String type, String text, int size, String userId)진입");
			
			String sql=null;
			ResultSet rs =null;
			PreparedStatement  stmt =null;
			
			sql="select * from (select rownum as rnum,reserve_no,mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
					"reserve_end " + 
					"from (select re.reserve_no,re.mem_id,mem_name,mem_call,reserve_price,reserve_method,reserve_pet_number,reserve_day,room_use,reserve_pay,room_name,reserve_start, " + 
					"reserve_end from reserve re,mem,room " + 
					"where re.mem_id = ? and re.mem_id=mem.mem_id and re.room_no=room.room_no and "+type+" like ? and to_char(re.reserve_start,'YYYY') like ? and to_char(re.reserve_start,'MM') like ? " + 
					"order by reserve_end desc)) where rnum between ? and ?";
		
			try {
					stmt=conn.prepareStatement(sql);
					
					stmt.setString(1,userId);
					stmt.setString(2, "%"+text+"%");
					stmt.setString(3,"%"+year+"%");
					stmt.setString(4, "%"+month+"%");
					stmt.setInt(5, startRow);
					stmt.setInt(6, startRow+size-1);
					rs=stmt.executeQuery();
					List<MyReservationDTO> result = new ArrayList<MyReservationDTO>();
					while(rs.next()) {
						MyReservationDTO myReservationdto = new MyReservationDTO(
																				rs.getInt("rnum"),
																				rs.getInt("reserve_no"), 
																				rs.getString("mem_id"), 
																				rs.getString("mem_name"), 
																				rs.getString("mem_call"), 
																				rs.getInt("reserve_price"), 
																				rs.getString("reserve_method"),
																				rs.getInt("reserve_pet_number"), 
																				rs.getTimestamp("reserve_day")	, 
																				rs.getString("reserve_pay"), 
																				rs.getString("room_name"), 
																				rs.getTimestamp("reserve_start"), 
																				rs.getTimestamp("reserve_end"),
																				rs.getString("room_use"));
						result.add(myReservationdto);
					}
					return result;
					
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}// selectReservation(Connection conn, int i, String year, String month, String type, String text, int size, String userId)

}
