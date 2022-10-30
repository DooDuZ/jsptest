<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<%
		session.setAttribute("m_id", null);	//특정 세션만 제거
		session.setAttribute("m_no", null);	//특정 세션만 제거
		
		//페이지 전환 [메인페이지 이동]
		response.sendRedirect("/mamin/view/index.jsp");
	%>
</body>
</html>