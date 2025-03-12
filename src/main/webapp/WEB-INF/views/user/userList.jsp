<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>userList.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs5.jsp"/>
	<script>
		'use strict';
		function delCheck(idx)	{
			let ans = confirm("회원을 삭제하시겠습니까?");
			if(!ans) return false;
			else location.href = "${ctp}/user/userDeleteOk?idx="+idx;
		}
		
		function partSearch() {
			let part = document.getElementById("part").value;
			location.href="${ctp}/user/userListSearchPart?part="+part;
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회원 전체 리스트</h2>
  <div class="text-end mb-2">
  	<select name="part" id="part" onchange="partSearch()">
  		<option value="idx desc" ${part == 'idx desc' ? 'selected' : ''}>최근가입순</option>
  		<option value="idx" ${part == 'idx' ? 'selected' : ''}>가입순</option>
  		<option value="name" ${part == 'name' ? 'selected' : ''}>성명순</option>
  		<option value="age" ${part == 'age' ? 'selected' : ''}>나이순</option>
 	 </select>
  </div>
  <table class="table table-hover text-center">
  	<tr class="table-secondary">
			<th>번호</th>
			<th>아이디</th>
			<th>성명</th>
			<th>비밀번호</th>
			<th>나이</th>
			<th>성별</th>
			<th>주소</th>
			<th>비고</th>
		</tr>
		<c:forEach items="${vos}" var="vo" varStatus="st">
			<tr>
				<td>${vo.idx}</td>
				<td>${vo.mid}</td>
				<td>${vo.name}</td>
				<td>${vo.pwd}</td>
				<td>${vo.age}</td>
				<td>${vo.gender}</td>
				<td>${vo.address}</td>
				<td>
					<a href="${ctp}/user/userUpdate?idx=${vo.idx}" class="badge bg-warning text-decoration-none">수정</a> /
					<a href="javascript:delCheck(${vo.idx})" class="badge bg-danger text-decoration-none">삭제</a>
				</td>
			</tr>
		</c:forEach>
  </table>
  <br/>
  <div class="text-center"><a href="${ctp}/user/userMain" class="btn btn-warning">돌아가기</a></div>
</div>
<p><br/></p>
</body>
</html>