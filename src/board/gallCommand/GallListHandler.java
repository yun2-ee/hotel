package board.gallCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.gallService.GallListService;
import board.gallService.GetGallListPage;
import mvc.command.CommandHandler;

public class GallListHandler implements CommandHandler {

	private static final String GALL_LIST = "../view/jsp/gall/gallList.jsp";
	private GallListService gallListService =  new GallListService();
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//파라미터 수령
		String gPageNo = request.getParameter("gPageNo");
		String gSearchType = request.getParameter("gSearchType");
		String gSearchText = request.getParameter("gSearchText");
		
		
		int pageNo = 1;
		if(gPageNo!=null) {
			pageNo=Integer.parseInt(gPageNo);
		}
		
		String searchType="gall_title";
		if(gSearchType!=null) {
			searchType=gSearchType;
		}
		
		String searchText="";
		if(gSearchText!=null) {
			searchText=gSearchText;
		}
		
		//로직실행
		GetGallListPage gallListPage = gallListService.getGallListPage(pageNo,searchType,searchText);		
		
		//모델처리
		request.setAttribute("gallListPage", gallListPage);
		//뷰
		return GALL_LIST;
		
		
	}

}
