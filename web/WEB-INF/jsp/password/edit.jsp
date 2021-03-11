<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="resource.pagecontent_ru_ru" />

<fmt:message var="title" key="password.change.title"/>

<u:page title="${title}">
    <c:url var="urlPasswordSave" value="/password/save.html"/>
    <form action="${urlPasswordSave}" method="post">
    
        <label for="old-password"><fmt:message key="password.change.form.old.password"/>:</label>
        <input id="old-password" name="old-password" type="password"  value="${user.password}">
        
        <label for="new-password"><fmt:message key="password.change.form.new.password"/>:</label>
        <input id="new-password" name="new-password" type="password" value="${user.password}">
        
        <label for="new-password-repeat"><fmt:message key="password.change.form.new.password.repeat"/>:</label>
        <input id="new-password-repeat" name="new-password-repeat" type="password" value="${user.password}">
        
        <button class="save"><fmt:message key="account.edit.button.save"/></button>
        <c:url var="urlBack" value="/index.html"/>
        <a class="back" href="${urlBack}"><fmt:message key="password.change.button.cancel"/></a>
    </form>
</u:page>