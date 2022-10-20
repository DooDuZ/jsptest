package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.dao.BoardDao;
import model.dto.BoardDto;


@WebServlet("/view")
public class view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public view() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cNo = (Integer) request.getSession().getAttribute("cNo");
		BoardDto dto = new BoardDao().getboard(cNo);
		
		JSONObject object = new JSONObject();
		
		object.put("cNo", dto.getcNo());
		object.put("title", dto.getTitle());
		object.put("content", dto.getContent());
		object.put("writer", dto.getWriter());
		object.put("date", dto.getCdate());
		object.put("view", dto.getView());
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(object);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
