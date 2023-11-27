<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"> </c:set>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${contextPath}/js/jquery-3.7.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${param.targetY}년 ${param.targetM + 1}월 ${param.targetD}일 일정리스트</h1>
	<table border="1">
		<tr>
			<td>일자</td>
			<td>메모</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="s" items="${list}" begin="0" end="${list.size()}">
			<tr>
				<td>${s.scheduleDate}</td>
				<td>${s.scheduleEmoji}${s.scheduleMemo}</td>
				<td>
					<a href="${pageContext.request.contextPath}/schedule/modifySchedule?scheduleNo=${s.scheduleNo}">
						수정
					</a>
				</td>
				<td>
				    <a href="${pageContext.request.contextPath}/schedule/removeSchedule?scheduleNo=${s.scheduleNo}">
				        삭제
				    </a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>일정 추가</h1>
	<form action="${pageContext.request.contextPath}/schedule/addSchedule" method="post">
	    <input type="hidden" name="year" value="${param.targetY}">
	    <input type="hidden" name="month" value="${param.targetM + 1}">
	    <input type="hidden" name="day" value="${param.targetD}">
	    <div>
	        <input type="radio" name="emoji" value="&#1F4DD;">&#128110;
	        <input type="radio" name="emoji" value="&#128186;">&#128186;
	        <input type="radio" name="emoji" value="&#127981;">&#127981;
	    </div>
	    <textarea rows="3" cols="80" name="scheduleMemo"></textarea>
	    <button type="submit">일정추가</button>
	</form>
</body>
</html>