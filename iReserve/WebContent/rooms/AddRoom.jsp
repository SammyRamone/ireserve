<%@page import="helper.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../main/iStyle.css">
<title>iReserve - Add Room</title>
</head>
<body>
	<form action="AddRoomServlet" method="post">
        Room Number: <input type="text" size="5" name="number"/>
        &nbsp;&nbsp;
        Size: <input type="text" size="5" name="size"/>
        &nbsp;&nbsp;
        Batiment: 
        <%
        String [] batiments = SQLHelper.getInstance().getAllBatiments();
		String option = HTMLHelper.makeOption(batiments, "batiment");
		%>
		<%=option %>
        &nbsp;&nbsp;
        <input type="submit" value="Add Room" />
    </form>
    <%=HTMLHelper.BACKBUTTON %>
</body>
</html>