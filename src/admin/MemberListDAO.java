package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.model.MemberListDTO;
import jdbc.JdbcUtil;

public class MemberListDAO {

	public List<MemberListDTO> select(Connection conn, int startRow, int size, String seach)throws SQLException{
		System.out.println(startRow);
		System.out.println(size);
		System.out.println(seach);
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM MEM A ";
		if(seach!=null && !"".equals(seach)) {
			sql +="WHERE(MEM_ID = ? or MEM_CALL = ?)";
		}
		sql += ") ";
		sql += "WHERE (RNUM BETWEEN ? AND ?) ";
		
		if(seach!=null && !"".equals(seach)) {
			sql +=" AND (MEM_ID = ? or MEM_CALL = ?)";
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
	try {
		stmt = conn.prepareStatement(sql);
		
		
		
		if(seach!=null && !"".equals(seach)) {
			stmt.setString(1, seach);
			stmt.setString(2, seach);
			stmt.setInt(3, startRow);
			stmt.setInt(4, size);
			stmt.setString(5, seach);
			stmt.setString(6, seach);
		}else {
			stmt.setInt(1, startRow);
			stmt.setInt(2, size);
		}
		
		rs = stmt.executeQuery();

		List<MemberListDTO> result = new ArrayList<MemberListDTO>();

		while(rs.next()) {
			result.add(convertArticle(rs));
		}
		return result;
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
	}
}

	private MemberListDTO convertArticle(ResultSet rs) throws SQLException {
		return new MemberListDTO(
							rs.getString("MEM_ID"),
							rs.getString("MEM_NAME"),
							rs.getDate("MEM_BIRTH"),
							rs.getString("MEM_CALL"),
							rs.getString("MEM_ADRRESS"),
							rs.getString("MEM_EMAIL"),
							rs.getString("MEM_GRADE"));
	}
	
	public int selectCount(Connection conn) throws SQLException {
		String sql = "SELECT count(*) as cnt FROM mem";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public MemberListDTO selectMember(Connection conn, String id) throws SQLException {
		System.out.println("----------MemberListDTO�� selectMember()����----------");
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MemberListDTO member = null;
		try {
			sql = "SELECT MEM_ID, MEM_NAME, MEM_CALL, MEM_EMAIL, MEM_ADRRESS, MEM_GRADE FROM MEM WHERE MEM_ID=?";
			System.out.println("----------MemberListDTO�� selectMember()����----------�� sql = "+sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			System.out.println("----------MemberListDTO�� selectMember()����----------�� id = "+id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new MemberListDTO(rs.getString("MEM_ID"),
															rs.getString("MEM_NAME"),
															null, rs.getString("MEM_CALL"),
															rs.getString("MEM_ADRRESS"),
															rs.getString("MEM_EMAIL"),
															rs.getString("MEM_GRADE"));
				
			}
			return member;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public void update(Connection conn, MemberListDTO member, String newgrade, String id) throws SQLException {
		System.out.println("----MemberListDTO�� update()����-----");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE MEM SET MEM_GRADE=? WHERE MEM_ID=?";
			System.out.println("----MemberListDTO�� update()����-----�� slq = "+sql);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newgrade);
			System.out.println("newgrade = "+newgrade);
			
			stmt.setString(2, id);
			System.out.println("id = "+id);
			
			stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}
}
