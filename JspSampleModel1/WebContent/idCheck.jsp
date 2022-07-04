<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");

System.out.println("id:::::::"+id);
MemberDao dao = MemberDao.getInstance();

boolean isMember = dao.chkMember(id);
System.out.println("isMember : "+isMember);
if (isMember) {%>true<%} else { %>false<%} %>