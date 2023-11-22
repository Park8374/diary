<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<form id="loginForm" action="loginMember" method="post">
    <label for="memberId">아이디:</label>
    <input type="text" id="memberId" name="memberId"><br><br>
    
    <label for="memberPw">비밀번호:</label>
    <input type="password" id="memberPw" name="memberPw"><br><br>
    
    <input type="button" id="loginBtn" value="로그인">
</form>
<div>
	<a href ="<%=request.getContextPath()%>/member/addMember">회원 가입</a>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#loginBtn').click(function() {
            if ($('#memberId').val().length < 1) {
                alert('아이디를 입력하세요');
            } else if ($('#memberPw').val().length < 1) {
                alert('비밀번호를 입력하세요');
            } else {
                $('#loginForm').submit();
            }
        });
    });
</script>

</body>
</html>