package admin.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.ListMemberService;
import admin.service.MemberPage;
import member.mem.Pet;
import member.mem.User;
import member.memService.InfoService;
import mvc.command.CommandHandler;

public class ListMemberHandler implements CommandHandler{
	
	private ListMemberService listService = new ListMemberService();
	private InfoService infoService = new InfoService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNoVal = request.getParameter("pageNo");
		String seach = request.getParameter("seach");
		String petorner	=	request.getParameter("id");
		System.out.println(petorner);
		String petOrner = "";
		if(petorner!=null) {
			petOrner=petorner;
		}
		if(!petOrner.isEmpty()||petOrner!=null) {
			
			User user= new User();
			user.setId(petOrner);
			List<Pet> petList = new ArrayList<Pet>();
			petList = infoService.getPetInfo(user);
			request.setAttribute("petList", petList);
			request.setAttribute("petOrner",petOrner);
		}
		
		int pageNo = 1;
		if(pageNoVal!=null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		MemberPage memberPage = listService.getMemberPage(pageNo, seach);
		
		request.setAttribute("memberPage", memberPage);
		
		return "../view/jsp/admin/adminMember.jsp";
	}
}
