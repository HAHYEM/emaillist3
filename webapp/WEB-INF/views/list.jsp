<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트에 가입되었습니다.</h1>
	<p>입력한 정보 내역입니다.</p>
	<!-- 메일정보 리스트 -->
	<%-- list에서 하나씩 빼서 테이블를 채운다--%>
	<c:forEach items="${eList}" var="emaillistVo">
		<table border="1" cellpadding="5" cellspacing="2">
			<tr>
				<td align=right width="110">Last name: </td>
				<td width="170">${emaillistVo.lastName}</td>
			</tr>
			<tr>
				<td align=right >First name: </td>
				<td>${emaillistVo.firstName}</td>
			</tr>
			<tr>
				<td align=right>Email address: </td>
				<td>${emaillistVo.email}</td>
			</tr>
		</table>
	</c:forEach>
		<br>
	<p>
		<a href="/emaillist3/form">추가메일 등록</a>
	</p>
	<br>
</body>
</html>