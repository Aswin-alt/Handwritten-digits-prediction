<%@page import="java.util.Set"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="com.aswin.model.Stock"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stocks Buyed</title>
</head>
<body>
	<h2 style = "text-align : center;">Stocks Buyed Details</h2>
	<br><br>
	
	<%
	int billId = Integer.parseInt(request.getParameter("billId"));
	
	try{
		Map<Integer, Stock> stockMap = SuperMarketDAO.getInstance().getStocks(billId);
		%>
		<table align="center" cellpadding="5" cellspacing="5" border="1">

		<tr bgcolor="#00ffff">
			<td><b>Stock ID</b></td>
			<td><b>Stock Name</b></td>
			<td><b>No of stocks</b></td>
			<td><b>Total</b></td>
		</tr>
		<%	
		Set<Integer> keys = stockMap.keySet();
		
		for(Integer key : keys){
			Stock s = stockMap.get(key);
			%>
			<tr>
			<td><%=s.getStockId() %></td>
			<td><%=s.getStockName() %></td>
			<td><%=s.getStockAvailable() %></td>
			<td><%=s.getStockPrice() %></td>
		</tr>
		<%
		}
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/billdetails.jsp" method="post">
		<button type="submit" name="offset" value="0">Back</button>
		</form>
		
		<%
	}
	catch(SQLException e){
		System.out.println(e);
		%>
		
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/billdetails.jsp" method="post">
		<button type="submit" name="offset" value="0">Back</button>
		</form>
		<%
	}
	%>
	
</body>
</html>