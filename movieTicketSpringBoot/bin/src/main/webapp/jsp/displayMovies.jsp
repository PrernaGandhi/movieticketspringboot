<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Movies</title>
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
			<form action="displayTheaters" method="get">
				<select name="movieSelected">
					<c:forEach items="${movieList}" var="movie" varStatus="loop">
						<option value="${movie.movieId}">${movie.movieName}(${movie.movieLanguage})
							<c:set var="movie"
								value="${movie.movieName}(${movie.movieLanguage})"
								scope="session" /></option>
						<br>
					</c:forEach>
				</select> <br> <input type="submit" value="Submit" id="submit">
			</form>
		</div>
	</div>
</body>
</html>