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
				if (request.getAttribute("errorMsg") != null) {
					out.print(request.getAttribute("errorMsg"));
				}
			%>
			<form action="registerUser" method="post">
				<label>Email</label> <input type="text" name="email" id="email"
					pattern="([A-z0-9_]+@epam.com)"
					oninvalid="setCustomValidity('Must be a valid epam email id')" required><br />
				<label>Username</label> <input type="text" name="username"
					id="username" required><br /> <label>Password</label> <input
					type="password" name="password" id="password"
					pattern="^((?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$)"
					oninvalid="setCustomValidity('Please include 2 uppercase, 3 lowercase, 2 digits ,1 special character and length >= 8')"
					 required><br /> <label>First Name</label> <input
					type="text" name="firstName" id="fname" pattern="([A-z]+)" required><br /> <label>Last
					Name</label> <input type="text" name="lastName" id="lname" pattern="([A-z]+)" required><br />
				<label>Age</label> <input type="number" name="age" id="age"
					max="120" min="1"
					oninvalid="setCustomValidity('Must be a valid age')" required><br />
				<label>Gender</label> <input type="radio" name="gender" id="gender"
					value="male" required>Male <input type="radio"
					name="gender" id="gender" value="female">Female<br /> <input
					type="submit" value="Register" id="submit">
			</form>
			<hr>
			<a href="login">Back To Login</a>
		</div>
	</div>
	<script>
	var fname = document.getElementById("fname")
	  , lname = document.getElementById("lname");

	function validatePassword(){
	  if(fname.value == lname.value) {
		  lname.setCustomValidity("First Name and Last Name should not be same");
	  } else {
		  lname.setCustomValidity('');
	  }
	}
	fname.onchange = validatePassword;
	lname.onkeyup = validatePassword;
	</script>
		<footer><%@include file="footer.jsp"%></footer>
</body>
</html>