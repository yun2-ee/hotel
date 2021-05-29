package member.memService;

import java.sql.Date;
import java.util.Map;

//회원 가입 기능을 구현할 떄 필요한 요청 데이터를 담는 쿨래스
public class JoinRequest {
	
	private String id;
	private String name;
	private String password;
	private String confirmPssword;
	private Date birthdate;
	private String ponnumber;
	private String address;
	private String email;
	
	
	
	
	public JoinRequest() {}
	public JoinRequest(String id, String name, String password, String confirmPssword, Date birthdate,
			String ponnumber, String address, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.confirmPssword = confirmPssword;
		this.birthdate = birthdate;
		this.ponnumber = ponnumber;
		this.address = address;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPssword() {
		return confirmPssword;
	}

	public void setConfirmPssword(String confirmPssword) {
		this.confirmPssword = confirmPssword;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPssword);
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPssword, "confirmPssword");
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

}
