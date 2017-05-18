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
		code="koty-webapp.ZabawkiController.dodajZabawke.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="<c:url value="../../kot-${kot.id}"/>"><spring:message
			code="koty-webapp.ZabawkiController.dodajZabawke.return" /></a>
	<br />
	<form:form method="POST" modelAttribute="zabawkaDto">
		<table border="1">
			<tbody>
				<tr>
					<th><spring:message
							code="koty-webapp.ZabawkiController.dodajZabawke.catName" /></th>
					<td bgcolor="pink" align="middle"><label>${kot.imie}</label></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.ZabawkiController.dodajZabawke.name" /></th>
					<td><form:input type="text" path="nazwa" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="nazwa" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.ZabawkiController.dodajZabawke.productionData" /></th>
					<td><form:input type="text" path="dataProdukcji" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="dataProdukcji" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<th><spring:message
							code="koty-webapp.ZabawkiController.dodajZabawke.weight" /></th>
					<td><form:input type="text" path="waga" /> <c:if
							test="${pageContext.request.method=='POST'}">
							<form:errors path="waga" cssClass="error" />
						</c:if></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="<spring:message
							code="koty-webapp.ZabawkiController.dodajZabawke.add" />" /></td>
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