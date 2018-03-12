<%-- <%@page import="board.BoardDBBean"%> --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- <% request.setCharacterEncoding("EUC-KR"); %> --%>

<%-- <%
	String boardid = request.getParameter("boardid");
	if (boardid==null) boardid = "1";
	String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
%> --%>

<%-- <jsp:useBean id="article" class="board.BoardDataBean">
<jsp:setProperty name="article" property="*"/>
</jsp:useBean> --%>
<%-- <% System.out.println(article); %>
<%
	BoardDBBean dbPro = BoardDBBean.getInstance();
	int chk = dbPro.updateArticle(article);
%> --%>

<c:if test="${chk==1}"><%-- <% if(chk ==1) { %> --%>

<meta http-equiv="Refresh" content="0;url=list?pageNum=${pageNum}">
</c:if>

<c:if test="${chk!=1}"><%-- <% } else { %> --%>
<script type="text/javascript">
alert("비밀번호가 맞지 않습니다.");
history.go(-1);
</script>
</c:if><%-- <% } %> --%>

</body>
</html>