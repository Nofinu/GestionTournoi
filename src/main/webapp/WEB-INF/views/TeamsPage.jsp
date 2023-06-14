<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 14/06/2023
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teams page</title>
</head>
<body>
    <form action="" method="post">
        <h3>Create a team</h3>
        <c:if test="${errorMessage!=null}">
            <h2>${errorMessage}</h2>
        </c:if>
        <label for="teamName">Team name:</label>
        <input type="text" name="teamName" id="teamName">
        <button>Create</button>
    </form>
    <c:if test="${teams != null}">
        <c:forEach items="${teams}" var="team">
            <div>${team.getName()} <a href="?id=${team.getId_team()}">Join</a></div>
        </c:forEach>
    </c:if>

</body>
</html>
