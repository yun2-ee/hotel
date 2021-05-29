package member.memCommand;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import member.memService.ChangePwdService;
import member.memService.InvalidPwdException;
import member.memService.MemberNotFoundException;
import mvc.command.CommandHandler;

//p622
//이 클래스는 비번을 변경하는 담당컨트롤러이다
//http://localhost/bo/changePwd.do 요청시 호출되는 컨트롤러
//담당컨트롤러<->ChangePwdService<->MemberDAO<->DB
public class ChangePwdHandler implements CommandHandler{

	//form지정-p622 16라인
	private static final String FORM_VIEW = "../view/jsp/member/changePwdFrm.jsp";
	
	//서비스클래스 객체 생성-p622 17라인
	ChangePwdService changePwdSvc = new ChangePwdService();
	
	//p622 20라인
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ChangePwdHandler의 process()진입");
		
		
		if(request.getMethod().equalsIgnoreCase("GET")) { //p622 22라인
			//암호변경페이지(changePwdFrm.jsp)에서 get방식으로 요청되면
			//암호변경페이지(changePwdFrm.jsp)로 이동
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { //p622 24라인
			//암호변경페이지(changePwdFrm.jsp)에서 post방식으로 요청되면
			//비즈니스로직 수행하기위한 함수호출
			return processSubmit(request,response);
		}else {
			//기타 에러처리
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

	//암호변경페이지(changePwdFrm.jsp)에서 get방식으로 요청되면
	//암호변경페이지(changePwdFrm.jsp)로 이동
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	//p623-37라인
	//실제 비즈니스로직 수행
	private String processSubmit(HttpServletRequest request, 
			                     HttpServletResponse response)
								throws Exception {
		
		//1.파라미터 가져오기-p523 44~45라인
		String curPwd = request.getParameter("curPwd");
		String newPwd = request.getParameter("newPwd");
		System.out.println("processSubmit() curPwd/newPwd="
		                   +curPwd+"/"+newPwd);
		
		//Map방식으로 error정보를 담아 관리 
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		//FORM_VIEW에서 에러정보를 확인하고 출력하기 위해 Model로 처리한다
		request.setAttribute("errors", errors);
		
		//현재암호와 새암호 필수입력 체크-p523 47~53라인
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put("curPwd",Boolean.TRUE);
		}
		
		if(newPwd==null || newPwd.isEmpty()) {
			errors.put("newPwd",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		
		//2.비즈니스로직수행 : <->ChangePwdService<->MemberDAO<->DB
		//암호변경페이지(changePwdFrm.jsp)에서
		//현재 암호를 잘못 입력하면  error Map에 에러로 등록
		//p623 - 58라인
		//로그인한 user만이  비밀번호를 변경할 수 있으므로
		//세션정보에서 로그인한 유저의 id를 가져온다
		//우리는 session.setAttribute("AUTHUSER", user); 
		HttpSession session = request.getSession();
		
		//session에는 Object타입이 들어갈 수 있으므로
		//세션.getAttribute(속성명)하면 Object를 return받는다
		//하지만 Object 클래스에는 유저의 정보를 가져올수 있는 method가 존재x
		//User type으로  down class casting을 한다
		User user = (User)session.getAttribute("AUTHUSER");
		String mId = user.getId(); //로그인한 user의 id
		
		//p623-58라인
		try {
			changePwdSvc.changePwd(mId, curPwd, newPwd,errors);
			//3.Model
			//4.View- 비번변경 성공시   changePwdSuccess.jsp이동
			return "../view/jsp/member/changePwdSuccess.jsp";
		}catch(InvalidPwdException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			//비번변경 실패시  changePwdFrm.jsp 이동
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			//비번변경 실패시  changePwdFrm.jsp 이동
			return null;
		}
		
	}
	
}





