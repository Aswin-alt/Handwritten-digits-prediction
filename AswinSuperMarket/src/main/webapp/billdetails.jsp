<%@page import="com.aswin.model.EnumError"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Set"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="com.aswin.model.Bill"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bill Details</title>
</head>
<body>
	<h2 style = "text-align : center;">Monthly Bill Details</h2>
	<br><br>
	
	<%
	LocalDate from;
	LocalDate to;

	int offset = Integer.parseInt(request.getParameter("offset"));
	
	if(request.getParameter("from") != null){	
		from = LocalDate.parse(request.getParameter("from"));
		to = LocalDate.parse(request.getParameter("to"));
		
		session.setAttribute("from", from);
		session.setAttribute("to", to);
	}
	else{
		from = LocalDate.parse(session.getAttribute("from").toString());
		to = LocalDate.parse(session.getAttribute("to").toString());
	}

	try{
		if(to.isBefore(from)){
			throw new NullPointerException();
		}
		%>
		<table align="center" cellpadding="5" cellspacing="5" border="1">

		<tr bgcolor="#00ffff">
		<td><b>Bill ID</b></td>
		<td><b>Date of Purchase</b></td>
		<td><b>Customer ID</b></td>
		<td><b>Representative ID</b></td>
		<td><b>Payment mode ID</b></td>
		<td><b>Total Amount</b></td>
		<td><b>Discount %</b></td>
		<td><b>Net Total Amount</b></td>
		</tr>
		<%
			int count = 0;
			Map<Integer, Bill> billMap = SuperMarketDAO.getInstance().getAllBill(from, to, offset);
			Set<Integer> keys = billMap.keySet();
			
			if (!keys.isEmpty()) 
			{
				for(Integer key : keys) {
					count++;
					Bill b = billMap.get(key);%>
					<tr>
						<td><%=b.getBillId() %></td>
						<td><%=b.getDateOfPurchase() %></td>
						<td><%=b.getCustId() %></td>
						<td><%=b.getRepId() %></td>
						<td><%=b.getPaymentModeId() %></td>
						<td><%=b.getTotalAmount() %></td>
						<td><%=b.getDiscount() %></td>
						<td><%=b.getTotalNetAmount() %></td>
						<td>
							<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/billdetails2.jsp" method="get">
							<button type="submit" name="billId" value="<%=b.getBillId() %>">Bill details</button>
							</form>
						</td>
					</tr>
					<%
				}
			}
		%>
		</table>
		
		<%
		if(count == 10){
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/billdetails.jsp" method="get">
		<button type="submit" name="offset" value="<%=offset+10%>">Next</button>
		</form>
		
		<%
		}
		if(offset != 0){
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/billdetails.jsp" method="get">
		<button type="submit" name="offset" value="<%=offset-10%>">previous</button>
		</form>
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