package free.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import free.service.FreeContentNotFoundException;
import free.service.FreeData;
import free.service.FreeNotFoundException;
import free.service.ReadFreeService;
import mvc.command.CommandHandler;

public class ReadFreeHandler implements CommandHandler{
	
	private ReadFreeService  readService =
			new ReadFreeService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNo = request.getParameter("pageNo");
		String strNo  = request.getParameter("no");
		int freeNum = Integer.parseInt(strNo);
		
		try {
			boolean isHit = false; 
			HttpSession session = request.getSession(); //session얻기
			
			ArrayList<Integer> list 
				= (ArrayList)session.getAttribute("HIT");
			
			if(list==null || list.size()==0) {
				list = new ArrayList<Integer>();
				list.add(freeNum); 
				isHit = true;
				session.setAttribute("HIT", list);
			}else if( list.contains(freeNum) ) { 
				isHit = false;
			}else {
				list.add(freeNum); 
				isHit = true;
				session.setAttribute("HIT", list);
			}
			
			FreeData freeData = readService.getFree(freeNum,isHit);
			
	     	request.setAttribute("freeData",freeData);
	     	request.setAttribute("pageNo",pageNo); 
			
			return "../view/jsp/free/readFree.jsp";
		}catch(FreeNotFoundException |
				   FreeContentNotFoundException e) {
				request.getServletContext().log("no free",e);
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}


	}
}
