<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
<link rel="stylesheet" href="css/loginInfo.css">
<title>Admin: Add Theater</title>
</head>
<body>

	<%@include file="adminheader.jsp"%>
	<div class="outer-div">
		<div class="inner-div">
			<form action="addTheater" method="post">
				Select existing location: <select id="loc" name="locationSelected">
					<c:forEach items="${locationList}" var="location" varStatus="loop">
						<option value="${location.locationId}">${location.locationName}</option>
						<br>
					</c:forEach>
				</select> <br> Select existing movie: <select id="mov"
					name="movie_id">
					<c:forEach items="${movieList}" var="movie" varStatus="loop">
						<option value="${movie.movieId}">${movie.movieName}(${movie.movieLanguage})
						</option>
						<br>
					</c:forEach>
				</select> <br> <input name="theaterName" type="text"
					placeholder="Enter theater to be added in database" required>
				<input name="screenNumber" type="text"
					placeholder="Enter screen number" pattern="([A-Z]{1}[0-9]{1})"
					required> <input type="submit" value="Add">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var k=true;
	var el = document.getElementById("loc");
	if(el){
		if(k){
		fetch('http://10.71.12.81:8079/restMovie/' + el.value)
		  .then(response => response.json())
		  .then(data => {
		    console.log(data); // Prints result from `response.json()` in getRequest
			  populate(data);
		  })
		  }
		el.addEventListener('change',() => {
		  console.log('You selected: ', el.value);
		  fetch('http://10.71.12.81:8079/restMovie/' + el.value)
		  .then(response => response.json())
		  .then(data => {
		    console.log(data); // Prints result from `response.json()` in getRequest
			  populate(data);
		  })
		  k= false;
		});	
	}
	function populate(data){
	     var ele = document.getElementById("mov");
	     console.log(ele);
	     ele.innerHTML = "";
	        for (var i = 0; i < data.length; i++) {
	            // POPULATE SELECT ELEMENT WITH JSON.
	            ele.innerHTML = ele.innerHTML +
	                '<option value="' + data[i]['movieId'] + '">' + data[i]['movieName'] +"("+data[i]['movieLanguage']+")"+ '</option>';
	        }
	    }
</script>
</body>
</html>