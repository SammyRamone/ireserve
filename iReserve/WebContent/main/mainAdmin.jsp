<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="iStyle.css">
<title>Insert title here</title>
</head>
<body>
	<form action="../rooms/RoomManagment.jsp">
    	<input type="submit" value="Manage Rooms">
	</form>
	<form action="../users/UserManagment.jsp">
    	<input type="submit" value="Manage Users">
	</form>
	<form action="../reservations/ReservationManagmentServlet">
    	<input type="submit" value="Manage Reservations">
	</form>
	<form action="../batiments/ManageBatimentsServlet">
    	<input type="submit" value="Manage Batiments">
	</form>
	<form action="../sites/ManageSitesServlet">
    	<input type="submit" value="Manage Sites">
	</form>
</body>
</html>