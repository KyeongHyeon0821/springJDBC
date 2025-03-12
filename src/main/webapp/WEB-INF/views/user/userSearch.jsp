<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>userSearch.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs5.jsp"/>
	<script>
		'use strict';
		
		function idSearch(flag) {
			let mid = document.getElementById("mid").value;
			if(mid.trim() == "") {
				alert("검색할 아이디를 입력하세요.");
				document.getElementById("mid").focus();
			}
			else {
				if(flag == 's')	location.href="${ctp}/user/userSearchList?mid=" + mid;
				else location.href="${ctp}/user/userSearchListOk?mid=" + mid;
			}
		}
		
		function formSearch() {
			let part = document.getElementById("part").value;
			let content = document.getElementById("content").value;
			
			if(content.trim() == "") {
				alert("검색할 내용을 입력하세요.");
				document.getElementById("content").focus();
			}
			else {
				
			}
			location.href="${ctp}/user/userSearchPart?part="+part+"&content="+content;
			
		}
	</script>
</head>
<body>
<p><br/></p>
<div class="container">
	<h2 class="text-center">개별 회원 조회</h2>
		<hr/>
	<c:if test="${!empty vo}">
		<hr/>
			<table class="table table-bordered text-center border-secondary">
	  		<tr>
	  			<th class="bg-secondary-subtle">회원번호</th><td>${vo.idx}</td>
	  		</tr>
	  		<tr>
	  			<th class="bg-secondary-subtle">아이디</th><td>${vo.mid}</td>
	  		</tr>
	  		<tr>
	  			<th class="bg-secondary-subtle">이름</th>	<td>${vo.name}</td>
	  		</tr>
	  		<tr>
	  			<th class="bg-secondary-subtle">나이</th>	<td>${vo.age}</td>
	  		</tr>
	  		<tr>
	  			<th class="bg-secondary-subtle">성별</th>	<td>${vo.gender}</td>
	  		</tr>
	  		<tr>
	  			<th class="bg-secondary-subtle">주소</th>	<td>${vo.address}</td>
	  		</tr>
  		</table>
		<hr/>
	
	</c:if>
	
		<div class="input-group">
				<select name="part" id="part" class="form-select">
					<option value="mid">아이디</option>
					<option value="name">성명</option>
					<option value="address">주소</option>
				</select>
			<input type="text" name="content" id="content" placeholder="검색할 내용을 입력하세요" autofocus required class="form-control" />
			<div class="input-group-append">
				<input type="button" value="회원정보검색(완전일치)" onclick="formSearch()" class="btn btn-success ms-1 me-1" />
			</div>
		</div>
	
	 <div class="text-center"><a href="${ctp}/user/userMain" class="btn btn-warning mt-3">돌아가기</a></div>
</div>
<p><br/></p>
</body>
</html>