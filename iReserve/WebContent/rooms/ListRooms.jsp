<%@page import="helper.HTMLHelper"%>
<%@page import="rooms.ListRoomsServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../main/iStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=ListRoomsServlet.getRoomTable() %>
<%=HTMLHelper.BACKBUTTON %>
</body>
</html>