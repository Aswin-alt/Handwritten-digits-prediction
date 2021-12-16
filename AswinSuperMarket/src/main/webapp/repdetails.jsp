<%@page import="com.aswin.dao.SuperMarketDAO"%>
<%@page import="com.aswin.model.Representative"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Representative Details</title>
</head>
<body>
	
	<h2 align="center"><font><strong>BILL DETAILS</strong></font></h2>
	<table align="center" cellpadding="5" cellspacing="5" border="1">

		<tr bgcolor="#00ffff">
		<td><b>Representative ID</b></td>
		<td><b>Representative Name</b></td>
		<td><b>Date of Joining</b></td>
		<td><b>Age</b></td>
		<td><b>Salary</b></td>
		<td><b>Designation ID</b></td>
		<td><b>Designation Name</b></td>
		</tr>
		<%
		try {
			Map<Integer, Representative> repMap = SuperMarketDAO.getInstance().getAllRep();
			
			Set<Integer> keys = repMap.keySet();
			
			if (!keys.isEmpty()) 
			{
				for(Integer key : keys) {
					Representative r = repMap.get(key);%>
					<tr>
						<td><%=r.getRepId() %></td>
						<td><%=r.getRepName() %></td>
						<td><%=r.getDoj() %></td>
						<td><%=r.getAge() %></td>
						<td><%=r.getSalary() %></td>
						<td><%=r.getDesigId() %></td>
						<td><%=r.getDesigName() %></td>
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