package member.memService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import jdbc.connection.ConnectionProvider;
import member.mem.Member;
import member.mem.MemberDAO;
import member.mem.User;

public class LoginService {
	
	private static MemberDAO memberDao = new MemberDAO();
	
	public User login(String id, String password,Map<String, Boolean> errors) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, id);
			
			if(member == null) {
				errors.put("idCheck", Boolean.TRUE);
				return null;
			}
			
			
			if(member.getGrade().equalsIgnoreCase("탈퇴")) {
				errors.put("unUser", Boolean.TRUE);
				return null;
				
			}
			
			if(!member.matchPassword(password)) {
				errors.put("pwdCheck", Boolean.TRUE);
				return null;
			}
		
			return new User(member.getId(), member.getName(),member.getGrade());
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
