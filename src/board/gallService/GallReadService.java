package board.gallService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.gall.GallDAO;
import board.gall.GallDTO;
import board.gall.GallData;
import board.gall.GallImgDAO;
import board.gall.GallImgDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GallReadService {
	private GallDAO gallDao = new GallDAO();
	private GallImgDAO gallImgDao = new GallImgDAO();
	public GallData getGall(int gall_no, boolean isHit)  {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			
			GallDTO content = gallDao.selectById(conn,gall_no);
			if(content==null) {
				throw new GallContentNotFoundException();
			}
			
			List<GallImgDTO> imgList = gallImgDao.selectById(conn,gall_no);
			if(imgList==null) {
				throw new GallImgNotFoundException();
			}   
			
			conn.setAutoCommit(false);
			if(isHit) {
				gallDao.incrementReadCount(conn,gall_no);
				conn.commit();
			}
			
				return new GallData(content, imgList);
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

}
