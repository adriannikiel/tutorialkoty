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
		code="koty-webapp.KotyController.szczegoly.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="lista"><spring:message
			code="koty-webapp.KotyController.szczegoly.return" /></a>
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th><spring:message
						code="koty-webapp.KotyController.szczegoly.name" /></th>
				<td>${kot.imie}</td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.KotyController.szczegoly.birthday" /></th>
				<td><fmt:formatDate pattern="dd.MM.yyyy"
						value="${kot.dataUrodzenia}" /></td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.KotyController.szczegoly.weight" /></th>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						maxFractionDigits="2" value="${kot.waga}" /> kg</td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.KotyController.szczegoly.guardianName" /></th>
				<td>${kot.imieOpiekuna}</td>
			</tr>
		</tbody>
	</table>
	<br />
	<a href="kot-${kot.id}/zabawka/dodaj"><spring:message
			code="koty-webapp.KotyController.szczegoly.add" /></a>
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th><spring:message
						code="koty-webapp.KotyController.szczegoly.toyName" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${zabawki}" var="zabawka" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><a
						href="<c:url value="kot-${kot.id}/zabawka/zabawka-${zabawka.id}" />">${zabawka.nazwa}</a></td>
					<td><a
						href="<c:url value="kot-${kot.id}/zabawka/usun-${zabawka.id}" />">Usuń</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
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