<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<c:if test="${empty user}"><jsp:useBean id="user"
		class="by.epam.jvd.vitebsk.task4.domain.User" /></c:if>
<c:choose>
	<c:when test="${not empty user.id}">
		<fmt:message var="title" key="user.edit.title.edit" />
	</c:when>
	<c:otherwise>
		<fmt:message var="title" key="user.edit.title.add" />
	</c:otherwise>
</c:choose>

<u:page title="${title}">
	<c:url var="urlUserList" value="/user/list.html" />
	<c:url var="urlUserSave" value="/user/save.html" />
	<c:url var="urlUserDelete" value="/user/delete.html" />
	<c:url var="urlPasswordReset" value="/password/edit.html" />
	<form action="${urlUserSave}" method="post">
		<c:if test="${not empty user.id}">
			<input name="id" value="${user.id}" type="hidden">
		</c:if>
		<label for="login"><fmt:message key="user.edit.form.login" />:</label>
		<input id="login" name="login" value="${user.login}"> <label
			for="email"><fmt:message key="user.edit.form.email" />:</label> <input
			id="email" name="email" value="${user.email}"> <label
			for="role"><fmt:message key="user.edit.form.role" />:</label> <select
			id="role" name="role">
			<c:forEach var="role" items="${roles}">
				<c:choose>
					<c:when test="${role.id == user.role.id}">
						<c:set var="selected" value="selected" />
					</c:when>
					<c:otherwise>
						<c:remove var="selected" />
					</c:otherwise>
				</c:choose>
				<option value="${role.id}" ${selected}><fmt:message
						key="${role.name}" /></option>
			</c:forEach>
		</select>
		<button class="save">
			<fmt:message key="user.edit.button.save" />
		</button>
		<c:if test="${not empty user.id}">
			<c:if test="${not empty  sessionUser}">
				<button class="delete" formaction="${urlUserDelete}"
					formmethod="post">
					<fmt:message key="user.edit.button.delete" />
				</button>
			</c:if>
		</c:if>
		<c:if test="${not empty user.id}">
			<button class="password-reset" formaction="${urlPasswordReset}"
				formmethod="post">
				<fmt:message key="user.edit.button.password.reset" />
			</button>
		</c:if>
		<button class="reset" type="reset">
			<fmt:message key="user.edit.button.reset" />
		</button>
		<a class="back" href="${urlUserList}"><fmt:message
				key="user.edit.button.cancel" /></a>
	</form>
</u:page>