<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
<title>Admin: Add Movie</title>
</head>
<body>
	<%@include file="adminheader.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<form action="addMovie" method="post">
				Select existing location: <select name="locationSelected">
					<c:forEach items="${locationList}" var="location" varStatus="loop">
						<option value="${location.locationName}">${location.locationName}</option>
						<br>
					</c:forEach>
				</select> <br> <input name="movieName" type="text"
					placeholder="Enter movie to be added in database" required>
					<input name="movieLanguage" type="text"
					placeholder="Enter movie lang" pattern="([A-Z]{3})"  required>
				<input type="submit" value="Add">
			</form>
		</div>
	</div>
</body>
</html>