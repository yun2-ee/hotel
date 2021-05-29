package member.memService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.Member;
import member.mem.MemberDAO;
import member.mem.Pet;
import member.mem.PetDAO;
import member.mem.User;

public class InfoService {
	PetDAO petDao = new PetDAO();
	MemberDAO memberDao = new MemberDAO();
	
	public Member getUserInfo(User user) throws SQLException {
		Connection conn=null;
		
		try{conn=ConnectionProvider.getConnection();
			Member userInfo = memberDao.selectById(conn, user.getId());
			return userInfo;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public List<Pet> getPetInfo(User user) throws SQLException {
		Connection conn=null;
		
		try{	conn=ConnectionProvider.getConnection();
				List<Pet> petList=petDao.selectById(conn, user.getId());
				Iterator<Pet> it =petList.iterator();
				if(it.hasNext()) {
					String name=it.next().getPname();
					System.out.println(name);
				}
				System.out.println();
				return petList;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public boolean edit(User user, EditRequest editReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			memberDao.editProfile(conn,user,editReq);
			conn.commit();
			return true;
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public boolean deletePet(User user) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			petDao.deletePetList(conn,user);
			conn.commit();
			return true;
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
