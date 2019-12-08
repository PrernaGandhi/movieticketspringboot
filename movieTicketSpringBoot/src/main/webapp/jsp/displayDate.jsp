<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Date</title>
<link rel="stylesheet" href="css/input.css">
<link rel="stylesheet" href="css/body.css">
<link rel="stylesheet" href="css/submit.css">
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
	<form action="displayTimings" method="get">
		<input id="date" name="dateSelected" type="date"
			min="<%=getCurrentDate()%>" max="<%=getDateAfterAWeek()%>"
			required="required"> <br> <br> <input type="submit"
			value="Submit" id="submit">
	</form>
</body>
</html>