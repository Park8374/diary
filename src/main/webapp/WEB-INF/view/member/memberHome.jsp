<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"> </c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	
	<%-- 공지사항 --%>
	<h2>최신 공지사항 : </h2>-
	<div></div>
	
	<!-- 달력 -->
	<div>
		<h3>${targetY}년 ${targetM+1}월</h3>
		
		<div>
			<a href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
			<!-- 만약 href속성값 매개값이 많으면 c:url jstl을 사용하면 가독성 높일 수 있다 -->
			<c:url var = "nextUrl" value="/member/memberHome">
				<c:param name="targetY" value="${targetY}"></c:param>
				<c:param name="targetM" value="${targetM+1}"></c:param>	
			</c:url>
			<a href="${nextUrl}">다음달</a>
		</div>
		
		<table border="1">
			<tr>
				<c:forEach var="i" begin="1" end="${totalTd}" step="1">
					<c:set var="d" value="${i - beginBlank}"></c:set>
					<td>
						<c:if test="${d < 1 || d > lastD}">
							&nbsp;
						</c:if>
						<c:if test="${!(d < 1 || d > lastD)}">
							<a href="${pageContext.request.contextPath}/schedule/scheduleListByDay?targetY=${targetY}&targetM=${targetM}&targetD=${d}">
								${d}
							</a>
							<div>
								<c:forEach var="m" items="${list}">
									<c:if test="${m.scheduleDay == d}">
										<div>${m.cnt} 개의 일정</div>
										<div>${m.memo}</div>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						
						<c:if test="${i<totalTd && i%7==0}">
							</tr><tr>
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>