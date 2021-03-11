<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<c:if test="${empty statementByEntrant}"><jsp:useBean
		id="statementByEntrant"
		class="by.epam.jvd.vitebsk.task4.domain.StatementByEntrant" /></c:if>
<c:choose>
	<c:when test="${not empty statementByEntrant.id}">
		<fmt:message var="title" key="statement.edit.title.edit" />
	</c:when>
	<c:otherwise>
		<fmt:message var="title" key="statement.edit.title.add" />
	</c:otherwise>
</c:choose>
<fmt:message var="title" key="statement.edit.title" />
<u:page title="${title}">
	<c:url var="urlStatementSave" value="/statement/save.html" />
	<c:url var="urlStatementDelete" value="/statement/delete.html" />
	<form action="${urlStatementSave}" method="post">
		<c:if test="${not empty statementByEntrant.id}">
			<input name="id" value="${statementByEntrant.id}" type="hidden">
		</c:if>
		<c:if test="${not empty statementByEntrant.userId}">
			<input name="userId" value="${statementByEntrant.userId}"
				type="hidden">
		</c:if>
		<table>
			<tr>
				<th><h3>
						<fmt:message key="statement.edit.application_form" />
					</h3></th>
			</tr>
			<tr>
				<td><label for="last_name"><fmt:message key="last_name" />:</label></td>
				<td><input id="last_name" type="text" name="last_name"
					value="${statementByEntrant.lastName}"></td>
			</tr>
			<tr>
				<td><label for="first_name"><fmt:message
							key="first_name" />:</label></td>
				<td><input id="first_name" type="text" name="first_name"
					value="${statementByEntrant.firstName}"></td>
			</tr>
			<tr>
				<td><label for="patronymic"><fmt:message
							key="patronymic" />:</label></td>
				<td><input id="patronymic" type="text" name="patronymic"
					value="${statementByEntrant.patronymic}"></td>
			</tr>
			<tr>
				<td><label for="passport_id"><fmt:message
							key="statement.edit.passport_id" />:</label></td>
				<td><input id="passport_id" type="text" name="passport_id"
					value="${statementByEntrant.passportId}"></td>
			</tr>
			<tr>
				<td><label for="faculty"><fmt:message
							key="statement.edit.faculty" />:</label></td>
				<td><select id="faculty" name="faculty">
						<c:forEach var="faculty" items="${faculties}">
							<c:choose>
								<c:when test="${faculty.id == statementByEntrant.faculty.id}">
									<c:set var="selected" value="selected" />
								</c:when>
								<c:otherwise>
									<c:remove var="selected" />
								</c:otherwise>
							</c:choose>
							<option value="${faculty.id}" ${selected}>${faculty.name}
							</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label for="certificate"><fmt:message
							key="statement.edit.certificate_score" />:</label></td>
				<td><input id="certificate" type="text"
					name="certificate_score"
					value="${statementByEntrant.certificateScore}"></td>
			</tr>
			<tr>
				<td><label for="subject_1"><fmt:message
							key="statement.edit.subject_1_score" />:</label></td>
				<td><input id="subject_1" type="text" name="subject_1_score"
					value="${statementByEntrant.subjectScore1}"></td>
			</tr>
			<tr>
				<td><label for="subject_2"><fmt:message
							key="statement.edit.subject_2_score" />:</label></td>
				<td><input id="subject_2" type="text" name="subject_2_score"
					value="${statementByEntrant.subjectScore2}"></td>
			</tr>
			<tr>
				<td><label for="subject_3"><fmt:message
							key="statement.edit.subject_3_score" />:</label></td>
				<td><input id="subject_3" type="text" name="subject_3_score"
					value="${statementByEntrant.subjectScore3}"></td>
			</tr>

		</table>
		<button class="reset" type="reset">
			<fmt:message key="statement.edit.button.reset" />
		</button>
		<button class="save">
			<fmt:message key="statement.edit.button.save" />
		</button>


		<c:if test="${not empty statementByEntrant.passportId}">
			<button class="delete" formaction="${urlStatementDelete}"
				formmethod="post">
				<fmt:message key="statement.edit.button.delete" />
			</button>
		</c:if>

	</form>
</u:page>