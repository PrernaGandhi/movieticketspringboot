<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epam.beans.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HomePage</title>
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/loginInfo.css">
<link rel="stylesheet" href="css/submit.css">
</head>
<body>
	<script>
		window.onbeforeunload = function() {
			return "You will be logged out if you click the back button";
		};
	</script>
	<%@include file="header.jsp"%>
	<div align="right"></div>
	<div align="justify">
		<div align="center">
			<form action="displayMovies" method="get">
				<select name="locationSelected">
					<c:forEach items="${locationList}" var="location" varStatus="loop">
						<option value="${location.locationId}-${location.locationName}">${location.locationName}</option>
						<br>
					</c:forEach>
				</select> <br> <input type="submit" value="Submit" id="submit">
			</form>
		</div>
	</div>
</body>
</html>