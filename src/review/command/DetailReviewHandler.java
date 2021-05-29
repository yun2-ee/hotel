package review.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import review.dto.ReviewDTO;
import review.service.DetailReviewService;
import review.service.WriteReviewRequest;
import review.service.WriteReviewService;

public class DetailReviewHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DetailReviewHandler의 process()진입"); //확인용 -> 나중에 삭제

		HttpSession session = request.getSession();
		DetailReviewService detailReviewService = new DetailReviewService();
		//1.파라미터 받기
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		System.out.println("reviewNo=" + reviewNo );
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		System.out.println("pageNo = " + pageNo);
		
		//2.비즈니스 로직 수행
		//1)
			boolean isHit = false; //조회수 증가할지 말지 여부를 저장하는 변수
			//user가 본 게시물의 글번호를 누적해서 저장하기위한 list준비
			ArrayList<Integer> list = (ArrayList)session.getAttribute("HIT");
			
			if(list==null || list.size()==0) {
				//한 번도 그 어떠한 글도 본적이 없는 경우
				list = new ArrayList<Integer>();
				list.add(reviewNo); //list.add(글번호) 1 100 99
				isHit = true;
				session.setAttribute("HIT", list);
			}else if( list.contains(reviewNo) ) { //해당글번호가  list에 포함되어있다면
				//글을 본적이지만 이 글을 본적이 있는 유저인 경우
				isHit = false;
			}else {
				//글을 본적이지만  다른 글(번호)을 본적이 있는 경우
				list.add(reviewNo); //list.add(글번호)
				isHit = true;
				session.setAttribute("HIT", list);//다음을 위해서  세션에 기록하자
			}
			
			ReviewDTO reviewdto =detailReviewService.getReview(reviewNo, isHit);
		
			//3.Model- 번호,작성자id,작성자명,제목,내용,작성일,수정일, 조회수
	     	request.setAttribute("reviewdto",reviewdto);
	     	request.setAttribute("pageNo",pageNo); //페이지번호. 릴레이용
			
			//4.View
			return "./view/jsp/review/detailReview.jsp";
		}//process
		
	
}
