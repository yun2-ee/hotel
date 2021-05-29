package admin.model;

import java.util.Date;

public class MemberListDTO {

	//field
	private String id;
	private String name;
	private Date birth;
	private String hp;
	private String addr;
	private String email;
	private String grade;
	private String newgrade;
	//constructor
	public MemberListDTO(){}
	public MemberListDTO(String id, String name, Date birth, String hp, String addr, String email, String grade) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.hp = hp;
		this.addr = addr;
		this.email = email;
		this.grade = grade;
		
	}
	
	public MemberListDTO(String id, String newgrade) {
		grade = newgrade;
	}
	//method
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNewgrade() {
		return newgrade;
	}
	public void setNewgrade(String newgrade) {
		this.newgrade = newgrade;
	}
	@Override
	public String toString() {
		return "MemberListDTO [id=" + id + ", name=" + name + ", birth=" + birth + ", hp=" + hp + ", addr=" + addr
				+ ", email=" + email + ", grade=" + grade + ", newgrade=" + newgrade + "]";
	}
	public void changeMember(String id, String newgrade) {
		grade = newgrade;
	}

	
	
}
