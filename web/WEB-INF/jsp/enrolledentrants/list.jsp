<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<fmt:message key="enrolledentrants.list.title" var="title" />

<u:page title="${title}">

	<table>
	</table>
	<tr>
		<th><c:if test="${empty enrolledPersons}">
				<p>
					<c:url var="urlEnrolledEntrantsList"
						value="/enrolledentrants/create.html" />
					<a href="${urlEnrolledEntrantsList}" class="add-button"><fmt:message
							key="enrolledentrants.list.button.create" /></a>
				</p>
			</c:if></th>
		<th><c:if test="${not empty enrolledPersons}">
				<p>
					<c:url var="urlResetEnrolledEntrantsList"
						value="/enrolledentrants/reset.html" />
					<a href="${urlResetEnrolledEntrantsList}" class="add-button"><fmt:message
							key="enrolledentrants.list.button.reset" /></a>
				</p>
			</c:if></th>
	</tr>

	<c:if test="${not empty enrolledPersons}">
		<table>
			<tr>
				<th><fmt:message key="enrolledentrants.list.table.name" /></th>
				<th><fmt:message key="enrolledentrants.list.table.total_score" /></th>
				<th><fmt:message key="enrolledentrants.list.table.faculty" /></th>
				<td>&nbsp;</td>
			</tr>
			<c:forEach var="person" items="${enrolledPersons}">
				<tr>
					<td class="content"><c:url var="urlStatementEdit"
							value="/statement/edit.html">
							<c:param name="id" value="${person.statementId}" />
						</c:url> <a href="${urlStatementEdit}" class="edit">${person.fullName}</a></td>
					<td class="content">
						<p align="center">${person.totalScore}</p>
					</td>
					<td class="content">
						<p align="center">${person.facultyId}</p>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</u:page>