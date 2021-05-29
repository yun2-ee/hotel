package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import review.service.ReviewListPage;
import review.service.ReviewListService;


public class ReviewListHandler implements CommandHandler {
	
	private  ReviewListService reviewListService = new ReviewListService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("process()진입");

		String searchyear = request.getParameter("searchyear");
		String searchmonth = request.getParameter("searchmonth");
		String searchtype = request.getParameter("searchtype");
		String searchtext = request.getParameter("searchtext");
		String pageNumber = request.getParameter("pageNo");
		String reserveNo = request.getParameter("no"); // 필요한가??
		String operate = request.getParameter("operate");
		
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
		
		HttpSession session = request.getSession();
		
		//비즈니스 로직 실행
		ReviewListPage reviewListPage= reviewListService.getReviewPage(pageNo,year,month,type,text);
		System.out.println(reviewListPage);
		//모델 처리
		session.setAttribute("reviewListPage",reviewListPage);
		
		//view처리
		return "./view/jsp/review/reviewList.jsp";
	}

}