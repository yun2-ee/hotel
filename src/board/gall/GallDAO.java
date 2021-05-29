package board.gall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;

public class GallDAO {


	public List<GallDTO> selectGall(Connection conn, int startRow, String searchType, String searchText, int size) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT * " + 
					"FROM (" + 
					"SELECT rownum as rnum,gall_no,mem_id,mem_name,gall_title,gall_wrday " + 
					"FROM (SELECT gall_no,gall.mem_id as mem_id,mem_name,gall_title,gall_wrday " + 
					"FROM gall,mem " + 
					"WHERE gall.mem_id=mem.mem_id and "+searchType+" like ? and gall_isShow='Y' order by gall_no desc)) " + 
					"WHERE rnum between ? and ?"; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+searchText+"%");
			stmt.setInt(2, startRow);
			stmt.setInt(3, startRow+(size-1));
			rs = stmt.executeQuery();
			List<GallDTO> gallList= new ArrayList<GallDTO>();
			while(rs.next()) {
				gallList.add(new GallDTO(rs.getInt("gall_no"),rs.getString("mem_id"), rs.getString("mem_name"), rs.getString("gall_title"), rs.getTimestamp("gall_wrday")));
			}
			return gallList;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}



	public int selectGallCount(Connection conn, String searchType, String searchText){
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT COUNT(*) as cnt " + 
					"FROM gall,mem " + 
					"WHERE gall.mem_id=mem.mem_id AND "+ searchType+" like ? AND gall_isShow='Y' "; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+searchText+"%");
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public GallDTO selectById(Connection conn, int gall_no) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT gall_no,gall.mem_id,mem_name,gall_title,gall_content,gall_wrday,gall_moday,gall_readcount " + 
					"FROM gall,mem " + 
					"WHERE gall.mem_id=mem.mem_id AND gall_no=? AND gall_isShow='Y' "; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new GallDTO(rs.getInt("gall_no"), rs.getString("mem_id"), rs.getString("mem_name"), rs.getString("gall_title"), rs.getString("gall_content"), rs.getTimestamp("gall_wrday"), rs.getTimestamp("gall_moday"), rs.getInt("gall_readcount"));
			}else {
			return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public void incrementReadCount(Connection conn,int gall_no) {
		String sql=null;
		PreparedStatement stmt = null;
		try {
			sql="UPDATE gall " + 
					"SET gall_readcount=gall_readcount+1 " + 
					"WHERE gall_no=? "; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	public GallDTO insert(Connection conn, GallDTO content) throws SQLException {
		String sql = null;
		PreparedStatement pstmt = null; //insert를 실행하기위한 객체
		Statement stmt =null; //마지막 insert된 글번호를 조회하기 위한 객체
		ResultSet rs = null; //마지막 insert된 글번호를 담기위한 객체
		int count=0;
		try {
			sql="INSERT INTO gall (gall_no,mem_id,gall_title,gall_content,gall_wrday,gall_moday,gall_readcount) " + 
					"VALUES (sequence_gall.nextval,?,?,?,?,?,0)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,content.getMem_id());
			pstmt.setString(2,content.getGall_title());
			pstmt.setString(3,content.getGall_content());
			pstmt.setTimestamp(4,content.getGall_wrday());
			pstmt.setTimestamp(5,content.getGall_moday());
			int insertedCount= pstmt.executeUpdate();
			if(insertedCount>0) {
				//오라클에서 nvl(max(article_no),0)을 해주면 null값일시 0으로 대체, 혹은
				//nvl(column,5,10)은 값이 있으면 5, 없으면10
				//article테이블에 insert성공되면
				//article 테이블에 insert된 글번호를 가져와서
				//article_content 테이블에 insert할 때의 글번호로 사용한다.
				stmt = conn.createStatement();
				rs= stmt.executeQuery("SELECT sequence_gall.currval as gall_no FROM dual");
				
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new GallDTO(newNum, content.getMem_id(), content.getMem_id(), 
								content.getGall_title(), content.getGall_content(), content.getGall_wrday(), content.getGall_moday(),0);
				}
			}

			return null;
		}finally { //resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		
	}





	public void update(Connection conn, String gall_title, String gall_content, int gall_no, Timestamp gall_moday) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		try {
			sql="UPDATE gall " + 
					"SET gall_title=?,gall_content=?,gall_moday=? " + 
					"WHERE gall_no=?";
			stmt= conn.prepareStatement(sql);
			stmt.setString(1,gall_title);
			stmt.setString(2,gall_content);
			stmt.setTimestamp(3,gall_moday);
			stmt.setInt(4,gall_no);
			stmt.executeUpdate();

		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
	}

	public int delete(Connection conn, int no) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		try {
			sql="UPDATE gall " + 
					"SET gall_isShow='N' " + 
					"WHERE gall_no=?";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,no);
			return stmt.executeUpdate();
			
		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
	}

}

