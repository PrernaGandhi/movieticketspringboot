<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/loginInfo.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/buttonStyle.css">
</head>
<body>
	<%@include file="adminheader.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<div title="ADD LOCATION">
				<a href="displayLocation"> <img alt=""
					src="/img/add-location.png"></a>
			</div>
			<div title="ADD MOVIE">
				<a href="displayMovie"> <img alt="" src="/img/addmovie.jpg"></a>
			</div>
		</div>
	</div>
</body>
</html>