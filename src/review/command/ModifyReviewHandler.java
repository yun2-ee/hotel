package review.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import review.dto.ReviewDTO;
import review.service.DetailReviewService;
import review.service.ModifyReviewRequest;
import review.service.ModifyReviewService;

public class ModifyReviewHandler implements CommandHandler {

	private static final String FORM_VIEW= "./view/jsp/review/modifyReview.jsp";
	
	//글상세보기를 위한 ReadArticleService 참조변수 선언
	private DetailReviewService detailReviewService= new DetailReviewService();
	
	//글수정을 위한 ModifyArticleService 참조변수 선언
	private ModifyReviewService modifyService  = new ModifyReviewService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ModifyReviewHandler의 process()진입/method방식="+request.getMethod());
		//METHOD방식을 구분해서
		if(request.getMethod().equalsIgnoreCase("GET")) {//수정폼 보여주기 요청(GET방식) 
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //수정폼에서 수정하기 버튼을 누른 것-> 최종 db에서 수정할수있게~
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

	//글쓰기처리 요청(POST방식) -p670 66라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ModifyReviewHandler 의 processSubmit 진입");
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//글번호
		int pageNo=Integer.parseInt(request.getParameter("pageNo")); //페이지번호.릴레이용
		String title = request.getParameter("title");	 //제목
		String content = request.getParameter("content"); //내용
		int score = Integer.parseInt(request.getParameter("score"));
		System.out.println("파라미터 받기 성공!");
		
		//로그인한 user의 정보가져오기
		HttpSession session=request.getSession();
		/*User authUser = (User)session.getAttribute("AUTHUSER");*/
		User user = (User)session.getAttribute("AUTHUSER");
		
		//비즈니스로직을 수행할 때 필요한 데이터를  ModifyRequest객체로 생성 
		ModifyReviewRequest modReq = new  ModifyReviewRequest(
				title,score,content,no);
		
		//P670 77라인
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors", errors); //에러정보
		modReq.validate(errors);
		if(!errors.isEmpty()) { //에러가 있으면(여기에서는 title유효성검사)
			return FORM_VIEW;   //에러정보를 가지고 수정폼으로 가라
		}
		
		//p670 84라인
		//2.비즈니스로직수행<->ModifyArticleService<->
		//					ArticleDAO,ArticleContentDAO<->DB 
		
		modifyService.modify(modReq);
		  
		//3.Model
		request.setAttribute("modReq", modReq); //수정된 글정보
		request.setAttribute("pageNo", pageNo); //페이지번호.릴레이용
		
		//4.View-성공시 detailReview로 이동 -수정된 내용을 보여주는 상세보기 창으로!!
		return "./view/jsp/review/modifySuccess.jsp";
	}

	//수정폼 보여주기 요청(GET방식)  -p669 38라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		//폼안에는  특정글번호의 상세정보가 출력되어있어야 한다
		System.out.println("ModifyReviewHandler의 processForm()진입");
		
		//1.파라미터가져오기 -no = 글번호
	
		
		int pNo = Integer.parseInt(request.getParameter("pageno"));//릴레이용
		int no  =Integer.parseInt( request.getParameter("reviewNo"));
		
		System.out.println("reveiewNo" + no + " pageNo = " + pNo);
		//2.비즈니스로직수행<->ReadArticleService<->


		ReviewDTO  reviewdto = detailReviewService.getReview(no,false);
		
			
			
			//로그인한 user의 정보가져오기
			HttpSession session=request.getSession();
			User user = (User)session.getAttribute("AUTHUSER");
	

			//로그인한 user가 수정을 할 수 있는 권한이 있는 check
			//==>로그인한 user의 id가   글 작성자id와 일치하는지 check
			boolean result = canModify(user, reviewdto);
			if(!result) { // 맞지 않아서 다시 detailReview 가서 alert 창 뜨게 해야함.
				System.out.println("달라요~");
				request.setAttribute("WriterErrors", "다르다!" );
				
				return "/detailReview.do?reviewNo="+no+"&pageNo=" + pNo;
			}
			
			ModifyReviewRequest modReq = new  ModifyReviewRequest(
					reviewdto.getReview_title(),
					reviewdto.getRoom_name(),
					reviewdto.getMem_name(),
					reviewdto.getReview_score(),
					reviewdto.getReview_content(),
					no
				);

	     	request.setAttribute("modReq",modReq);
	     	request.setAttribute("pageNo",pNo); //페이지번호. 릴레이용
			
			//4.View- 수정폼
			return FORM_VIEW;
		
	}//processForm
	
	//p670 61라인
	//로그인한 user가 수정을 할 수 있는 권한이 있는 check
	//==>로그인한 user의 id가   글 작성자id와 일치하는지 check
	private boolean  canModify(User authUser,ReviewDTO reviewdto ) {
		String loginUserId =  authUser.getId();  //로그인한 user의 id
		String writerId = reviewdto.getMem_id();//글 작성자id
		//스트링클래스.equals("비교문자열") : 값일치비교
		return  loginUserId.equals(writerId);
	}


}
