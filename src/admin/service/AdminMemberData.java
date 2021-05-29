package admin.service;

import admin.model.MemberListDTO;

public class AdminMemberData {

	private MemberListDTO memberDto;
	
	public AdminMemberData() {}

	public AdminMemberData(MemberListDTO memberDto) {
		this.memberDto = memberDto;
	}

	public MemberListDTO getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberListDTO memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public String toString() {
		return "AdminMemberData [memberDto=" + memberDto + "]";
	}

	
	
}
