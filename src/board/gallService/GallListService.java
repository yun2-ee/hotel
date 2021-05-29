package board.gallService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import board.gall.GallDAO;
import board.gall.GallDTO;
import board.gall.GallImgDAO;
import board.gall.GallImgDTO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GallListService {
	private int size=6;
	private GallDAO gallDao = new GallDAO();
	private GallImgDAO gallImgDao = new GallImgDAO();
	public GetGallListPage getGallListPage(int pageNo, String searchType, String searchText) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = gallDao.selectGallCount(conn,searchType,searchText);
			List<GallDTO> content = null;
			content = gallDao.selectGall(conn,(pageNo-1)*size+1,searchType,searchText,size);
			
			List<GallImgDTO> img = new ArrayList<GallImgDTO>();
			Iterator<GallDTO> it = content.iterator();
			while(it.hasNext()) {
			int gall_no =it.next().getGall_no();
			img.add(gallImgDao.selectImg(conn,gall_no));
			}
			//오라클은 1번부터 시작해서 (pageNo-1)*size+1로 해야한다 여기서 +1 은 mysql에선 없다.
			return new GetGallListPage(total,pageNo,size,content,img);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

}
