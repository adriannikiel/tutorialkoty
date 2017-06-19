<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message
		code="koty-webapp.CatController.catList.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="addCat"><spring:message
			code="koty-webapp.CatController.catList.add" /></a>
	<br />
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th><spring:message
						code="koty-webapp.CatController.catList.name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cats}" var="cat" varStatus="status">
				<tr>
					<td>${cat.id}</td>
					<td><a href="<c:url value="cat-${cat.id}" />">${cat.name}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />

	<c:set var="logoutUrl">
		<c:url value="/j_spring_security_logout" />
	</c:set>

	<!-- csrt for log out-->
	<form method="post" action="${logoutUrl}" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>${pageContext.request.userPrincipal.name}
			| <a href="javascript:formSubmit()"> <spring:message
						code="koty-webapp.CatController.catList.logout" /></a>
		</h2>
	</c:if>

	<hr />
	Uwaga! W kontekście użycia HTML oraz sposobu budowania stron, te
	rozwiązania są bardziej antyprzykładem niż materiałem do nauki!
	<br /> Jeśli chciałabyś także tworzyć same widoki i pisac poprawny,
	dobrze skonstruowany kod HTML, zachęcamy do zapoznania się np. z
	frameworkiem
	<a href="http://getbootstrap.com" target="_blank">Bootstrap</a>.
</body>
</html>