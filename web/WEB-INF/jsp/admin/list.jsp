<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />
<fmt:message key="admin.list.title" var="title" />

<u:page title="${title}">

	<p>
		<c:url var="urlUserList" value="/user/list.html" />
		<a href="${urlUserList}" class="add-button"><fmt:message
				key="admin.list.button.go.userlist" /></a>
	</p>

	<p>
		<c:url var="urlFacultyList" value="/faculty/list.html" />
		<a href="${urlFacultyList}" class="add-button"><fmt:message
				key="admin.list.button.go.faculty" /></a>
	</p>

	<p>
		<c:url var="urlStatementList" value="/statement/list.html" />
		<a href="${urlStatementList}" class="add-button"><fmt:message
				key="admin.list.button.go.statements" /></a>
	</p>

	<p>
		<c:url var="urlEnrolledEntrantsList"
			value="/enrolledentrants/list.html" />
		<a href="${urlEnrolledEntrantsList}" class="add-button"><fmt:message
				key="admin.list.button.go.enrolledentrants" /></a>
	</p>
</u:page>