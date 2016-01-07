<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" href="" />
</head>
<body>
	<section>
		<form method="post"  action="/iReserve/rooms/SearchRoom">
			<input type="date" value="date" name="date"/>
			<input type="text" value= "batiment" name="batiment"/>
			<input type="text" value= "site" name="site"/>
			<input type="text" value= "capacite" name="capa"/>
			<input type="radio" value="true" name="particularite-secu"/>securite<br>
			<input type="radio" value="true" name="particularite-visio"/>visio<br>
			<input type="radio" value="true" name="particularite-digilab"/>digilab<br>
			<input type="radio" value="true" name="particularite-tableau"/>tableau<br>
			<input type="submit" value="rechercher" name="rechercher"/>
		</form>
		<article><p id="p"></p></article>
	</section>

</body>