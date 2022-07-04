<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	int seq = Integer.parseInt(request.getParameter("seq"));
	
	BbsDao dao = BbsDao.getInstance();	

	boolean isW = dao.updateBbs(title, content, seq);
%>    

	<%	
	if (isW) {
		%>
		<script type="text/javascript">
		alert('글쓰기 성공');
		location.href = "bbsdetail.jsp?seq="+<%=seq%>;
		</script>
	<%
		}else{
	
	%>
		<script type="text/javascript">
		alert('다시 작성해 주십시오');
		location.href = "bbsdetail.jsp";
		</script>
	<%
	}
	%>
