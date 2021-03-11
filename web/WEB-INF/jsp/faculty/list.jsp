<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<fmt:message key="faculty.list.title" var="title" />

<u:page title="${title}">

	<table>
		<tr>
			<th><fmt:message key="faculty.list.table.name" /></th>
			<th><fmt:message key="faculty.list.table.recruitment_plan" /></th>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="faculty" items="${faculties}">
			<tr>
				<td class="content">
				<c:url var="urlFacultyEdit" value="/faculty/edit.html">
				<c:param name="id" value="${faculty.id}" /></c:url>
				<a href="${urlFacultyEdit}" class="edit">${faculty.name}</a>
				</td>
				
				<td class="content"><p align="center">${faculty.recruitment_plan} </p>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:url var="urlFacultyEdit" value="/faculty/edit.html" />
	<a href="${urlFacultyEdit}" class="add-button"><fmt:message
			key="faculty.list.button.add" /></a>
</u:page>