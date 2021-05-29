package board.gallCommand;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.gall.GallDTO;
import board.gall.GallData;
import board.gallService.GallReadService;
import mvc.command.CommandHandler;

public class GallReadHandler implements CommandHandler {
	private GallReadService readService = new GallReadService();
	private static final String READ_VIEW = "../view/jsp/gall/gallRead.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no");
		int gall_no = Integer.parseInt(strNo);
		String gPageNo = request.getParameter("gPageNo");
		
		try {
			//조회수 증가 옵션
			boolean isHit = false;
			HttpSession session = request.getSession();
			
			//user가 본 게시물의 글번호를 누적해서 저장하기위한 list준비
			ArrayList<Integer> list = (ArrayList<Integer>)session.getAttribute("HIT");
			
			if(list==null) {
				list=new ArrayList<Integer>();
				list.add(gall_no);
				session.setAttribute("HIT",list);
				isHit=true;
				//세션에서 글을 본적 없는 경우 조회수 증가OK
			}else if(list.contains(gall_no)){
				isHit=false;
				//세션에서 글을 본적 있는경우 조회수 증가X
			}else {
				list.add(gall_no);
				isHit=true;
				session.setAttribute("HIT",list);
			}
			
			GallData gallData = readService.getGall(gall_no,isHit);	
			
			request.setAttribute("gallData",gallData);
			request.setAttribute("gPageNo",gPageNo);
			
			return READ_VIEW;
		}catch(GallDataNotFoundException e) {
			request.getServletContext().log("no gallData",e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
