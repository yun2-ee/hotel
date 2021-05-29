package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.reservation.ReservationDAO;
import admin.reservation.ReservationDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class reserveInfoService {
	private ReservationDAO reservationDao = new ReservationDAO();


	public ReservationDTO select(Integer changeNo) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		ReservationDTO reservationDto= reservationDao.selectByNo(conn,changeNo);
		JdbcUtil.close(conn);
		return reservationDto;
	}

}
