package board.gallService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import board.gall.ContentRequest;
import board.gall.GallDAO;
import board.gall.GallDTO;
import board.gall.GallImgDAO;
import board.gall.GallImgDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteGallService {
	private GallDAO gallDao = new GallDAO();
	private GallImgDAO gallImgDao = new GallImgDAO();
	public int write(ContentRequest contentReq, List<GallImgDTO> gallDtoList) {
		Connection conn =null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			GallDTO content = changeGallDto(contentReq);
			
			GallDTO savedContent =  gallDao.insert(conn,content);
			if(savedContent==null) {
				throw new RuntimeException("fail to insert gall_content");
			}
			Iterator<GallImgDTO> it =gallDtoList.iterator();
			while(it.hasNext()) {				
				gallImgDao.insert(conn,savedContent.getGall_no(),it.next());
			}
			
			conn.commit();
			return savedContent.getGall_no();
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		}catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

	private GallDTO changeGallDto(ContentRequest contentReq) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		GallDTO gallDto = new GallDTO(null, contentReq.getUserId(), contentReq.getUserName(), contentReq.getTitle(), contentReq.getContent(), now, now, 0);
		return gallDto;
	}


}
