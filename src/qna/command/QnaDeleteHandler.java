package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.QnaDeleteService;

public class QnaDeleteHandler implements CommandHandler {

	private QnaDeleteService qnaDeleteService = new QnaDeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaDeleteHandler�� process() ���� �Ϸ�");
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("���� �� �� ��ȣ Ȯ�� " + no);
		
		int result = qnaDeleteService.delete(no);
		
		request.setAttribute("result", result);
		return "/view/jsp/qnaboard/qnaDeleteSuccess.jsp";
	}

}
