<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<c:if test="${empty faculty}"><jsp:useBean
        id="statementByEntrant"
        class="by.epam.jvd.vitebsk.task4.domain.Faculty" /></c:if>
<c:choose>
    <c:when test="${not empty faculty.id}">
        <fmt:message var="title" key="faculty.edit.title.edit" />
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="faculty.edit.title.add" />
    </c:otherwise>
</c:choose>

<fmt:message var="title" key="faculty.edit.title" />

<u:page title="${title}">

    <c:url var="urlFacultySave" value="/faculty/save.html" />
    <c:url var="urlFacultyDelete" value="/faculty/delete.html"/>
    <form action="${urlFacultySave}" method="post">
        <c:if test="${not empty faculty.id}">
            <input name="id" value="${faculty.id}" type="hidden">
        </c:if>
        <table>
            <tr>
                <th><h3>
                        <fmt:message key="faculty.edit.application_form" />
                    </h3></th>
            </tr>
            <tr>
                <td><label for="name"><fmt:message key="faculty.edit.name" />:</label></td>
                <td><input id="name" type="text" name="name"
                    value="${faculty.name}"></td>
            </tr>
            <tr>
                <td><label for="recruitment_plan"><fmt:message
                            key="faculty.edit.recruitment_plan" />:</label></td>
                <td><input id="recruitment_plan" type="text" name="recruitment_plan"
                    value="${faculty.recruitment_plan}"></td>
            </tr>
            <tr>
                <td><label for="certificate"><fmt:message
                            key="faculty.edit.certificate" />:</label></td>
                <td><input id="certificate" type="text" name="certificate"
                    value="${faculty.certificate}"></td>
            </tr>
            <tr>
                <td><label for="subject_1"><fmt:message
                            key="faculty.edit.subject_1" />:</label></td>
                <td><input id="subject_1" type="text" name="subject_1"
                    value="${faculty.subject_1}"></td>
            </tr>
             <tr>
                <td><label for="subject_2"><fmt:message
                            key="faculty.edit.subject_2" />:</label></td>
                <td><input id="subject_2" type="text" name="subject_2"
                    value="${faculty.subject_2}"></td>
            </tr>
             <tr>
                <td><label for="subject_3"><fmt:message
                            key="faculty.edit.subject_3" />:</label></td>
                <td><input id="subject_3" type="text" name="subject_3"
                    value="${faculty.subject_3}"></td>
            </tr>
        </table>
        
        <button class="reset" type="reset">
            <fmt:message key="faculty.edit.button.reset" />
        </button>
        
        <button class="save">
            <fmt:message key="faculty.edit.button.save" />
        </button>
        
        <c:if test="${not empty faculty.id}">
            <button class="delete" formaction="${urlFacultyDelete}" formmethod="post" ><fmt:message key="faculty.edit.button.delete"/></button>
        </c:if>
        
    </form>
</u:page>