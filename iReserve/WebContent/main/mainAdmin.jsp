<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../rooms/ListRoomsServlet">
    	<input type="submit" value="List Rooms">
	</form>
	<form action="RoomManagment.jsp">
    	<input type="submit" value="Manage Rooms">
	</form>
	<form action="UserManagment.jsp">
    	<input type="submit" value="Manage Users">
	</form>
	<form action="ReservationManagmentServlet">
    	<input type="submit" value="Manage Reservations">
	</form>
	
</body>
</html>