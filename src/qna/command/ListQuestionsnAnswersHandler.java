package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.ListQnaService;
import qna.service.QnaPage;

public class ListQuestionsnAnswersHandler implements CommandHandler {

	private ListQnaService listQnaService = new ListQnaService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pageNoVal = request.getParameter("pageNo");
		String qnaseach = request.getParameter("qnaseach");
		
		int pageNo = 1;
		if(pageNoVal!=null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		QnaPage qnaPage = listQnaService.getQnaPage(pageNo, qnaseach);
		request.setAttribute("qnaPage", qnaPage);
		return "../view/jsp/qnaboard/qnaboard.jsp";
	}

}
