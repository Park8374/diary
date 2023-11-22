<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Change</title>
</head>
<body>
    <h2>회원 암호 수정</h2>
    <form action="<%=request.getContextPath()%>/member/modifyPwMember" method="post">
        <label for="currentPassword">현재 비밀번호:</label>
        <input type="password" id="currentPassword" name="currentPassword" required><br><br>
        <label for="newPassword">변경할 비밀번호:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        <label for="confirmPassword">변경 비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
        <input type="submit" value="암호 변경">
    </form>
</body>
</html>
