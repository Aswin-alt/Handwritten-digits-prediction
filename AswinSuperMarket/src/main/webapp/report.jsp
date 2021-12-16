<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Summary Report</title>
</head>
<body>

	<h2 style = "text-align : center;">Report DashBoard (Summary)</h2>
	<br><br>
	
	<!-- Total sales -->
	<form name="sales" method="post" id="sales" action="/AswinSuperMarket/totalsales.jsp">
		<label class="form-label text-left" for="sales"><b style="background-color: #d4e52a;">Total Sales : </b></label>
		<br>
		From
		<input type="date" id="sales" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="sales" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit">Submit</button>
	</form>
	<br>
	
	<!-- Top Customer -->
	<form name="cust" method="post" id="cust" action="/AswinSuperMarket/tpocustomer.jsp">
		<label class="form-label text-left" for="cust"><b style="background-color: #d4e52a;">Top Customer :  </b></label>
		<br>
		From
		<input type="date" id="cust" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="cust" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit" name="choice" value="amount">Maximum Amount</button>
		<button type="submit" name="choice" value="bill">Maximum no of Bill</button>
	</form>
	<br>
	
	<!-- Top Representative -->
	<form name="rep" method="post" id="rep" action="/AswinSuperMarket/toprep.jsp">
		<label class="form-label text-left" for="rep"><b style="background-color: #d4e52a;">Top Representative :  </b></label>
		<br>
		From
		<input type="date" id="rep" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="rep" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit" name="choice" value="amount">Maximum Amount</button>
		<button type="submit" name="choice" value="bill">Maximum no of Bill</button>
	</form>
	<br>
	
	<!-- Top Stock -->
	<form name="stock" method="post" id="stock" action="/AswinSuperMarket/topstock.jsp">
		<label class="form-label text-left" for="stock"><b style="background-color: #d4e52a;">Top Stocks :  </b></label>
		<br>
		From
		<input type="date" id="stock" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="stock" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit" name="choice" value="amount">Maximum Amount</button>
		<button type="submit" name="choice" value="bill">Maximum count sold</button>
	</form>
	<br>
	
	<!-- Sales in a month -->
	<form name="salesrep" method="post" id="salesrep" action="/AswinSuperMarket/salesrep.jsp">
		<label class="form-label text-left" for="salesrep"><b style="background-color: #d4e52a;">Sales in a month :  </b></label>
		<br>
		From
		<input type="date" id="salesrep" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="salesrep" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit" name="offset" value="0">Submit</button>
	</form>
	<br>
	
	<!-- Bill details in a month -->
	<form name="bill" method="post" id="bill" action="/AswinSuperMarket/billdetails.jsp">
		<label class="form-label text-left" for="stock"><b style="background-color: #d4e52a;">Bills in a month :  </b></label>
		<br>
		From
		<input type="date" id="bill" class="form-control" size="50" name="from" Required/>
		<br>
		To
		<input type="date" id="bill" class="form-control" size="50" name="to" Required/>
		<br>
		<button type="submit" name="offset" value="0">Submit</button>
	</form>
	<br>
	
	<!-- Increment Details -->
	<form name="increment" method="post" id="increment" action="/AswinSuperMarket/increment.jsp">
		<label class="form-label text-left" for="stock"><b style="background-color: #d4e52a;"> Representative Increment Details :  </b></label>
		<br>
		Enter Security code 
		<input type="text" id="increment" class="form-control" size="50" name="code" Required/>
		<br>
		<button type="submit" name="offset" value="0">Submit</button>
	</form>
	<br>
	
	<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/index.jsp" method="get">
		<button type="submit" name="offset" value="0">Back</button>
	</form>

</body>
</html>