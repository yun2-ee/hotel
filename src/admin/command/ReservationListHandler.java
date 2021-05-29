package admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.ReservationListPage;
import admin.service.ReservationListService;
import mvc.command.CommandHandler;

/* 이 핸들러는 http:localhost/web/admin/reservationList.jsp를 불러온다
 */

public class ReservationListHandler implements CommandHandler {
	
	private ReservationListService reservationListService = new ReservationListService();
	private ReservationChangeHandler reservationChangeHandler = new ReservationChangeHandler();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String searchyear = request.getParameter("searchyear");
		String searchmonth = request.getParameter("searchmonth");
		String searchtype = request.getParameter("searchtype");
		String searchtext = request.getParameter("searchtext");
		String pageNumber = request.getParameter("pageNo");
		String reserveNo = request.getParameter("no");
		String operate = request.getParameter("operate");
		String selectno[] = request.getParameterValues("selectNo");
		String year ="";
		
		
		
		if(searchyear!=null) {
			year = searchyear;
		}
		String month="";
		if(searchmonth!=null) {
			month = searchmonth;
		}
		String type="mem_name";
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
		
		String selectNo ="";
		if(selectno!=null) {
			for(int i=0; i<selectno.length;i++) {
				selectNo +=selectno[i];
				if(i<selectno.length-1) {
					selectNo+=",";
				}
			}
		}
		
		//비즈니스 로직 실행
		if(submit.equals("삭제")) {
			reservationListService.deleteReserve(no);
		}else if(submit.equals("입금대기처리")) {
			reservationListService.depositX(selectNo);
		}else if(submit.equals("입금완료처리")) {
			reservationListService.depositOk(selectNo);
		}else if(submit.equals("체크인")) {
			reservationListService.checkIn(selectNo);
		}else if(submit.equals("체크아웃")) {
			reservationListService.checkOut(selectNo);
		}
		ReservationListPage reservationListPage= reservationListService.getReservationPage(pageNo,year,month,type,text);
		
		//모델 처리
		request.setAttribute("reservationListPage",reservationListPage);
		//view처리
		return "../view/jsp/admin/reservationList.jsp";
	}

}
