<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" href="" />
</head>
<body>
	<section>
		<form method="post"  action="/iReserve/rooms/BookRoom">
			<input type="date" value="date" name="date"/>
			<input type="time" value="start" name="start"/>
			<input type="time" value="end" name="end"/>
			<input type="text" value= "batiment" name="batiment"/>
			<input type="text" value= "site" name="site"/>
			<input type="text" value= "room" name="room"/>
			<input type="text" value= "personne" name="personne"/>
			<input type="text" value= "object" name="object"/>
			<input type="submit" value="book" name="book"/>
		</form>
	</section>

</body>