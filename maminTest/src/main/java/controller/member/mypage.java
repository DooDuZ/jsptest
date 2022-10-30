package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.dao.MemberDao;
import model.dto.MemberDto;

@WebServlet("/member/mypage")
public class mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public mypage() {
        super();
    }

    //비아 - 회원정보 불러오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 [세션에 로그인 정보 호출]
		String m_id = (String)request.getSession().getAttribute("m_id");
		//2. DB
		MemberDto dto = MemberDao.getInstance().getMember(m_id);
		
		// *** JS는 DTO 사용 x
		//1. JS가 이해할 수 있는 형식으로 변경 [JSON 형식]
		//2. DTO -> JSON 형식 [JAVA 제공x -> google 외부라이브러리 json.simple 적용]
		JSONObject object = new JSONObject();
		object.put("m_nick", dto.getM_nick());
		object.put("m_img", dto.getM_img());
		object.put("m_profile", dto.getM_profile());
		
		//3. 응답
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(object);
	}

	//비아 - 회원정보 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 변수 요청시 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		String m_id = (String)request.getSession().getAttribute("m_id");
		String m_password = request.getParameter("m_password");
		String m_nick = request.getParameter("m_nick");
		String m_profile = request.getParameter("m_profile");
		String m_img = request.getParameter("character");
		if(m_img.equals("아가양")) m_img += ".jpg";
		else m_img += ".png";
		
		
		boolean result = MemberDao.getInstance().update(m_id,m_password,m_nick,m_profile,m_img);

		// 5. 결과 제어
		if (result) response.sendRedirect("/mamin/view/index.jsp");
		else System.out.println("회원 정보 수정 실패");
	}

}
