package member.memCommand;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.Member;
import member.mem.Pet;
import member.mem.User;
import member.memService.DuplicateidException;
import member.memService.EditRequest;
import member.memService.InfoService;
import member.memService.JoinRequest;
import member.memService.JoinService;
import mvc.command.CommandHandler;

public class EditProfile implements CommandHandler {
	private static final String FORM_VIEW = "../view/jsp/member/editProfile.jsp";
	private InfoService infoService = new InfoService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("AUTHUSER");
		if(user==null) {
			return "../member/login.do";
		}
		Member userInfo =infoService.getUserInfo(user);
		String[] email = userInfo.getEmail().split("@");
		String phonNum = userInfo.getPonnumber();
		String frontNum = phonNum.substring(0,3);
		String middleNum = phonNum.substring(4,8);
		String endNum = phonNum.substring(9,13);
	
		List<Pet> petList = new ArrayList<Pet>();
		petList = infoService.getPetInfo(user);
		
		req.setAttribute("frontNum", frontNum);
		req.setAttribute("middleNum", middleNum);
		req.setAttribute("endNum", endNum);
		req.setAttribute("email", email);
		req.setAttribute("USERINFO", userInfo);
		req.setAttribute("petList", petList);
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		EditRequest editReq = new EditRequest();
		editReq.setPassword(req.getParameter("password"));
		editReq.setPonnumber(req.getParameter("ponnumber"));
		editReq.setAddress(req.getParameter("address"));
		editReq.setEmail(req.getParameter("email"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		editReq.validate(errors);
		System.out.println("유효성");
		editReq.pwdCheck(errors, user, editReq.getPassword());
		System.out.println("비밀번호");
		
		if(!errors.isEmpty()) {
			return processForm(req,res);
		}
		try {

			boolean edit = infoService.edit(user,editReq);
			
			if(edit) {
				infoService.deletePet(user);
			String submit[] = req.getParameterValues("pname");
			

		
			if(submit!=null &&submit.length>0) {
				System.out.println("asdad2");
					String str = req.getParameter("id");
					String pnameArr[] =req.getParameterValues("pname");
					String pbirthArr[] =req.getParameterValues("pbirth");
					String weightArr[] = req.getParameterValues("weight");
					String neuterArr[] = req.getParameterValues("neuter");
					String medicalArr[] = req.getParameterValues("medical");
					String memoArr[] = req.getParameterValues("memo");
					
					System.out.println("SAd");
					JoinService joinService = new JoinService();
					joinService.petList(str,pnameArr,pbirthArr,weightArr,neuterArr,medicalArr,memoArr);
			}
			return "../view/jsp/member/editProfileSuccess.jsp";
			}
			return "../view/jsp/member/editProfileSuccess.jsp";
			
		}catch(DuplicateidException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}
	
}
