package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.reservation.ReservationDAO;
import admin.reservation.ReservationDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ReservationListService {
	private ReservationDAO reservationDao = new ReservationDAO();
	private int size = 5; //한페이지에 출력할 게시물수
	/*
	public ReservationListPage getReservationPage(int pageNo) throws SQLException {
	Connection conn= ConnectionProvider.getConnection();
	reservationDao.selectReservation(conn,(pageNo-1)*size,size);
	
	}
	*/
	
	/*
	public ReservationListPage getReservationPage(int pageNo) throws SQLException {
		
		Connection conn= ConnectionProvider.getConnection();
		int total = reservationDao.selectReservationCount(conn);
		List<ReservationDTO> content = null;
		content = reservationDao.selectReservation(conn,(pageNo-1)*size+1,size);
		return new ReservationListPage(total,pageNo,size,content);
		}
		*/
	
	
	public ReservationListPage getReservationPage(int pageNo, String year, String month, String type, String text) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		int total = reservationDao.selectReservationCount(conn,year,month,type,text);
		List<ReservationDTO> content = null;
		content = reservationDao.selectReservation(conn,(pageNo-1)*size+1,year,month,type,text,size);
		JdbcUtil.close(conn);
		return new ReservationListPage(total,pageNo,size,content);
	}

	public void deleteReserve(int no) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		reservationDao.deleteReservation(conn,no);
		JdbcUtil.close(conn);
	}

	public void depositX(String selectNo) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		reservationDao.depositX(conn,selectNo);
		JdbcUtil.close(conn);
	}
	public void depositOk(String selectNo) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		reservationDao.depositOk(conn,selectNo);
		JdbcUtil.close(conn);
		
	}
	public void checkIn(String selectNo) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		reservationDao.checkIn(conn,selectNo);
		JdbcUtil.close(conn);
		
	}
	public void checkOut(String selectNo) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		reservationDao.checkOut(conn,selectNo);
		JdbcUtil.close(conn);
		
	}
		
	
}


