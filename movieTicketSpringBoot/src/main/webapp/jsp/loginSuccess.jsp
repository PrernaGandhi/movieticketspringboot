<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/buttonStyle.css">
</head>
<body>	
<script type="text/javascript">
                    window.history.forward();
                    function noBack() {
                        window.history.forward();
                    }
                </script>
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<label>welcome ${userInfo.firstName}</label> <br> <br> <img
				alt="" src="img/Popcorn.jpg">
			<form action="homePage">
				<button class="button">click to continue..</button>
			</form>
		</div>
	</div>
</body>
</html>