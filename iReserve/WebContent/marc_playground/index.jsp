<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<link rel="stylesheet" href="iRsrve.css"/>
	<title>iReserve</title>
</head>
<body>
	<p><img src="steria-sopra.jpg" id="flottante" alt="imageflottante" /></p>
	

	<header>
		<h1>iReserve</h1>
	</header>
	<section class="greytext">
		<form action="LoginServlet" method="post">
			<div><label>entrer votre pseudonyme   : </label><input type="text" value="login" name="login" id="login" autofocus/></div>
			<div><label>entrez votre mot de passe :</label><input type="password" value="password" name="pass"/></div>
			<div><input type="submit" value="submit" id="submit"/></div>
		</form>
	</section>

</body>