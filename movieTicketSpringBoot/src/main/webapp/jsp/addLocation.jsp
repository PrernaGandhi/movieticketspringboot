<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
<title>Admin: Add Location</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<form action="addLocation" method="post">
			<input name="location" type="text" pattern="^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$" placeholder="Enter city to be added in database">
			<input type="submit" value="Add">
			</form>
		</div>
	</div>
</body>
</html>