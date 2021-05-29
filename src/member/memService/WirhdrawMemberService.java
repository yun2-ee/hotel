package member.memService;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.Member;
import member.mem.MemberDAO;

public class WirhdrawMemberService {

	MemberDAO memberDao = new MemberDAO();
	
	public void Wirthdraw(String id) {
		System.out.println("Wirthdraw() 진입======");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, id);
			
			memberDao.Wirthdraw(conn, member);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
