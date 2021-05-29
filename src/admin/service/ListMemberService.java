package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.MemberListDAO;
import admin.model.MemberListDTO;
import jdbc.connection.ConnectionProvider;

public class ListMemberService {
	
	private MemberListDAO memberlistdao = new MemberListDAO();
	private int size = 10;
	
	public MemberPage getMemberPage(int pageNum , String seach) {
		try {
			List<MemberListDTO> content = null;
			Connection conn = ConnectionProvider.getConnection();
			
			int total = memberlistdao.selectCount(conn);
			content = memberlistdao.select(conn, ((pageNum-1)*size)+1, ((pageNum-1)*size)+size, seach);
			
			return new MemberPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
