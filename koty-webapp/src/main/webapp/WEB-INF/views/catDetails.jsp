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
		code="koty-webapp.CatController.catDetails.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="catList"><spring:message
			code="koty-webapp.CatController.catDetails.return" /></a>
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th><spring:message
						code="koty-webapp.CatController.catDetails.name" /></th>
				<td>${cat.name}</td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.CatController.catDetails.birthday" /></th>
				<td><fmt:formatDate pattern="dd.MM.yyyy"
						value="${cat.birthday}" /></td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.CatController.catDetails.weight" /></th>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						maxFractionDigits="2" value="${cat.weight}" /> kg</td>
			</tr>
			<tr>
				<th><spring:message
						code="koty-webapp.CatController.catDetails.guardianName" /></th>
				<td>${cat.guardianName}</td>
			</tr>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${empty cat.photo}">
			<a href="cat-${cat.id}/photo/add"><spring:message
					code="koty-webapp.CatController.catDetails.addPhoto" /></a>
		</c:when>
		<c:otherwise>
			<a href="cat-${cat.id}/photo/photo-${cat.photo.id}/download"><spring:message
					code="koty-webapp.CatController.catDetails.downPhoto" /></a>
			<a href="cat-${cat.id}/photo/photo-${cat.photo.id}/delete"><spring:message
					code="koty-webapp.CatController.catDetails.delPhoto" /></a>
			<br />
			<img src="cat-${cat.id}/photo/photo-${cat.photo.id}"
				alt="Upload Image" height="150" width="150" />
		</c:otherwise>
	</c:choose>
	<br />
	<hr />
	<a href="cat-${cat.id}/toy/add"><spring:message
			code="koty-webapp.CatController.catDetails.add" /></a>
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th><spring:message
						code="koty-webapp.CatController.catDetails.toyName" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${toys}" var="toy" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><a
						href="<c:url value="cat-${cat.id}/toy/toy-${toy.id}" />">${toy.name}</a></td>
					<td><a
						href="<c:url value="cat-${cat.id}/toy/delete-${toy.id}" />"><spring:message
								code="koty-webapp.CatController.catDetails.delToy" /></a></td>
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