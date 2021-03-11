<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" rtexprvalue="true"
	type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Приемная комиссия<c:if test="${not empty title}"> :: ${title}</c:if>
</title>

<style>
   .btn {
    display: inline-block; /* Строчно-блочный элемент */
    background: #8C959D; /* Серый цвет фона */
    color: #fff; /* Белый цвет текста */
    padding: 1rem 1.5rem; /* Поля вокруг текста */
    text-decoration: none; /* Убираем подчёркивание */
    border-radius: 3px; /* Скругляем уголки */
   }
  </style>
</head>
<body>

<body> 
  <a href="/rcreception_commission/index.html" class="btn">Приемная комиссия</a><a href="/rcreception_commission/logout.html">Выйти</a>
 </body>

	<div style="background: wihte;">
		<c:if test="${not empty title}">
			<h2>${title}</h2>
		</c:if>
		<jsp:doBody />
	</div>
</body>
</html>