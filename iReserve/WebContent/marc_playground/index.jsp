<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iReserve</title>
</head>
<body>
	<p> Welcome to iReserve </p>
	<p> Please log in </p>
	<form action="LoginServlet" method="post">
		Name: <input type="text" size="5" name="login"/>
		Pass: <input type="password" size="5" name="pass"/>
    	<input type="submit" value="Login" name="login">
	</form>
</body>
</html>