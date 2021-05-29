package member.mem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import jdbc.JdbcUtil;
import member.memService.EditRequest;

public class MemberDAO {	
	//id중복체크
	public boolean overlappedID(Connection conn, String id)
			 throws SQLException {
		
		boolean result = false;
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			sql = "SELECT IF(count(*)>=1,'true','false') as result "+
			      "from mem "+
			      "where mem_id=? ";
			System.out.println("prepareStatememt: " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = Boolean.parseBoolean(rs.getString("result"));
			}

			return result;
		}finally{ 
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}//overlappedID
	
	
	//개인회원정보조회
	public  Member  selectById(Connection conn, String id)
	 throws SQLException {
		
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null; //개인 회원의 다양한 정보를 담기위한 객체변수 선언
		try {
			sql = "SELECT  mem_id, mem_pw, mem_name, " + 
			              "mem_birth, mem_call, mem_adrress, mem_email, mem_grade " + 
				  " FROM   mem " + 
				  " WHERE  mem_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);	//?세팅	
			rs = stmt.executeQuery();
			if(rs.next()) { 
				
				member = new Member(	rs.getString("mem_id"),
										rs.getString("mem_pw"),
										rs.getString("mem_name"), 
										rs.getDate("mem_birth"),
										rs.getString("mem_call"),
										rs.getString("mem_adrress"),
										rs.getString("mem_email"),																													
										rs.getString("mem_grade")																													
										);
			}
		return member;
		}finally{ //p593 32라인
			//resource 반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}//selectById()
	

	//로그인한 user의 비번을 변경
	public void update(Connection conn, Member member) 
	             throws SQLException{
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE mem " + 
				  "SET    mem_pw=? " + 
				  "WHERE  mem_id=?";
			stmt = conn.prepareStatement(sql);
			//?세팅
			stmt.setString(1, member.getPassword() ); //변경할 새로운 비번
			stmt.setString(2, member.getId()  ); //회원id
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}//update



	public void insert(Connection conn, String id, String password,String confirmPssword, String name, Date birthdate, String ponnumber,
			String address, String email) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		
		try {
			sql = " INSERT INTO mem VALUES(?,?,?,?,?,?,?,'일반') ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setDate(4,  birthdate);
			stmt.setString(5, ponnumber);
			stmt.setString(6, address);
			stmt.setString(7, email);
		
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
		
	}


	public void editProfile(Connection conn, User user, EditRequest editReq) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = " UPDATE mem SET mem_call=? ,mem_email=?,mem_adrress=? where mem_id=? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, editReq.getPonnumber());
			stmt.setString(2, editReq.getEmail());
			stmt.setString(3, editReq.getAddress());
			stmt.setString(4,user.getId());
		
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
		
		
	}

	//회원탈퇴
	public void Wirthdraw(Connection conn, Member member) throws SQLException {
		System.out.println("Wirthdraw진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE mem SET mem_grade='탈퇴' WHERE MEM_ID=?";
			System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getId());
			System.out.println("member.getId() "+member.getId());
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}
}





