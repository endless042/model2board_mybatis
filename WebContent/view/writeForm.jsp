<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"></head>
<html>
<!-- 
☆ id 와 class 의 차이점
	id는 한페이지에 하나의 정의(style/css)로 하나의 태그(id="")만 사용할 수 있다. 
	즉 로고, 상단메뉴, 하단정보 같은 스타일을 정의할 때 id를 사용한다
	class는 한페이지에 반복적으로 사용되는 스타일을 정의할 수 있다.
	반복적으로 사용되는 스타일에는 class를 이용하여 정의하고, 그 내부에 세부 스타일을 정의할 때 id를 사용해주면 효과적이다.
 -->
<head>
<title>글쓰기</title>

</head>

<p class="w3-left"  style="padding-left:30px;"></p>
<div class="w3-container"><center>

<b>글쓰기</b>
<br>
<form method="post" name="writeform" action="<%=request.getContextPath()%>/board/writePro" >
<input type="hidden" name="boardid" value="${boardid}">
<!-- 답글 게시판 관련 -->
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="re_level" value="${re_level}">
<input type="hidden" name="re_step" value="${re_step}">
<!--============-->

<table class="w3-table-all"  style="width:70%;" >
   <tr>
    <td align="right" colspan="2" >
	    <a href="<%=request.getContextPath()%>/board/list"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"   align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"   align="center" >제 목
    </td>
    <td width="330">
 	
 	<c:if test="${num==0 }">
 	 	<input type="text" size="40" maxlength="50" name="subject">
 	 </c:if>
 	 <c:if test="${num!=0}">
 	 	<input type="text" size="40" maxlength="50" name="subject" value="[답글]">
 	 </c:if>
 	
   </td>
  </tr>
  <tr>
    <td  width="70"   align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"   align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"   align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> 
	 </td>
  </tr>
<tr>      
 <td colspan=2  align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='list.jsp'">
</td></tr></table>    
     
</form>
</div> 
</center>




</body>
</html>      
