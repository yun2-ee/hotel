package member.memCommand;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.memService.DuplicateidException;
import member.memService.JoinRequest;
import member.memService.JoinService;
import member.memService.request;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "../view/jsp/member/joinForm.jsp";
	private JoinService joinService = new JoinService();
	
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
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("asdw1");
		JoinRequest joinReq = new JoinRequest();
		System.out.println("asdw2");
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPssword(req.getParameter("confirmPassword"));
		String birthdate = req.getParameter("birthdate");
		Date d = java.sql.Date.valueOf(birthdate);
		joinReq.setBirthdate(d);
		joinReq.setPonnumber(req.getParameter("ponnumber"));
		joinReq.setAddress(req.getParameter("address"));
		joinReq.setEmail(req.getParameter("email"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {

			boolean join = joinService.join(joinReq);
			
			if(join) {
			
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
			return "../view/jsp/member/joinSuccess.jsp";
			}
			return "../view/jsp/member/joinSuccess.jsp";
		}catch(DuplicateidException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}
	
}
