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

String choice = request.getParameter("choice");
String search = request.getParameter("search");

if(choice == null) {
	choice = "";
}
if(search == null) {
	search = "";
}


%>
<!DOCTYPE html>
<html>

<head>
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
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>

</head>
<body>

<%


	BbsDao dao = new BbsDao();
	
	//페이지 번호에 맞는 글목록
	String sPageNumber = request.getParameter("pageNumber");	//파라미터 번호
	int pageNumber = 0;
	if (sPageNumber != null && !sPageNumber.equals("")) {
		pageNumber = Integer.parseInt(sPageNumber);
	}
	
	//List<BbsDto> list = dao.getBbslist();
	//List<BbsDto> list = dao.getBbsSearchList(choice, search);
	List<BbsDto> list = dao.getBbsPageList(choice, search, pageNumber);

	// 글의 총수
	int len = dao.getAllBbs(choice, search);
	
	// 페이지 수
	int bbsPage = len/10;
	if ((len%10)> 0) {
		bbsPage++;
	}
	
%>
<h2>게시판</h2>
<div align="center">
<table border="1">
	<colgroup>
		<col width="70">
		<col width="600">
		<col width="100">
		<col width="150">
	</colgroup>
	<tr>
		<th>번호</th><th>제목</th><th>조회수</th><th>작성자</th>
	</tr>
	
	<%
		if(len==0){
			%>
			<tr>
				<td colspan="4">작성된 글이 없습니다</td>
			</tr>
			<%
		} else {
			for (int i = 0; i<list.size();i++) {
				BbsDto bbs = list.get(i);
			%>
			<tr>
				<th><%=i+1 %></th>
				<td align="left">
					<%-- <a href="<%=request.getContextPath() %>/bbs?param=bbsdetail&seq=<%=bbs.getSeq()%>"> --%>
					<a href ="bbsdetail.jsp?seq=<%=bbs.getSeq()%>" >
					<%=bbs.getTitle() %></a>
				</td>
				<td><%=bbs.getReadcount() %></td>
				<td><%=bbs.getId() %></td>
			</tr>	
			<%
			}
		}
	%>
</table>
</div>

<div align="center">
<a href="<%=request.getContextPath() %>/bbs?param=bbswrite">글쓰기</a>
<!-- <a href="bbswrite.jsp">글쓰기</a> -->
</div>
<br>

<div align="center">
<%
for(int i=0;i<bbsPage;i++) {
	if(pageNumber == i) {	// 현재 페이지
		%>
		<span style="font-size:15px; color:#0000ff;font-weight:bold;" >
			<%=i+1 %>
		</span>
		<%
	} else {				// 그밖의 페이지
		%>
		<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
			style="font-size:15pt; color:#000; text-decoration:none;">
			[<%=i+1 %>]</a>
		<%
	}
}
%>
</div>
<div align="center">

	<select id="choice">
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	
	<input type="text" id="search" value="<%=search %>" placeholder="검색어를 입력해주세요.">
	<button type="button" onclick="searchBtn()">검색</button>
	</div>
</body>


<script type="text/javascript">
/* 단어 검색 후에 화면 세팅*/
let search = "<%=search%>"; 
if(search != ""){
	let obj = document.getElementById("choice");
	obj.value = "<%=choice %>";
	obj.setAttribute("selected", "selected");
}

function searchBtn() {
	let choice = document.getElementById('choice').value;
	let search = document.getElementById('search').value;
	
	if(search.trim()=='') {
		alert("검색어를 입력해주세요");
		return;
	}
	
	location.href = "bbslist.jsp?choice="+ choice + "&search=" + search;
}

function goPage(pageNumber) {
	let choice = document.getElementById('choice').value;
	let search = document.getElementById('search').value;

	location.href = "bbslist.jsp?choice="+ choice + "&search=" + search + "&pageNumber=" + pageNumber;

}
</script>
</html>