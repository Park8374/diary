<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
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
	
	<!-- 달력 -->
   <div>
      <h1>${targetY}년 ${targetM+1}월</h1>
      
      <div>
         <a href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
         <!--  만약 href속성값 매개값이 많으면 c:url jstl을 사용하면 가독성 높일 수 있다. -->
         <c:url var = "nextUrl" value="/member/memberHome">
            <c:param name="targetY" value="${targetY}"></c:param>
            <c:param name="targetM" value="${targetM+1}"></c:param>
         </c:url>
         <a href="${nextUrl}">다음달</a>
      </div>
      
      <table border="10">
         <tr>
            <c:forEach var="i" begin="1" end="${totalTd}" step="1">
               <td>
                  <c:if test="${(i-beginBlank)<1 || (i-beginBlank)>lastD} ">
                     &nbsp;
                  </c:if>
                  <c:if test="${!((i-beginBlank)<1 || (i-beginBlank)>lastD)}">
                     ${i-beginBlank}
                  </c:if>
                  
                  <c:if test="${i<totalTd&&i%7==0}">
                     </tr><tr>                  
                  </c:if>
               </td>
               
            </c:forEach>
         </tr>
      </table>
   </div>
</body>
</html>