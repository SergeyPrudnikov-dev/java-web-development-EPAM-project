<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<fmt:message key="user.list.title" var="title" />

<u:page title="${title}">

	<table>
		<tr>
			<th><fmt:message key="user.list.table.login" /></th>
			<th><fmt:message key="user.list.table.role" /></th>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td class="content"><c:url var="urlUserEdit"
						value="/user/edit.html">
						<c:param name="id" value="${user.id}" />
					</c:url> <a href="${urlUserEdit}" class="edit">${user.login}</a></td>

				<td class="content"><fmt:message key="${user.role.name}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:url var="urlUserEdit" value="/user/edit.html" />
	<a href="${urlUserEdit}" class="add-button"><fmt:message
			key="user.list.button.add" /></a>
</u:page>