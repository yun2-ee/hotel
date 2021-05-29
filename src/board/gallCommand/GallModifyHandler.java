package board.gallCommand;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.gall.GallData;
import board.gall.GallImgDTO;
import board.gallService.GallReadService;
import board.gallService.ModifyGallService;
import member.mem.User;
import mvc.command.CommandHandler;

public class GallModifyHandler implements CommandHandler {
	private static final String FORM_VIEW = "../view/jsp/gall/gallModify.jsp";
	private static final String SUCCESS_VIEW = "../view/jsp/gall/gallModifySuccess.jsp";
	private GallReadService readService = new GallReadService();
	
	ModifyGallService modifyService = new ModifyGallService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		String encType="UTF-8"; //encondig타입 파일 받을때의
		int maxFilesize=10*1024*1024; //10MB
		String realPath = request.getServletContext().getRealPath("/images");	
		File Folder = new File(realPath); //폴더 객체 생성
		if(!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("폴더가 생성되었습니다.");
			}catch(Exception e) {
				e.getStackTrace();
			}
		}else {
			System.out.println("폴더가 이미 생성되어있습니다.");
			
		}

		
		
		MultipartRequest mr = new MultipartRequest(request,realPath,maxFilesize,encType,new DefaultFileRenamePolicy());
		
		List<GallImgDTO> gallDtoList = new ArrayList<GallImgDTO>();
		String userFileName=""; //유저가 올린 파일명
		String uploadFileName=""; //업로도된 파일명
		String newFileName=""; //내가 새로 이름지어준 파일명
		int i=0;
		
		Enumeration<String> fileNames = mr.getFileNames(); //파일이름 받아와줌
		while(fileNames.hasMoreElements()) {
			String parameter = fileNames.nextElement();
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
			i++;
			userFileName = mr.getOriginalFileName(parameter);
			uploadFileName = mr.getFilesystemName(parameter);
			System.out.println(realPath+uploadFileName);
			System.out.println(realPath+"/"+uploadFileName);
			if(uploadFileName==null||uploadFileName.length()==0) continue;
			if(!uploadFileName.endsWith(".jpg")&&!uploadFileName.endsWith(".png")) {
				File file = new File(realPath+"/"+uploadFileName);
		
				file.delete();
			}else {
				File originFile = new File(realPath+"/"+uploadFileName);
				System.out.println("originFile.getName()="+originFile.getName());
				newFileName = simDf.format(new Date(currentTime))+i+"."+uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
				File tempFile = new File(realPath+"/"+newFileName);
				GallImgDTO gallImgDto = new GallImgDTO(0, 0, userFileName, newFileName);
				gallDtoList.add(gallImgDto);
				if(!originFile.renameTo(tempFile)) {
					System.out.println("파일명 변경실패");
				}
			}
			
		}
		
		List<Integer> deleteImgNoList = new ArrayList<Integer>(); 
		String deleteImgStr1 = mr.getParameter("deleteImgNo1");
		System.out.println(deleteImgStr1);
		if(deleteImgStr1!=null) {
			Integer deleteImgNo1 = Integer.parseInt(deleteImgStr1);
			deleteImgNoList.add(deleteImgNo1);
		}
		String deleteImgStr2 = mr.getParameter("deleteImgNo2");
		if(deleteImgStr2!=null) {
			Integer deleteImgNo2 = Integer.parseInt(deleteImgStr2);
			deleteImgNoList.add(deleteImgNo2);
		}
		String deleteImgStr3 = mr.getParameter("deleteImgNo3");
		if(deleteImgStr3!=null) {
			Integer deleteImgNo3 = Integer.parseInt(deleteImgStr3);
			deleteImgNoList.add(deleteImgNo3);
		}
		
		int no = Integer.parseInt(mr.getParameter("no"));
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		int gPageNo = Integer.parseInt(mr.getParameter("gPageNo"));
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		
		ModifyRequest modifyReq = new ModifyRequest(user.getId(),no,title,content,new Timestamp(System.currentTimeMillis()));
		
		
		modifyReq.validate(errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("errors",errors);
			return FORM_VIEW;
		}
		modifyService.modify(modifyReq,deleteImgNoList,realPath,gallDtoList);			
		request.setAttribute("modifyReq", modifyReq);
		request.setAttribute("gPageNo", gPageNo);
		
		return  SUCCESS_VIEW;
	}
	
	
	
	
	
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int gall_no = Integer.parseInt(request.getParameter("no"));
		int gPageNo = Integer.parseInt(request.getParameter("gPageNo"));
		
		try {
			
			GallData gallData = readService.getGall(gall_no,false);
			
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("AUTHUSER");
			System.out.println(gallData.getGallDto().getMem_id());
			ModifyForm modifyForm = new ModifyForm(gall_no,gallData);
			
			boolean result = canModifiy(user.getId(),gallData.getGallDto().getMem_id());
			
			if(!result) {
				
			}
			
			request.setAttribute("modifyForm",modifyForm);
			request.setAttribute("gPageNo",gPageNo);
			
			return FORM_VIEW;
		}catch(GallDataNotFoundException e) {
			request.getServletContext().log("no gallData",e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		

	}
	private boolean canModifiy(String user_id, String gall_id) {
		return user_id.equals(gall_id);
	}
}
