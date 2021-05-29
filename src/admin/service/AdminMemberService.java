package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.MemberListDAO;
import admin.model.MemberListDTO;
import jdbc.connection.ConnectionProvider;

public class AdminMemberService {

	private MemberListDAO memberDao = new MemberListDAO();
	
	public AdminMemberData getAdminMember(String id, String newgrade) throws MemberNotFoundException {
	
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			MemberListDTO memberDto = memberDao.selectMember(conn, id);
			System.out.println("###############id = "+id);
			if(memberDto == null) {
				throw new MemberNotFoundException();
			}
			return new AdminMemberData(memberDto);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
