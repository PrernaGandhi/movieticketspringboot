<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="k"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Theaters</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
<link rel="stylesheet" href="css/seats.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
		<label>City Selected :: ${order.locationName}</label>	
		<br>
		<label>Movie Selected :: ${order.movieName}</label>
		<br>
		<label>Theater Selected :: ${order.theaterName}</label>
		<br>
		<label>Date Selected :: ${order.dateOfPurchase}</label>
		<br>
		<label>Time Selected :: ${time}</label>
			<form action="bookSeats" method="post">
				<c:set var="bookedSeats" value="${seatsList}" />
				<c:forEach var="totalSeats" items="${theaterCapacity}"
					varStatus="seat">
					<c:forEach var="seatNumber" begin="1"
						end="${totalSeats.countOfSeats}">
						<c:set var="seatType"
							value="${totalSeats.categoryOfSeat}${seatNumber}" scope="session" />
						<c:out value="${seatType}"></c:out>
						<input type="checkbox" class="check"
							name="${totalSeats.categoryOfSeat}"
							value="${seatType}-${totalSeats.priceOfSeat}"
							${k:isCheckboxChecked(bookedSeats,seatType) ? "checked disabled" : ""}>
						<label for="${totalSeats.categoryOfSeat}${seatNumber}"></label>
						<c:if test="${seatNumber%10 == 0}">
							<br>
						</c:if>
					</c:forEach>
					<br>
					<hr>
				</c:forEach>
				<br> <input type="submit" value="Submit" id="submit"
					 disabled>
			</form>
		</div>
	</div>
	<br>
	<script>
		let selected = 0;
		let checkboxes = document.querySelectorAll('input[type=checkbox]');
		
		checkboxes.forEach((element)=> {
			element.addEventListener('change', () => {
				if(element.checked){
				selected += 1;
				}
				else{
					selected-=1;
				}
				console.log(selected);
				if (selected > 0) {
					document.getElementById('submit').disabled = false;
				}else{
					document.getElementById('submit').disabled = true;
				}
			})
		})	
	</script>
</body>
</html>