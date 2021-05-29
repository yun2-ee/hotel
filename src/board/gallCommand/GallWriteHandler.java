package board.gallCommand;

import java.io.File;
import java.io.IOException;
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

import board.gall.ContentRequest;
import board.gall.GallDTO;
import board.gall.GallImgDTO;
import board.gallService.WriteGallService;
import member.mem.User;
import mvc.command.CommandHandler;




public class GallWriteHandler implements CommandHandler {
	private static final String FORM_VIEW = "../view/jsp/gall/gallWrite.jsp";
	private static final String SUCCESS_VIEW = "../view/jsp/gall/gallWriteSuccess.jsp";
	WriteGallService writeService = new WriteGallService();
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
		System.out.println("GallWriteHandler의 processSubmit()진입");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");  
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		String encType = "UTF-8"; // cos.jar파일의 객체 생성 시점에 넣어줌
		int maxFileSize = 10 * 1024 *1024; // 5MB
		String realPath = request.getServletContext().getRealPath("/images");
		System.out.println("realPath ="+realPath);
		
		
		String path = realPath; //폴더 경로
		File Folder = new File(path);

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
			System.out.println();
		}
		  // 인코딩필터 = body에 들어가는 인코딩이라 파일업로드시 인코딩과 개념 다름
		  // 파일인코딩시 encType은 MultipartRequest의 관점 => 파일 제목등 파일과 관련이 있음
	
		  // MulitpartRequest(request, 저장경로[, 최대허용크기, 인코딩캐릭터셋, 동일한 파일명 보호여부]);
		  // 파일명보호: DefaultFileRenamePolicy => name.zip, name1.zip, ...
		  
		  // 업로드에 해당하는 부분
		  MultipartRequest mr = new MultipartRequest(request, realPath, maxFileSize, encType, new DefaultFileRenamePolicy());
		  
		  List<GallImgDTO> gallDtoList = new ArrayList<GallImgDTO>();
		  String userFileName="";
		  String uploadFileName="";
		  String newFileName="";
		  int i = 0;
		  
		  Enumeration fileNames = mr.getFileNames();
		  while(fileNames.hasMoreElements()) {
			  String parameter = (String)fileNames.nextElement();
			  long currentTime = System.currentTimeMillis();
			    SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
			    	i++;
			    	userFileName = mr.getOriginalFileName(parameter);
			    	//userFileName=	mr.getOriginalFileName(originFile.getName());
			    	// 파일업로드
//			        uploadFileName = mr.getFilesystemName(parameter);
			        uploadFileName = mr.getFilesystemName(parameter);
			        //사용자가 저장한 파일명
			        
			        if(uploadFileName==null || uploadFileName.length()==0) continue;
			        if(!uploadFileName.endsWith(".jpg")&&!uploadFileName.endsWith(".png")) {
			        	File file = new File(realPath+uploadFileName);
			        	file.delete();
			        }else {
			        	File originFile = new File(realPath+"/"+uploadFileName);
			        	System.out.println("originFile.getName()="+originFile.getName());
			        	newFileName = simDf.format(new Date(currentTime)) +i+"."+uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			        	File tempFile = new File(realPath+"/"+newFileName);
			        	//경로까지포함  GallImgDTO gallImgDto = new GallImgDTO(0, 0, userFileName,realPath+"\\"+newFileName);
			        	 GallImgDTO gallImgDto = new GallImgDTO(0, 0, userFileName,newFileName);
			        	gallDtoList.add(gallImgDto);
			        	if(!originFile.renameTo(tempFile)) {
			        		System.out.println("파일명변경 실패");
			        	}
			        }
			        
			        
			        
			        
			  }//while


		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		System.out.println("title ="+title);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		String userId=user.getId();
		String userName=user.getName();
		
		

		
		ContentRequest contentReq =new ContentRequest(userId,userName,title,content);
		
		contentReq.validate(errors);
		if(!errors.isEmpty()) {
			request.setAttribute("errors",errors);
			return FORM_VIEW;
		}
		int newGallArticleNo = writeService.write(contentReq,gallDtoList);			
		request.setAttribute("newGallArticleNo", newGallArticleNo);
		
		return  SUCCESS_VIEW;
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

}
