<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Timings</title>
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
		<label>City Selected :: ${order.locationName}</label>	
		<br>
		<label>Movie Selected :: ${order.movieName}</label>
		<br>
		<label>Theater Selected :: ${order.theaterName}</label>
		<br>
		<label>Date Selected :: ${order.dateOfPurchase}</label>
		<br>
		<br>
			<label>Select Time</label>
			<form action="displaySeats" method="get">
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:formatDate var="currentTime" type="time" pattern="HH:00"
					value="${now}" />
				<fmt:formatDate var="currentDate" type="date" pattern="YYYY-MM-dd"
					value="${now}" />
				<select id="dropdown" name="timeSelected">
					<c:choose>
						<c:when test="${currentDate == dateSelected}">
							<c:forEach items="${timingsList}" var="timings" varStatus="loop">
								<c:if test="${timings.timing > currentTime}">
									<option value="${timings.timingsId}-${timings.timing}">${timings.timing}
									</option>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${timingsList}" var="timings" varStatus="loop">
								<option value="${timings.timingsId}-${timings.timing}">${timings.timing}
								</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</select> <br> <input type="submit" value="Submit" id="submit">
			</form>
		</div>
	</div>
</body>
</html>