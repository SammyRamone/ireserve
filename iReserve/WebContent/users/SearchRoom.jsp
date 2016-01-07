<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<span>Date: </span>
			<input type="date" value="date" name="date"/>
			<span>Batiments: </span>
			<select name="batiment" id="batiments">
				<c:forEach items="${Batiments}" var="entry">
					<c:if test="${entry.key=='Toulouse'}">
						<c:forEach items="${entry.value}" var="batE">
							<option>${batE}</option>
						</c:forEach>
					</c:if>
				</c:forEach>
			</select>
			<span>Sites: </span>
			<select name="sites" onchange="updateBatiments()" id="sites">
				<c:forEach items="${Sites}" var="site">
					<option>${site}</option>
				</c:forEach>
			</select>
			<span>Capacité: </span>
			<input type="text" value= "capacite" name="capa" id="capa"/>
			<br/>
			<span>Fonctionnalités: </span>
			<input type="radio"  name="particularite-secu" onclick="update()"/>securite
			<input type="radio"  name="particularite-visio"/>visio
			<input type="radio"  name="particularite-digilab"/>digilab
			<input type="radio"  name="particularite-tableau"/>tableau<br>
			<input type="submit" value="rechercher" name="rechercher"/>
		</form>
		<article><p id="p"></p></article>
	</section>

</body>

<script>
		function updateBatiments()
		{
			var siteL = document.getElementById("sites");
			var site = siteL.options[siteL.selectedIndex].value
			var batiments = <%=request.getAttribute("GBatiments")%>			
			var batL = document.getElementById("batiments");
			batL.length = 0;
			var tab = batiments[site];
			tab.forEach(addOptions);
		}
		function addOptions(element, index, array) 
		{
			var batL = document.getElementById("batiments");
	 		batL.options[batL.options.length] = new Option(element, element);
		}
		function update()
		{
			if(document.getElementById("updateSecu").checked==false)
			{
				document.getElementById("updateSecu").checked=true;
				document.getElementById("updateSecu").value = "true";
			}
			else
			{
				document.getElementById("updateSecu").checked=false;
				document.getElementById("updateSecu").value = null;
			}
		}
		
</script>