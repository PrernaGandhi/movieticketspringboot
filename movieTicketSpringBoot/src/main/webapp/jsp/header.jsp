<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>header</title>
<link rel="stylesheet" href="css/head.css">
</head>
<body>
	<div class="header">
		<div class="One">
			<div class="loginInfo" align="right" class="headerText">Welcome
				${userInfo.firstName}</div>
			<a class="loginInfo" href="logout">Logout</a>
		</div>
		<div align="right" class="Two"></div>
		<div align="left">
			<div class="loginInfo" align="left" class="headerText">
				<img id="logo" alt="" src="img/logo.png">
			</div>
			<a class="loginInfo" href="/login-success">Home</a>
		</div>
	</div>
</body>
</html>