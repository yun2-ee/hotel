package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import review.dto.ReviewDTO;

public class ReviewListDAO {

	public int selectReviewCount(Connection conn, String year, String month, String type, String text) {
		System.out.println("selectReviewCount(Connection conn, String year, String month, String type, String text) 진입");
		
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		System.out.println("type = " + type + "/text=" + text);
		sql=  " select count(*) as cnt  from review re,mem,reserve " + 
				" WHERE re.reserve_no = reserve.reserve_no  and re.mem_id = mem.mem_id and " 
				+ type+" like ? and to_char(re.review_wrday,'YYYY') like ? and to_char(re.review_wrday,'MM') like ?";
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

	public List<ReviewDTO> selectReview(Connection conn, int startRow, String year, String month, String type, String text, int size) throws SQLException {
		System.out.println("selectReservation(Connection conn, int i, String year, String month, String type, String text, int size)진입");
		
		String sql=null;
		ResultSet rs =null;
		PreparedStatement  stmt =null;
		
		
		sql = "select * from (select rownum as rnum,review_no,review_score,reserve_no,mem_id,review_title, " + 
				"review_content,review_wrday,review_readcount,review_isshow,room_name, mem_name from (select re.review_no,review_score,reserve.reserve_no,  "+ 
				"re.mem_id,review_title,review_content,review_wrday,review_readcount,review_isshow, room.room_name, mem.mem_name " + 
				"from review re,mem,reserve,room " + 
				"WHERE re.reserve_no = reserve.reserve_no  and re.mem_id = mem.mem_id and reserve.room_no = room.room_no and " + 
				type+" like ? and to_char(re.review_wrday,'YYYY') like ? and to_char(re.review_wrday,'MM') like ? " + 
				" order by re.review_no desc))  where rnum between ? and ?";
		
		try {
				stmt=conn.prepareStatement(sql);
				
				stmt.setString(1, "%"+text+"%");
				stmt.setString(2,"%"+year+"%");
				stmt.setString(3, "%"+month+"%");
				stmt.setInt(4, startRow);
				stmt.setInt(5, startRow+size-1);
				rs=stmt.executeQuery();
				List<ReviewDTO> result = new ArrayList<ReviewDTO>();
				System.out.println("여기까지 진행");
				while(rs.next()) {
					ReviewDTO  Reviewdto = new ReviewDTO( 
																		    rs.getInt("review_no"), 
																			rs.getInt("review_score"), 
																			rs.getString("review_title"), 
																			rs.getTimestamp("review_wrday"),
																			rs.getInt("review_readcount"),	
																		    rs.getInt("rnum"),
																			rs.getString("room_name"),
																			rs.getString("mem_name"));
					result.add(Reviewdto);
				}
				return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}// selectReservation(Connection conn, int i, String year, String month, String type,String text, int size) 
}