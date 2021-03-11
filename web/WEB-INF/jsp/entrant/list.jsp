<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />
<fmt:message key="entrant.list.title" var="title" />

<u:page title="${title}">
	<p>
		<c:url var="urlUserEdit" value="/user/edit.html" />
		<a href="${urlUserEdit}" class="add-button"><fmt:message
				key="admin.list.button.go.user_edit" /></a>
	</p>
	<p>
		<c:url var="urlStatementEdit" value="/statement/edit.html" />
		<a href="${urlStatementEdit}" class="add-button"><fmt:message
				key="admin.list.button.go.statement" /></a>
	</p>
</u:page>