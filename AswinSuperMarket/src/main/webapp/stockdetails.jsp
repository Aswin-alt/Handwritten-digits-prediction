<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="com.aswin.model.Stock"%>
<%@page import="java.util.Set"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Details</title>
</head>
<body>

	<h2 align="center"><font><strong>BILL DETAILS</strong></font></h2>
	<table align="center" cellpadding="5" cellspacing="5" border="1">

		<tr bgcolor="#00ffff">
		<td><b>Stock ID</b></td>
		<td><b>Stock Name</b></td>
		<td><b>No of Stocks Available</b></td>
		<td><b>Price</b></td>
		</tr>
		<%
		try {
			Map<Integer, Stock> stockMap = SuperMarketDAO.getInstance().getAllStock();
			
			Set<Integer> keys = stockMap.keySet();
			
			if (!keys.isEmpty()) 
			{
				for(Integer key : keys) {
					Stock s = stockMap.get(key);%>
					<tr>
						<td><%=s.getStockId() %></td>
						<td><%=s.getStockName() %></td>
						<td><%=s.getStockAvailable() %></td>
						<td><%=s.getStockPrice() %></td>
					</tr>
					<%
				}
			}
		}
		catch(SQLException e){
			out.println("SQL Exception : " + e);
		}
		%>
		
	</table>
	<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/index.jsp" method="get">
		<button type="submit">Back</button>
	</form>
</body>
</html>