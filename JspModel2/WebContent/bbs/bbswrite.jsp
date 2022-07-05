<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>

<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	request.setCharacterEncoding("utf-8");	
	Object obj = session.getAttribute("login");
	MemberDto mem = null;
	MemberDao dao = MemberDao.getInstance();	
	obj = dao.getMember("hrywehf");
	
	if(obj ==null) { //세션이 끊어졌을때 > 매 페이지마다 해줘야됨 > 스프링에서는web.xml에서 해줌
		%>
		<script type="text/javascript">
			alert('로그인 해주십시오');
			location.href= "login.jsp";
		</script>
		<%
	}
	
	mem = (MemberDto)obj;
	
%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">

    <title>Document</title>
    <style>
        * {
            font-family: 'Noto Sans KR', sans-serif;
        }
        h2 {
            font-weight: 700;
        }
        th {
            background-color: lemonchiffon;
            font-weight: 500;
            text-align: center;
        }

        td {
            text-align: center;
        }
	</style>
<title>bbs write</title>
</head>
<body>
<div align="center">
<form action="<%=request.getContextPath() %>/bbs" method="post">
<input type="hidden"  name="param"	value="bbswrite">

	<table border = "1">
		<colgroup>
			<col width="100">
			<col width="600">
		</colgroup>
	<tr>
		<th>아이디</th>
		<td>
			<input type="text" id="id"	  name="id" value="<%=mem.getId() %>">
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<input type="text" id="title" name="title"	style="width:580px;">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea id="content" name="content" rows="5" cols=""	style="width:580px;"></textarea></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<button type="button" id="btnWrite">글쓰기</button>
		</td>
		</tr>
	</table>
	

</form>
</div>
<br>
<script type="text/javascript">
$(function () {
	
	$("#btnWrite").click(function(){
		goWrite();
	});
	
	
});
function goWrite() {
	alert("what");
	$.ajax({
		type:"post",
		url:"../bbs?param=bbswrite",
		data:{ "id":$("#id").val(), "title":$("#title").val(), "content":$("#content").val() },
		success:function( data ){
			alert("글작성완료!");
			location.href = "../bbs/bbslist.jsp";
		},
		error:function(){
			alert("error");
		}
	});
}
</script> 
</body>
</html>