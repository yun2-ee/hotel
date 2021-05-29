package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reserve.dao.ReserveDAO;

public class ReserveService {
	Connection conn = null;
	ReserveDAO reservedao = new ReserveDAO();
	Calendar start = null, last= null;
	
	public int insertReserveTable(ReserveRequest reserveReq) {
		System.out.println("insertReserveTable(ReserveRequest reserveReq) 진입"); //확인용
		//변수 
		start = stringtoCal(reserveReq.getReservationdto().getUserReq().getCheckIn());
		last = stringtoCal(reserveReq.getReservationdto().getUserReq().getCheckOut());
		int num = 0; // 예약번호
		System.out.println(start + "   / "  + last);
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//예약번호 가져오기
			num = reservedao.selectReno(conn);
			
			//예약테이블에 insert 하기
			int count = reservedao.insertReserveTable(conn,reserveReq, num,start, last);
			if(count == 0) { //insert 실패
				return 0;
			}	
			conn.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return num; //insert 성공
	}// insertReserveTable

	public boolean insertReservedTable(ReserveRequest reserveReq, int no) {
		start = stringtoCal(reserveReq.getReservationdto().getUserReq().getCheckIn());
		last = stringtoCal(reserveReq.getReservationdto().getUserReq().getCheckOut());
		int count=0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//예약 객실테이블에 insert하기 - 시작일부터 종료일-1 까지 날짜 하루하루 저장 
			Calendar day= start;
			while(!day.equals(last)) {//start.compareTo(last)== 0 : 입실시간과 퇴실날짜가 같다 
				
				count = reservedao.insertReservedTable(conn, reserveReq, no, day);
				if(count == 0) {
					break;
				}
				day.add(Calendar.DATE, 1);
			}//while문 끝
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		if(count == 0)// 실패
			return false;
		else // 성공 
			return true;
		
	}//insertReservedTable
	public Calendar stringtoCal(String day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = null;
		try {
			
			date = Calendar.getInstance();
			
		    date.setTime(dateFormat.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}//stringtoCalenar;
	
}




























