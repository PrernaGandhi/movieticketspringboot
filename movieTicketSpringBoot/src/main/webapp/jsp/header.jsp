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
	<div>
		<div align="left">
			<img id="logo" alt="" src="img/logo.png">
		</div>
		<div class="loginInfo" align="right" class="headerText">Welcome
			${userInfo.firstName}</div>
	</div>
	<div align="right">
		<a class="loginInfo" href="logout">Logout</a>
	</div>
</body>
</html>