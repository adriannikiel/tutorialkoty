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
		code="koty-webapp.CatController.addCat.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="catList"><spring:message
			code="koty-webapp.CatController.addCat.return" /></a>
	<br />
	<form:form method="POST" modelAttribute="catDto">
		<table border="1">
			<tbody>
				<tr>
					<th><spring:message
							code="koty-webapp.CatController.addCat.name" /></th>
					<td><form:input type="text" path="name" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="name" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.CatController.addCat.birthday" /></th>
					<td><form:input type="text" path="birthday" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="birthday" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.CatController.addCat.weight" /></th>
					<td><form:input type="text" path="weight" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="weight" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.CatController.addCat.guardianName" /></th>
					<td><form:input type="text" path="guardianName" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="guardianName" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="<spring:message code="koty-webapp.CatController.addCat.add" />" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<br />
	<hr />
	Uwaga! W kontekście użycia HTML oraz sposobu budowania stron, te
	rozwiązania są bardziej antyprzykładem niż materiałem do nauki!
	<br /> Jeśli chciałabyś także tworzyć same widoki i pisac poprawny,
	dobrze skonstruowany kod HTML, zachęcamy do zapoznania się np. z
	frameworkiem
	<a href="http://getbootstrap.com" target="_blank">Bootstrap</a>.


</body>
</html>