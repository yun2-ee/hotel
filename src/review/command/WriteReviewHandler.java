package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import review.service.WriteReviewRequest;
import review.service.WriteReviewService;

public class WriteReviewHandler implements CommandHandler {
	private static final String FORM_VIEW = "./view/jsp/review/writeReview.jsp";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteReviewHandler의 process()진입"); //확인용 -> 나중에 삭제
		
		if(request.getMethod().equalsIgnoreCase("GET")) { 
	
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { 
		
			return processSubmit(request,response);
		}else {
			//기타 에러처리
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	//리스트에서 get 방식으로 넘어옴 -> 예약번호와 객실명을 받아옴
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processForm 진입");
		//1. 파라미터 전달받기
		int review_no = Integer.parseInt(request.getParameter("reNo"));
		String rName = request.getParameter("rName");
		int pNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println(" review_no = " +  review_no + "/ room_name = " + rName);
		
		//2.비즈니스로직 수행하기
		//1) 예약번호당 후기작성은 한개여야됨. -> 만약에 후기가 존재한다면 MyReservationList로 돌아가서 alert문으로 알려야함.
		WriteReviewService writeReviewService = new WriteReviewService();
		if(writeReviewService.hasReview(review_no)) {
			String hasreview = "있음";
			request.setAttribute("HASREVIEW", hasreview);
			
			return "/myReservationList.do?pageNo=" +pNo;
		}else {
		//2) writeReview.jsp로 파라미터 전달해서 맞게 출력한다. 
	
		}
		return  "./view/jsp/review/writeReview.jsp?room_name="+rName;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processSubmit 진행");
		//1.파라미터받기
		String title = request.getParameter("title");
		String roomName = request.getParameter("roomName");
		String score = request.getParameter("score");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		int sc = Integer.parseInt(score); // 점수 정수로 변환 
		int reserve_no = Integer.parseInt(no);
		System.out.println("여기까지 진행");
		//로그인 정보 가져오기
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("AUTHUSER");
		String id = user.getId();
		
		WriteReviewRequest writeReReq = new WriteReviewRequest(id,title,roomName,sc,content, reserve_no); 
		
		//1-2. 나머지 값 가져오기
	
		//2.비즈니스 로직 수행
		//1) 후기게시판에 insert 하기 
		WriteReviewService writeReviewService = new WriteReviewService();
		if(writeReviewService.insertReviewTable(writeReReq)) {//insert 성공하면
			
			return "/reviewList.do";//후기게시판 목록으로 감
		}
		else {
			
			return FORM_VIEW;
		}
		
	}

	

}
