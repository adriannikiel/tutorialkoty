<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message
		code="koty-webapp.ZabawkiController.szczegolyZabawki.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="<c:url value="../../kot-${kot.id}"/>"><spring:message
			code="koty-webapp.ZabawkiController.szczegolyZabawki.return" /></a>
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th><spring:message
						code="koty-webapp.ZabawkiController.szczegolyZabawki.name" /></th>
				<td>${zabawka.nazwa}</td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.ZabawkiController.szczegolyZabawki.productionData" /></th>
				<td><fmt:formatDate pattern="dd.MM.yyyy"
						value="${zabawka.dataProdukcji}" /></td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.ZabawkiController.szczegolyZabawki.weight" /></th>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						maxFractionDigits="2" value="${zabawka.waga}" /> kg</td>
			</tr>
		</tbody>
	</table>
	<br />
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