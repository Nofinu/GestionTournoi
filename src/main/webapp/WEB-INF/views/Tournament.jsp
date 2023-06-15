<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 15/06/2023
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${tournaments != null}">
    <c:forEach items="${tournaments}" var="tournament">
        <div>name :${tournament.getName()}, date : ${tournament.getDate().toString()}</div>
    </c:forEach>
</c:if>



<a href="?type=add">Create a tournament</a>
</body>
</html>
