<%--
  Created by IntelliJ IDEA.
  User: Da Nguym
  Date: 9/13/2024
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h1>Dashboard</h1>

<form action="ControllerServlet" method="post">
    <input type="hidden" name="action" value="logout"/>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
