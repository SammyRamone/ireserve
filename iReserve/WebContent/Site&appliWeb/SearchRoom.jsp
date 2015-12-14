<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" href="" />
</head>
<body>
	<section>
		<form method="post"  action="/SearchRoom">
			<input type="date" value="date" name="date"/>
			<input type="text" value= "etage" name="etage"/>
			<input type="text" value= "site" name="site"/>
			<input type="text" value= "capacite" name="capa"/>
			<input type="radio" value=" particularite" name="particularite-secu"/>securite<br>
			<input type="radio" value=" particularite" name="particularite-visio"/>visio<br>
			<input type="radio" value=" particularite" name="particularite-digilab"/>digilab<br>
			<input type="radio" value=" particularite" name="particularite-tableau"/>tableau<br>
			<input type="submit" value="rechercher" name="rechercher"/>
		</form>
		<article><p id="p"></p></article>
	</section>

</body>