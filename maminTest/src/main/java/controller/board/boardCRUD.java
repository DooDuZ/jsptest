package controller.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.BoardDao;
import model.dto.BoardDto;


/**
 * Servlet implementation class boardCRUD
 */
@WebServlet("/board/boardCRUD")
public class boardCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardCRUD() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    //글 출력  GET 김장군
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String type= request.getParameter("type");
			////////////전체 글출력//////////////////////////////////
		if( type.equals("1") ) {
			ArrayList<BoardDto> list =  BoardDao.getInstance().getBoardList();
			JSONArray array = new JSONArray();
			for(int i=0;i<list.size();i++) {
				JSONObject object = new JSONObject();
				object.put("b_no", list.get(i).getB_no() );
				object.put("b_title", list.get(i).getB_title() );
				object.put("b_date", list.get(i).getB_date() );
				object.put("m_id", list.get(i).getM_id() );
				array.add(object);
				
			}
			
			response.getWriter().print(array);
				//////////////////////////////개별 글 출력///////////////////////
		}else if( type.equals("2") ){
			int b_no = (Integer)request.getSession().getAttribute("b_no");///세션에서 글번호 호출
		
			BoardDto dto = BoardDao.getInstance().getBoard(b_no);
			JSONObject object = new JSONObject();
			object.put("b_no", dto.getB_no());
			object.put("b_title", dto.getB_title());
			object.put("b_content", dto.getB_content());
			object.put("m_id", dto.getM_id());
			object.put("b_file", dto.getB_file());
			if( request.getSession().getAttribute("m_id")!=null && dto.getM_id().equals( request.getSession().getAttribute("m_id"))) {//세션 아이디랑 글작성 아이디랑 일치하면
				object.put("btnaction", true);
			}
			
			response.getWriter().print(object);
		}
	}

	//글 등록 POST 김장군
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadpath = request.getSession().getServletContext().getRealPath("/upload"); ////업로드 파일 저장 경로
	
		MultipartRequest multi = new MultipartRequest(
				request ,  						
				uploadpath , 					
				1024 * 1024 * 10, 				
				"UTF-8" , 					
				new DefaultFileRenamePolicy() 
				); 
		String b_title = multi.getParameter("b_title");	
			 
		String b_content = multi.getParameter("b_content");
			
		
	    String b_file = multi.getFilesystemName("b_file"); 
			
		int m_no  = (Integer)request.getSession().getAttribute("m_no");
		
		
			
		boolean result = BoardDao.getInstance().write(b_title, b_content, b_file ,m_no);
		response.getWriter().print(result);

	}	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	//글 삭제 메소드 김장군
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		BoardDto dto = BoardDao.getInstance().getBoard(b_no);
		boolean result = BoardDao.getInstance().boardDelete(b_no);///DB삭제
		if( result ) { //파일 삭제
			String deletepath = request.getSession().getServletContext().getRealPath("/upload/"+ dto.getB_file() );
			File file = new File( deletepath );
			if( file.exists() ) file.delete();	// 해당 경로에 존재하는 파일을 삭제
			// File 클래스 
				// 자바 외부에 존재 하는 파일 조작/제어 메소드 제공하는 클래스
				// 1. 객체명.length() : 해당 파일의 바이트 길이
				// 2. 객체명.delete() : 해당 파일의 삭제
				// 3. 객체명.exists()	 : 해당 파일이 존재하면 true / false 
		}
		response.getWriter().print(result);
	}

}
