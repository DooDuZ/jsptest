package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class filedown
 */
@WebServlet("/board/filedown")
public class filedown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filedown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ---- 업로드 된 파일을 다운로드  ---
		// 1. 다운로드 할 파일명 요청 
		request.setCharacterEncoding("UTF-8");					// 한글 인코딩
		String b_file = request.getParameter("b_file");			// 다운로드할 파일명 요청 
		// 2. 업로드된 경로 + 파일명으로 해당 파일 위치 찾기 
		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("/upload/"+b_file);
		
		// 3. 해당 경로의 파일을 객체화 [ java에서 파일클래스 = File ]
		File file = new File(uploadpath);	// 해당 경로에 존재하는 파일을 객체화 불러오기  [ 해당 파일을 조작/메소드 ]
		
		// 4. HTTP 해서 지원하는 다운로드형식 메소드 [ response ]
		response.setHeader( 
				"Content-Disposition" , // 다운로드 형식 HTML에서 지원 [ 브라우저 차이 있음 ] 
				"attachment;filename="+ URLEncoder.encode(b_file , "UTF-8")   // 다운로드시 명시된 파일명
				);	// URL 경로에 한글이 있을경우 : URLEncoder.encode( 데이터  , "UTF-8" )
		
		// 5. 파일 전송 [ 외부와 데이터 통신 (스트림) = 바이트단위 ]
		// *  파일의 내용물의 바이트로 모두 읽어온다.
			// 1. [ 입력스트림 객체 생성 ]
		BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file) );
			
		byte[] bytes = new byte[ (int)file.length() ];
		
		fin.read( bytes );	// 읽어온 바이트를 바이트배열에 저장 
		// * 읽어온 바이트배열를 출력한다.
			// 4. [ 출력 스트림 객체 생성 ]	// http로 출력하기 위한 response.getOutputStream()
		BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );
			// 5. 배열에 존재하는 바이트 출력하기
		fout.write(bytes); // 바이트배열에 저장된 바이트를 모두 내보내기 
			// 스트림은 버퍼 ( 전송시 사용되는 임시 메모리 공간 = 대용량에서 꼭 모두 전송후 초기화 )
		fout.flush();	// 출력 스트림 버퍼 초기화 
		fin.close();	// 입력 스트림 닫기 
		fout.close();	// 출력 스트림 닫기 [ close 하면 버퍼 초기화 ] 
		
		
		
		
		
		
			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
