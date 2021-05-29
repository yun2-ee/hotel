package board.gallService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import board.gall.GallDAO;
import board.gall.GallImgDAO;
import board.gall.GallImgDTO;
import board.gallCommand.DeleteFailException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteService {
	private GallDAO gallDao = new GallDAO();
	private GallImgDAO gallImgDao = new GallImgDAO();
	public int delete(int no, String realPath) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = gallDao.delete(conn,no);
			
			List<GallImgDTO> deleteFileList = gallImgDao.selectById(conn, no);
			
			Iterator<GallImgDTO> filelist = deleteFileList.iterator();
			
			while(filelist.hasNext()) {
				String filename = filelist.next().getImg_new();
				File file = new File(realPath,filename);
				if(file.exists()) {
					if(file.delete()) {
						System.out.println("파일삭제성공");
					}
				}else {
					System.out.println("파일이 존재하지 않습니다.");
				}
			}
			
			gallImgDao.delete(conn,no);
			
			conn.commit();
			return result;
			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new DeleteFailException();
		}finally {
			JdbcUtil.close(conn);
		}
				
	}
}