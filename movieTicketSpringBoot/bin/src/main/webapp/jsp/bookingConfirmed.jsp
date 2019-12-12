<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Booking Confirmed</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">

			BOOKING CONFIRMED!!!!!!!!<br> USERNAME : ${order.userName}<br>
			LOCATION : ${order.locationName}<br> THEATER DETAILS :
			${order.theaterName}<br> SEATS BOOKED: ${order.seatsBooked}<br>
			MOVIE DETAILS : ${order.movieName}<br> TIMINGS :
			${order.timings.timing}<br> MOVIE SHOW DATE : ${order.dateOfPurchase}<br>
			TOTAL PRICE : ${order.totalPrice}<br>
		</div>
	</div>
</body>
</html>