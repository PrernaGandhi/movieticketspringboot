<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="k"%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Seats</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
<link rel="stylesheet" href="css/seats.css">
</head>
<body onload="disableBackButton()" onload="">
	<script>
	  function disableBackButton()
      {
	        window.history.forward();
       }
	</script>
	<%@include file="header.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<label>City Selected :: ${order.locationName}</label> <br> <label>Movie
				Selected :: ${order.movieName}</label> <br> <label>Theater
				Selected :: ${order.theaterName}</label> <br> <label>Date
				Selected :: ${order.dateOfPurchase}</label> <br> <label>Time
				Selected :: ${time}</label> <br> <br> <label>Select Seats</label>
			<form action="bookSeats" method="post">
				<c:set var="bookedSeats" value="${seatsList}" />
				<c:forEach var="totalSeats" items="${theaterCapacity}"
					varStatus="seat">
					<label></label>
					<c:if test="${totalSeats.categoryOfSeat =='N' }">
						<label>Normal Seats :: Rs. ${totalSeats.priceOfSeat}</label>
					</c:if>
					<c:if test="${totalSeats.categoryOfSeat =='R' }">
						<label>Royal Seats :: Rs. ${totalSeats.priceOfSeat}</label>
					</c:if>
					<c:if test="${totalSeats.categoryOfSeat =='P' }">
						<label>Platinum Seats :: Rs. ${totalSeats.priceOfSeat}</label>
					</c:if>
					<br>
					<br>
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
				<br>
				*Note: Next button will be enabled once you select a seat!!
				<br> <input type="submit" value="Next" id="submit"
					onclick="return proceed()" disabled>
			</form>
		</div>
	</div>
	<br>
	<script>
		let selected = 0;
		let checkboxes = document.querySelectorAll('input[type=checkbox]');
		let price = 0;
		var seats = [];
		checkboxes.forEach((element)=> {
			element.addEventListener('change', () => {
				if(element.checked){
				selected += 1;
				console.log(element.value);
				var array = element.value.split("-");
				price += parseInt(array[1], 10);
				seats.push(array[0]);
				}
				else{
					selected-=1;
					var array = element.value.split("-");
					price -= parseInt(array[1], 10);
					seats.splice(array[0], 1);
				}
				console.log(selected);
				console.log(price);
				console.log(seats);
				if (selected > 0) {
					document.getElementById('submit').disabled = false;
				}else{
					document.getElementById('submit').disabled = true;
				}
			})
		})
		function proceed() {
			  if(!confirm("Are you sure you want to proceed to pay Rs."+price+" for seats "+seats+"?"))
				  return false;
			}
	</script>
	<footer><%@include file="footer.jsp"%></footer>
</body>
</html>