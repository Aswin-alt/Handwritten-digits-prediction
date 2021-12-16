<%@page import="com.aswin.model.EnumError"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.aswin.model.Representative"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top Representative</title>
</head>
<body>
	<h2 style = "text-align : center;">Top Representative Details</h2>
	<br><br>
	
	<%
	LocalDate from = LocalDate.parse(request.getParameter("from"));
	LocalDate to = LocalDate.parse(request.getParameter("to"));
	String choice = request.getParameter("choice");
	
	Representative r;
	try{
		if(to.isBefore(from)){
			throw new NullPointerException();
		}
		
		r = SuperMarketDAO.getInstance().getTopRep(from, to, choice);
	
		if(choice.equals("amount")){
			%>
			<label style="font-size: large;">
			<b>Top Representative</b> of the month for the period 
			from <b><%=from %>
			</b> to <b><%=to %></b> is 
			<br><b style="font-size: 24px;">Representative Name : <%=r.getRepName() %></b>
			<br><b style="font-size: 24px;">Representative ID : <%=r.getRepId() %></b>
			<br><b style="font-size: 24px;">Total Amount : <%=r.getToalAmount() %></b>
			</label>
			
			<%
		}
		else{
			%>
			<label style="font-size: large;">
			<b>Top Representative</b> of the month for the period 
			from <b><%=from %>
			</b> to <b><%=to %></b> is 
			<br><b style="font-size: 24px;">Representative Name : <%=r.getRepName() %></b>
			<br><b style="font-size: 24px;">Representative ID : <%=r.getRepId() %></b>
			<br><b style="font-size: 24px;">Total Bills : <%=r.getToalAmount() %></b>
			</label>
			
			<%
		}
		%>
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
	catch(NullPointerException e){
		out.println(EnumError.DATEFORMAT.getMessage());
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/report.jsp" method="get">
		<button type="submit">Back</button>
		</form>
		<%
	}
	%>
	
</body>
</html>