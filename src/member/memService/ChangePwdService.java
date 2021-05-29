package member.memService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.Member;
import member.mem.MemberDAO;

public class ChangePwdService {
	
	MemberDAO memberDao = new MemberDAO();
	//로그인한 user의 비번을 변경
		public void changePwd(String mId, String curPwd, String newPwd,Map<String,Boolean> errors) {
		
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				
				conn.setAutoCommit(false);
				
				//로그인한 user의 db안의 현재 비번을 조회하여
				Member member = memberDao.selectById(conn, mId);
				
				
				//user가 입력한 현재 비번과 일치하지않으면
				if(!member.matchPassword(curPwd)) {
					errors.put("badCurPwd",Boolean.TRUE);
				}
				
				member.setPassword(newPwd);
				
				//로그인한 user의 비번을 변경-p621 28라인
				memberDao.update(conn, member);
				conn.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn);
			}
			
		}

	}