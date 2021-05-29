
package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import reserve.dao.MyReservationDAO;
import reserve.dto.MyReservationDTO;

public class MyReservationListService {
	private MyReservationDAO myReservationdao = new MyReservationDAO();
	private int size = 5; //한페이지에 출력할 게시물수
	/*
	public ReservationListPage getReservationPage(int pageNo) throws SQLException {
	Connection conn= ConnectionProvider.getConnection();
	reservationDao.selectReservation(conn,(pageNo-1)*size,size);
	
	}
	*/

	
	public  MyReservationListPage getReservationPage(int pageNo, String year, String month, String type, String text, String userId) throws SQLException {
		System.out.println("getReservationPage(int pageNo, String year, String month, String type, String text)");
		Connection conn= ConnectionProvider.getConnection();
		
		int total = myReservationdao.selectReservationCount(conn,year,month,type,text, userId); // 예약내역 수 
		List<MyReservationDTO> content = myReservationdao.selectReservation(conn,(pageNo-1)*size+1,year,month,type,text,size, userId); // 각각의 예약내역
		
		return new  MyReservationListPage(total,pageNo,size,content);
		
	}//getReservationPage(int pageNo, String year, String month, String type, String text)

	
		
	
}


