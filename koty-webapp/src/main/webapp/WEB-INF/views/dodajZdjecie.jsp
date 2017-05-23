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
		code="koty-webapp.KotyController.dodajZdjecie.title" /></title>
</head>
<body>
	<a href="?language=en">EN</a>
	<a href="?language=pl">PL</a>
	<br />
	<br />
	<a href="<c:url value="../../kot-${kot.id}" />"><spring:message
			code="koty-webapp.KotyController.dodajZdjecie.return" /> </a>
	<br />
	<hr />
	<form:form method="POST" modelAttribute="fotkaDto"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="file" name="plik" /></td>
			</tr>
			<tr>
				<td><spring:message
						code="koty-webapp.KotyController.dodajZdjecie.description" />:</td>
			</tr>
			<tr>			
				<td><textarea name="opisPliku" rows="3" cols="50"></textarea>
					<c:if test="${pageContext.request.method=='POST'}">
						<form:errors path="opisPliku" cssClass="error" />
					</c:if>
				</td>
			</tr>
			<tr>
				<td><input type="submit"
					value="<spring:message
							code="koty-webapp.KotyController.dodajZdjecie.add" />" /></td>
			</tr>
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