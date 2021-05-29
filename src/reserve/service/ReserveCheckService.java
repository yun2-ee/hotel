package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.mem.User;
import reserve.dao.ReserveCheckDAO;
import reserve.dto.BeforeReserveDTO;
import reserve.dto.ResultRoomDTO;
import reserve.dto.RoomDTO;


public class ReserveCheckService {
	BeforeReserveDTO reservation = null;
	ReserveCheckDAO reservecheckdao = new ReserveCheckDAO();
	Connection conn = null;
	
	int hotelRooms = 9;//전체 객실수

	public List<RoomDTO> isMaxDog() throws SQLException {
		System.out.println("isMaxDog() 진입"); //확인용
		conn = ConnectionProvider.getConnection();
		List<RoomDTO> roomList = reservecheckdao.selectRoomTable(conn);
		System.out.println("isMaxDog()의 roomList.size()="+roomList.size());
		return  roomList;
	}


	public ResultRoomDTO isRoom(ReserveCheckRequest checkReq, User user) {
	//변수
		System.out.println("isRoom(ReserveCheckRequest checkReq, User user) 진입");
		System.out.println(checkReq.toString());
		int period = 0; // 투숙기간을 세주는 변수여서 나중에 금액에 곱한다.
		int[][] roomStatus = new int[hotelRooms][2];
		int resultRno = 0; //예약할수 있는 객실번호
		int roomPrice = 0; //객실가격
		ArrayList<Integer> rList = new ArrayList<Integer>(); //객실현황(예약-1 사용가능-0)
		ResultRoomDTO resultRoom = null;
		Calendar start = null;
		Calendar last = null;
	//실행문
		//roomStatus 초기화
		for(int i = 0; i < hotelRooms; i++)
			roomStatus[i][0] = i+1;
		for(int i = 0; i < hotelRooms; i++)
			roomStatus[i][1] = 0;
		
		//투숙기간 입실날짜와 퇴실날짜 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		
			start = Calendar.getInstance();
			last = Calendar.getInstance();
			
		    start.setTime(dateFormat.parse(checkReq.getCheckIn()));
			last.setTime(dateFormat.parse(checkReq.getCheckOut()));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//시작 - 투숙기간동안 객실명에 대한 예약된 객실 리스트 뽑기
		try {
			conn = ConnectionProvider.getConnection();
		
			Calendar day= start;
			String userRoom = checkReq.getRoomName();
			
			while(!day.equals(last)) {//start.compareTo(last)== 0 : 입실시간과 퇴실날짜가 같다 
				rList = reservecheckdao.selectReservedRoom(conn, checkReq.getRoomName(), day);
				if(rList.get(0) != 0){// 예약된 객실이 존재한다.
					for(int i = 0; i < rList.size(); i++)
					{ //rList 돌리기 		
						System.out.println("예약된 객실이 존재해서 if문 존재");
						if(userRoom.equalsIgnoreCase("single")){ //객실번호 1~3
							for(int j = 0; j < 3; j++){
								if(roomStatus[j][0] == rList.get(i))//객실번호가 예약된 객실번호랑 같을때
									roomStatus[j][1] = 1;
							}
						}else if(userRoom.equalsIgnoreCase("double")){//객실번호 4~6
							for(int j = 3; j < 6; j++){
								if(roomStatus[j][0] == rList.get(i))//객실번호가 예약된 객실번호랑 같을때
									roomStatus[j][1] = 1;
							}

						}else{//double- 객실번호 7~9
							for(int j = 6; j < 9; j++){
								if(roomStatus[j][0] == rList.get(i))//객실번호가 예약된 객실번호랑 같을때
									roomStatus[j][1] = 1;
							}
						}
					}
				}
				period++;
				day.add(Calendar.DATE, 1);
			}//while문 끝
			
			for(int i=0; i<9; i++) {System.out.println(roomStatus[i][1]);}
			//이 while 문이 끝나면 roomStatus[객실수][i] 배열에 이미 예약되어있는 객실에는 1이 있고, 아니면 0이다.
			
			//예약할 수 있는 객실이 있는지 
			if(userRoom.equalsIgnoreCase("single")){ //객실번호 1~3
				for(int i = 0; i < 3; i++){
					if(roomStatus[i][1] == 0) {
						resultRno = roomStatus[i][0];
						break;
					}
				}
			}else if(userRoom.equalsIgnoreCase("double")){//객실번호 4~6
					for(int i = 3; i < 6; i++){
					if(roomStatus[i][1] == 0) {
						resultRno = roomStatus[i][0];
						break;
						}
					}
			}else{//객실번호 7~9
					for(int i = 6; i < 9; i++){
					if(roomStatus[i][1] == 0) {
						resultRno = roomStatus[i][0];
						break;
						}
					}			
			}
			roomPrice = reservecheckdao.selectRoomPrice(conn, checkReq.getRoomName());
			resultRoom = new ResultRoomDTO(hasResultRno(resultRno),roomPrice*period, resultRno);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn);
			}
		return resultRoom;

	}


	public boolean hasResultRno(int resultRno) {
		if(resultRno == 0) // 에약할수 있는 객실 없음
			return false;
		else {
			
			return true;
		}
			
	}


}
