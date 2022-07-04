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
	MemberDao mdao = MemberDao.getInstance();	
	obj = mdao.getMember("hrywehf");
	
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
    
<%
	//int seq = 4;
	int seq = Integer.parseInt(request.getParameter("seq"));

	BbsDao dao = BbsDao.getInstance();
	BbsDto dto = dao.getBbsDto(seq);
	dao.addReadCount(seq);
	dto = dao.getBbsDto(seq);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
            text-align: left;
        }
	</style>
<title>bbs write</title>
</head>
<body>
<div align="center">
<form action="updateAf.jsp"  method="post">
	<input type="hidden" name="seq" id="seq" value="<%=dto.getSeq()%>">
	<table border = "1">
		<colgroup>
			<col width="100">
			<col width="600">
		</colgroup>
	<tr>
		<th>아이디</th>
		<td>
			<%=dto.getId() %>
		</td>
	</tr>
	<tr id="readcount">
		<th>조회수</th>
		<td><%=dto.getReadcount() %></td>
	</tr>
	<tr id="wdate">
		<th>작성일</th>
		<td><%=dto.getWdate() %></td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<span id = "titleSpan"><%=dto.getTitle() %></span>
			<input type="text" id="titleInput" name="title"	value="<%=dto.getTitle() %>"style="width:580px;display:none;">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<span id="contentSpan"><%=dto.getContent() %></span>
			<textarea id="contentInput" name="content" rows="5" cols=""	style="width:580px;display:none;"><%=dto.getContent() %></textarea>
		</td>
	</tr>
	
	<tr>
		<td colspan="2" id="submit" style="text-align:center; display:none;">
			<input type="submit"  value="수정하기"	>
		</td>
		</tr>
	</table>
	<br>
	<div style="align:center;">
		<button type="button" onclick="goList()">목록</button>
		<button type="button" id="btnMod"	onclick="mod()">글 수정</button>
		<button type="button" id="btnDel"	onclick="del()">글 삭제</button>
		<button type="button" id="btnReply">댓글 달기</button>
	</div>

</form>
</div>
<br>

<script type="text/javascript">

function goList() {
	location.href = "bbslist.jsp";
}

function mod() {
	document.getElementById("readcount").style.display 		= 'none';
	document.getElementById("wdate").style.display			= 'none';
	document.getElementById("titleSpan").style.display		= 'none';
	document.getElementById("contentSpan").style.display	= 'none';
	
	document.getElementById("titleInput").style.display 	= 'block';
	document.getElementById("contentInput").style.display	= 'block';
	document.getElementById("submit").style.display 		= 'block';

	
}

function del() {
    if (!confirm("게시글을 삭제하시겠습니까?")) {
        alert("취소(아니오)를 누르셨습니다.");
    } else {    	
        location.href = "delAf.jsp?seq="+document.getElementById("seq").value;
    }
}


</script> 
</body>
</html>