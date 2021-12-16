<%@page import="com.aswin.model.EnumError"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Total sales</title>
</head>
<body>
	
	<h2 style = "text-align : center;">Total Sales Details</h2>
	<br><br>
	
	<%
	LocalDate from = LocalDate.parse(request.getParameter("from"));
	LocalDate to = LocalDate.parse(request.getParameter("to"));
	int totalSales = 0;
	try{
		if(to.isBefore(from)){
			throw new NullPointerException();
		}
		totalSales = SuperMarketDAO.getInstance().getTotalSales(from, to);
	}
	catch(NullPointerException e){
		out.println(EnumError.DATEFORMAT.getMessage());
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
		<button type="submit">Back</button>
		</form>
		<%
	}
	%>
	
	<label style="font-size: large;"><b>Total Sales</b> Made from <b><%=from %></b> to <b><%=to %></b><br>is <b style="font-size: 24px;"><%=totalSales %></b></label>
	
	<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
		<button type="submit">Back</button>
	</form>
</body>
</html>