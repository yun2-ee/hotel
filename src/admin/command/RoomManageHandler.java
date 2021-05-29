package admin.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.room.RoomDTO;
import admin.service.RoomManageService;
import mvc.command.CommandHandler;

public class RoomManageHandler implements CommandHandler {
	private static final String ROOM_MANGE ="../view/jsp/admin/roomManage.jsp" ;
	private static final String ROOM_MANGE_SUCCESS ="../view/jsp/admin/roomManageSuccess.jsp" ;
	private RoomManageService roomManageService = new RoomManageService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String submit = request.getParameter("changeList");
		if(submit!=null) {
			if(submit.equals("변경")) {
				String changeNoArr[] =request.getParameterValues("changeNo");
				String roomStatusArr[] =	request.getParameterValues("roomstatus");
				String priceArr[] =request.getParameterValues("price");
				
				int result = roomManageService.changeList(changeNoArr,roomStatusArr,priceArr);
				request.setAttribute("result",result);
				return ROOM_MANGE_SUCCESS;
			}
		}
		List<RoomDTO> roomList = roomManageService.getRoomManagePage();
		request.setAttribute("roomList", roomList);
		
		
		return ROOM_MANGE ;
	}

}
