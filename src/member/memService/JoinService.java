package member.memService;

import java.sql.Connection;
import java.sql.SQLException;



import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.Member;
import member.mem.MemberDAO;
import member.mem.PetDAO;

//회원 가입 기능을 제공하는 클래스 
public class JoinService {
	
	private MemberDAO memberDao = new MemberDAO();
	private PetDAO petDao = new PetDAO();
	
	
	public boolean join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, joinReq.getId());
			if(member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateidException();
			}
			
			memberDao.insert(conn,
								joinReq.getId(),
								joinReq.getPassword(),
								joinReq.getConfirmPssword(),
								joinReq.getName(),
								joinReq.getBirthdate(),
								joinReq.getPonnumber(),
								joinReq.getAddress(),
								joinReq.getEmail()
								);
			
			
			conn.commit();
			return true;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}

	public void petList( String str, String[] pnameArr, String[] pbirthArr,
			String[] weightArr, String[] neuterArr, String[] medicalArr, String[] memoArr) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);			
						
			for(int i=0;i<pnameArr.length;i++) {
				petDao.insert(conn, str, pnameArr[i],pbirthArr[i],weightArr[i],neuterArr[i],
							  medicalArr[i],memoArr[i]);
			}
			
			conn.commit();
			System.out.println("DB입력완료");
			
	}catch (SQLException e) {
		JdbcUtil.rollback(conn);
		throw new RuntimeException(e);
	}finally {
		JdbcUtil.close(conn);
	}
			
	
	

	
	}

}
