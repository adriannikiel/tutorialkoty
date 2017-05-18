<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message
		code="koty-webapp.RejestracjaController.rejestracja.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<h3>
		<spring:message
			code="koty-webapp.RejestracjaController.rejestracja.description" />
	</h3>
	<form:form method="POST" modelAttribute="rejestracjaDto">
		<table>
			<tbody>
				<tr>
					<td><spring:message
							code="koty-webapp.RejestracjaController.rejestracja.user" />:</td>
					<td><form:input type="text" path="user" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="user" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<td><spring:message
							code="koty-webapp.RejestracjaController.rejestracja.email" />:</td>
					<td><form:input type="text" path="email" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="email" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<td><spring:message
							code="koty-webapp.RejestracjaController.rejestracja.password" />:</td>
					<td><form:input type="password" path="pass" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="pass" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="<spring:message
			code="koty-webapp.RejestracjaController.rejestracja.register" />" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>