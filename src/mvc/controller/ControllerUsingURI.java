package mvc.controller;

//io패키지는 Input 과   Output관련 기능을 제공하는  패키지
import java.io.FileReader;

//io패키지는  Exception처리해야 한다
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

//p540
//이 클래스는 
//client의 reuqest을 처리하는 Controller(컨트롤러)클래스이다
public class ControllerUsingURI extends HttpServlet {

	//field - p540 22라인
	//        <커맨드,  핸들러인스턴스> 매핑 정보 저장
	//map.put("요청내용",담당Controller명);
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();
	
	//constructor
	
	//method
	//get방식으로 요청할 때마다 매번 호출되는 method
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()호출");
		process(request,response);
	}

	//post방식으로 요청할 때마다 매번 호출되는 method
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()호출");
		process(request,response);
	}

	//p541 60라인
	//client의 reuqest을 처리 => 요청을 처리하는 함수
	private void process(HttpServletRequest request, HttpServletResponse response) 
			     throws ServletException, IOException {
		System.out.println("process()호출------");
		
		//p541 62라인~65라인
		String command = request.getRequestURI();
		if( command.indexOf(request.getContextPath())==0 ){
			 command = command.substring(request.getContextPath().length());
		}
		/*command.substring(beginIndex, endIndex) :
	   Returns a string that is a substring of this string. 
	   The substring begins at the specified beginIndex and extends to the character at index endIndex - 1*/

        
        //command 변수에 저장된 값은
        //Map의 key로 사용
        //요청내용을 Key로 사용해서  꺼낸 value는 담당컨트롤러가 된다
        //handler변수에는 담당컨트롤러가 저장된 상태가 된다
		System.out.println("command =  "+command);
        CommandHandler handler = commandHandlerMap.get(command);
        if (handler == null) {
            handler = new NullHandler();
        }
        
        //View를 담기위한 변수선언
        String viewPage = null;
        try {
        	//2.비즈니스로직을 수행
        	//(여기에서는 각 담당컨트롤러.java파일의  process()함수를 호출)
        	//담당컨트롤러가 최종적으로 return해주는
        	//String 타입의 값은   view명이 된다
            viewPage = handler.process(request, response);
            System.out.println("viewPage = "+ viewPage);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        
        //지정된 View가 있으면   강제로 forwarding하여
        //해당 view문서를 이동시킨다
        if (viewPage != null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
		
	}//process()
	
	//p533  25라인
	//최초 1번만 호출되어 실행 -> 주로 환경설정
	@Override
	public void init() throws ServletException {
		System.out.println("init()호출-");
		
		//web.xml안에서 configFile이름을 가진  InitParameter의 value가져오기
        String configFile = getInitParameter("configFile");
        //여기에서는  /WEB-INF폴더/commandHandlerURI.properties을 읽어들인다
        
        /*자바의 Properties 클래스(P535참고) 
         * The Properties class represents a persistent set of properties. 
         * The Properties can be saved to a stream or loaded from a stream. Each key and its corresponding value in the property list is a string.
         * 내부적으로 Hashtable을 사용하며, key와 value를 (String, String) 로 저장
 		 * 주로 어플리케이션의 환경설정에 관련된 속성을 저장하는데 사용되며 
   		      파일로부터 편리하게 값을 읽고 쓸 수 있는 메서드를 제공한다. 
         */
        
        //Properties객체생성
        Properties prop = new Properties();
        
        //commandHandlerURI.properties 설정파일의 경로를 가져와 configFilePath에 저장
        String configFilePath = getServletContext().getRealPath(configFile);
        System.out.println("configFilePath = "+configFilePath);
        try (
        	//외부에 있는 File을 읽어오기위해서	
            //FileReader 클래스의 객체를 생성
            //FileReader.FileReader(String fileName)
        	//String타입의  fileName=>읽어올 파일의 경로	
        	FileReader fis = new FileReader(configFilePath)) {
        	
        	//지정한 InputStream으로부터
        	//(읽어들인 파일->/WEB-INF/commandHandlerURI.properties)
        	//파일의 내용을 읽어온다
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        
        //.keySet() : Returns a Set view of the keys contained in this map.
        //위에서 생성한  Properties객체의 참조변수 prop에서
        //key목록을 set으로 가져온다
        //컬렉션 Set의 iterator()를 이용해서 꺼낸다 => key목록꺼내기
        Iterator keyIter = prop.keySet().iterator();
        
        //예)  /hello.do=ch18.hello.HelloHandler
        //모든 key목록을 꺼내서
        while (keyIter.hasNext()) {
        	
        	//꺼내진 1개의 Key를 이용하여 -> 예)  /hello.do
            String command = (String) keyIter.next();
            
            //해당 key의 value를 꺼낸다   
            //이 value는 담당 Controller가 된다 
            //예) ch18.hello패키지.HelloHandler.java
            String handlerClassName = prop.getProperty(command);
            
            System.out.println(handlerClassName);
            try {
            	
            	//handlerClassName에 저장된 것은  단순 String이므로
            	//실행파일(즉 java파일로) 만들어야 한다
            	//위에서 저장된 변수안의 이름으로 된  class파일을 찾아서
            	//handlerClass에 저장한다
                Class<?> handlerClass = Class.forName(handlerClassName);
                
                //.class을  담당컨트롤러 파일 객체로 처리
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                
                //실제 동작가능한 파일객체를  
                //map형식으로 저장해놓는다
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
	}//init()

}




