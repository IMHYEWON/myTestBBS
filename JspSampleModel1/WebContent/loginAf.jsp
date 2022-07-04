<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

%>    


<%
MemberDao dao = MemberDao.getInstance();
MemberDto mem = dao.login(new MemberDto(id, pwd, null, null, 0)); //로그임처리

if(mem != null && !mem.getId().equals("")){
	
	// session에 로그인한 dto 저장해둠
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(60 * 60 * 2);
	%>
	<script type="text/javascript">
		alert("안녕하세요.<%=mem.getName()%>님");
		location.href = "bblist.jsp";
	</script>
	<%
} else {
	%>
	<script type="text/javascript">
	
		location.href = "login.jsp";
	</script>
	<%
}
%>

