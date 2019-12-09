<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
</head>
<body>
	<div class="outer-div">
		<div class="inner-div">
		<%
		if(request.getAttribute("errorMsg") != null){
			out.print(request.getAttribute("errorMsg"));
		}
		%>
			<form action="registerUser" method="post">
				<label>Username</label> <input type="text" name="username"
					id="username" required><br /> <label>Password</label> <input
					type="password" name="password" id="password" required><br />
				<label>First Name</label> <input type="text" name="firstName"
					id="fname" required><br /> <label>Last Name</label> <input
					type="text" name="lastName" id="lname" required><br /> <label>Age</label>
				<input type="number" name="age" id="age" max="120" min="1" required><br /> <label>Gender</label>
				<input type="radio" name="gender" id="gender" value="male" required>Male
				<input type="radio" name="gender" id="gender" value="female">Female<br />
				<input type="submit" value="Submit" id="submit">
			</form>
			<hr>
			<a href="login">Back To Login</a>
		</div>
	</div>
</body>
</html>