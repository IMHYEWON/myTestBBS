<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	int seq = Integer.parseInt(request.getParameter("seq"));
	
	BbsDao dao = BbsDao.getInstance();	

	boolean isS = dao.delBbs(seq);
%>    

	<%	
	if (isS) {
		%>
		<script type="text/javascript">
		alert("삭제되었습니다");
		location.href = "bbslist.jsp";
		</script>
	<%
		}else{
	
	%>
		<script type="text/javascript">
		alert('삭제 실패');
		location.href = "bbsdetail.jsp?seq="+seq;
		</script>
	<%
	}
	%>
