package member.memService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.MemberDAO;
import member.mem.User;

public class EditRequest {
	private String password;
	private String ponnumber;
	private String address;
	private String email;
	
	
	@Override
	public String toString() {
		return "EditRequest [password=" + password + ", ponnumber=" + ponnumber + ", address=" + address + ", email="
				+ email + "]";
	}
	
	public EditRequest() {}
	public EditRequest(String password, String ponnumber, String address, String email) {
		this.password = password;
		this.ponnumber = ponnumber;
		this.address = address;
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPonnumber() {
		return ponnumber;
	}
	public void setPonnumber(String ponnumber) {
		this.ponnumber = ponnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, password, "password");
		checkEmpty(errors, ponnumber, "ponnumber");
		checkEmpty(errors, address, "address");
		checkEmpty(errors, email, "email");
		
		
		
	}
	
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null || value.isEmpty())
		{	System.out.println(fieldName);
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
	
	public void pwdCheck(Map<String, Boolean> errors, User user,String password) throws SQLException {
		MemberDAO memberDao = new MemberDAO();
		Connection conn =null;
		
		try{conn=ConnectionProvider.getConnection();
			if(!password.equals(memberDao.selectById(conn, user.getId()).getPassword())) {
				errors.put("notMatchPassword", Boolean.TRUE);
			}
		
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
