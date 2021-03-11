<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />
<fmt:message key="statement.list.title" var="title" />

<u:page title="${title}">

	<table>
		<tr>
			<th><fmt:message key="statement.list.table.name" /></th>
			<th><fmt:message key="statement.list.table.faculty" /></th>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="statement" items="${statements}">
			<tr>
				<td class="content"><c:url var="urlStatementEdit"
						value="/statement/edit.html">
						<c:param name="id" value="${statement.id}" />
					</c:url> <a href="${urlStatementEdit}" class="edit">${statement.lastName}
						${statement.firstName} ${statement.patronymic}</a></td>
				<td class="content">
				<p align="center">${statement.faculty.name}
					</p></td>
			</tr>
		</c:forEach>
	</table>
</u:page>