<%@page import="helper.HTMLHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../main/iStyle.css">
<title>iReserve - Room Management</title>
</head>
<body>

	<form action="ListRooms.jsp">
    	<input type="submit" value="List Rooms">
	</form>
	<form action="AddRoom.jsp">
    	<input type="submit" value="Add Rooms">
	</form>
	<form action="RemoveRoomServlet">
    	<input type="submit" value="Remove/Change Rooms">
	</form>
	
	<%=HTMLHelper.BACKBUTTON %>
</body>
</html>