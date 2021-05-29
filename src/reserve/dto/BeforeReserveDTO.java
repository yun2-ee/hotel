package reserve.dto;

import member.mem.User;
import reserve.service.ReserveCheckRequest;


//확인버튼 누르면 사용자 입력정보와 함께, reserve.jsp 에 정보를 보여주기 위해 만든 dto 
//실제로 예약하기 전 사용자가 확인해야하는...그런 내역들을 저장하는 클래스
public class BeforeReserveDTO {
	
	private ReserveCheckRequest userReq; // 객실명,입실,퇴실,투숙 강아지 수 
	private User user;
	private ResultRoomDTO resultRoomdto;
	
	public BeforeReserveDTO(ReserveCheckRequest userReq, User user, ResultRoomDTO resultRoomdto) {
		
		this.userReq = userReq;
		this.user = user;
		this.resultRoomdto = resultRoomdto;
	}

	public ReserveCheckRequest getUserReq() {
		return userReq;
	}

	public void setUserReq(ReserveCheckRequest userReq) {
		this.userReq = userReq;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ResultRoomDTO getResultRoomdto() {
		return resultRoomdto;
	}

	public void setResultRoomdto(ResultRoomDTO resultRoomdto) {
		this.resultRoomdto = resultRoomdto;
	}

	@Override
	public String toString() {
		return "ReservationDTO [userReq=" + userReq + ", user=" + user + ", resultRoomdto=" + resultRoomdto + "]";
	}
	

	





}
