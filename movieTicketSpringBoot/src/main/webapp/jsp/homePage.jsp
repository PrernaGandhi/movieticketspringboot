<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.epam.beans.Users"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Locations</title>
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/loginInfo.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/buttonStyle.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<div align="right"></div>
	<div align="justify">
		<div align="center">
			<br> <label>Select Location</label>
			<form action="displayMovies" method="get">
				<select name="locationSelected">
					<c:forEach items="${locationList}" var="location" varStatus="loop">
						<option value="${location.locationId}-${location.locationName}">${location.locationName}</option>
						<br>
					</c:forEach>
				</select> <br> <input type="submit" value="Next" id="submit">
			</form>
		</div>
	</div>
	<footer><%@include file="footer.jsp"%></footer>
</body>
</html>