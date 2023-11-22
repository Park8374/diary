<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = " http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"> </c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
     

    <!--  가져온 정보를 이용해서 내용 채우기 -->
    <h1>Welcome, ${member.memberId} !</h1>
    <p>당신은 ${member.memberNo}번쨰 회원니다.</p> 
	<div> 
		<a href ="${pageContext.request.contextPath}/member/logoutMember">로그아웃</a>
		<!-- LogoutMemberController.doGet() --[session invaliadate]-- /member/loginMember -->
		
		<a href ="${pageContext.request.contextPath}/member/modifyPwMember">비밀번호 변경</a>
		<!-- 수정폼 ModifyMemberControler.doGet() -- modifyMemberPw.jsp -->
		<!-- 수정액션 ModifyMemberControler.doPost() --[session invaliadate]-- /member/loginMember  -->
		
		<a href ="${pageContext.request.contextPath}/member/removeMember">회원 탈퇴</a>
		<!-- 탈퇴폼 RemoveMemberControler.doGet() -- RemoveMember.jsp -->
		<!-- 탈퇴액션 RemoveMemberControler.doPost() --[session invaliadate]-- /member/loginMember  -->
	</div>
</body>
</html>