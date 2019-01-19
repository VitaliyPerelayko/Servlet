<%@ page import="java.util.ArrayList" %>
<html><head><title>task15</title></head>
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
</body>
</html>
