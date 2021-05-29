package board.gallService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import board.gall.GallDAO;
import board.gall.GallDTO;
import board.gall.GallImgDAO;
import board.gall.GallImgDTO;
import board.gallCommand.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyGallService {
	private GallDAO gallDao = new GallDAO();
	private GallImgDAO gallImgDao = new GallImgDAO();
	public void modify(ModifyRequest modifyReq, List<Integer> deleteImgNoList, String realPath, List<GallImgDTO> gallDtoList) {
		Connection conn =null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Iterator<Integer> deleteIt =deleteImgNoList.iterator();
			while(deleteIt.hasNext()) {
				GallImgDTO deleteImgDto= gallImgDao.selectByImgNo(conn, deleteIt.next());
					String fileName = deleteImgDto.getImg_new();
					File file = new File(realPath,fileName);
					if(file.exists()) {
						if(file.delete()) {
							System.out.println("수정에서의 파일삭제성공");
						}
					}
					gallImgDao.deleteImgNo(conn, deleteImgDto.getImg_no());
			}
			
			GallDTO content = gallDao.selectById(conn,modifyReq.getGall_no());
			if(content==null) {
				throw new GallContentNotFoundException();
			}
			boolean result = checkModifiy(modifyReq,content);
			//작성글과 요청받은 사용자 이름이 동일한지 확인
			if(!result) {
				throw new PermissionErrorException();
			}
			//수정 시작
			gallDao.update(conn,modifyReq.getGall_title(),modifyReq.getGall_content(),modifyReq.getGall_no(),modifyReq.getGall_moday());
			
			Iterator<GallImgDTO> it =gallDtoList.iterator();
			while(it.hasNext()) {
				gallImgDao.insert(conn, modifyReq.getGall_no(), it.next());
			}
			conn.commit();
			
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


	private boolean checkModifiy(ModifyRequest modifyReq, GallDTO content) {
		String writerId = content.getMem_id();
		
		return writerId.equals(modifyReq.getMem_id());
	}

}
