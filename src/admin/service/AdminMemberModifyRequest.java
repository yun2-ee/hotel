package admin.service;


public class AdminMemberModifyRequest {
	private String userId;
	private String id;
	private String name;
	private String hp;
	private String addr;
	private String email;
	private String grade;
	
	public AdminMemberModifyRequest() {}

	public AdminMemberModifyRequest(String userId, String id, String name, String hp, String addr, String email,
			String grade) {
		this.userId = userId;
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.addr = addr;
		this.email = email;
		this.grade = grade;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "AdminMemberModifyRequest [userId=" + userId + ", id=" + id + ", name=" + name + ", hp=" + hp + ", addr="
				+ addr + ", email=" + email + ", grade=" + grade + "]";
	}
	
	
}
