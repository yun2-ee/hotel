package reserve.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import reserve.service.MyReservationListPage;
import reserve.service.MyReservationListService;


/* 이 핸들러는 http:localhost/web/reserve/MyReservationList.jsp를 불러온다*/
public class MyReservationListHandler implements CommandHandler {
	
	private  MyReservationListService myReservationListService = new  MyReservationListService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyReservationListHandler의 process 진입");
		String roomName = request.getParameter("room_name");
		String searchyear = request.getParameter("searchyear");
		String searchmonth = request.getParameter("searchmonth");
		String searchtype = request.getParameter("searchtype");
		String searchtext = request.getParameter("searchtext");
		String pageNumber = request.getParameter("pageNo");
		String reserveNo = request.getParameter("no"); 
		String operate = request.getParameter("operate");
		
		String year ="";
		if(searchyear!=null) {
			year = searchyear;
		}
		String month="";
		if(searchmonth!=null) {
			month = searchmonth;
		}
		String type="room_name";
		if(searchtype!=null) {
			type = searchtype;
		}
		String text="";
		if(searchtext!=null) {
			text = searchtext;
		}
		
		int pageNo=1;
		if(pageNumber!=null) {
			pageNo = Integer.parseInt(pageNumber);
		}
		
		int no =0;
		if(reserveNo!=null) {
			no=Integer.parseInt(reserveNo);
		}
		String submit = "";
		if(operate!=null) {
			submit=operate;
		}
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("AUTHUSER");
		
		
		String userName = user.getName();
		String userId = user.getId();
		
		//비즈니스 로직 실행
		
		 MyReservationListPage MyreservationListPage=  myReservationListService.getReservationPage(pageNo,year,month,type,text, userId);
		
		//모델 처리
		session.setAttribute("MyreservationListPage",MyreservationListPage);
		
		//view처리
		return"./view/jsp/reserve/myReservationList.jsp";
	}

}