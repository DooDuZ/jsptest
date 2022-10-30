package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDao;

@WebServlet("/member/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m_id = request.getParameter("m_id");
		String m_password = request.getParameter("m_password");
		
		int result = MemberDao.getInstance().login(m_id, m_password);
		
		// 만약에 로그인 성공하면 세션 할당
		if(result == 1) {
			int m_no = MemberDao.getInstance().getNo(m_id);
			HttpSession session = request.getSession();		//1. 세션 객체 선언
			session.setAttribute("m_id", m_id);				//2. 세션 생성 [세션 메모리 할당]
			session.setAttribute("m_no", m_no);				//2. 세션 생성 [세션 메모리 할당]
		}
		
		//3. DB의 결과를 JS로 보냄
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
