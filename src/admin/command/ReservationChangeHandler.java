package admin.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.reservation.ReservationDTO;
import admin.service.ReserveChangeService;
import admin.service.reserveInfoService;
import member.mem.User;
import mvc.command.CommandHandler;
import reserve.dto.BeforeReserveDTO;
import reserve.dto.ResultRoomDTO;
import reserve.dto.RoomDTO;
import reserve.service.ReserveChangeRequest;
import reserve.service.ReserveCheckRequest;
import reserve.service.ReserveCheckService;

public class ReservationChangeHandler implements CommandHandler {
	private static final String RESERVECHANGE = "../view/jsp/admin/reserveChange.jsp";
	private static final String RESERVECHANGE_SUCCESS = "../view/jsp/admin/reserveChangeSuccess.jsp";
	private reserveInfoService reserveInfoService = new reserveInfoService();
	
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" ReserveHandler.process()진입");
		String returnView = null;
		String changeno = request.getParameter("no");
		System.out.println(changeno);
		Integer changeNo =0;
		
		changeNo=Integer.parseInt(changeno);
		ReservationDTO rsDto = reserveInfoService.select(changeNo);
		User user = new User(rsDto.getMem_id(), rsDto.getMem_name());
		System.out.println(request.getParameter("btn"));
		
		if(request.getMethod().equalsIgnoreCase("GET")) { //get 방식으로 handler 오는게 2가지있다. -main 화면에서 넘어올때, 방이 있는지 확인할때 
			 if(Integer.parseInt(request.getParameter("btn")) == 0) { //main으로 넘어올때
			     returnView = toReserve(request,response); 
			  }
			 else { //퇴실 날짜를 정하면 넘어옴.(방이 있는지 확인)
					returnView = reserveCheck(request, response,user);
			 }
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) { //예약하기
			returnView = reserve(request, response);
		}
		rsDto = reserveInfoService.select(changeNo);
		request.setAttribute("no",changeNo);
		request.setAttribute("rsDto", rsDto);
		return returnView;
	}// process
	
	private String toReserve(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("toReserve");
		ReserveCheckService reserveCheckService = new ReserveCheckService();
		
		List<RoomDTO> roomList = new ArrayList<RoomDTO>(); //객실관련 정보 담을 예정
		try {
			roomList = reserveCheckService.isMaxDog();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("ROOMS",roomList);
		System.out.println(roomList);
		return RESERVECHANGE;
	}
	
	private String reserveCheck(HttpServletRequest request, HttpServletResponse response,User user) {
		System.out.println("reserveCheck 진입");
		
		//1.파라미터받기 -p651 17라인
		String rName = request.getParameter("room");
		String checkIn  = request.getParameter("check_in");
		String checkOut = request.getParameter("check_out");
		String numofDogs = request.getParameter("numofdogs");
		
		//로그인 정보 가져오기
		HttpSession session = request.getSession();
		
	
		//Map방식으로 error정보를 담아 관리 
		Map<String,Boolean> reserveErrors = new HashMap<String,Boolean>();
		//FORM_VIEW에서 에러정보를 확인하고 출력하기 위해 Model로 처리한다
		request.setAttribute("RESERVEERRORS", reserveErrors);

		//2.비즈니스로직수행<->reserveCheckServie<->CheckroomDAO<->DB
		//입력받은 객실타입에 따라서 객실번호,날짜,상태를 확인하고 입실 가능한지 보고, 입실가능한 객실의 번호를 돌려준다.
		//파라미터들을 저장해줄 클래스 정리
		ReserveCheckRequest checkReq = new ReserveCheckRequest(rName,checkIn, checkOut, numofDogs);
		ReserveCheckService reserveCheckService = new ReserveCheckService();
	
		ResultRoomDTO resultIsRoom = reserveCheckService.isRoom(checkReq, user); 
		System.out.println(resultIsRoom.getIsRoom());
		if(resultIsRoom.getIsRoom()){ //-2)예약자의 투숙기간에 사용가능한 객실이 있는지 - public Boolean isRoom
			BeforeReserveDTO reservation = new  BeforeReserveDTO(checkReq, user, resultIsRoom);
			session.setAttribute("RESERVATION", reservation);
			System.out.println(reservation.toString());
		}
		else{ // 강아지수는 맞지만 투숙가능한 방이 없다. 
			reserveErrors.put("ROOMERRORS", Boolean.TRUE);
		}
		return RESERVECHANGE;
	}//reserveCheck
	
	private String reserve(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("reserve 진입");
		
		
		//1.파라미터받기
				HttpSession session = request.getSession();
				//1-2. 나머지 값 가져오기
				BeforeReserveDTO reservationdto = (BeforeReserveDTO)session.getAttribute("RESERVATION");
				System.out.println(reservationdto.toString());
				
				ReserveChangeRequest reserveReq = new ReserveChangeRequest(reservationdto);
				System.out.println(reserveReq.toString()); // 확인용
				
				//Map방식으로 error정보를 담아 관리 
				Map<String,Boolean> insertReserveErrors = new HashMap<String,Boolean>();
				//FORM_VIEW에서 에러정보를 확인하고 출력하기 위해 Model로 처리한다
				request.setAttribute("INSERTRESERVEERRORS", insertReserveErrors);
				String changeNo = request.getParameter("no");
				int changeno = Integer.parseInt(changeNo);
				//2.비즈니스 로직 수행
				//1) 예약테이블에 넣기 -> 2)예약객실 테이블에 넣기 -> 성공테이ㅣ블로 이동 
				ReserveChangeService reserveChangeService = new ReserveChangeService(); // 서비스 객체 부르기
				
				//1)예약번호 (마지막 번호 + 1) select 하기 -> 성공하면 예약번호를 반환한다. 실패하면 0
				int resultNo = reserveChangeService.changeReserveTable(reserveReq,changeno);
				
				if(resultNo != 0) {//예약 테이블에 insert를 성공했다면 
					//2)예약객실 테이블에 넣기
				int delete = reserveChangeService.deleteReservedTable(changeno);
					if(delete>=1) {
						boolean result = reserveChangeService.insertReservedTable(reserveReq, changeno);
						if(result){//성공
							System.out.println("성공!!");
							request.setAttribute("result",result);
							request.setAttribute("no", changeno);
							session.removeAttribute("RESERVATION");
							return RESERVECHANGE_SUCCESS;
							/*return "pet.do?petNum = " +reserveReq.getReservationdto().getUserReq().getNumofDogs() ;*/
						}
					}
					else { // 실패
						insertReserveErrors.put("RESERVEDTABERRORS", Boolean.TRUE);
					}
				}
				else {//예약 테이블에 insert를 성공했다면 
					insertReserveErrors.put("RESERVETABERRORS", Boolean.TRUE);
				}
				request.setAttribute("no",changeNo);
				session.removeAttribute("RESERVATION");
				
				return RESERVECHANGE;
	}

}
