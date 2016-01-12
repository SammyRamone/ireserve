<%@page import="helper.HTMLHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"/>
	<%=HTMLHelper.CSS %>
	<c:set var="currentD" value="<%=new java.util.Date()%>" />
</head>
<body>
	<section>
		<form method="post"  action="/iReserve/rooms/SearchRoom">
			<span>Date: </span>
			<input type="date" value="2016-01-01" name="date"/>
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
			<select name="site" onchange="updateBatiments()" id="sites">
				<c:forEach items="${Sites}" var="site">
					<option>${site}</option>
				</c:forEach>
			</select>
			<span>Capacité: </span>
			<select name="capa">
				<c:forEach items="${Capacities}" var="entry">
					<option>${entry}</option>
				</c:forEach>
			</select>
			<br/>
			<span>Fonctionnalités: </span>
			<input type="checkbox"  value="true" name="particularite-secu"/>securite
			<input type="checkbox"  value="true" name="particularite-visio"/>visio
			<input type="checkbox"  value="true" name="particularite-digilab"/>digilab
			<input type="checkbox"  value="true" name="particularite-tableau"/>tableau<br>
			<input type="submit" value="rechercher" name="rechercher"/>
		</form>
	</section>
	<%=HTMLHelper.BACKBUTTON %>
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
</script>