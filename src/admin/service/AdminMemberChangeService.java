package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.MemberListDAO;
import admin.model.MemberListDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class AdminMemberChangeService {

	MemberListDAO memberDao = new MemberListDAO();
	
	public void change(String id, String newgrade) {
		System.out.println("---------------------------------------------");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			MemberListDTO member = null;
//					memberDao.selectMember(conn, id, newgrade);
			System.out.println("changeMember  ID = "+id);
			System.out.println("changeMember newgrade = "+newgrade);
			memberDao.update(conn,member, newgrade,  id);
			//member.changeMember(id, newgrade);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

}
