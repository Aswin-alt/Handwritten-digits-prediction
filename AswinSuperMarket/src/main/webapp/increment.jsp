<%@page import="com.aswin.model.EnumError"%>
<%@page import="java.time.Year"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.aswin.model.RepIncrement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>increment</title>
</head>
<body>
	<h2 style = "text-align : center;">Increment Details</h2>
	<br><br>
	
	<%
	String securityCode = request.getParameter("code");
	int offset = Integer.parseInt(request.getParameter("offset"));
	
	int year = Year.now().getValue();
	//Authenticate
	if(securityCode.equals("12345")){
		try{
			%>
			<table align="center" cellpadding="5" cellspacing="5" border="1">
	
			<tr bgcolor="#00ffff">
			<td><b>Representative ID</b></td>
			<td><b>Representative Name</b></td>
			<td><b>Desig ID</b></td>
			<td><b>Date of Joining</b></td>
			<td><b>Age</b></td>
			<td><b>Total no of Sales</b></td>
			<td><b>Total sales Amount</b></td>
			<td><b>Current Salary</b></td>
			<td><b>Increment %</b></td>
			<td><b>Additional Increment %</b></td>
			<td><b>Incremented Salary</b></td>
			</tr>
			<%
				Map<Integer, RepIncrement> incMap = SuperMarketDAO.getInstance().getIncrementDetails(year, offset);
				Set<Integer> keys = incMap.keySet();
				
				LocalDate today = LocalDate.now();
				LocalDate todayMinus2Years = today.minusMonths(24);
				LocalDate todayMinus5Years = today.minusMonths(60);
				
				if (!keys.isEmpty()) 
				{
					for(Integer key : keys) {
						
						RepIncrement r = incMap.get(key);
						
						double increment = 0;
						double additionalIncrement = 0;
				
						LocalDate doj = LocalDate.parse(r.getDoj());
						if(doj.isBefore(todayMinus5Years)){
							increment += 8;
						}
						else if(doj.isBefore(todayMinus2Years)){
							increment += 10;
						}
						
						if(r.getTotalSalesAmount() > 10000){
							additionalIncrement += 3;
						}
						
						r.setIncrementPercent(increment);
						r.setAdditionalIncrementedPercent(additionalIncrement);
						
						double incrementedSalary = r.getCurrentSalary() + ((r.getCurrentSalary() * increment) / 100);
						incrementedSalary = incrementedSalary + ((incrementedSalary * additionalIncrement) / 100);
						
						r.setIncrementedSalary(incrementedSalary);
						
						%>
						<tr>
							<td><%=r.getRepId() %></td>
							<td><%=r.getRepName() %></td>
							<td><%=r.getDesigId() %></td>
							<td><%=r.getDoj() %></td>
							<td><%=r.getAge() %></td>
							<td><%=r.getTotalNoOfSales() %></td>
							<td><%=r.getTotalSalesAmount() %></td>
							<td><%=r.getCurrentSalary() %></td>
							<td><%=r.getIncrementPercent() %></td>
							<td><%=r.getAdditionalIncrementedPercent() %></td>
							<td><%=r.getIncrementedSalary() %></td>
						</tr>
						<%
					}
					
					boolean active = SuperMarketDAO.getInstance().updateRepSalary(incMap);
					if(active){
						%>
						<p>Successfully Incremented!</p>
						<%
					}
					else{
						%>
						<p>Increment Unsuccessful!</p>
						<%
					}
				}
			%>
			</table>
			
			<!-- Back -->
			<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
			<button type="submit">Back</button>
			</form>
			<%
		}
		catch(SQLException e){
			out.println("No Purchased made in this month!");
			%>
			<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
			<button type="submit">Back</button>
			</form>
			<%
		}
	}
	else{
		out.println(EnumError.UNAUTHENTICATED.getMessage());
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
		<button type="submit">Back</button>
		</form>
		<%
	}
	%>

</body>
</html>