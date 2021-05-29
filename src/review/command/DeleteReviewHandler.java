package review.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import review.service.DeleteReviewService;


public class DeleteReviewHandler implements CommandHandler {
	DeleteReviewService deleteservice = new DeleteReviewService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteReviewHandler의 process()진입"); //확인용 -> 나중에 삭제
		
		//1.파라미터 받기
		String rNo = request.getParameter("reviewNo");
		String id = request.getParameter("id");
		System.out.println("rNO"+rNo +"id=" +id);
		
		int rno = Integer.parseInt(rNo);
		//2.비즈니스 로직실행
		
		HttpSession session = request.getSession();
		session.getAttribute("AUTHUSER");// 현재 로그인한 회원의 정보를 가져온다. (나중에 연결)

		//delete 관련 처리
		Map<String, Boolean> delete = new HashMap<String,Boolean>();
		request.setAttribute("DELETE", delete);
		
		User user = (User)session.getAttribute("AUTHUSER");// 현재 로그인한 회원의 정보를 가져온다. (나중에 연결)
		
		//1. 작성자와 현재 로그인한 회원의 아이디가 맞는지 확인 
		if(id.equals(user.getId())){ //맞다면 삭제 가능 하다.
			//2. 삭제를 진행한다.
			if(deleteservice.deleteReview(rno)) { //성공
				return "./view/jsp/review/DeleteSuccess.jsp";
			}
			else {//실패
				delete.put("deleteErrors", Boolean.TRUE);
			}
		}else { // 다시 상세보기로 돌아가서 alert문 띄워준다.
			delete.put("idErrors",Boolean.TRUE);
			
		}
	
		return "/DetailReview.do?reviewNo=rNo";
		}//process
}

