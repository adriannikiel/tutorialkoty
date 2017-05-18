<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message
		code="koty-webapp.RejestracjaController.login.title" /></title>
<link href="<c:url value="/resources/css/default.css" />"
	rel="stylesheet">
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<c:set var="loginUrl">
		<c:url value="j_spring_security_check" />
	</c:set>
	<form method="post" action="${loginUrl}">
		<table>
			<tr>
				<td><spring:message
						code="koty-webapp.RejestracjaController.login.user" />:</td>
				<td><input type="text" name="username" value=""></td>
			</tr>
			<tr>
				<td><spring:message
						code="koty-webapp.RejestracjaController.login.password" />:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="<spring:message
						code="koty-webapp.RejestracjaController.login.login" />" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<br />
	<a href="rejestracja"><spring:message
			code="koty-webapp.RejestracjaController.login.register" /></a>

</body>
</html>