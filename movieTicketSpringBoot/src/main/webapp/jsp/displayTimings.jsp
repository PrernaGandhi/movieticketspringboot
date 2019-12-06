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
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<%!public String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Date date = Calendar.getInstance().getTime();
		return dateFormat.format(date);
	}

	public String getDateAfterAWeek() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, 7);
		Date date = calender.getTime();
		return dateFormat.format(date);
	}%>
	<div align="right"></div>
	<div align="justify">
		<div align="center">
			<form action="displaySeats" method="get">
				<input id="date" name="dateSelected" type="date"
					min="<%=getCurrentDate()%>" max="<%=getDateAfterAWeek()%>"
					 required="required"> <br> <br>
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:formatDate var="now" type="time" pattern="HH:mm" value="${now}" />
				<select id="dropdown" name="timeSelected">
					<c:forEach items="${timingsList}" var="timings" varStatus="loop">
					${now } ${timings.timing }   
					${timings.timing gt now}
					<fmt:formatDate var="now2" type="time" pattern="ha"
							value="<%=new Date(new Date().getTime() + 1)%>" />
					${now2 }
						<c:if test="${timings.timing gt now}">
							<option value="${timings.timingsId}">${timings.timing}
								<c:set var="timing" value="${timings.timing}" scope="session" />
							</option>
						</c:if>
					</c:forEach>
				</select> <br> <input type="submit" value="Submit" id="submit">
			</form>
		</div>
	</div>
	<script>
		$(function() {
			$("#submit").click(function() {
				//alerting the value inside the textbox
				var date = $("#date");console.log(date);
				alert($.datepicker.formatDate("mm/dd/yyyy", date));
			});
		});
	</script>
</body>
</html>