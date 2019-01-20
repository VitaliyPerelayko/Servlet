<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>task15</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<%--Подключаем JSTL--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <h1>Table of expenses</h1>
    <table style="width:100%">
        <tr>
            <th>Num</th>
            <th>Pay Date</th>
            <th>Receiver</th>
            <th>Value</th>
        </tr>
        <%--Берём информацию из БД у запроса по имени атрибута--%>
        <c:forEach var="line" items="${requestScope.result}">
            <tr>
                <c:forEach var="value" items="${line}">
                    <td>
                        <c:out value="${value}"/>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <div class="w3-container w3-center">
        <div class="w3-bar w3-padding-large w3-padding-24">    <!-- buttons holder -->
            <button onclick="location.href='/a-1.0/AddExpenses'">Add Expenses</button>
            <button onclick="location.href='/a-1.0/DeleteExpenses'">Delete Expenses</button>
        </div>
    </div>
</body>
</html>
