package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;
import model.dto.MemberDto;

@WebServlet("/member/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public signup() {
		super();
	}

	//비아 - 아이디 중복체크와 이메일 중복체크
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 변수요청
		String type = request.getParameter("type");
		if (type.equals("1")) { // 아이디 중복체크
			String mid = request.getParameter("mid");
			// 2. DAO
			boolean result = MemberDao.getInstance().idcheck(mid);
			// 3. DAO 결과 응답
			response.getWriter().print(result);
		} else if (type.equals("2")) { // 이메일 중복체크
			String memail = request.getParameter("memail");
			boolean result = MemberDao.getInstance().emailcheck(memail);
			response.getWriter().print(result);
		}
	}

	//비아 - 회원가입
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 변수 요청시 한글 인코딩
		request.setCharacterEncoding("UTF-8");

		// 1. JSP(HTML) <form>에 입력된 데이터 요청하기
		// name값과 동일해야 함
		String m_id = request.getParameter("id");
		String m_password = request.getParameter("pw");
		String m_email = request.getParameter("email");
		String m_nick = request.getParameter("m_nick");
		String profile = request.getParameter("profile");
		String m_img = request.getParameter("character")+".png";
		//System.out.println("character : "+character);
		
		// 2. 변수 6개 -> DTO 객체화
		// 회원번호와 날짜, 포인트는 일단 0이나 null로
		MemberDto dto = new MemberDto(0, m_id, m_password, m_email, 0, m_nick, m_img, profile, 0, 0);
		// 3. 통신 확인 [dto 내용 확인]
		//System.out.println(dto.toString());

		// 4. DAO 싱글톤 객체를 호출해서 메소드 호출
		boolean result = MemberDao.getInstance().signup(dto);

		// 5. 결과 제어
		if (result) response.sendRedirect("/mamin/view/login.jsp");
		else System.out.println("회원가입 실패");
	}

}
