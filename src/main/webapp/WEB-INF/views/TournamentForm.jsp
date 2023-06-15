<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 15/06/2023
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${errorMessage != null}">
        <h2>${errorMessage}</h2>
    </c:if>
    <h1>form Tournament</h1>
    <form action="" method="post">
        <label for="EquipeName">Equipe name :</label>
        <input type="text" name="EquipeName" id="EquipeName" required>
        <label for="date">Day :</label>
        <input type="date" name="date" id="date" required>
        <label for="hours">Hours :</label>
        <input type="number" max="23" min="0" name="hours" id="hours" required>
        <label for="mins">minutes :</label>
        <input type="number" max="59" min="0" name="mins" id="mins" required>
        <button>add</button>
    </form>
</body>
</html>
