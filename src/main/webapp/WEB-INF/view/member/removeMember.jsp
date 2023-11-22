<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"> </c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Member</title> 
</head>
<body>


		<form action="${pageContext.request.contextPath}/member/removeMember" method="post">
		    <input type="hidden" name="memberNo" value="${member.memberNo}">
		    <label for="currentPassword">현재 비밀번호:</label>
		    <input type="password" id="currentPassword" name="currentPassword">
		    <input type="submit" value="회원 탈퇴">
		</form>

</body>
</html>