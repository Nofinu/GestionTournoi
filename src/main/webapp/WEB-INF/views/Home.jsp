<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<h1>islogged ${isLogged}</h1>
<h1>isAdmin ${isAdmin}</h1>
<c:if test="${isLogged}">
    <a href="connection?type=loOut">Log Out</a>
    <a href="teams">Teams</a>
</c:if>
<c:if test="${!isLogged}">
    <a href="connection?type=login">Login</a>
    <a href="connection?type=register">register</a>
</c:if>

</body>
</html>