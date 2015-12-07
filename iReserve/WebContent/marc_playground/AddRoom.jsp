<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iReserve - Add Room</title>
</head>
<body>
	<form action="AddRoomServlet" method="post">
        Name: <input type="text" size="5" name="name"/>
        &nbsp;&nbsp;
        Size: <input type="text" size="5" name="size"/>
        &nbsp;&nbsp;
        <input type="submit" value="Add Room" />
    </form>
</body>
</html>