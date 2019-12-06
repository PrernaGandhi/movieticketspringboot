<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<select name="timeSelected">
					<c:forEach items="${timingsList}" var="timings" varStatus="loop">
						<option value="${timings.timingsId}">${timings.timing}<c:set
								var="timing" value="${timings.timing}" scope="session" /></option>
					</c:forEach>
				</select> <br> <br> <input name="dateSelected" type="date"
					min="<%=getCurrentDate()%>" max="<%=getDateAfterAWeek()%>"
					required="required"> <br> <input type="submit"
					value="Submit" id="submit">
			</form>
		</div>
	</div>
</body>
</html>