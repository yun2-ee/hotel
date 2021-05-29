package member.mem;

import java.sql.Date;

public class Member {
	private String id;
	private String name;
	private String password;
	private Date birthdate;
	private String ponnumber;
	private String address;
	private String email;
	private String grade;	
	
	public Member() {}
	
	public Member(String id, String password, String name, Date birthdate, String ponnumber, String address,
			String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.ponnumber = ponnumber;
		this.address = address;
		this.email = email;
		
	}
	
	
	
	public Member (String id, String password, String name, Date birthdate, String ponnumber, String address,
	String email, String grade) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.ponnumber = ponnumber;
		this.address = address;
		this.email = email;
		this.grade = grade;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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





	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", birthdate=" + birthdate
				+ ", ponnumber=" + ponnumber + ", address=" + address + ", email=" + email + "]";
	}



	public boolean matchPassword(String pwd) {
		return password.contentEquals(pwd);
	}
	
	

	

}
