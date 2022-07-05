<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	String msg = request.getParameter("msg");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if (msg.equals("REGIOK")) {
		%>
		<script type="text/javascript">
		alert('성공적으로 가입되었습니다');
		location.href = "./member?param=login";
		</script>
		<%
	}else if (msg.equals("REGINO")) {
		%>
		<script type="text/javascript">
		alert('다시 기입해 주십시오');
		location.href = "./member?param=regi";
		</script>
		<%
	} else if (msg.equals("LOGINOK")) {
		%>
		<script type="text/javascript">
		alert('로그인성공');
		location.href = "./member?param=bbslist";
		</script>
		<%
	} else if (msg.equals("LOGINNO")) {
		%>
		<script type="text/javascript">
		alert('로그인 실패');
		location.href = "./member?param=login";
		</script>
		<%
	}
%>


</body>
</html>