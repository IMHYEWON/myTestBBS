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
	
	//int seq = Integer.parseInt(request.getParameter("seq"));
	int seq = 16;
	System.out.println("seq:::"+seq);
	// 테스트로 SEQ 박아둠!
	
%>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<body onload="setData(<%=seq%>)">
<div align="center">
<form action="updateAf.jsp"  method="post">
	<input type="hidden" name="seq" id="seq" value="<%=seq%>">
	<table border = "1">
		<colgroup>
			<col width="100">
			<col width="600">
		</colgroup>
	<tr>
		<th>아이디</th>
		<td id="r_id"></td>
	</tr>
	<tr id="readcount">
		<th>조회수</th>
		<td id="r_readcount"></td>
	</tr>
	<tr id="wdate">
		<th>작성일</th>
		<td id="r_wdate"></td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<span id = "titleSpan"></span>
			<input type="text" id="titleInput" name="title"	value=""style="width:580px;display:none;">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<span id="contentSpan"></span>
			<textarea id="contentInput" name="content" rows="5" cols=""	style="width:580px;display:none;"></textarea>
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
		<button type="button" id="btnMod"	onclick="bbsMod()">글 수정</button>
		<button type="button" id="btnDel"	onclick="bbsDel()">글 삭제</button>
		<button type="button" id="btnReply"	onclick="bbsReply()">댓글 달기</button>
	</div>

</form>
</div>
<br>

<!-- //페이지 로딩됐을때
 --><script type="text/javascript">
$(function () {
	let seq = $("#seq").val();
	setData(seq);
	
	
});

function setData(seq) {
	//alert("what");
	$.ajax({
		type:"post",
		url:"../bbs?param=bbsdetail",
		data:{ "seq":seq },
		success:function( data ){
			console.log(data);			
			
			$("#seq").val(data.SEQ);
			
			$("#r_id").text(data.ID);
			$("#r_readcount").text(data.READCOUNT);
			$("#r_wdate").text(data.WDATE);
			
			$("#titleSpan").text(data.TITLE);
			$("#titleInput").val(data.TITLE);
			
			$("#contentSpan").text(data.CONTENT);
			$("#contentInput").val(data.CONTENT);
			
		},
		error:function(){
			alert("error");
		}
	});
}

function goList() {
	location.href = "bbslist.jsp";
}

function bbsMod() {
	document.getElementById("readcount").style.display 		= 'none';
	document.getElementById("wdate").style.display			= 'none';
	document.getElementById("titleSpan").style.display		= 'none';
	document.getElementById("contentSpan").style.display	= 'none';
	
	document.getElementById("titleInput").style.display 	= 'block';
	document.getElementById("contentInput").style.display	= 'block';
	document.getElementById("submit").style.display 		= 'block';

	
}

function bbsDel() {
    if (!confirm("게시글을 삭제하시겠습니까?")) {
        alert("취소(아니오)를 누르셨습니다.");
    } else {    	
        location.href = "delAf.jsp?seq="+document.getElementById("seq").value;
    }
}

function bbsReply() {
	document.href = "answer.jsp";	
}

</script> 
</body>
</html>