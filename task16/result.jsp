<%@ page contentType="text/html;charset=UTF-8"%>
<%--Подключаем JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AddExpenses</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Result</h1>
</div>

<div class="w3-container w3-center w3-green">
    <h2>
        <c:out value="${requestScope.message}"/>
    </h2>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/a-1.0/Task15'">Back to main</button>
</div>
</body>
</html>