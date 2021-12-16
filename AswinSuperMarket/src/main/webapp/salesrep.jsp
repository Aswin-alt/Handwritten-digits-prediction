<%@page import="com.aswin.model.EnumError"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="com.aswin.model.Representative"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sales Representative</title>
</head>
<body>
	<h2 style = "text-align : center;">Monthly Sales Details</h2>
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
		<td><b>Representative ID</b></td>
		<td><b>Representative Name</b></td>
		<td><b>Total Sales</b></td>
		</tr>
		<%
			int count = 0;
			Map<Integer, Representative> repMap = SuperMarketDAO.getInstance().getTopRep(from, to, offset);
			Set<Integer> keys = repMap.keySet();
			
			if (!keys.isEmpty()) 
			{
				for(Integer key : keys) {
					count++;
					Representative r = repMap.get(key);%>
					<tr>
						<td><%=r.getRepId() %></td>
						<td><%=r.getRepName() %></td>
						<td><%=r.getToalAmount() %></td>
					</tr>
					<%
				}
			}
		%>
		</table>
		
		<%
		if(count == 5){
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/salesrep.jsp" method="get">
		<button type="submit" name="offset" value="<%=offset+5%>">Next</button>
		</form>
		
		<%
		}
		if(offset != 0){
		%>
		<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/salesrep.jsp" method="get">
		<button type="submit" name="offset" value="<%=offset-5%>">previous</button>
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