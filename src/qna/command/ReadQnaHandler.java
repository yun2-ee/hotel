package qna.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.QnaContentNotFoundException;
import qna.service.QnaData;
import qna.service.QnaNotFoundException;
import qna.service.ReadQnaService;

public class ReadQnaHandler implements CommandHandler {
	
	private ReadQnaService readQnaService = new ReadQnaService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strNo = request.getParameter("no");
		int qnaNum = Integer.parseInt(strNo);
			
		try {
			QnaData qnaData = readQnaService.getQnaDTO(qnaNum, true);
			
			request.setAttribute("qnaData", qnaData);
			
			return "../view/jsp/qnaboard/readQna.jsp";
		}catch(QnaNotFoundException | QnaContentNotFoundException e) {
			request.getServletContext().log("no Qna",e);
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
