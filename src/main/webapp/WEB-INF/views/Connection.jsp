<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 13/06/2023
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>connection</title>
</head>
<body>
<h1>${errorMessage}</h1>
<br>
<form action="" method="post">
    <label for="userName">UserName :</label>
    <input type="text" name="userName" id="userName" required>

    <label for="password">Password :</label>
    <input type="password" name="password" id="password" required>

    <button>Connection</button>
</form>
</body>
</html>
