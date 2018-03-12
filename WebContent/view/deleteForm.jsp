<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <% int num = Integer.parseInt(request.getParameter("num"));
   String pageNum = request.getParameter("pageNum");
%> --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>게시판</title>
</head>
<body>
<p class="w3-left" style="padding-left: 30px;"></p>
<center>
<div class="w3-container">
<b>글삭제</b><br>
<form method="post" name="delForm" action="deletePro?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table class="table=bordered" width="360">
<tr height="30">
<td align="center"><b>비밀번호를 입력해 주세요.</b></td></tr>
	<tr height="30">
	<td align="center">비밀번호:
	<input type="password" name="passwd" size="8" maxlength="12">
	<input type="hidden" name="num" value="${num}">
	<!--
		hidden : 사용자에게 보이지 않으나, 서버로 넘겨지는 값 가지는 필드
		- 예를 들어 회원가입 폼에서 사용자가 입력하지 않아도 되는 정보를 서버로 넘길 때 사용하는 폼
		- name 속성으로 필드 이름 지정, value 속성으로 서버로 넘김
		<input type="hidden" name="이름" value="서버로 넘길 값">
	  -->
</td></tr> <tr height="30">
<td align="center"><input type="submit" value="글삭제" class="w3-button w3-pink">
<input type="button" value="글목록" class="w3-button w3-pink"
onclick="document.location.href='list?pageNum=${pageNum}'">
</td></tr>
</table>


</form>
</div>

</center>

</body>
</html>