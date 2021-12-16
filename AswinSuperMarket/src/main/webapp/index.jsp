<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aswin's Supermarket</title>
</head>
<body>

	<h2 style = "text-align : center;">Welcome to Aswin's Supermarket</h2>
	<br><br>
	
	<!-- Summary Report -->
	<form name="report" method="post" id="report" action="/AswinSuperMarket/report.jsp">
		<button type="submit" name="choice" value="report">Summary Report</button>
	</form>
	<br>
	
	<!-- Customer Details -->
	<form name="customer" method="post" id="customer" action="customerdetailcontroller">
		<button type="submit" name="choice" value="customer">Customer Details</button>
	</form>
	<br>
	
	<!-- Representative Details -->
	<form name="rep" method="post" id="rep" action="/AswinSuperMarket/repdetails.jsp">
		<button type="submit" name="choice" value="rep">Representative Details</button>
	</form>
	<br>
	
	<!-- Stock Details -->
	<form name="stock" method="post" id="stock" action="/AswinSuperMarket/stockdetails.jsp">
		<button type="submit" name="choice" value="stock">Stock Details</button>
	</form>
	<br>
	
	<!-- Bill -->
	<form name="bill" method="post" id="bill" action="/AswinSuperMarket/customer.jsp">
		<button type="submit" name="choice" value="bill">Billing</button>
	</form>

	
	
	
	
	
	
	<h2 align="center"><font><strong>BILL DETAILS</strong></font></h2>
	<table align="center" cellpadding="5" cellspacing="5" border="1">
		
		<tr >
		<td><b>Customer ID </b></td>
		<td>1</td>
		</tr>
		
		<tr >
		<td><b>Customer Name </b></td>
		<td>Aswin</td>
		</tr>
		
		<tr >
		<td><b>Date of Purchase </b></td>
		<td>2021-08-24</td>
		</tr>
		
		<tr >
		<td><b>Representative ID </b></td>
		<td>1</td>
		</tr>
		
		<tr >
		<td><b>Representative Name </b></td>
		<td>Qwerty</td>
		</tr>
		
		<tr >
		<td><b>Payment Mode </b></td>
		<td>Cash</td>
		</tr>
		
		<tr bgcolor="#DEB887">
		<td><b>Stock ID</b></td>
		<td><b>Stock Name</b></td>
		<td><b>Stock Price</b></td>
		<td><b>Quantity</b></td>
		<td><b>Total</b></td>
		</tr>
		
		<tr>
		<td>1</td>
		<td>pen</td>
		<td>10</td>
		<td>2</td>
		<td>20</td>
		</tr>
		
		<tr >
		<td><b>Grand Total </b></td>
		<td>20</td>
		</tr>
		
		<tr >
		<td><b>Discount </b></td>
		<td>20</td>
		</tr>
		
		<tr >
		<td><b>Net Grand Total </b></td>
		<td>16</td>
		</tr>
	</table>
	
</body>
</html>