<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>




<h1>Login</h1>
<form action="ControllerServlet" method="post">
  <input type="hidden" name="action" value="login"/>
  Email: <input type="text" name="email"/><br/>
  Password: <input type="password" name="password"/><br/>
  <input type="submit" value="Login"/>
  <input type="reset" value="Reset"/>
</form>

</body>
</html>