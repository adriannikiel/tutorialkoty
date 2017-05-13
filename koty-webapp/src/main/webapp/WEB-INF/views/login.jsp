<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
	<c:if test="${not empty error}">
		<font size="3" color="red">${error}</font>
	</c:if>
	<c:if test="${not empty msg}">
		<font size="3" color="red">${msg}</font>
	</c:if>
	<br />
	<br />
	<c:set var="loginUrl"><c:url value="j_spring_security_check" /></c:set>
	<form method="post" action="${loginUrl}">
		<table>
			<tr>
				<td>Użytkownik:</td>
				<td><input type="text" name="username" value=""></td>
			</tr>
			<tr>
				<td>Hasło:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Zaloguj" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<br />
	<a href="rejestracja">Zarejestruj się</a>
	<br />
</body>
</html>