<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 13/06/2023
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="connection?type=register" method="post">
    <input type="text" hidden="hidden" value="Player" name="player">

    <label for="userName">UserName :</label>
    <input type="text" name="userName" id="userName" required>

    <label for="password">Password</label>
    <input type="password" name="password" id="password" required>

    <label for="game">Game :</label>
    <input type="password" name="game" id="game" required>

    <button>send</button>
</form>
</body>
</html>
