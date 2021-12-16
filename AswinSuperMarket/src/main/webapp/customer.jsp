<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer</title>
</head>
<body>
	<h2 style = "text-align : center;">Customer UI</h2>
	<br><br>

	<form name="phone" method="post" id="phone" action="billcontroller">
		<label class="form-label text-left" for="phone">Enter phone no.</label>
		<input type="number" id="phone" class="form-control" size="50" name="phone" Required/>
		<br>
		<button type="submit">Submit</button>
	</form>
	
	<h3>OR</h3>
	
	<form name="email" method="get" id="email" action="billcontroller">
	
		<label class="form-label text-left" for="email">Enter email Id</label>
		<input type="email" id="email" class="form-control" size="50" name="email" Required/>
		<br>
		<button type="submit">Submit</button>
	</form>
	
	<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/index.jsp" method="get">
		<button type="submit">Back</button>
	</form>

</body>
</html>