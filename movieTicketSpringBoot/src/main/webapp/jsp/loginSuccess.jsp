<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/buttonStyle.css">
</head>
<body>	
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<label>Welcome ${userInfo.firstName}</label> <br> <br> <img
				alt="" src="img/Popcorn.jpg">
			<form action="homePage" method="get">
				<button class="button">click to continue..</button>
			</form>
		</div>
	</div>
	<footer><%@include file="footer.jsp"%></footer>		
</body>
</html>