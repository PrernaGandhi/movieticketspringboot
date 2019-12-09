<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Theaters</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<div align="right"></div>
	<div align="justify">
		<div align="center">
		<label>City Selected :: ${order.locationName}</label>	
		<br>
		<label>Movie Selected :: ${order.movieName}</label>
			<form action="displayDate" method="get">
				<select name="theaterSelected">
					<c:forEach items="${theaterList}" var="theater" varStatus="loop">
						<option value="${theater.theaterId}-${theater.theaterName}(${theater.screenNumber})">${theater.theaterName}(${theater.screenNumber})
							</option>
						<br>
					</c:forEach>
				</select> <br> <input type="submit" value="Submit" id="submit">
			</form>
		</div>
	</div>
</body>
</html>