<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Member</title>
</head>
<body>

<%
    // 세션에서 로그인된 회원 정보 받아오기
    Member member = (Member)session.getAttribute("loginMember");
    int memberNo = member.getMemberNo(); // 회원 번호 받아오기
%>

		<form action="<%=request.getContextPath()%>/member/removeMember" method="post">
		    <input type="hidden" name="memberNo" value="<%= memberNo %>">
		    <label for="currentPassword">현재 비밀번호:</label>
		    <input type="password" id="currentPassword" name="currentPassword">
		    <input type="submit" value="회원 탈퇴">
		</form>

</body>
</html>