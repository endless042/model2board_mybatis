<%@page import="board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
모델2 writePro.jsp는 WriteProAction.java에서 다 처리하기에 이파일은 없어도 됨.
<%-- <% request.setCharacterEncoding("EUC-KR"); %>
<%
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null || pageNum == "") {
		pageNum = "1";
	}
	String boardid = request.getParameter("boardid");
	if (boardid==null) boardid = "1";

%>
<jsp:useBean id="article" class="board.BoardDataBean">
<jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<% System.out.println(article); %>
<%
	BoardDBBean dbPro = BoardDBBean.getInstance();
	
	

	article.setIp(request.getRemoteAddr());
	
	dbPro.insertArticle(article);
	response.sendRedirect("list.jsp?pageNum="+pageNum+"&boardid="+boardid);
%> --%>
</body>
</html>